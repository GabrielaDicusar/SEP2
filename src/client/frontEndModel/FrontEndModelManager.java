package client.frontEndModel;

import client.network.Client;
import client.network.RMIClient;
import shared.sharedObjects.LoginCredentials;
import shared.sharedObjects.TrainingSession;
import shared.sharedObjects.TrainingSessionList;
import shared.utils.Subject;

public interface FrontEndModelManager extends Subject {
    void addSession(TrainingSession session);
    int verifyLogin(LoginCredentials loginCredentials);
    RMIClient getClient();
    TrainingSessionList getSessions();
}
