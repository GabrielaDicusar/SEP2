package client.core;

import client.network.RMIClient;
import client.network.Client;


public class ClientFactory {
    private RMIClient client;

    public RMIClient getClient() {
        if (client == null)
        {
            client = new Client();
            client.startClient();
        }
        return client;
    }
}
