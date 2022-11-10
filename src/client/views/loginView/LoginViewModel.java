package client.views.loginView;

import client.core.ModelFactory;
import client.frontEndModel.FrontEndModelManager;
import client.network.RMIClient;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.sharedObjects.Account;
import shared.sharedObjects.AccountList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.List;

public class LoginViewModel implements PropertyChangeListener {

    private List<Account> accounts;
    private FrontEndModelManager frontEndModelManager;
    private StringProperty checkLogin;

    public LoginViewModel(ModelFactory modelFactory) {
        frontEndModelManager = modelFactory.getFrontEndModelManager();
        checkLogin = new SimpleStringProperty();

    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        checkLogin.setValue(evt.getNewValue().toString());
        accounts.add((Account) evt.getNewValue());
    }

    public boolean verifyLogin(Account account) {
        for (int i = 0; i < accounts.size(); i++) {
            if(accounts.contains(account)){
                return true;
            }
        } return false;
    }

    public StringProperty checkProperty(){
        return checkLogin;
    }

}
