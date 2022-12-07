package client.views.memberView.bookedSessionsView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import client.views.memberView.availableToBookView.AvailableToBookViewModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.sharedObjects.TrainingSession;

public class BookedSessionsViewController implements ViewController {

    public TableView<TrainingSession> sessionTableView;
    public TableColumn<TrainingSession, String> timeColumn;
    public TableColumn<TrainingSession, String> typeColumn;
    public TableColumn<TrainingSession, String> trainerColumn;
    public TableColumn<TrainingSession, String> participantsColumn;
    public DatePicker datePicker;
    public Button btnRemoveSession;
    private ViewHandler viewHandler;
    private BookedSessionsViewModel bookedSessionsViewModel;


    public void reset()
    {
        bookedSessionsViewModel.loadSessions();
        sessionTableView.setItems(bookedSessionsViewModel.getSessions());
    }

    public void onButtonPressed(ActionEvent actionEvent) {
        bookedSessionsViewModel.removeSession(sessionTableView.getSelectionModel().getSelectedItem());
        viewHandler.openBookingView();
        reset();
    }

    @Override
    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.viewHandler = viewHandler;
        bookedSessionsViewModel = viewModelFactory.getBookedSessionsViewModel();
        reset();

        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        trainerColumn.setCellValueFactory(new PropertyValueFactory<>("trainer"));
        participantsColumn.setCellValueFactory(new PropertyValueFactory<>("participants"));

    }
}
