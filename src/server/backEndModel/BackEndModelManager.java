package server.backEndModel;

import shared.sharedObjects.LoginCredentials;
import shared.sharedObjects.TrainingSession;
import shared.utils.Subject;

/**
 * Interface BackEndModelManager creates addSession, removeSession, addAccount, removeAccount, and verifyLogin which are implemented
   by BackEndModel. It extends Subject.
 * @author
 */

public interface BackEndModelManager extends Subject
{
    void addSession(TrainingSession session);
    void removeSession(TrainingSession session);
    void addAccount(LoginCredentials loginCredentials);
    void removeAccount(LoginCredentials loginCredentials);
    boolean verifyLogin(LoginCredentials loginCredentials);


}
