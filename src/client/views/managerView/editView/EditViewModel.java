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


  public EditViewModel(FrontEndModelManager frontEndModelManager) {
    this.frontEndModelManager = frontEndModelManager;
    frontEndModelManager.addListener("SendToEdit", this);
    type = new SimpleStringProperty();
    capacity = new SimpleStringProperty();
    time = new SimpleStringProperty();
    date = new SimpleStringProperty();
    dateTimeFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
  }

  public FrontEndModelManager getFrontEndModelManager() {
    return frontEndModelManager;
  }

  public StringProperty getCapacity() {
    return capacity;
  }

  public StringProperty getTime() {
    return time;
  }

  public StringProperty getDate() {
    return date;
  }
  public void loadListOfTrainers(){
    try {
      ArrayList trainersList = frontEndModelManager.getTrainers();
      trainers = FXCollections.observableArrayList(trainersList);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  public ObservableList<Account> getTrainers() {
    return trainers;
  }

  public StringProperty getType() {
    return type;
  }
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
  public ObservableList<String> getTimeSlots(){
    return timeList;
  }

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
    frontEndModelManager.updateSession(session);
  }

  public void deleteSession(TrainingSession session) {
    frontEndModelManager.deleteSession(session);
  }
}
