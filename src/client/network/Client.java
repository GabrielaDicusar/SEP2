package client.network;

import shared.sharedObjects.Account;

public interface Client {
    boolean login(Account account);
}
