package client.network;

import shared.sharedObjects.Account;
import shared.sharedObjects.TrainingSession;
import shared.sharedObjects.TrainingSessionList;
import shared.utils.Subject;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface RMIClient extends Subject {
    Account login(Account account);

    Account getLoginCredentials();
    void startClient();
    void addSession(TrainingSession session);
    void createAccount(Account account);
    TrainingSessionList getSessions();
    ArrayList getTrainers() throws RemoteException;

    boolean verifyAvailabilityOfSession(TrainingSession session) throws RemoteException;

    void addParticipant(Account account, TrainingSession session) throws RemoteException;

    TrainingSessionList getAvailableSessionsForMember(Account account) throws RemoteException;
    TrainingSessionList getAvailableSessionsForMember(Account account, LocalDate date) throws RemoteException;
    TrainingSessionList getListOfSessionsBookedByMember(Account account) throws RemoteException;
}
