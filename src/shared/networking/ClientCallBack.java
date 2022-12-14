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
    void updateNewParticipant(TrainingSession prevSession, TrainingSession newSession) throws RemoteException;
    void updateDeleteSession(TrainingSession session) throws RemoteException;
    void updateUnassignedTrainer(TrainingSession session) throws RemoteException;
    void updateUpdateSession(TrainingSession session) throws RemoteException;
}
