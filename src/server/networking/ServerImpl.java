package server.networking;

import server.backEndModel.BackEndModel;
import shared.networking.RMIServer;
import shared.sharedObjects.LoginCredentials;
import shared.sharedObjects.TrainingSession;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * A class containing the methods for the server and it implements the RMIServer.
 * @author
 */
public class ServerImpl implements RMIServer

{
  private BackEndModel modelManager;

  public ServerImpl(BackEndModel modelManager) throws RemoteException
  {
    UnicastRemoteObject.exportObject(this, 0);
    this.modelManager = modelManager;
  }

  /**
   * A method creating log instances once the server has been started.
   */
  public void startServer()
  {
    Registry registry = null;
    try
    {
      registry = LocateRegistry.createRegistry(1234);
      registry.bind("Server", this);
      System.out.println("The server has started.");
    }
    catch (RemoteException | AlreadyBoundException e)
    {
      System.out.println("Something went wrong..");
      e.printStackTrace();
    }
  }

//  public void registerCallback(ClientCallBack clientCallBack) throws RemoteException {
//    modelManager.addListener("VerifyLogin", evt -> {
//      try {
//        clientCallBack.updateLogIn((boolean) evt.getNewValue());
//        System.out.println("Client connected");
//      } catch (RemoteException e) {
//        e.printStackTrace();
//      }
//    });
//  }

  /**
   * Returns the verified login loginCredentials via a boolean method.
   * @param loginCredentials
   * @return loginCredentials
   * @throws RemoteException
   */
  @Override
  public boolean verifyLogin(LoginCredentials loginCredentials) throws RemoteException
  {
    System.out.println("5 Got loginCredentials from member, passing it to back model " + loginCredentials.toString());
    return modelManager.verifyLogin(loginCredentials);
  }

  /**
   * ??
   * @param trainingSession
   * @throws RemoteException
   */

  @Override public void bookATrainingSession(TrainingSession trainingSession)
      throws RemoteException
  {
   modelManager.addSession(trainingSession);
  }

  /**
   * Adds a user and their credentials to the listOfAccounts.
   * @param loginCredentials
   * @throws RemoteException
   */

  @Override public void addUser(LoginCredentials loginCredentials) throws RemoteException
  {
  modelManager.addAccount(loginCredentials);
  }

  /**
   * ??
   * @param trainingSession
   * @throws RemoteException
   */

public void cancelTrainingSession(TrainingSession trainingSession) {
    modelManager.removeSession(trainingSession);
}

  }
