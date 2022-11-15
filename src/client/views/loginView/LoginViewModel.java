package client.views.loginView;

import client.core.ModelFactory;
import client.frontEndModel.FrontEndModelManager;
import client.network.RMIClient;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.sharedObjects.Account;
import shared.sharedObjects.AccountList;
import shared.utils.Subject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.List;

public class LoginViewModel implements PropertyChangeListener, Subject {

    private List<Account> accounts;
    private FrontEndModelManager frontEndModelManager;
    private StringProperty username;
    private StringProperty password;
//    private boolean permissionToSwitchViews;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public LoginViewModel(ModelFactory modelFactory) {
        frontEndModelManager = modelFactory.getFrontEndModelManager();
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
//        frontEndModelManager.addListener("VerifyLogin", this::onVerifiedAccount);
    }

//    private void onVerifiedAccount(PropertyChangeEvent evt){
//        support.firePropertyChange(evt);
//    }


    public StringProperty usernameStringProperty(){
        return username;
    }

    public StringProperty passwordStringProperty(){
        return password;
    }

    public boolean verifyLogin(Account account) {
        System.out.println("2 Log In View Model got account from controller, passing it to front model " + account.toString());
        return frontEndModelManager.verifyLogin(account);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
//        checkLogin.setValue(evt.getNewValue().toString());
        accounts.add((Account) evt.getNewValue());
    }

    public StringProperty checkProperty(){
        return null;
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }
}
