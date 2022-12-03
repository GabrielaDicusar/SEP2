package client.frontEndModel;

import client.core.ClientFactory;
import client.network.RMIClient;
import shared.sharedObjects.LoginCredentials;
import shared.sharedObjects.TrainingSession;
import shared.sharedObjects.TrainingSessionList;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class FrontEndModel implements FrontEndModelManager {
    private RMIClient client;
    private PropertyChangeSupport support;

    public FrontEndModel(ClientFactory clientFactory) {
        client = clientFactory.getClient();
        support = new PropertyChangeSupport(this);
    }

    public int verifyLogin(LoginCredentials loginCredentials){
        System.out.println("3 Front model got loginCredentials from view model, passing it to member " + loginCredentials.toString());
        return client.login(loginCredentials);
    }

    @Override
    public TrainingSessionList getSessions() {
        return client.getSessions();
    }

    @Override
    public ArrayList getTrainers() throws RemoteException {
        return client.getTrainers();
    }

    @Override
    public boolean verifyAvailabilityOfSession (TrainingSession session){
        try {
            return client.verifyAvailabilityOfSession(session);
        } catch (RemoteException e) {
            throw new RuntimeException("Something went wrong");
        }
    }

    @Override
    public void addParticipant(LoginCredentials loginCredentials, TrainingSession session) {
        try {
            client.addParticipant(loginCredentials, session);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TrainingSessionList getAvailableSessionsForMember(LoginCredentials loginCredentials) {
        try {
            return client.getAvailableSessionsForMember(loginCredentials);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public RMIClient getClient() {
        return client;
    }

    @Override
    public void addSession(TrainingSession session) {
        client.addSession(session);
        support.firePropertyChange("SessionAdded", null, session);
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }
}
