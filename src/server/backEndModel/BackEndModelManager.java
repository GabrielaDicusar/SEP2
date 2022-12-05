package server.backEndModel;

import shared.sharedObjects.Account;
import shared.sharedObjects.TrainingSession;
import shared.sharedObjects.TrainingSessionList;
import shared.utils.Subject;

import java.util.ArrayList;

/**
 * Interface BackEndModelManager creates addSession, removeSession, addAccount, removeAccount, and verifyLogin which are implemented
   by BackEndModel. It extends Subject.
 * @author
 */

public interface BackEndModelManager extends Subject
{
    Account verifyLogin(Account account);
    void addSession(TrainingSession session);
    ArrayList getTrainersList();

    boolean verifyAvailabilityOfSession(TrainingSession session);

    TrainingSessionList getListOfSessionsAvailableForMember(Account account);
    TrainingSessionList getListOfSessionsBookedByMember(Account account);
}
