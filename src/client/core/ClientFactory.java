package client.core;

import client.network.Client;
import client.network.Member;

public class ClientFactory {

    private Client client;

    public Client getClient() {
       if(client == null){
           client = new Member();
           client.startClient();
       }
       return client;
    }
}
