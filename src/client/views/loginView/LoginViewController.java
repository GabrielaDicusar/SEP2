package client.views.loginView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.event.ActionEvent;
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

    public void onbtnLogin() {
        Account account = new Account(txtUsername.getText(), txtPassword.getText());
        System.out.println("1 Login controller created loginCredentials to pass to login view model " + account);
        if (loginViewModel.verifyLogin(account) != null)
        {
            if (loginViewModel.verifyLogin(account).getAccountType() == 1) {
                System.out.println(loginViewModel.verifyLogin(account));
                viewHandler.openBookingView();
            }
            else if (loginViewModel.verifyLogin(account).getAccountType() == 2) {
                System.out.println(loginViewModel.verifyLogin(account));
                viewHandler.openManagerView();
            }
            else if (loginViewModel.verifyLogin(account).getAccountType() == 3)
            {
                System.out.println(loginViewModel.verifyLogin(account));
                viewHandler.openTrainerView();
            }
        }
        else
        {
            errorLabel.setText("Invalid username or password");
        }
    }

    public void onBtnRegister(ActionEvent event) {
     viewHandler.openRegisterMemberView();
    }
}
