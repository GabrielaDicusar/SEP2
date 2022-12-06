package client.frontEndModel;

import client.core.ClientFactory;
import client.network.RMIClient;
import shared.sharedObjects.Account;
import shared.sharedObjects.TrainingSession;
import shared.sharedObjects.TrainingSessionList;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public class FrontEndModel implements FrontEndModelManager
{
    private RMIClient client;
    private PropertyChangeSupport support;

    public FrontEndModel(ClientFactory clientFactory)
    {
        client = clientFactory.getClient();
        support = new PropertyChangeSupport(this);
    }

    public Account verifyLogin(Account account)
    {
        System.out.println(
            "3 Front model got loginCredentials from view model, passing it to member "
                + account.toString());
        return client.login(account);
    }

    @Override public TrainingSessionList getSessions()
    {
        return client.getSessions();
    }

    @Override public ArrayList getTrainers() throws RemoteException
    {
        return client.getTrainers();
    }

    @Override public void createAccount(Account account) {
        client.createAccount(account);
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
    public void addParticipant(Account account, TrainingSession session) {
        try {
            client.addParticipant(account, session);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public TrainingSessionList getAvailableSessionsForMember(Account account, LocalDate date) {
        try {
            return client.getAvailableSessionsForMember(account, date);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    public TrainingSessionList getListOfSessionsBookedByMember(Account account) throws RemoteException
    {
        return client.getListOfSessionsBookedByMember(account);
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
