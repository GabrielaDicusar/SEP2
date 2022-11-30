package shared.networking;

import shared.sharedObjects.LoginCredentials;
import shared.sharedObjects.TrainingSession;
import shared.sharedObjects.TrainingSessionList;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Interface RMI Server creates verifyLogin, bookATrainingSession, and addUser which are implemented by
   BackEndModelManager, FrontEndModel, FrontEndModelManager, Client, LoginViewController, LoginViewModel,
   BackEndModel and ServerImpl. Its extends Remote.
 * @author
 */

public interface RMIServer extends Remote {
    int verifyLogin(LoginCredentials loginCredentials) throws RemoteException;
    void addSession(TrainingSession session) throws RemoteException;
    void registerCallback(ClientCallBack ccb) throws RemoteException;
    TrainingSessionList getSessions() throws RemoteException;
    ArrayList getTrainers() throws RemoteException;

    boolean verifyAvailabilityOfSession(TrainingSession session) throws RemoteException;
}
