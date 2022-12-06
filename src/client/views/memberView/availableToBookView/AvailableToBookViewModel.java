package client.views.memberView.availableToBookView;

import client.frontEndModel.FrontEndModelManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.sharedObjects.Account;
import shared.sharedObjects.TrainingSession;
import shared.sharedObjects.TrainingSessionList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AvailableToBookViewModel implements PropertyChangeListener {
    private FrontEndModelManager modelManager;
    private ObservableList<TrainingSession> sessions;
    private StringProperty date;
    private DateTimeFormatter dateTimeFormatter;

    public AvailableToBookViewModel(FrontEndModelManager frontEndModelManager) {
        modelManager = frontEndModelManager;
        modelManager.getClient().addListener("SessionAdded", this);
        modelManager.getClient().addListener("ParticipantAdded", this);
        date = new SimpleStringProperty();
        dateTimeFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
    }

    public StringProperty getDate() {
        return date;
    }

    public void loadSessions() {
        System.out.println(date.get());
            TrainingSessionList logList = modelManager.getAvailableSessionsForMember(modelManager.getClient().getLoginCredentials(), LocalDate.parse(date.get(), dateTimeFormatter));
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
                if (!newValue.getAssignedMembers().contains(modelManager.getClient().getLoginCredentials()))
                {
                    sessions.add(newValue);
                }
            }
            sessions.remove((TrainingSession) evt.getOldValue());
        }
        else if (evt.getPropertyName().equals("SessionAdded"))
        {
            sessions.add((TrainingSession) evt.getNewValue());
        }
    }

    public void addParticipant(Account account, TrainingSession session) {
        modelManager.addParticipant(account, session);
    }
}
