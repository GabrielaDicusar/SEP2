package client.frontEndModel;

import client.network.RMIClient;
import shared.sharedObjects.Account;
import shared.sharedObjects.TrainingSession;
import shared.sharedObjects.TrainingSessionList;
import shared.utils.Subject;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface FrontEndModelManager extends Subject, PropertyChangeListener {
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


    TrainingSessionList getSessionsForTrainer(Account account, LocalDate date) throws RemoteException;

    void removeSession(TrainingSession trainingSession);

    TrainingSessionList getSessionsForManager(LocalDate parse);

    void sendToEdit(TrainingSession selectedItem);

    void updateSession(TrainingSession session);

    void deleteSession(TrainingSession session);

    void unassignTrainer(TrainingSession session);
}
