package client.network;

import shared.sharedObjects.Account;

public interface RMIClient {
    boolean login(Account account);

    void startClient();
}
