package server;

import server.backEndModel.BackEndModel;
import server.networking.ServerImpl;



import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * A class containing an execution method for ServerImpl.
 * @author
 */
public class RunServer
{
  public static void main(String[] args) throws RemoteException, SQLException
  {

    ServerImpl ss = new ServerImpl(new BackEndModel());
    ss.startServer();
  }
}
