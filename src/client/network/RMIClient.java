package client.network;

import shared.sharedObjects.LoginCredentials;
import shared.utils.Subject;

public interface RMIClient extends Subject {
    boolean login(LoginCredentials loginCredentials);

    void startClient();
}
