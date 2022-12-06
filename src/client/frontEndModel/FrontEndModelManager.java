package client.frontEndModel;

import client.network.RMIClient;
import shared.sharedObjects.Account;
import shared.sharedObjects.TrainingSession;
import shared.sharedObjects.TrainingSessionList;
import shared.utils.Subject;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface FrontEndModelManager extends Subject {
    void addSession(TrainingSession session);
    Account verifyLogin(Account account);
    RMIClient getClient();
    TrainingSessionList getSessions();
    ArrayList getTrainers() throws RemoteException;
   void createAccount(Account account);
    boolean verifyAvailabilityOfSession(TrainingSession session) throws RemoteException;

    void addParticipant(Account account, TrainingSession session);
    TrainingSessionList getAvailableSessionsForMember(Account account, LocalDate date);
    TrainingSessionList getListOfSessionsBookedByMember(Account account) throws RemoteException;


}
