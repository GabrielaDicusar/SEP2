package client.views.loginView;

import client.core.ModelFactory;
import client.frontEndModel.FrontEndModelManager;
import shared.sharedObjects.Account;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginViewModel implements PropertyChangeListener
{
    private FrontEndModelManager frontEndModelManager;

    public LoginViewModel(ModelFactory modelFactory) {
        frontEndModelManager = modelFactory.getFrontEndModelManager();
    }
    public Account verifyLogin(Account account) {
        System.out.println("2 Log In View Model got loginCredentials from controller, passing it to front model " + account.toString());
        return frontEndModelManager.verifyLogin(account);
    }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {

    }
}
