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

/**
 * A controller class for the registerTrainerViewModel.
 * @author Group 7
 */
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

  /**
   * An init method fo the viewHandler and viewModelFactory.
   * @param viewHandler the viewHandler
   * @param viewModelFactory the viewModelFactory
   */
  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {

    this.viewHandler = viewHandler;
    registerTrainerViewModel = viewModelFactory.getRegisterTrainerViewModel();
    bind();
  }

  /**
   * An action when a button is pressed.
   */
  @FXML private void onbtnCreateTrainer()
  {
    registerTrainerViewModel.createAccount();

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
        "You have successfully created a trainer account");
    alert.setHeaderText(null);
    alert.showAndWait();
    viewHandler.openManagerView();
  }

  /**
   * A binding method for text properties.
   */
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
