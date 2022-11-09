package client.frontEndModel;

import shared.sharedObjects.Account;
import shared.sharedObjects.TrainingSession;

public interface FrontEndModelManager {
    void addSession(TrainingSession session);
    void removeSession(TrainingSession session);
    void addAccount(Account account);
    void removeAccount(Account account);
}
