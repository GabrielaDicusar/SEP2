package client.frontEndModel;

import shared.sharedObjects.LoginCredentials;
import shared.sharedObjects.TrainingSession;
import shared.utils.Subject;

public interface FrontEndModelManager extends Subject {
    void addSession(TrainingSession session);
    void removeSession(TrainingSession session);
    void addAccount(LoginCredentials loginCredentials);
    void removeAccount(LoginCredentials loginCredentials);
    boolean verifyLogin(LoginCredentials loginCredentials);
}
