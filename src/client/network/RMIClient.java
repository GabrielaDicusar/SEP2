package client.network;

import shared.sharedObjects.Account;
import shared.utils.Subject;

import java.rmi.RemoteException;

public interface RMIClient extends Subject {
    boolean login(Account account);

    void startClient();
}
