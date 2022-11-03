package server.backEndModel;

import shared.sharedObjects.Account;
import shared.sharedObjects.TrainingSession;

public interface BackEndModelManager {
    void addSession(TrainingSession session);
    void removeSession(TrainingSession session);
    void addAccount(Account account);
    void removeAccount(Account account);

}
