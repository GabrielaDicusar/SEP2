package client.views.managerView.registerTrainerView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import client.views.registerView.RegisterViewModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegisterTrainerViewController implements ViewController
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
  private RegisterTrainerViewModel registerTrainerViewModel;


  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {

    this.viewHandler = viewHandler;
    registerTrainerViewModel = viewModelFactory.getRegisterTrainerViewModel();
    bind();
  }

  @FXML private void onbtnCreateTrainer()
  {
    registerTrainerViewModel.createAccount();

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
        "You have successfully created a trainer account");
    alert.setHeaderText(null);
    alert.showAndWait();
    viewHandler.openManagerView();
  }

  private void bind() {
    firstName.textProperty().bindBidirectional(
        registerTrainerViewModel.getFirstNameProperty());
    lastName.textProperty().bindBidirectional(
        registerTrainerViewModel.getLastNameProperty());
    eMail.textProperty().bindBidirectional(registerTrainerViewModel.geteMailProperty());
    phoneNumber.textProperty().bindBidirectional(
        registerTrainerViewModel.getPhoneNumberProperty());
    username.textProperty().bindBidirectional(
        registerTrainerViewModel.getUserNameProperty());
    password.textProperty().bindBidirectional(
        registerTrainerViewModel.getPasswordProperty());
    errorLabel.textProperty().bind(
        registerTrainerViewModel.errorLabelProperty());
  }

}
