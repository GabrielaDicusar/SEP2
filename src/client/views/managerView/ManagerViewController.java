package client.views.managerView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.sharedObjects.TrainingSession;
/**
 * A controller class for the ManagerViewModel.
 * @author Group 7
 */
public class ManagerViewController implements ViewController
{
  public DatePicker datePicker;
  public TableView<TrainingSession> sessionTable;
  public Button btnCreateSession;
  public Button btnEditSession;
  public Button btnRegisterTrainer;
  public TableColumn<TrainingSession, String> timeColumn;
  public TableColumn<TrainingSession, String> typeColumn;
  public TableColumn<TrainingSession, String> trainerColumn;
  public TableColumn<TrainingSession, String> participantsColumn;
  private ViewHandler viewHandler;
  private ManagerViewModel managerViewModel;

  /**
   * An init method for the viewHandler and viewModelFactory
   * @param viewHandler the viewHandler
   * @param viewModelFactory the viewModelFactory
   */
  @Override
  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory){
    this.viewHandler = viewHandler;
    managerViewModel = viewModelFactory.getManagerViewModel();

    timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
    typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
    trainerColumn.setCellValueFactory(new PropertyValueFactory<>("trainer"));
    participantsColumn.setCellValueFactory(new PropertyValueFactory<>("participants"));
    datePicker.getEditor().textProperty().bindBidirectional(managerViewModel.getDate());
  }

  /**
   * A reset method.
   */
  public void reset()
  {
    managerViewModel.loadSessions();
    sessionTable.setItems(managerViewModel.getSessions());
  }

  /**
   * An action when a button is pressed.
   * @param actionEvent the action event
   */
  public void onButtonPressed(ActionEvent actionEvent) {
    if (actionEvent.getSource() == btnCreateSession)
    {
      viewHandler.openCreateSessionView();
      reset();
    }
    else if (actionEvent.getSource() == btnEditSession) {
      managerViewModel.sendToEdit(sessionTable.getSelectionModel().getSelectedItem());
      viewHandler.openEditView();
      reset();
    }
  }

  /**
   * A reset method for the calendar.
   * @param actionEvent the action event
   */
  public void onDatePicker(ActionEvent actionEvent)
  {
    reset();
  }

  /**
   * An action when a trainer is registered.
   * @param actionEvent the action event
   */
  public void onRegisterTrainer(ActionEvent actionEvent) {
    viewHandler.openRegisterTrainerView();
    reset();
  }
}
