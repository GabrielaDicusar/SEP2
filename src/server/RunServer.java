package server;

import server.backEndModel.BackEndModel;
import server.backEndModel.BackEndModelManager;
import server.networking.ServerImpl;
import shared.networking.RMIServer;

import javax.management.remote.rmi.RMIServerImpl;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RunServer
{
  public static void main(String[] args) throws RemoteException {

    ServerImpl ss = new ServerImpl(new BackEndModel());
    ss.startServer();
  }
}
