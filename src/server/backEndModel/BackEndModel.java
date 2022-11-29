package server.backEndModel;

import shared.sharedObjects.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Calendar;
import java.util.Date;

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
        listOfAccount.addAccount(new Account(new LoginCredentials("member", "member"), 1, "Lukasz", "luskk@vestas.com", "52683345", "Kollegievenget 1"));
        listOfAccount.addAccount(new Account(new LoginCredentials("manager", "manager"), 2, "Lukasz", "luskk@vestas.com", "52683345", "Kollegievenget 1"));
        listOfAccount.addAccount(new Account(new LoginCredentials("trainer", "trainer"), 3, "Lukasz", "luskk@vestas.com", "52683345", "Kollegievenget 1"));
        listOfSessions.addSession(new TrainingSession("Yoga", "13:00", 15, listOfAccount.getAccount(new LoginCredentials("trainer", "trainer"))));
    }

    public TrainingSessionList getListOfSessions() {
        System.out.println("testBackend");
        return listOfSessions;
    }

    @Override public int verifyLogin(LoginCredentials loginCredentials)
    {
        return listOfAccount.getAccountType(loginCredentials);
    }

    @Override
    public void addSession(TrainingSession session) {
        listOfSessions.addSession(session);
        System.out.println(listOfSessions.getTrainingSessions().size());
        support.firePropertyChange("SessionAdded", null, session);
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



