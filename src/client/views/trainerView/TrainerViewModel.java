package client.views.trainerView;

import client.frontEndModel.FrontEndModel;
import client.frontEndModel.FrontEndModelManager;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.sharedObjects.Account;
import shared.sharedObjects.TrainingSession;
import shared.sharedObjects.TrainingSessionList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TrainerViewModel implements PropertyChangeListener
{
  private FrontEndModelManager modelManager;
  private ObservableList<TrainingSession> sessions;
  private DateTimeFormatter dateTimeFormatter;
  private StringProperty date;

  public TrainerViewModel (FrontEndModel frontEndModelManager) {
    modelManager = frontEndModelManager;
    dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
    modelManager.addListener("SessionAdded", this);
    modelManager.addListener("ParticipantAdded", this);
    modelManager.addListener("SessionDeleted", this);
    modelManager.addListener("UpdateSession", this);
    date = new SimpleStringProperty();
  }

  public void loadSessions() {
    TrainingSessionList logList = null;
    try {
      logList = modelManager.getSessionsForTrainer(modelManager.getClient().getLoginCredentials(), LocalDate.parse(date.get(), dateTimeFormatter));
      System.out.println(date);

      sessions = FXCollections.observableArrayList(logList.getTrainingSessions());
      System.out.println(sessions);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  public ObservableList<TrainingSession> getSessions() {
    return sessions;
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals("ParticipantAdded")) {
      TrainingSession newValue = (TrainingSession) evt.getNewValue();
      if (newValue.getParticipants() != 0)
      {
        if (modelManager.getClient().getLoginCredentials().equals(newValue.getTrainerAccount()))
          System.out.println(modelManager.getClient().getLoginCredentials().equals(newValue.getTrainerAccount()));
        {
          if (date.get().equals(newValue.getDate().format(DateTimeFormatter.ofPattern("M/d/yyyy"))))
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
      if(modelManager.getClient().getLoginCredentials().equals(newValue.getTrainerAccount())) {
        if (date.get().equals(newValue.getDate().format(DateTimeFormatter.ofPattern("M/d/yyyy")))) {
          sessions.add((TrainingSession) evt.getNewValue());
        }
      }
    }
    else if (evt.getPropertyName().equals("SessionDeleted"))
    {
      sessions.remove((TrainingSession) evt.getNewValue());
    }
    else if (evt.getPropertyName().equals("UpdateSession"))
    {
      System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!");
      TrainingSession newValue = (TrainingSession) evt.getNewValue();
      if (date.get().equals(newValue.getDate().format(DateTimeFormatter.ofPattern("M/d/yyyy"))))
      {
        int size = sessions.size();
        int i;
        for (i = 0; i < size; i++) {
          if(sessions.get(i).getTime().equals(newValue.getTime())){
            sessions.remove(sessions.get(i));
            System.out.println("REMOVED OLD VALUE");
            break;
          }
        }
        if (sessions.get(i).getTrainerAccount().equals(newValue.getTrainerAccount()))
        sessions.add((TrainingSession) evt.getNewValue());
      }
    }
  }

  public void unassignTrainer(TrainingSession session) {
    modelManager.unassignTrainer(session);
  }

  public Property<String> getDate() {
    return date;
  }
}
