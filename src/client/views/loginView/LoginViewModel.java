package client.views.loginView;

import client.core.ModelFactory;
import client.frontEndModel.FrontEndModelManager;
import shared.sharedObjects.Account;

public class LoginViewModel {
    private FrontEndModelManager frontEndModelManager;

    public LoginViewModel(ModelFactory modelFactory) {
        frontEndModelManager = modelFactory.getFrontEndModelManager();
    }
    public boolean verifyLogin(Account account) {
        System.out.println("2 Log In View Model got account from controller, passing it to front model " + account.toString());
        return frontEndModelManager.verifyLogin(account);
    }

}
