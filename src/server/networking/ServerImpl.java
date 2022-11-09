package server.networking;

import server.backEndModel.BackEndModel;
import shared.networking.ClientCallBack;
import shared.networking.RMIServer;
import shared.sharedObjects.Account;
import shared.sharedObjects.TrainingSession;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl implements RMIServer
{
  private BackEndModel modelManager;

  public ServerImpl(BackEndModel modelManager) throws RemoteException
  {
    UnicastRemoteObject.exportObject(this, 0);
    this.modelManager = modelManager;
  }

  public void startServer()
  {
    Registry registry = null;
    try
    {
      registry = LocateRegistry.createRegistry(1099);
      registry.bind("Server", this);
    }
    catch (RemoteException | AlreadyBoundException e)
    {
      e.printStackTrace();
    }
  }

//  public void registerCallback(ClientCallback clientCallback) throws RemoteException {
//    modelManager.addListener("NewLogEntry", evt -> {
//      try {
//        clientCallback.update((Account) evt.getNewValue());
//      } catch (RemoteException e) {
//        e.printStackTrace();
//      }
//    });
//  }

  public Account getAccount(Account account) {
    return modelManager.getAccount(account);
  }
  public TrainingSession getSession(TrainingSession session) {
    return modelManager.getSession(session);
  }

  @Override public boolean verifyLogin(Account account) throws RemoteException
  {
    return false;
  }

  @Override public void registerCallback(ClientCallBack clientCallBack)
      throws RemoteException
  {

  }
}
