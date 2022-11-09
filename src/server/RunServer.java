package server;

import server.backEndModel.BackEndModel;
import server.networking.ServerImpl;



import java.rmi.RemoteException;


public class RunServer
{
  public static void main(String[] args) throws RemoteException {

    ServerImpl ss = new ServerImpl(new BackEndModel());
    ss.startServer();
  }
}
