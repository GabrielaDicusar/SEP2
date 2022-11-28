package client.frontEndModel;

import client.core.ClientFactory;
import client.network.RMIClient;
import shared.sharedObjects.Account;
import shared.sharedObjects.TrainingSession;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class FrontEndModel implements FrontEndModelManager {
    private RMIClient client;
    private PropertyChangeSupport support;

    public FrontEndModel(ClientFactory clientFactory) {
        client = clientFactory.getClient();
        support = new PropertyChangeSupport(this);
    }

    public boolean verifyLogin(Account account){
        System.out.println("3 Front model got account from view model, passing it to member " + account.toString());
        return client.login(account);
    }

    @Override
    public void addSession(TrainingSession session) {

    }

    @Override
    public void removeSession(TrainingSession session) {

    }

    @Override
    public void addAccount(Account account) {

    }

    @Override
    public void removeAccount(Account account) {

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
