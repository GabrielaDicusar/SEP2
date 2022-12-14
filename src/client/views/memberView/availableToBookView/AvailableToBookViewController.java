package client.views.memberView.availableToBookView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.frontEndModel.FrontEndModelManager;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.sharedObjects.TrainingSession;

/**
 *  A controller class for the availableToBookViewModel.
 * @author group 7
 */
public class AvailableToBookViewController implements ViewController {

    public TableView<TrainingSession> sessionTableView;
    public TableColumn<TrainingSession, String> timeColumn;
    public TableColumn<TrainingSession, String> typeColumn;
    public TableColumn<TrainingSession, String> trainerColumn;
    public TableColumn<TrainingSession, String> participantsColumn;
    public DatePicker datePicker;
    private ViewHandler viewHandler;
    private AvailableToBookViewModel availableToBookViewModel;

    /**
     * An init method for the viewHandler and viewModelFactory.
     * @param viewHandler the viewHandler
     * @param viewModelFactory the viewModelFactory
     */
    @Override
    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.viewHandler = viewHandler;
        availableToBookViewModel = viewModelFactory.getAvailableToBookViewModel();
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        trainerColumn.setCellValueFactory(new PropertyValueFactory<>("trainer"));
        participantsColumn.setCellValueFactory(new PropertyValueFactory<>("participants"));
        datePicker.getEditor().textProperty().bindBidirectional(availableToBookViewModel.getDate());
    }

    /**
     * A reset method
     */
    public void reset()
    {
        availableToBookViewModel. loadSessions();
        sessionTableView.setItems(availableToBookViewModel.getSessions());
    }

    /**
     * An action when the button is pressed.
     * @param actionEvent the action event
     */
    public void onButtonPressed(ActionEvent actionEvent) {
        availableToBookViewModel.addParticipant(availableToBookViewModel.getModelManager().getClient().getLoginCredentials(), sessionTableView.getSelectionModel().getSelectedItem());
        viewHandler.openBookingView();
        reset();
    }

    /**
     * A reset calender method
     * @param actionEvent the action event
     */
    public void onDatePicker(ActionEvent actionEvent)
    {
        reset();
    }
}
