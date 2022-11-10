package client.views.loginView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import shared.sharedObjects.Account;

import javax.swing.plaf.synth.Region;

public class LoginViewController implements ViewController {

    public TextField txtUsername;
    public TextField txtPassword;
    public Label lblUsername;
    public Label lblPassword;
    public Label lblLoginTitle;
    public Button btnRegister;
    public Button btnLogin;
    private ViewHandler viewHandler;
    private LoginViewModel loginViewModel;


    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory){
        this.viewHandler = viewHandler;
    loginViewModel = viewModelFactory.getLoginViewModel();
    }
    public void onbtnLogin(){
        Account account = new Account(txtUsername.getText(), txtPassword.getText());
        if (loginViewModel.verifyLogin(account)){
            viewHandler.openBookingView();
        }
    }
    public void onbtnRegister(){
    }
}
