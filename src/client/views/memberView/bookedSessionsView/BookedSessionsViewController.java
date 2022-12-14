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
/**
 *  A controller class for the bookedSessionsViewModel.
 * @author group 7
 */
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

    /**
     * A reset method
     */
    public void reset()
    {
        bookedSessionsViewModel.loadSessions();
        sessionTableView.setItems(bookedSessionsViewModel.getSessions());
    }

    /**
     * An action when the button is pressed.
     * @param actionEvent the action event
     */
    public void onButtonPressed(ActionEvent actionEvent) {
        bookedSessionsViewModel.removeSession(sessionTableView.getSelectionModel().getSelectedItem());
        viewHandler.openBookingView();
        reset();
    }

    /**
     * An init method for the viewHandler and ViewModelFactory
     * @param viewHandler the viewHandler
     * @param viewModelFactory the viewModelFactory
     */
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
