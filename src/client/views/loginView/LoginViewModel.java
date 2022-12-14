package client.views.loginView;

import client.core.ModelFactory;
import client.frontEndModel.FrontEndModelManager;
import shared.sharedObjects.Account;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
/**
 * A Class implementing the methods for the loginViewModel.
 * @author Group 7
 */
public class LoginViewModel implements PropertyChangeListener
{
    private FrontEndModelManager frontEndModelManager;

    /**
     * A constructor for the instantiation of frontEndModel.
     * @param modelFactory the modelFactory
     */
    public LoginViewModel(ModelFactory modelFactory) {
        frontEndModelManager = modelFactory.getFrontEndModelManager();
    }

    /**
     * Returns the verified account
     * @param account the account
     * @return account
     */
    public Account verifyLogin(Account account) {
        System.out.println("2 Log In View Model got loginCredentials from controller, passing it to front model " + account.toString());
        return frontEndModelManager.verifyLogin(account);
    }

    /**
     * A property change event
     * @param evt the event
     */
    @Override public void propertyChange(PropertyChangeEvent evt)
    {

    }
}
