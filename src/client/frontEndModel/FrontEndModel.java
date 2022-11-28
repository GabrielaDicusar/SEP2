package client.frontEndModel;

import client.core.ClientFactory;
import client.network.RMIClient;
import shared.sharedObjects.LoginCredentials;
import shared.sharedObjects.TrainingSession;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class FrontEndModel implements FrontEndModelManager {
    private RMIClient client;
    private PropertyChangeSupport support;

    public FrontEndModel(ClientFactory clientFactory) {
        client = clientFactory.getClient();
        support = new PropertyChangeSupport(this);
    }

    public boolean verifyLogin(LoginCredentials loginCredentials){
        System.out.println("3 Front model got loginCredentials from view model, passing it to member " + loginCredentials.toString());
        return client.login(loginCredentials);
    }

    @Override
    public void addSession(TrainingSession session) {

    }

    @Override
    public void removeSession(TrainingSession session) {

    }

    @Override
    public void addAccount(LoginCredentials loginCredentials) {

    }

    @Override
    public void removeAccount(LoginCredentials loginCredentials) {

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
