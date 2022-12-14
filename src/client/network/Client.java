package client.network;

import shared.networking.ClientCallBack;
import shared.networking.RMIServer;
import shared.sharedObjects.Account;
import shared.sharedObjects.TrainingSession;
import shared.sharedObjects.TrainingSessionList;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A class implemented the methods for the RMIClient.
 * @author Group 7
 */
public class Client implements RMIClient, ClientCallBack {
    private RMIServer server;
    private PropertyChangeSupport support;
    private Account account;

    /**
     * A method instantiating a new account and support.
     */
    public Client(){
        account = new Account("temp", "temp");
        support = new PropertyChangeSupport(this);
    }

    /**
     * A method that connects the clients to the server.
     */
    @Override
    public void startClient() {
        Registry registry = null;
        try{
            UnicastRemoteObject.exportObject(this,0);
            registry = LocateRegistry.getRegistry("localhost",1099);
            server = (RMIServer) registry.lookup("Server");
            server.registerCallback(this);
            System.out.println("Client started");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifies if the member has been added to the training session already.
     * @param account the account
     * @param trainingSession the training session
     * @return boolean
     */
    public boolean isMemberInSession(Account account, TrainingSession trainingSession)
    {
        try {
            return server.isMemberInSession(account, trainingSession);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Removes the training session.
     * @param trainingSession the training session
     * @throws RemoteException
     */
    @Override
    public void removeSession(TrainingSession trainingSession) throws RemoteException {
        try {
            server.removeSession(account, trainingSession);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the managers sessions.
     * @param parse the parse object
     * @return parse
     * @throws RemoteException
     */
    @Override
    public TrainingSessionList getSessionsForManager(LocalDate parse) throws RemoteException {
        return server.getSessionsForManager(parse);
    }

    /**
     * Updates the session in the server.
     * @param session the session
     * @throws RemoteException
     */
    @Override
    public void updateSession(TrainingSession session) throws RemoteException {
        System.out.println("Client update session " + session.toString());
        server.updateSession(session);
    }

    /**
     * Deletes the session
     * @param session the session
     * @throws RemoteException
     */
    @Override
    public void deleteSession(TrainingSession session) throws RemoteException {
        server.deleteSession(session);
    }

    /**
     * Unassigns the trainer from the session.
     * @param session the session
     * @throws RemoteException
     */
    @Override
    public void unassignTrainer(TrainingSession session) throws RemoteException{
        System.out.println(session.toString());
        server.unassignTrainer(session);
    }

    /**
     * Adds the session.
     * @param session the session
     */
    @Override
    public void addSession(TrainingSession session){
        try {
            server.addSession(session);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates the account in the server.
     * @param account the account
     */
    @Override public void createAccount(Account account)
    {
        try {
            server.createAccount(account);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the session.
     * @return session
     */
    @Override
    public TrainingSessionList getSessions() {
        try {
            System.out.println("testClient");
            return server.getSessions();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the trainer.
     * @return trainer
     * @throws RemoteException
     */
    @Override
    public ArrayList getTrainers() throws RemoteException {
        return server.getTrainers();
    }

    /**
     * Verifies the availability of the session.
     * @param session
     * @return
     */
    @Override
    public boolean verifyAvailabilityOfSession(TrainingSession session) {
        try {
            return server.verifyAvailabilityOfSession(session);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException("Something went wrong");
        }
    }

    /**
     * Adds the participant to the session.
     * @param account the account
     * @param session the session
     * @throws RemoteException
     */
    @Override
    public void addParticipant(Account account, TrainingSession session) throws RemoteException{
        server.addParticipant(account, session);
    }

    /**
     * Returns the available session for the specific account and date.
     * @param account the account
     * @param date the date
     * @return available session
     * @throws RemoteException
     */
    @Override
    public TrainingSessionList getAvailableSessionsForMember(Account account, LocalDate date) throws RemoteException {
        return server.getAvailableSessionsForMember(account, date);
    }

    /**
     * Returns the training sessions for the specific trainer.
     * @param account the account
     * @param date the date
     * @return trainers session
     * @throws RemoteException
     */
    @Override
    public TrainingSessionList getSessionsForTrainer(Account account, LocalDate date) throws RemoteException{
        return server.getSessionsForTrainer(account, date);
    }

    /**
     * Verifies the account.
     * @param account the account
     * @return account
     */
    @Override
    public Account login(Account account) {
        try {
            System.out.println("4 Member got loginCredentials from front model, using server to verify " + account.toString());
            return this.account = server.verifyLogin(account);
        } catch (RemoteException e) {
            throw new RuntimeException("Could not connect to the server :(");
        }
    }

    /**
     * Returns the members booked sessions.
     * @param account the account
     * @return account
     * @throws RemoteException
     */
    public TrainingSessionList getListOfSessionsBookedByMember(Account account) throws RemoteException
    {
        return server.getListOfSessionsBookedByMember(account);
    }

    /**
     * Returns the account
     * @return account
     */
    public Account getLoginCredentials() {
        return account;
    }

    /**
     * Adds a listener.
     * @param eventName the event name
     * @param listener the listener
     */
    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    /**
     * Removes a listener.
     * @param eventName the event name
     * @param listener the listener
     */
    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }

    /**
     * Notifies when a session is added
     * @param session the session
     * @throws RemoteException
     */
    @Override
    public void updateNewSession(TrainingSession session) throws RemoteException {
        System.out.println("Fires the update method");
        support.firePropertyChange("SessionAdded", null, session);
    }

    /**
     * Notifies when a participant is added
     * @param prevSession thr previous session
     * @param newSession the latest session
     * @throws RemoteException
     */
    @Override
    public void updateNewParticipant(TrainingSession prevSession, TrainingSession newSession) throws RemoteException {
        support.firePropertyChange("ParticipantAdded", prevSession, newSession);
    }

    /**
     * Notifies when a session is deleted.
     * @param session the session
     * @throws RemoteException
     */
    @Override
    public void updateDeleteSession(TrainingSession session) throws RemoteException {
        support.firePropertyChange("SessionDeleted", null, session);
    }

    /**
     * Notifies when a trainer is unassigned to the session.
     * @param session the session
     * @throws RemoteException
     */
    @Override
    public void updateUnassignedTrainer(TrainingSession session) throws RemoteException {
        support.firePropertyChange("UnassignedTrainer", null, session);
        System.out.println("client fired event unassigned trainer");
    }

    /**
     * Notifies when the session is updated.
     * @param session the session
     * @throws RemoteException
     */
    @Override
    public void updateUpdateSession(TrainingSession session) throws RemoteException {
        support.firePropertyChange("UpdateSession", null, session);
    }

}
