package client.network;

import shared.sharedObjects.LoginCredentials;
import shared.sharedObjects.TrainingSession;
import shared.sharedObjects.TrainingSessionList;
import shared.utils.Subject;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RMIClient extends Subject {
    int login(LoginCredentials loginCredentials);

    LoginCredentials getLoginCredentials();
    void startClient();
    void addSession(TrainingSession session);
    TrainingSessionList getSessions();
    ArrayList getTrainers() throws RemoteException;

    boolean verifyAvailabilityOfSession(TrainingSession session) throws RemoteException;

    void addParticipant(LoginCredentials loginCredentials, TrainingSession session) throws RemoteException;
}
