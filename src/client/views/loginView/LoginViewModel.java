package client.views.loginView;

import client.core.ModelFactory;
import client.frontEndModel.FrontEndModelManager;
import shared.sharedObjects.LoginCredentials;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginViewModel implements PropertyChangeListener
{
    private FrontEndModelManager frontEndModelManager;

    public LoginViewModel(ModelFactory modelFactory) {
        frontEndModelManager = modelFactory.getFrontEndModelManager();
    }
    public int verifyLogin(LoginCredentials loginCredentials) {
        System.out.println("2 Log In View Model got loginCredentials from controller, passing it to front model " + loginCredentials.toString());
        return frontEndModelManager.verifyLogin(loginCredentials);
    }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {

    }
}
