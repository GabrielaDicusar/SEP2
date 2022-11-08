package client.views.loginView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;

public class LoginViewController implements ViewController {

    private ViewHandler viewHandler;
    private ViewModelFactory viewModelFactory;


    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory){
        this.viewHandler = viewHandler;
        this.viewModelFactory = viewModelFactory;
    }
}
