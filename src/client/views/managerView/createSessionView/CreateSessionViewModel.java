package client.views.managerView.createSessionView;

import client.frontEndModel.FrontEndModelManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.sharedObjects.Account;
import shared.sharedObjects.AccountList;
import shared.sharedObjects.TrainingSession;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.time.LocalTime;
import java.util.ArrayList;

public class CreateSessionViewModel implements PropertyChangeListener
{
  private FrontEndModelManager frontEndModelManager;
  private ObservableList<Account> trainers;
  private ObservableList<String> timeList;

  public CreateSessionViewModel(FrontEndModelManager frontEndModelManager) {
    this.frontEndModelManager = frontEndModelManager;
  }

  public void loadListOfTrainers(){
    try {
      ArrayList trainersList = frontEndModelManager.getTrainers();
      trainers = FXCollections.observableArrayList(trainersList);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  public ObservableList<Account> getTrainers(){
    return trainers;
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

  public FrontEndModelManager getFrontEndModelManager() {
    return frontEndModelManager;
  }


  @Override
  public void propertyChange(PropertyChangeEvent evt) {

  }

  public boolean verifyAvailabilityOfSession(TrainingSession session) {
    try {
      return frontEndModelManager.verifyAvailabilityOfSession(session);
    } catch (RemoteException e) {
      throw new RuntimeException("Something went wrong");
    }
  }

  public void addSession(TrainingSession session) {
    frontEndModelManager.addSession(session);
  }
}
