package shared.networking;

import shared.sharedObjects.TrainingSession;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface ClientCallBack extends Remote and hosts the observer pattern object for updateNewSession,
 * updateNewParticipant and updateDeleteSession.
 * @author
 */
public interface ClientCallBack extends Remote {
    void updateNewSession(TrainingSession session) throws RemoteException;
    void updateNewParticipant(TrainingSession prevSession, TrainingSession newSession) throws RemoteException;
    void updateDeleteSession(TrainingSession session) throws RemoteException;
    void updateUnassignedTrainer(TrainingSession session) throws RemoteException;
    void updateUpdateSession(TrainingSession session) throws RemoteException;
}
