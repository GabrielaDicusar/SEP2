package shared.networking;

import shared.sharedObjects.Account;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServer extends Remote {
    boolean verifyLogin(Account account) throws RemoteException;
    void registerCallback(ClientCallBack clientCallBack) throws RemoteException;
}
