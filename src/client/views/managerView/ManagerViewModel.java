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

/**
 * A Class implementing the methods for the ManagerViewModel.
 * @author Group 7
 */
public class ManagerViewModel implements PropertyChangeListener
{
  private FrontEndModelManager modelManager;
  private ObservableList<TrainingSession> sessions;
  private DateTimeFormatter dateTimeFormatter;
  private StringProperty date;
  public ManagerViewModel(FrontEndModelManager frontEndModelManager) {
    this.modelManager = frontEndModelManager;
    date = new SimpleStringProperty();
    dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
    modelManager.addListener("SessionAdded", this);
    modelManager.addListener("ParticipantAdded", this);
    modelManager.addListener("SessionDeleted", this);
    modelManager.addListener("UnassignedTrainer", this);
    modelManager.addListener("UpdateSession", this);

  }

  /**
   * Loads the sessions
   */
  public void loadSessions() {
    System.out.println(date.get());
    TrainingSessionList logList = modelManager.getSessionsForManager(LocalDate.parse(date.get(), dateTimeFormatter));
    sessions = FXCollections.observableArrayList(logList.getTrainingSessions());
  }

  /**
   * Returns the sessions
   * @return sessions
   */
  public ObservableList<TrainingSession> getSessions() {
    return sessions;
  }

  /**
   * Returns the date
   * @return date
   */
  public StringProperty getDate() {
    return date;
  }

  /**
   * Returns the modelManager
   * @return modelManager
   */
  public FrontEndModelManager getFrontEndModelManager() {
    return modelManager;
  }

  /**
  *  A listener method that reacts to the events from frontEndModel
  * @param evt the event
  */
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
      TrainingSession newValue = (TrainingSession) evt.getNewValue();
      if (date.get().equals(newValue.getDate().format(DateTimeFormatter.ofPattern("M/d/yyyy"))))
      {
        sessions.remove((TrainingSession) evt.getNewValue());
        sessions.add((TrainingSession) evt.getNewValue());
      }
    }
    else if (evt.getPropertyName().equals("SessionDeleted"))
    {
      sessions.remove((TrainingSession) evt.getNewValue());
    }
    else if (evt.getPropertyName().equals("UnassignedTrainer"))
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
    } else if (evt.getPropertyName().equals("UpdateSession"))
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
            break;
          }
        }
        sessions.add((TrainingSession) evt.getNewValue());
      }
    }
  }

  /**
   * A method that sends the selected item to be edited, when a manager edits a training session.
   * @param selectedItem
   */
  public void sendToEdit(TrainingSession selectedItem) {
    modelManager.sendToEdit(selectedItem);
  }
}