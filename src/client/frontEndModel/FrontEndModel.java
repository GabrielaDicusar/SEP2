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

public class FrontEndModel implements FrontEndModelManager
{
    private RMIClient client;
    private PropertyChangeSupport support;

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

    @Override
    public TrainingSessionList getSessionsForTrainer(Account account, LocalDate date) throws RemoteException{
        return client.getSessionsForTrainer(account, date);
    }

    @Override
    public void removeSession(TrainingSession trainingSession) {
        try {
            client.removeSession(trainingSession);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TrainingSessionList getSessionsForManager(LocalDate parse) {
        try {
            return client.getSessionsForManager(parse);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendToEdit(TrainingSession selectedItem) {
        support.firePropertyChange("SendToEdit", null, selectedItem);
    }

    @Override
    public void updateSession(TrainingSession session) {
        try {
            System.out.println("Front Model update session " + session.toString());
            client.updateSession(session);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteSession(TrainingSession session) {
        try {
            client.deleteSession(session);
//            support.firePropertyChange("SessionDeleted", null, session);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void unassignTrainer(TrainingSession session) {
        try {
            client.unassignTrainer(session);
//            support.firePropertyChange("UnassignedTrainer", null, session);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public RMIClient getClient() {
        return client;
    }

    @Override
    public void addSession(TrainingSession session) {
        client.addSession(session);
//        support.firePropertyChange("SessionAdded", null, session);
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
