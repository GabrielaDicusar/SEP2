package client.views.registerView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;



public class RegisterViewController implements ViewController
{
  @FXML public TextField firstName;
  @FXML public TextField lastName;
  @FXML public TextField eMail;
  @FXML public  TextField phoneNumber;
  @FXML public TextField username;
  @FXML public TextField password;
  @FXML public Label errorLabel;
  @FXML public Button btnCreate;

  private ViewHandler viewHandler;
  private RegisterViewModel registerViewModel;


  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {

    this.viewHandler = viewHandler;
    registerViewModel = viewModelFactory.getRegisterViewModel();
    bind();
  }

  @FXML private void onbtnCreate()
  {
   registerViewModel.createAccount();

   viewHandler.openLoginView();
  }

  private void bind() {
    firstName.textProperty().bindBidirectional(
        registerViewModel.getFirstNameProperty());
    lastName.textProperty().bindBidirectional(
        registerViewModel.getLastNameProperty());
    eMail.textProperty().bindBidirectional(registerViewModel.geteMailProperty());
    phoneNumber.textProperty().bindBidirectional(
        registerViewModel.getPhoneNumberProperty());
    username.textProperty().bindBidirectional(
        registerViewModel.getUserNameProperty());
    password.textProperty().bindBidirectional(
        registerViewModel.getPasswordProperty());
    errorLabel.textProperty().bind(
        registerViewModel.errorLabelProperty());
  }

}
