package shared.networking;

import shared.sharedObjects.LoginCredentials;
import shared.sharedObjects.TrainingSession;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface RMI Server creates verifyLogin, bookATrainingSession, and addUser which are implemented by
   BackEndModelManager, FrontEndModel, FrontEndModelManager, Client, LoginViewController, LoginViewModel,
   BackEndModel and ServerImpl. Its extends Remote.
 * @author
 */

public interface RMIServer extends Remote {
    boolean verifyLogin(LoginCredentials loginCredentials) throws RemoteException;
    void bookATrainingSession(TrainingSession trainingSession) throws RemoteException;
    void addUser(LoginCredentials loginCredentials) throws RemoteException;
//    void registerCallback(ClientCallBack clientCallBack) throws RemoteException;
}
