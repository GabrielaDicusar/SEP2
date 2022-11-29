package client.views.memberView;

import client.frontEndModel.FrontEndModelManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.sharedObjects.TrainingSession;
import shared.sharedObjects.TrainingSessionList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class BookingViewModel implements PropertyChangeListener {

    FrontEndModelManager frontEndModelManager;
    ObservableList<TrainingSession> sessions;
    public BookingViewModel(FrontEndModelManager frontEndModelManager) {
        this.frontEndModelManager = frontEndModelManager;
    }

    public void loadSessions() {
        TrainingSessionList logList = frontEndModelManager.getSessions();
        sessions = FXCollections.observableArrayList(logList.getTrainingSessions());
    }
    ObservableList<TrainingSession> getSessions() {
        return sessions;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("SessionAdded"))
        {
            loadSessions();
        }
    }
}
