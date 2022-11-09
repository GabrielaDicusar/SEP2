package shared.networking;

import shared.sharedObjects.Account;
import shared.sharedObjects.TrainingSession;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServer extends Remote {
    boolean verifyLogin(Account account) throws RemoteException;
    void bookATrainingSession(TrainingSession trainingSession) throws RemoteException;
    void addUser(Account account);
    void registerCallback(ClientCallBack clientCallBack) throws RemoteException;
}
