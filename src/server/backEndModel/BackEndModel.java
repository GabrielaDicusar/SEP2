package server.backEndModel;

import shared.sharedObjects.LoginCredentials;
import shared.sharedObjects.TrainingSession;
import shared.sharedObjects.TrainingSessionList;

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
    private LoginCredentialList listOfAccount;

    /**
     * BackEndModel constructor to instantiate support, listofAccount, listOfSessions.
     */
    public BackEndModel()
    {
        support = new PropertyChangeSupport(this);
        listOfAccount = new LoginCredentialList();
        listOfSessions = new TrainingSessionList();
        addAccount(new LoginCredentials("lol", "lol"));
    }

    @Override public void addSession(TrainingSession session)
    {
        listOfSessions.addSession(session);
    }

    @Override public void removeSession(TrainingSession session)
    {
        listOfSessions.removeTrainingSession(session);
    }

    @Override public void addAccount(LoginCredentials loginCredentials)
    {
        listOfAccount.addLoginCredentials(loginCredentials);
    }

    @Override public void removeAccount(LoginCredentials loginCredentials)
    {
        listOfAccount.removeLoginCredentials(loginCredentials);
    }

    @Override public boolean verifyLogin(LoginCredentials loginCredentials)
    {

        if (listOfAccount.getLoginCredential(loginCredentials) == null)
        {
            System.out.println("6 Back model verified loginCredentials false " + loginCredentials.toString()) ;
            return false;
        }
        else {
            System.out.println("6 Back model verified loginCredentials true " + loginCredentials.toString());
            return true;
        }
    }

    public LoginCredentials getAccount(LoginCredentials loginCredentials)
    {
        return listOfAccount.getLoginCredential(loginCredentials);
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



