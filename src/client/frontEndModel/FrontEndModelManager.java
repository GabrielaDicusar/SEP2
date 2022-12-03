package client.frontEndModel;

import client.network.Client;
import client.network.RMIClient;
import shared.sharedObjects.LoginCredentials;
import shared.sharedObjects.TrainingSession;
import shared.sharedObjects.TrainingSessionList;
import shared.utils.Subject;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface FrontEndModelManager extends Subject {
    void addSession(TrainingSession session);
    int verifyLogin(LoginCredentials loginCredentials);
    RMIClient getClient();
    TrainingSessionList getSessions();
    ArrayList getTrainers() throws RemoteException;

    boolean verifyAvailabilityOfSession(TrainingSession session) throws RemoteException;

    void addParticipant(LoginCredentials loginCredentials, TrainingSession session);

    TrainingSessionList getAvailableSessionsForMember(LoginCredentials loginCredentials);
}
