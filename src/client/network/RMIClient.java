package client.network;

import shared.sharedObjects.LoginCredentials;
import shared.utils.Subject;

public interface RMIClient extends Subject {
    int login(LoginCredentials loginCredentials);

    void startClient();
}
