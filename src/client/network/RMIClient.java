package client.network;

import shared.sharedObjects.LoginCredentials;
import shared.sharedObjects.TrainingSession;
import shared.sharedObjects.TrainingSessionList;
import shared.utils.Subject;

public interface RMIClient extends Subject {
    int login(LoginCredentials loginCredentials);

    void startClient();
    void addSession(TrainingSession session);
    TrainingSessionList getSessions();
}
