package client.network;

import shared.networking.ClientCallBack;
import shared.networking.RMIServer;
import shared.sharedObjects.LoginCredentials;
import shared.sharedObjects.TrainingSession;
import shared.sharedObjects.TrainingSessionList;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Client implements RMIClient, ClientCallBack {
    private RMIServer server;
    private PropertyChangeSupport support;
    private LoginCredentials loginCredentials;

    public Client(){
        loginCredentials = new LoginCredentials("", "");
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

    @Override
    public void addSession(TrainingSession session){
        try {
            server.addSession(session);
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
    public void addParticipant(LoginCredentials loginCredentials, TrainingSession session) throws RemoteException{
        server.addParticipant(loginCredentials, session);
    }

    @Override
    public TrainingSessionList getAvailableSessionsForMember(LoginCredentials loginCredentials) throws RemoteException {
        return server.getAvailableSessionsForMember(loginCredentials);
    }

    @Override
    public int login(LoginCredentials loginCredentials) {
        try {
            System.out.println("4 Member got loginCredentials from front model, using server to verify " + loginCredentials.toString());
            this.loginCredentials = loginCredentials;
            return server.verifyLogin(loginCredentials);
        } catch (RemoteException e) {
            throw new RuntimeException("Could not connect to the server :(");
        }
    }

    public LoginCredentials getLoginCredentials() {
        return loginCredentials;
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
