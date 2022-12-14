package client.frontEndModel;

import client.core.ClientFactory;
import client.network.RMIClient;
import shared.sharedObjects.Account;
import shared.sharedObjects.TrainingSession;
import shared.sharedObjects.TrainingSessionList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A class handling the methods for the frontEndModelManager.
 */
public class FrontEndModel implements FrontEndModelManager
{
    private RMIClient client;
    private PropertyChangeSupport support;

    /**
     * A constructor for instantiating the variables.
     * @param clientFactory the clientFactory
     */
    public FrontEndModel(ClientFactory clientFactory)
    {
        client = clientFactory.getClient();
        client.addListener("SessionAdded", this);
        client.addListener("ParticipantAdded", this);
        client.addListener("SessionDeleted", this);
        client.addListener("UnassignedTrainer", this);
        client.addListener("UpdateSession", this);
        support = new PropertyChangeSupport(this);
    }

    /**
     * Verifies the account login information.
     * @param account the account
     * @return
     */
    public Account verifyLogin(Account account)
    {
        System.out.println(
            "3 Front model got loginCredentials from view model, passing it to member "
                + account.toString());
        return client.login(account);
    }

    /**
     * Returns the training session.
     * @return sessions
     */
    @Override public TrainingSessionList getSessions()
    {
        return client.getSessions();
    }

    /**
     * Returns the trainer list
     * @return trainer list
     * @throws RemoteException
     */
    @Override public ArrayList getTrainers() throws RemoteException
    {
        return client.getTrainers();
    }

    /**
     * Creates an account
     * @param account the account
     */
    @Override public void createAccount(Account account) {
        client.createAccount(account);
    }

    /**
     * Verifies the availability of the session.
     * @param session the session
     * @return boolean
     */
    @Override
    public boolean verifyAvailabilityOfSession (TrainingSession session){
        try {
            return client.verifyAvailabilityOfSession(session);
        } catch (RemoteException e) {
            throw new RuntimeException("Something went wrong");
        }
    }

    /**
     * Adds a participant to the session.
     * @param account the account
     * @param session the session
     */
    @Override
    public void addParticipant(Account account, TrainingSession session) {
        try {
            client.addParticipant(account, session);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the members available sessions.
     * @param account the account
     * @param date the date
     * @return available sessions
     */
    @Override
    public TrainingSessionList getAvailableSessionsForMember(Account account, LocalDate date) {
        try {
            return client.getAvailableSessionsForMember(account, date);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the members booked sessions.
     * @param account the account
     * @return list of sessions
     * @throws RemoteException
     */
    public TrainingSessionList getListOfSessionsBookedByMember(Account account) throws RemoteException
    {
        return client.getListOfSessionsBookedByMember(account);
    }

    /**
     * Returns the sessions by date and account.
     * @param account the account
     * @param date the date
     * @return trainer sessions
     * @throws RemoteException
     */
    @Override
    public TrainingSessionList getSessionsForTrainer(Account account, LocalDate date) throws RemoteException{
        return client.getSessionsForTrainer(account, date);
    }

    /**
     * Removes a training session
     * @param trainingSession the training session
     */
    @Override
    public void removeSession(TrainingSession trainingSession) {
        try {
            client.removeSession(trainingSession);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the managers training session.
     * @param parse the parse object
     * @return parse
     */
    @Override
    public TrainingSessionList getSessionsForManager(LocalDate parse) {
        try {
            return client.getSessionsForManager(parse);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Notifies when a session is sent for editing.
     * @param selectedItem the selected item
     */
    @Override
    public void sendToEdit(TrainingSession selectedItem) {
        support.firePropertyChange("SendToEdit", null, selectedItem);
    }

    /**
     * Notifies when a session is update.
     * @param session the session
     */
    @Override
    public void updateSession(TrainingSession session) {
        try {
            System.out.println("Front Model update session " + session.toString());
            client.updateSession(session);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes a session.
     * @param session the session
     */
    @Override
    public void deleteSession(TrainingSession session) {
        try {
            client.deleteSession(session);
//            support.firePropertyChange("SessionDeleted", null, session);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Unassigns the trainer from the session
     * @param session the session
     */
    @Override
    public void unassignTrainer(TrainingSession session) {
        try {
            client.unassignTrainer(session);
//            support.firePropertyChange("UnassignedTrainer", null, session);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the client.
     * @return client
     */
    public RMIClient getClient() {
        return client;
    }

    /**
     * Adds the session to the client.
     * @param session the session
     */
    @Override
    public void addSession(TrainingSession session) {
        client.addSession(session);
//        support.firePropertyChange("SessionAdded", null, session);
    }

    /**
     * Creates a listener.
     * @param eventName the event name
     * @param listener the listener
     */
    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    /**
     * Removes a listener
     * @param eventName the event name
     * @param listener the listener
     */
    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }

    /**
     * Action when a button is pressed.
     * @param evt the event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("SessionAdded"))
        {
            support.firePropertyChange("SessionAdded", null, evt.getNewValue());
        }
        else if(evt.getPropertyName().equals("ParticipantAdded"))
        {
            support.firePropertyChange("ParticipantAdded", evt.getOldValue(), evt.getNewValue());
        }
        else if (evt.getPropertyName().equals("SessionDeleted"))
        {
            support.firePropertyChange("SessionDeleted", null, evt.getNewValue());
        }  else if (evt.getPropertyName().equals("SessionDeleted"))
        {
            support.firePropertyChange("SessionDeleted", null, evt.getNewValue());
        }
        else if (evt.getPropertyName().equals("UnassignedTrainer"))
        {
            support.firePropertyChange("UnassignedTrainer", null, evt.getNewValue());
        }
        else if (evt.getPropertyName().equals("UpdateSession"))
        {
            support.firePropertyChange("UpdateSession", null, evt.getNewValue());
        }
    }
}
