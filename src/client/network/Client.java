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

public class Client implements RMIClient, ClientCallBack {
    private RMIServer server;
    private PropertyChangeSupport support;
    private Account account;

    public Client(){
        account = new Account("temp", "temp");
        support = new PropertyChangeSupport(this);
    }

    @Override
    public void startClient() {
        Registry registry = null;
        try{
            UnicastRemoteObject.exportObject(this,0);
            registry = LocateRegistry.getRegistry("localhost",1234);
            server = (RMIServer) registry.lookup("Server");
            server.registerCallback(this);
            System.out.println("Client started");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    public boolean isMemberInSession(Account account, TrainingSession trainingSession)
    {
        try {
            return server.isMemberInSession(account, trainingSession);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeSession(TrainingSession trainingSession) throws RemoteException {
        try {
            server.removeSession(account, trainingSession);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void addSession(TrainingSession session){
        try {
            server.addSession(session);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override public void createAccount(Account account)
    {
        try {
            server.createAccount(account);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TrainingSessionList getSessions() {
        try {
            System.out.println("testClient");
            return server.getSessions();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList getTrainers() throws RemoteException {
        return server.getTrainers();
    }

    @Override
    public boolean verifyAvailabilityOfSession(TrainingSession session) {
        try {
            return server.verifyAvailabilityOfSession(session);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException("Something went wrong");
        }
    }

    @Override
    public void addParticipant(Account account, TrainingSession session) throws RemoteException{
        server.addParticipant(account, session);
    }

    @Override
    public TrainingSessionList getAvailableSessionsForMember(Account account) throws RemoteException {
        return server.getAvailableSessionsForMember(account);
    }
    @Override
    public TrainingSessionList getAvailableSessionsForMember(Account account, LocalDate date) throws RemoteException {
        return server.getAvailableSessionsForMember(account, date);
    }

    @Override
    public Account login(Account account) {
        try {
            System.out.println("4 Member got loginCredentials from front model, using server to verify " + account.toString());
            return this.account = server.verifyLogin(account);
        } catch (RemoteException e) {
            throw new RuntimeException("Could not connect to the server :(");
        }
    }
    public TrainingSessionList getListOfSessionsBookedByMember(Account account) throws RemoteException
    {
        return server.getListOfSessionsBookedByMember(account);
    }

    public Account getLoginCredentials() {
        return account;
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }

    @Override
    public void updateNewSession(TrainingSession session) throws RemoteException {
        System.out.println("Fires the update method");
        support.firePropertyChange("SessionAdded", null, session);
    }

    @Override
    public void updateNewParticipant(TrainingSession prevSession, TrainingSession newSession) throws RemoteException {
        support.firePropertyChange("ParticipantAdded", prevSession, newSession);
    }
}
