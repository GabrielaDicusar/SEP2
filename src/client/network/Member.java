package client.network;

import shared.networking.ClientCallBack;
import shared.sharedObjects.Account;

public class Member implements RMIClient, ClientCallBack {

    @Override
    public boolean login(Account account) {
        return false;
    }

    @Override
    public void startClient() {

    }
}
