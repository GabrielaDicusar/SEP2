package client.views.managerView;

import client.frontEndModel.FrontEndModelManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.sharedObjects.TrainingSession;
import shared.sharedObjects.TrainingSessionList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ManagerViewModel implements PropertyChangeListener
{
  private FrontEndModelManager modelManager;
  private ObservableList<TrainingSession> sessions;
  private DateTimeFormatter dateTimeFormatter;
  private StringProperty date;
  public ManagerViewModel(FrontEndModelManager frontEndModelManager) {
    this.modelManager = frontEndModelManager;
    date = new SimpleStringProperty();
    dateTimeFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
    modelManager.addListener("SessionAdded", this);
    modelManager.addListener("ParticipantAdded", this);
    modelManager.addListener("SessionDeleted", this);
  }

  public void loadSessions() {
    System.out.println(date.get());
    TrainingSessionList logList = modelManager.getSessionsForManager(LocalDate.parse(date.get(), dateTimeFormatter));
    sessions = FXCollections.observableArrayList(logList.getTrainingSessions());
  }

  public ObservableList<TrainingSession> getSessions() {
    return sessions;
  }

  public StringProperty getDate() {
    return date;
  }

  public FrontEndModelManager getFrontEndModelManager() {
    return modelManager;
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals("ParticipantAdded"))
    {
      TrainingSession newValue = (TrainingSession) evt.getNewValue();
      sessions.add(newValue);
      sessions.remove((TrainingSession) evt.getOldValue());
    }
    else if (evt.getPropertyName().equals("SessionAdded"))
    {
      sessions.add((TrainingSession) evt.getNewValue());
    }
    else if (evt.getPropertyName().equals("SessionDeleted"))
    {
      sessions.remove((TrainingSession) evt.getNewValue());
    }
  }

  public void sendToEdit(TrainingSession selectedItem) {
    modelManager.sendToEdit(selectedItem);
  }
}