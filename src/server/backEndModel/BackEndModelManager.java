package server.backEndModel;

import shared.sharedObjects.LoginCredentials;
import shared.sharedObjects.TrainingSession;
import shared.utils.Subject;

import java.util.ArrayList;

/**
 * Interface BackEndModelManager creates addSession, removeSession, addAccount, removeAccount, and verifyLogin which are implemented
   by BackEndModel. It extends Subject.
 * @author
 */

public interface BackEndModelManager extends Subject
{
    int verifyLogin(LoginCredentials loginCredentials);
    void addSession(TrainingSession session);
    ArrayList getTrainersList();

    boolean verifyAvailabilityOfSession(TrainingSession session);
}
