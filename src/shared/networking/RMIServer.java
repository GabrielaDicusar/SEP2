package shared.networking;

import server.backEndModel.BackEndModel;
import shared.sharedObjects.Account;
import shared.sharedObjects.TrainingSession;
import shared.sharedObjects.TrainingSessionList;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Interface RMI Server creates verifyLogin, bookATrainingSession, and addUser which are implemented by
   BackEndModelManager, FrontEndModel, FrontEndModelManager, Client, LoginViewController, LoginViewModel,
   BackEndModel and ServerImpl. Its extends Remote.
 * @author Group 7
 */

public interface RMIServer extends Remote {
    Account verifyLogin(Account account) throws RemoteException;
    void addSession(TrainingSession session) throws RemoteException;
    void registerCallback(ClientCallBack ccb) throws RemoteException;
    TrainingSessionList getSessions() throws RemoteException;
    ArrayList getTrainers() throws RemoteException;
    void createAccount(Account account) throws RemoteException;
    boolean verifyAvailabilityOfSession(TrainingSession session) throws RemoteException;

    void addParticipant(Account account, TrainingSession session) throws RemoteException;
    TrainingSessionList getAvailableSessionsForMember(Account account, LocalDate date) throws RemoteException;

    TrainingSessionList getSessionsForTrainer(Account account, LocalDate date) throws RemoteException;

    TrainingSessionList getListOfSessionsBookedByMember(Account account) throws RemoteException;

    boolean isMemberInSession(Account account, TrainingSession trainingSession) throws RemoteException;

    void removeSession(Account account, TrainingSession trainingSession) throws RemoteException;

    TrainingSessionList getSessionsForManager(LocalDate parse) throws RemoteException;

    void updateSession(TrainingSession session) throws RemoteException;

    void deleteSession(TrainingSession session) throws RemoteException;

    void unassignTrainer(TrainingSession session) throws RemoteException;
}
