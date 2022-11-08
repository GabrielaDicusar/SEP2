package client.frontEndModel;

import shared.sharedObjects.Account;
import shared.sharedObjects.TrainingSession;

import java.util.List;

public class FrontEndModelClass implements FrontEndModel{
    private List<TrainingSession> listOfSessions;
    private List<Account> listOfAccount;

    @Override
    public void addSession(TrainingSession session) {
        listOfSessions.add(session);
    }

    @Override
    public void removeSession(TrainingSession session) {
        listOfSessions.remove(session);
    }

    @Override
    public void addAccount(Account account) {
        listOfAccount.add(account);
    }

    @Override
    public void removeAccount(Account account) {
        listOfAccount.remove(account);
    }

    public Account getAccount(Account account) {
        for (int i = 0; i < listOfAccount.size(); i++) {
            if (account.equals(i)) {
                return account;
            }
        }
        return null;
    }

    public Account getSession(Account account) {
        for (int i = 0; i < listOfSessions.size(); i++) {
            if (account.equals(i)) {
                return account;
            }
        }
        return null;
    }
}
