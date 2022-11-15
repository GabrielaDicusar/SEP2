package client.core;

import client.network.RMIClient;
import client.network.Member;

public class ClientFactory {

    private RMIClient client;

    public RMIClient getClient() {
       if(client == null){
           client = new Member();
       }
       return client;
    }
}
