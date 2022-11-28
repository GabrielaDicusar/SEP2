package shared.networking;

import shared.sharedObjects.Account;
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
    boolean verifyLogin(Account account) throws RemoteException;
    void bookATrainingSession(TrainingSession trainingSession) throws RemoteException;
    void addUser(Account account) throws RemoteException;
//    void registerCallback(ClientCallBack clientCallBack) throws RemoteException;
}
