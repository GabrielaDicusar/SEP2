package client.views.memberView.availableToBookView;

import client.frontEndModel.FrontEndModelManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.sharedObjects.LoginCredentials;
import shared.sharedObjects.TrainingSession;
import shared.sharedObjects.TrainingSessionList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AvailableToBookViewModel implements PropertyChangeListener {
    private FrontEndModelManager modelManager;
    private ObservableList<TrainingSession> sessions;

    public AvailableToBookViewModel(FrontEndModelManager frontEndModelManager) {
        modelManager = frontEndModelManager;
        modelManager.getClient().addListener("SessionAdded", this);
        modelManager.getClient().addListener("ParticipantAdded", this);
    }

    public void loadSessions() {
        TrainingSessionList logList = modelManager.getAvailableSessionsForMember(modelManager.getClient().getLoginCredentials());
        sessions = FXCollections.observableArrayList(logList.getTrainingSessions());
    }

    public ObservableList<TrainingSession> getSessions() {
        return sessions;
    }

    public FrontEndModelManager getModelManager() {
        return modelManager;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("ParticipantAdded")) {
            TrainingSession newValue = (TrainingSession) evt.getNewValue();
            if (newValue.getParticipants() != 0)
            {
                sessions.add(newValue);
            }
            sessions.remove((TrainingSession) evt.getOldValue());
        }
        else if (evt.getPropertyName().equals("SessionAdded"))
        {
            sessions.add((TrainingSession) evt.getNewValue());
        }
    }

    public void addParticipant(LoginCredentials loginCredentials, TrainingSession session) {
        modelManager.addParticipant(loginCredentials, session);
    }
}
