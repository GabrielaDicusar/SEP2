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
/**
 * A Class implementing the methods for the availableToBookViewModel.
 * @author Group 7
 */
public class AvailableToBookViewModel implements PropertyChangeListener {
    private FrontEndModelManager modelManager;
    private ObservableList<TrainingSession> sessions;
    private StringProperty date;
    private DateTimeFormatter dateTimeFormatter;

    /**
     * A constructor to instantiate the variables.
     * @param frontEndModelManager the frontEndModelManager
     */
    public AvailableToBookViewModel(FrontEndModelManager frontEndModelManager) {
        modelManager = frontEndModelManager;
        modelManager.addListener("SessionAdded", this);
        modelManager.addListener("ParticipantAdded", this);
        modelManager.addListener("SessionDeleted", this);
        modelManager.addListener("UnassignedTrainer", this);
        modelManager.addListener("UpdateSession", this);
        date = new SimpleStringProperty();
        dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
    }

    /**
     * Returns the date.
     * @return date
     */
    public StringProperty getDate() {
        return date;
    }

    /**
     * Loads the sessions
     */
    public void loadSessions() {
        System.out.println(date.get());
            TrainingSessionList logList = modelManager.getAvailableSessionsForMember(modelManager.getClient().getLoginCredentials(), LocalDate.parse(date.get(), dateTimeFormatter));
            sessions = FXCollections.observableArrayList(logList.getTrainingSessions());
    }

    /**
     * Returns the training sessions.
     * @return sessions
     */
    public ObservableList<TrainingSession> getSessions() {
        return sessions;
    }

    /**
     * Returns the modelManager
     * @return modelManager
     */
    public FrontEndModelManager getModelManager() {
        return modelManager;
    }

    /**
     * A listener method that reacts to the events from frontEndModel
     * @param evt the event
     */
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
            if (date.get().equals(newValue.getDate().format(DateTimeFormatter.ofPattern("M/d/yyyy"))))
            {
                sessions.add((TrainingSession) evt.getNewValue());
            }
        }
        else if (evt.getPropertyName().equals("SessionDeleted"))
        {
            sessions.remove((TrainingSession) evt.getNewValue());
        }else if (evt.getPropertyName().equals("UnassignedTrainer"))
        {
            TrainingSession newValue = (TrainingSession) evt.getNewValue();
                if (date.get().equals(newValue.getDate().format(DateTimeFormatter.ofPattern("M/d/yyyy"))))
                {
                    for (int i = 0; i < sessions.size(); i++) {
                        if(sessions.get(i).getTime().equals(newValue.getTime())){
                            sessions.remove(sessions.get(i));
                        }
                    }
                    sessions.add((TrainingSession) evt.getNewValue());
                }
                System.out.println("Unassigned trainer " + newValue);
        }
        else if (evt.getPropertyName().equals("UpdateSession"))
        {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            TrainingSession newValue = (TrainingSession) evt.getNewValue();
            if (date.get().equals(newValue.getDate().format(DateTimeFormatter.ofPattern("M/d/yyyy"))))
            {
                int size = sessions.size();
                for (int i = 0; i < size; i++) {
                    if(sessions.get(i).getTime().equals(newValue.getTime())){
                        sessions.remove(sessions.get(i));
                        System.out.println("REMOVED OLD VALUE");
                        sessions.add((TrainingSession) evt.getNewValue());
                        break;
                    }
                }
            }
        }
    }

    /**
     * Adds a participant to the session.
     * @param account the account
     * @param session the session
     */
    public void addParticipant(Account account, TrainingSession session) {
        modelManager.addParticipant(account, session);
    }
}
