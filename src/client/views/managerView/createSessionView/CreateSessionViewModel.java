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
/**
 * A Class implementing the methods for the createSessionViewModel.
 * @author Group 7
 */
public class CreateSessionViewModel implements PropertyChangeListener
{
  private FrontEndModelManager frontEndModelManager;
  private ObservableList<Account> trainers;
  private ObservableList<String> timeList;

  /**
   * A constructor instantiating the frontEndModelManager.
   * @param frontEndModelManager the frontEndModelManager
   */
  public CreateSessionViewModel(FrontEndModelManager frontEndModelManager) {
    this.frontEndModelManager = frontEndModelManager;
  }

  /**
   * loads the list of trainers
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
  public ObservableList<Account> getTrainers(){
    return trainers;
  }

  /**
   * loads a list of time slots.
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
   * Returns the time list
   * @return timeList
   */
  public ObservableList<String> getTimeSlots(){
    return timeList;
  }

  /**
   * Returns the frontEndModelManager
   * @return frontEndModelManager
   */
  public FrontEndModelManager getFrontEndModelManager() {
    return frontEndModelManager;
  }

  /**
   * A property change event
   * @param evt the event
   */
  @Override
  public void propertyChange(PropertyChangeEvent evt) {

  }

  /**
   * Verifies the availability of the session.
   * @param session the session
   * @return boolean
   */
  public boolean verifyAvailabilityOfSession(TrainingSession session) {
    try {
      return frontEndModelManager.verifyAvailabilityOfSession(session);
    } catch (RemoteException e) {
      throw new RuntimeException("Something went wrong");
    }
  }

  /**
   * Adds the training session.
   * @param session the session
   */
  public void addSession(TrainingSession session) {
    frontEndModelManager.addSession(session);
  }
}
