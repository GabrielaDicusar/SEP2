package server.networking;

import server.backEndModel.BackEndModel;
import shared.networking.ClientCallBack;
import shared.networking.RMIServer;
import shared.sharedObjects.Account;
import shared.sharedObjects.TrainingSession;
import shared.sharedObjects.TrainingSessionList;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A class containing the methods for the server and it implements the RMIServer.
 * @author
 */
public class ServerImpl implements RMIServer {
  private BackEndModel modelManager;

  public ServerImpl(BackEndModel modelManager) throws RemoteException {
    UnicastRemoteObject.exportObject(this, 0);
    this.modelManager = modelManager;
  }

  /**
   * A method creating log instances once the server has been started.
   */
  public void startServer() {
    Registry registry = null;
    try {
      registry = LocateRegistry.createRegistry(1099);
//      System.setProperty("java.rmi.server.hostname","192.168.1.2");
      registry.bind("Server", this);
      System.out.println("The server has started.");
    } catch (RemoteException | AlreadyBoundException e) {
      System.out.println("Something went wrong..");
      e.printStackTrace();
    }
  }

  /**
   * Returns the verified login loginCredentials via a boolean method.
   *
   * @param account
   * @return loginCredentials
   * @throws RemoteException
   */
  @Override
  public Account verifyLogin(Account account) throws RemoteException {
    return modelManager.verifyLogin(account);
  }

  /**
   * Adds a session to the Training Session.
   * @param session
   * @throws RemoteException
   */
  @Override
  public void addSession(TrainingSession session) throws RemoteException{
    modelManager.addSession(session);
  }

  /**
   * Registers an action when a session is added, deleted, updated or when a participant is added to the session,
   * and when a trainer is unassigned to to the session.
   * @param ccb
   * @throws RemoteException
   */
  @Override
  public void registerCallback(ClientCallBack ccb) throws RemoteException {
    modelManager.addListener("SessionAdded", evt -> {
      try {
        ccb.updateNewSession((TrainingSession) evt.getNewValue());
      } catch (RemoteException e) {
        throw new RuntimeException(e);
      }
    });
    modelManager.addListener("ParticipantAdded", evt -> {
      try {
        ccb.updateNewParticipant((TrainingSession)evt.getOldValue(), (TrainingSession) evt.getNewValue());
      } catch (RemoteException e)
      {
        throw new RuntimeException(e);
      }
    });
    modelManager.addListener("SessionDeleted", evt -> {
      try {
        ccb.updateDeleteSession((TrainingSession) evt.getNewValue());
      } catch (RemoteException e)
      {
        throw new RuntimeException(e);
      }
    });
    modelManager.addListener("UnassignedTrainer", evt -> {
      try {
        ccb.updateUnassignedTrainer((TrainingSession) evt.getNewValue());
      } catch (RemoteException e)
      {
        throw new RuntimeException(e);
      }
    });
    modelManager.addListener("UpdateSession", evt -> {
      try {
        ccb.updateUpdateSession((TrainingSession) evt.getNewValue());
      } catch (RemoteException e)
      {
        throw new RuntimeException(e);
      }
    });
  }

  /**
   * Returns the list of sessions
   * @return list of sessions
   * @throws RemoteException
   */
  @Override
  public TrainingSessionList getSessions() throws RemoteException {
    return modelManager.getListOfSessions();
  }

  /**
   * Returns the trainers ArrayList.
   * @return list of trainers
   * @throws RemoteException
   */
  @Override
  public ArrayList getTrainers() throws RemoteException {
    return modelManager.getTrainersList();
  }

  /**
   * Creates an account.
   * @param account
   * @throws RemoteException
   */
  @Override public void createAccount(Account account) throws RemoteException
  {
    modelManager.createAccount(account);
  }

  /**
   * Verifies the availability of the session.
   * @param session
   * @return
   * @throws RemoteException
   */
  @Override
  public boolean verifyAvailabilityOfSession(TrainingSession session) throws RemoteException{
    return modelManager.verifyAvailabilityOfSession(session);
  }

  /**
   * Adds the participant of a specified account to the specific session.
   * @param account
   * @param session
   * @throws RemoteException
   */
  @Override
  public void addParticipant(Account account, TrainingSession session) throws RemoteException {
    modelManager.addParticipant(account, session);
  }

  /**
   * Returns an accounts available session.
   * @param account
   * @param date
   * @return accounts available sessions
   * @throws RemoteException
   */
  public TrainingSessionList getAvailableSessionsForMember(Account account, LocalDate date) throws RemoteException {
    return modelManager.getListOfSessionsAvailableForMember(account, date);
  }

  /**
   * Returns the specific sessions for a trainer.
   * @param account
   * @param date
   * @return  sessions for a trainer.
   * @throws RemoteException
   */
  @Override
  public TrainingSessionList getSessionsForTrainer(Account account, LocalDate date) throws RemoteException {
    return modelManager.getTrainingSessionsForTrainer(account, date);
  }

  /**
   * Returns the booked training sessions for a specific member.
   * @param account
   * @return booked sessions
   * @throws RemoteException
   */
  @Override
  public TrainingSessionList getListOfSessionsBookedByMember(Account account) throws RemoteException {
    return modelManager.getListOfSessionsBookedByMember(account);
  }

  /**
   * Checks if the member has booked the training session.
   * @param account
   * @param trainingSession
   * @return boolean
   * @throws RemoteException
   */
  @Override
  public boolean isMemberInSession(Account account, TrainingSession trainingSession) throws RemoteException {
    return modelManager.isMemberInSession(account, trainingSession);
  }

  /**
   * Removes the training session from the account.
   * @param account
   * @param trainingSession
   * @throws RemoteException
   */
  @Override
  public void removeSession(Account account, TrainingSession trainingSession) throws RemoteException {
    modelManager.removeSession(account, trainingSession);
  }

  /**
   * Returns the training sessions from for a manager.
   * @param parse
   * @return managers training sessions
   * @throws RemoteException
   */
  @Override
  public TrainingSessionList getSessionsForManager(LocalDate parse) throws RemoteException {
    return modelManager.getSessionsForManager(parse);
  }

  /**
   * Updates the training sessions.
   * @param session
   * @throws RemoteException
   */
  @Override
  public void updateSession(TrainingSession session) throws RemoteException {
    System.out.println("Server update session " + session.toString());
    modelManager.updateSession(session);
  }

  /**
   * Deletes the training sessions
   * @param session
   * @throws RemoteException
   */
  @Override
  public void deleteSession(TrainingSession session) throws RemoteException {
    modelManager.deleteSession(session);
  }

  /**
   * Unassigns a trainer to the training sessions.
   * @param session
   * @throws RemoteException
   */
  @Override
  public void unassignTrainer(TrainingSession session) throws RemoteException {
    modelManager.unassignTrainer(session);
  }


}
