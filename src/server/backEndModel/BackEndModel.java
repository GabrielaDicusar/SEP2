package server.backEndModel;

import shared.sharedObjects.Account;
import shared.sharedObjects.AccountList;
import shared.sharedObjects.TrainingSession;
import shared.sharedObjects.TrainingSessionList;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class BackEndModel implements BackEndModelManager
{
    private PropertyChangeSupport support;
    private TrainingSessionList listOfSessions;
    private AccountList listOfAccount;

    public BackEndModel()
    {
        support = new PropertyChangeSupport(this);
        listOfAccount = new AccountList();
        listOfSessions = new TrainingSessionList();
        addAccount(new Account("lol", "lol"));
    }

    @Override public void addSession(TrainingSession session)
    {
        listOfSessions.addSession(session);
    }

    @Override public void removeSession(TrainingSession session)
    {
        listOfSessions.removeTrainingSession(session);
    }

    @Override public void addAccount(Account account)
    {
        listOfAccount.addAccount(account);
    }

    @Override public void removeAccount(Account account)
    {
        listOfAccount.removeAccount(account);
    }

    @Override public boolean verifyLogin(Account account)
    {

        if (listOfAccount.getAccount(account) == null)
        {
            System.out.println("6 Back model verified account false " + account.toString()) ;
            return false;
        }else
            System.out.println("6 Back model verified account true " + account.toString());
            return true;
    }

    public Account getAccount(Account account)
    {
        return listOfAccount.getAccount(account);
    }

    public TrainingSession getSession(TrainingSession session)
    {
        return listOfSessions.getTrainingSession(session);
    }

    @Override public void addListener(String eventName,
        PropertyChangeListener listener)
    {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override public void removeListener(String eventName,
        PropertyChangeListener listener)
    {
        support.removePropertyChangeListener(eventName, listener);
    }
}



