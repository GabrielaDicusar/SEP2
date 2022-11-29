package shared.networking;

import shared.sharedObjects.TrainingSession;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface ClientCallBack creates ...
 * @author
 */
public interface ClientCallBack extends Remote {
    void updateNewSession(TrainingSession session) throws RemoteException;
}
