package client.views.trainerView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import client.views.managerView.ManagerViewModel;
import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.sharedObjects.TrainingSession;

import java.awt.event.ActionEvent;

public class TrainerViewController implements ViewController

{
  public DatePicker datePicker;
  public TableView Timetable;
  public Button btnCancelBooking;
  private ViewHandler viewHandler;
  private TrainerViewModel trainerViewModel;
  public TableColumn<TrainingSession, String> timeColumn;
  public TableColumn<TrainingSession, String> typeColumn;
  public TableColumn<TrainingSession, String> trainerColumn;
  public TableColumn<TrainingSession, String> participantsColumn;
  public TableView<TrainingSession> sessionTableView;

  @Override
  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    trainerViewModel = viewModelFactory.getTrainerViewModel();
    timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
    typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
    trainerColumn.setCellValueFactory(new PropertyValueFactory<>("trainer"));
    participantsColumn.setCellValueFactory(new PropertyValueFactory<>("participants"));
    datePicker.getEditor().textProperty().bindBidirectional(trainerViewModel.getDate());
    sessionTableView.setItems(trainerViewModel.getSessions());

  }



  public void onDatePicker()
  {
    trainerViewModel.loadSessions();
    sessionTableView.setItems(trainerViewModel.getSessions());
    System.out.println("1");
  }

  public void onCancelBtn(){
    TrainingSession session = sessionTableView.getSelectionModel().getSelectedItem();
    trainerViewModel.unassignTrainer(session);
    trainerViewModel.loadSessions();
    sessionTableView.setItems(trainerViewModel.getSessions());
  }
}
