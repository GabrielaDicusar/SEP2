package server.backEndModel;

import shared.sharedObjects.Account;
import shared.sharedObjects.TrainingSession;
import shared.utils.Subject;



public interface BackEndModelManager extends Subject
{
    void addSession(TrainingSession session);
    void removeSession(TrainingSession session);
    void addAccount(Account account);
    void removeAccount(Account account);
    boolean verifyLogin(Account account);


}
