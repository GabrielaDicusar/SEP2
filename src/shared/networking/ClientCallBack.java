package shared.networking;

import shared.sharedObjects.Account;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallBack extends Remote {
    void updateLogIn(Account account) throws RemoteException;
}
