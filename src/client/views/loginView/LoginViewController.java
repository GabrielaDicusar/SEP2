package client.views.loginView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import shared.sharedObjects.Account;

public class LoginViewController implements ViewController {

    @FXML
    public Label lblUsername;
    public Label lblPassword;
    public Label lblLoginTitle;
    public Label errorLabel;
    @FXML
    public TextField txtUsername;
    public TextField txtPassword;
    @FXML
    public Button btnRegister;
    public Button btnLogin;

    private ViewHandler viewHandler;
    private LoginViewModel loginViewModel;


    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.viewHandler = viewHandler;
        loginViewModel = viewModelFactory.getLoginViewModel();
    }

    public void onbtnLogin(){
        Account account = new Account(txtUsername.getText(), txtPassword.getText());
        System.out.println("1 Login controller created account to pass to login view model " + account);
        if(loginViewModel.verifyLogin(account)){
            System.out.println(loginViewModel.verifyLogin(account));
            viewHandler.openBookingView();
        }else{
            errorLabel.setText("Invalid username or password");}
    }
}
