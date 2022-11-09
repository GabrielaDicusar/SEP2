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
        return false;
    }

    public Account getAccount(Account account)
    {
        for (int i = 0; i < listOfAccount.getAccounts().size(); i++)
        {
            if (account.equals(i))
            {
                return account;
            }
        }
        return null;
    }

    public TrainingSession getSession(TrainingSession session)
    {
        for (int i = 0; i < listOfSessions.getTrainingSessions().size(); i++)
        {
            if (session.equals(i))
            {
                return session;
            }
        }
        return null;
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



