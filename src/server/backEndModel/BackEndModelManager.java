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
    int verifyLogin(LoginCredentials loginCredentials);


}
