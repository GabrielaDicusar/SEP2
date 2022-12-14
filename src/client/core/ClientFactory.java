package client.core;

import client.network.RMIClient;
import client.network.Client;

/**
 * A class handling the methods for Client Factory.
 * @author Group 7
 */
public class ClientFactory {
    private RMIClient client;

    /**
     * Returns the client
     * @return client
     */
    public RMIClient getClient() {
        if (client == null)
        {
            client = new Client();
            client.startClient();
        }
        return client;
    }
}
