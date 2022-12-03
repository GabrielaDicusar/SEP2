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

public class AvailableToBookViewController implements ViewController {

    public TableView<TrainingSession> sessionTableView;
    public TableColumn<TrainingSession, String> timeColumn;
    public TableColumn<TrainingSession, String> typeColumn;
    public TableColumn<TrainingSession, String> trainerColumn;
    public TableColumn<TrainingSession, String> participantsColumn;
    public DatePicker datePicker;
    private ViewHandler viewHandler;
    private AvailableToBookViewModel availableToBookViewModel;

    @Override
    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.viewHandler = viewHandler;
        availableToBookViewModel = viewModelFactory.getAvailableToBookViewModel();
        availableToBookViewModel.loadSessions();
        sessionTableView.setItems(availableToBookViewModel.getSessions());

        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        trainerColumn.setCellValueFactory(new PropertyValueFactory<>("trainer"));
        participantsColumn.setCellValueFactory(new PropertyValueFactory<>("participants"));
    }

    public void onButtonPressed(ActionEvent actionEvent) {
        availableToBookViewModel.addParticipant(availableToBookViewModel.getModelManager().getClient().getLoginCredentials(), sessionTableView.getSelectionModel().getSelectedItem());
    }
}
