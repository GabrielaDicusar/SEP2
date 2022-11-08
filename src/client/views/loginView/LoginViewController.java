package client.views.loginView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;

public class LoginViewController implements ViewController {

    private ViewHandler viewHandler;
    private LoginViewModel loginViewModel;


    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory){
        this.viewHandler = viewHandler;
        loginViewModel = viewModelFactory.getLoginViewModel();
    }
}
