package client.views.managerView.editView;

import client.frontEndModel.FrontEndModelManager;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.sharedObjects.Account;
import shared.sharedObjects.TrainingSession;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
/**
 * A Class implementing the methods for the EditViewModel.
 * @author Group 7
 */
public class EditViewModel implements PropertyChangeListener
{

  private FrontEndModelManager frontEndModelManager;
  private StringProperty type;
  private StringProperty capacity;
  private ObservableList<Account> trainers;
  private StringProperty time;
  private StringProperty date;
  private DateTimeFormatter dateTimeFormatter;
  private ObservableList<String> timeList;

  /**
   * A constructor for the initialisation of the variables.
   * @param frontEndModelManager the frontEndModelManager
   */
  public EditViewModel(FrontEndModelManager frontEndModelManager) {
    this.frontEndModelManager = frontEndModelManager;
    frontEndModelManager.addListener("SendToEdit", this);
    type = new SimpleStringProperty();
    capacity = new SimpleStringProperty();
    time = new SimpleStringProperty();
    date = new SimpleStringProperty();
    dateTimeFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
  }

  /**
   * Returns the frontEndModelManager
   * @return frontEndModelManager
   */
  public FrontEndModelManager getFrontEndModelManager() {
    return frontEndModelManager;
  }

  /**
   * Returns the capacity
   * @return capacity
   */
  public StringProperty getCapacity() {
    return capacity;
  }

  /**
   * Returns the time
   * @return time
   */
  public StringProperty getTime() {
    return time;
  }

  /**
   * Returns the date
   * @return date
   */
  public StringProperty getDate() {
    return date;
  }

  /**
   * loads the list of trainers.
   */
  public void loadListOfTrainers(){
    try {
      ArrayList trainersList = frontEndModelManager.getTrainers();
      trainers = FXCollections.observableArrayList(trainersList);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  /**
   * Returns the trainers
   * @return trainers
   */
  public ObservableList<Account> getTrainers() {
    return trainers;
  }

  /**
   * Returns the type
   * @return type
   */
  public StringProperty getType() {
    return type;
  }

  /**
   * loads the times of the sessions
   */
  public void loadTimeList(){
    ArrayList<String> time = new ArrayList<>();
    time.add("8.00");
    time.add("9.00");
    time.add("10.00");
    time.add("11.00");
    time.add("12.00");
    time.add("13.00");
    time.add("14.00");
    time.add("15.00");
    time.add("16.00");
    timeList = FXCollections.observableArrayList(time);
  }

  /**
   * Returns the time slots.
   * @return timeList
   */
  public ObservableList<String> getTimeSlots(){
    return timeList;
  }

  /**
   * A listener method for the frontEndModel
   * @param evt the event
   */
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals("SendToEdit"))
    {
      TrainingSession temp = (TrainingSession) evt.getNewValue();
      System.out.println(temp.getTime());
      type.set(temp.getType());
      capacity.set(String.valueOf(temp.getParticipants()));
      time.set(temp.getTime());
      date.set(temp.getDate().format(dateTimeFormatter));
    }
  }

  public void updateSession(TrainingSession session) {
    System.out.println("Edit VM update session" + session.toString());
    frontEndModelManager.updateSession(session);

  }

  public void deleteSession(TrainingSession session) {
    frontEndModelManager.deleteSession(session);
  }
}
