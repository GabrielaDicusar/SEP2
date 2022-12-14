package client.views.managerView.editView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import shared.sharedObjects.Account;
import shared.sharedObjects.TrainingSession;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * A controller class for the EditViewModel.
 * @author Group 7
 */
public class EditViewController implements ViewController
{
  public TextField typeField;
  public DatePicker datePicker;
  public ComboBox trainerCombo;
  public Button btnSave;
  public TextField capacityField;
  public ComboBox availableTimeCombo;
  public Button btnCancel;
  public Button btnDelete;
  private ViewHandler viewHandler;
  private EditViewModel editViewModel;
  private DateTimeFormatter dateTimeFormatter;

  /**
   * An init method for the viewHandler and viewModelFactory.
   * @param viewHandler the viewHandler
   * @param viewModelFactory the viewModelFactory
   */
  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    editViewModel = viewModelFactory.getEditViewModel();
    dateTimeFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
    typeField.textProperty().bindBidirectional(editViewModel.getType());
    capacityField.textProperty().bindBidirectional(editViewModel.getCapacity());
    datePicker.getEditor().textProperty().bindBidirectional(editViewModel.getDate());
    availableTimeCombo.getEditor().textProperty().bindBidirectional(editViewModel.getTime());
    editViewModel.loadListOfTrainers();
    trainerCombo.setItems(editViewModel.getTrainers());

  }

  /**
   * An action when a button is pressed.
   * @param actionEvent the action event
   */
  public void onButtonPressed(ActionEvent actionEvent) {
    if (actionEvent.getSource() == btnCancel)
    {
      viewHandler.openManagerView();
    }
    else if (actionEvent.getSource() == btnSave)
    {
      if(typeField.getText() != null && capacityField.getText() != null && trainerCombo.getSelectionModel().getSelectedItem() != null) {
        System.out.println("fields are not empty");
        TrainingSession session = new TrainingSession(typeField.getText(), editViewModel.getTime().get(), (Integer) Integer.valueOf(capacityField.getText()), (Account) trainerCombo.getSelectionModel().getSelectedItem(), LocalDate.parse(editViewModel.getDate().get(), dateTimeFormatter));
          typeField.clear();
          capacityField.clear();
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                  "You have successfully updated a training session");
          alert.setHeaderText(null);
          alert.showAndWait();
          viewHandler.openManagerView();
          editViewModel.updateSession(session);
      } else{
        System.out.println("fields are empty");
        Alert alert = new Alert(Alert.AlertType.ERROR,
                "Empty fields");
        alert.setHeaderText(null);
        alert.showAndWait();
      }
    }
    else if (actionEvent.getSource() == btnDelete)
    {
      TrainingSession session = new TrainingSession(editViewModel.getType().get(), editViewModel.getTime().get(), Integer.parseInt(editViewModel.getCapacity().get()), (Account) trainerCombo.getSelectionModel().getSelectedItem(), LocalDate.parse(editViewModel.getDate().get(), dateTimeFormatter));
      editViewModel.deleteSession(session);
      typeField.clear();
      capacityField.clear();

      Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
              "You have successfully deleted a training session");
      alert.setHeaderText(null);
      alert.showAndWait();
      viewHandler.openManagerView();
    }

  }
}
