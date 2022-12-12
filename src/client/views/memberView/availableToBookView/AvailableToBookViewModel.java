package client.views.memberView.availableToBookView;

import client.frontEndModel.FrontEndModelManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.mediator.TrainingSessionDAO.TrainingSessionDAO;
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
        modelManager.addListener("SessionAdded", this);
        modelManager.addListener("ParticipantAdded", this);
        modelManager.addListener("SessionDeleted", this);
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
                if (!modelManager.getClient().isMemberInSession(modelManager.getClient().getLoginCredentials(), (TrainingSession) evt.getNewValue())) //check if the client is in bookedSessions database
                {
                    if (date.get().equals(newValue.getDate().format(DateTimeFormatter.ofPattern("d/MM/yyyy"))))
                    {
                        sessions.add((TrainingSession) evt.getNewValue());
                    }
                }
            }
            sessions.remove((TrainingSession) evt.getOldValue());
        }
        else if (evt.getPropertyName().equals("SessionAdded"))
        {
            TrainingSession newValue = (TrainingSession) evt.getNewValue();
            if (date.get().equals(newValue.getDate().format(DateTimeFormatter.ofPattern("d/MM/yyyy"))))
            {
                sessions.add((TrainingSession) evt.getNewValue());
            }
        }
        else if (evt.getPropertyName().equals("SessionDeleted"))
        {
            sessions.remove((TrainingSession) evt.getNewValue());
        }
    }

    public void addParticipant(Account account, TrainingSession session) {
        modelManager.addParticipant(account, session);
    }
}
