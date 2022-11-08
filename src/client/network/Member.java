package client.network;

import shared.sharedObjects.Account;

public class Member implements Client{

    @Override
    public boolean login(Account account) {
        return false;
    }
}
