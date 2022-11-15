package client.network;

import shared.networking.ClientCallBack;
import shared.networking.RMIServer;
import shared.sharedObjects.Account;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Member implements RMIClient, ClientCallBack {
    private RMIServer server;
    private PropertyChangeSupport support;

    public Member(){
        support = new PropertyChangeSupport(this);
    }

    @Override
    public void startClient() {
        Registry registry = null;
        try{
            UnicastRemoteObject.exportObject(this,0);
            registry = LocateRegistry.getRegistry("localhost",1234);
            server = (RMIServer) registry.lookup("Server");
//            server.registerCallback(this);
            System.out.println("Client started");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean login(Account account) {
        try {
            System.out.println("4 Member got account from front model, using server to verify " + account.toString());
            return server.verifyLogin(account);
        } catch (RemoteException e) {
            throw new RuntimeException("Could not connect to the server :(");
        }
    }

//    @Override
//    public void updateLogIn(boolean verifiedValue) throws RemoteException
//    {
//        support.firePropertyChange("VerifyLogin",null, verifiedValue);
//    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }
}
