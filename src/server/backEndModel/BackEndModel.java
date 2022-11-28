package server.backEndModel;

import shared.sharedObjects.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A class containing the methods for the BackEndModel, whilst implementing BackEndModelManager.
 * @author
 */

public class BackEndModel implements BackEndModelManager
{
    private PropertyChangeSupport support;
    private TrainingSessionList listOfSessions;
    private AccountList listOfAccount;

    /**
     * BackEndModel constructor to instantiate support, listofAccount, listOfSessions.
     */
    public BackEndModel()
    {
        support = new PropertyChangeSupport(this);
        listOfAccount = new AccountList();
        listOfSessions = new TrainingSessionList();
        listOfAccount.addAccount(new Account(new LoginCredentials("lol", "lol"), 1, "Lukasz", "luskk@vestas.com", "52683345", "Kollegievenget 1"));
        listOfAccount.addAccount(new Account(new LoginCredentials("manager", "manager"), 2, "Lukasz", "luskk@vestas.com", "52683345", "Kollegievenget 1"));
    }


    @Override public int verifyLogin(LoginCredentials loginCredentials)
    {
        return listOfAccount.getAccountType(loginCredentials);
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



