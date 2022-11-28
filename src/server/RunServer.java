package server;

import server.backEndModel.BackEndModel;
import server.networking.ServerImpl;



import java.rmi.RemoteException;

/**
 * A class containing an execution method for ServerImpl.
 * @author
 */
public class RunServer
{
  public static void main(String[] args) throws RemoteException {

    ServerImpl ss = new ServerImpl(new BackEndModel());
    ss.startServer();
  }
}
