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
  private ObservableList<LocalTime> timeList;

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
    ArrayList<LocalTime> time = new ArrayList<>();
    time.add(LocalTime.of(8,0));
    time.add(LocalTime.of(9,0));
    time.add(LocalTime.of(10,0));
    time.add(LocalTime.of(11,0));
    time.add(LocalTime.of(12,0));
    time.add(LocalTime.of(13,0));
    time.add(LocalTime.of(14,0));
    time.add(LocalTime.of(15,0));
    time.add(LocalTime.of(16,0));
    time.add(LocalTime.of(17,0));
    time.add(LocalTime.of(18,0));
    time.add(LocalTime.of(19,0));
    timeList = FXCollections.observableArrayList(time);
  }

  public ObservableList<LocalTime> getTimeSlots(){
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
