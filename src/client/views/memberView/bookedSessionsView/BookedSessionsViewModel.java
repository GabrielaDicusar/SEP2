package client.views.memberView.bookedSessionsView;

import client.frontEndModel.FrontEndModelManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.sharedObjects.TrainingSession;
import shared.sharedObjects.TrainingSessionList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

/**
 * A Class implementing the methods for the bookedSessionsViewModel.
 * @author Group 7
 */
public class BookedSessionsViewModel implements PropertyChangeListener {
    private FrontEndModelManager modelManager;
    private ObservableList<TrainingSession> sessions;

    /**
     * A constructor instantiating the variables.
     * @param frontEndModelManager the frontEndModelManager
     */
    public BookedSessionsViewModel(FrontEndModelManager frontEndModelManager)
    {
        modelManager = frontEndModelManager;
        modelManager.addListener("ParticipantAdded", this);
        modelManager.addListener("SessionDeleted", this);
        modelManager.addListener("UnassignedTrainer", this);
    }

    /**
     * A method that loads the sessions booked by the member.
     */
    public void loadSessions() {
        TrainingSessionList logList = null;
        try {
            logList = modelManager.getListOfSessionsBookedByMember(modelManager.getClient().getLoginCredentials());
            sessions = FXCollections.observableArrayList(logList.getTrainingSessions());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the sessions
     * @return sessions
     */
    public ObservableList<TrainingSession> getSessions() {
        return sessions;
    }

    /**
     * Returns the modelManger
     * @return modelManager
     */
    public FrontEndModelManager getModelManager() {
        return modelManager;
    }

    /**
     * A listener method that reacts to changes in the frontEndModel.
     * @param evt the event
     */
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
        else if(evt.getPropertyName().equals("SessionDeleted"))
        {
            sessions.remove((TrainingSession) evt.getNewValue());
        } else if(evt.getPropertyName().equals("UnassignedTrainer"))
        {
            TrainingSession newValue = (TrainingSession) evt.getNewValue();
            System.out.println("available vm heard the event of unassign trainer");
            for (int i = 0; i < sessions.size(); i++) {
                if(sessions.get(i).getTime().equals(newValue.getDate()) && sessions.get(i).getDate().equals(newValue.getDate())){
                    sessions.remove(sessions.get(i));

                }
            }
            sessions.add((TrainingSession) evt.getNewValue());
            System.out.println("!!!!! booked session vm heard the event of unassign trainer " + evt.getNewValue().toString());
        }
    }

    /**
     * Removes the training session.
     * @param trainingSession the training session
     */
    public void removeSession(TrainingSession trainingSession) {
        modelManager.removeSession(trainingSession);
    }
}
