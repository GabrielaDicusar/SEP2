package client.views.memberView.bookedSessionsView;

import client.frontEndModel.FrontEndModelManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.sharedObjects.TrainingSession;
import shared.sharedObjects.TrainingSessionList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

public class BookedSessionsViewModel implements PropertyChangeListener {
    private FrontEndModelManager modelManager;
    private ObservableList<TrainingSession> sessions;

    public BookedSessionsViewModel(FrontEndModelManager frontEndModelManager)
    {
        modelManager = frontEndModelManager;
        modelManager.getClient().addListener("ParticipantAdded", this);
        modelManager.getClient().addListener("SessionDeleted", this);
    }
    public void loadSessions() {
        TrainingSessionList logList = null;
        try {
            logList = modelManager.getListOfSessionsBookedByMember(modelManager.getClient().getLoginCredentials());
            sessions = FXCollections.observableArrayList(logList.getTrainingSessions());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
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
        else if(evt.getPropertyName().equals("SessionDeleted"))
        {
            sessions.remove((TrainingSession) evt.getNewValue());
        }
    }

    public void removeSession(TrainingSession trainingSession) {
        modelManager.removeSession(trainingSession);
    }
}
