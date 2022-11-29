package client.views.memberView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.sharedObjects.TrainingSession;

public class BookingViewController implements ViewController {

    public DatePicker datePicker;
    public TableView<TrainingSession> sessionTableView;
    public TableColumn<String, TrainingSession> timeColumn;
    public TableColumn<String, TrainingSession> typeColumn;
    public TableColumn<String, TrainingSession> trainerColumn;
    public TableColumn<String, TrainingSession> participantsColumn;

    public Button btnBookSession;
    public Button btnRemoveBooking;
    private ViewHandler viewHandler;
    private BookingViewModel bookingViewModel;

   public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory){
        this.viewHandler = viewHandler;
       bookingViewModel = viewModelFactory.getBookingViewModel();
       bookingViewModel.loadSessions();
       sessionTableView.setItems(bookingViewModel.getSessions());

       timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
       typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
       trainerColumn.setCellValueFactory(new PropertyValueFactory<>("trainer"));
       participantsColumn.setCellValueFactory(new PropertyValueFactory<>("participants"));
    }

}
