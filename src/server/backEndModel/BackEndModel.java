package server.backEndModel;

import shared.sharedObjects.Account;
import shared.sharedObjects.TrainingSession;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class BackEndModel implements BackEndModelManager
{
    private PropertyChangeSupport support;
    private List<TrainingSession> listOfSessions;
    private List<Account> listOfAccount;

    public BackEndModel()
    {
        support = new PropertyChangeSupport(this);
        listOfSessions = new ArrayList<>();
        listOfAccount = new ArrayList<>();
    }

    @Override public void addSession(TrainingSession session)
    {
        listOfSessions.add(session);
    }

    @Override public void removeSession(TrainingSession session)
    {
        listOfSessions.remove(session);
    }

    @Override public void addAccount(Account account)
    {
        listOfAccount.add(account);
    }

    @Override public void removeAccount(Account account)
    {
        listOfAccount.remove(account);
    }

    @Override public boolean verifyLogin(Account account)
    {
        return false;
    }

    public Account getAccount(Account account)
    {
        for (int i = 0; i < listOfAccount.size(); i++)
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
        for (int i = 0; i < listOfSessions.size(); i++)
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

    }

    @Override public void removeListener(String eventName,
        PropertyChangeListener listener)
    {

    }
}



