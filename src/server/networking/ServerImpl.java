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
      registry = LocateRegistry.createRegistry(1234);
      //System.setProperty("java.rmi.server.hostname","192.168.1.2");
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

  @Override
  public void addSession(TrainingSession session) throws RemoteException{
    modelManager.addSession(session);
  }

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
  }

  @Override
  public TrainingSessionList getSessions() throws RemoteException {
    return modelManager.getListOfSessions();
  }

  @Override
  public ArrayList getTrainers() throws RemoteException {
    return modelManager.getTrainersList();
  }

  @Override public void createAccount(Account account) throws RemoteException
  {
    modelManager.createAccount(account);
  }

  @Override
  public boolean verifyAvailabilityOfSession(TrainingSession session) throws RemoteException{
    return modelManager.verifyAvailabilityOfSession(session);
  }

  @Override
  public void addParticipant(Account account, TrainingSession session) throws RemoteException {
    modelManager.addParticipant(account, session);
  }

  @Override
  public TrainingSessionList getAvailableSessionsForMember(Account account) throws RemoteException {
    return modelManager.getListOfSessionsAvailableForMember(account);
  }
  public TrainingSessionList getAvailableSessionsForMember(Account account, LocalDate date) throws RemoteException {
    return modelManager.getListOfSessionsAvailableForMember(account, date);
  }
  @Override
  public TrainingSessionList getListOfSessionsBookedByMember(Account account) throws RemoteException {
    return modelManager.getListOfSessionsBookedByMember(account);
  }

  @Override
  public boolean isMemberInSession(Account account, TrainingSession trainingSession) throws RemoteException {
    return modelManager.isMemberInSession(account, trainingSession);
  }

  @Override
  public void removeSession(Account account, TrainingSession trainingSession) throws RemoteException {
    modelManager.removeSession(account, trainingSession);
  }

  @Override
  public TrainingSessionList getSessionsForManager(LocalDate parse) throws RemoteException {
    return modelManager.getSessionsForManager(parse);
  }

  @Override
  public void updateSession(TrainingSession session) throws RemoteException {
    modelManager.updateSession(session);
  }

  @Override
  public void deleteSession(TrainingSession session) throws RemoteException {
    modelManager.deleteSession(session);
  }

}
