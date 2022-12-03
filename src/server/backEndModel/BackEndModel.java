package server.backEndModel;

import shared.sharedObjects.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * A class containing the methods for the BackEndModel, whilst implementing BackEndModelManager.
 * @author
 */

public class BackEndModel implements BackEndModelManager
{
    private PropertyChangeSupport support;
    private TrainingSessionList listOfSessions;
    private AccountList listOfAccount;

    /**
     * BackEndModel constructor to instantiate support, listOfAccount, listOfSessions.
     */
    public BackEndModel()
    {
        support = new PropertyChangeSupport(this);
        listOfAccount = new AccountList();
        listOfSessions = new TrainingSessionList();
        listOfAccount.addAccount(new Account(new LoginCredentials("member", "member"), 1, "Lukasz", "luskk@vestas.com", "52683345", "Kollegievenget 1"));
        listOfAccount.addAccount(new Account(new LoginCredentials("member1", "member1"), 1, "Adam", "luskk@vestas.com", "52683345", "Kollegievenget 1"));
        listOfAccount.addAccount(new Account(new LoginCredentials("manager", "manager"), 2, "Diana", "luskk@vestas.com", "52683345", "Kollegievenget 1"));
        listOfAccount.addAccount(new Account(new LoginCredentials("trainer", "trainer"), 3, "Chris", "luskk@vestas.com", "52683345", "Kollegievenget 1"));
        listOfAccount.addAccount(new Account(new LoginCredentials("trainer", "trainer"), 3, "Gabriela", "luskk@vestas.com", "52683345", "Kollegievenget 1"));
        listOfSessions.addSession(new TrainingSession("Yoga", LocalTime.of(9,0), 15, listOfAccount.getAccount(new LoginCredentials("trainer", "trainer")), LocalDate.now()));
        listOfSessions.addSession(new TrainingSession("Fitness", LocalTime.of(10,0), 10, listOfAccount.getAccount(new LoginCredentials("manager", "manager")), LocalDate.now()));
    }

    public TrainingSessionList getListOfSessions() {
        return listOfSessions;
    }

    @Override public int verifyLogin(LoginCredentials loginCredentials)
    {
        return listOfAccount.getAccountType(loginCredentials);
    }

    @Override
    public void addSession(TrainingSession session) {
        listOfSessions.addSession(session);
        System.out.println(listOfSessions.getTrainingSessions().size());
        support.firePropertyChange("SessionAdded", null, session);
    }

    public void addParticipant(LoginCredentials loginCredentials, TrainingSession trainingSession)
    {
        TrainingSession temp = listOfSessions.addParticipant(loginCredentials, trainingSession);
        support.firePropertyChange("ParticipantAdded", trainingSession, temp);
    }
    @Override
    public ArrayList getTrainersList(){
        ArrayList trainers = new ArrayList();

        for (int i = 0; i < listOfAccount.size(); i++) {
            if(listOfAccount.getAccount(i).getAccountType() == 3){
                trainers.add(listOfAccount.getAccount(i));
            }
        }
        return trainers;
    }

    @Override
    public boolean verifyAvailabilityOfSession(TrainingSession session) {
        for (int i = 0; i < listOfSessions.size(); i++) {
            if(session.getDate().isBefore(LocalDate.now())){
                return false;
            }
        }
        return true;
    }

    @Override
    public TrainingSessionList getListOfSessionsAvailableForMember(LoginCredentials loginCredentials) {
        TrainingSessionList temp = new TrainingSessionList();
        for (int i = 0; i < listOfSessions.size(); i++)
        {
            if (listOfSessions.getTrainingSessionByIndex(i).getAccount(loginCredentials) == null && listOfSessions.getTrainingSessionByIndex(i).getParticipants() != 0)
            {
                temp.addSession(listOfSessions.getTrainingSessionByIndex(i));
            }
        }
        return temp;
    }

    @Override public void addListener(String eventName,
        PropertyChangeListener listener)
    {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override public void removeListener(String eventName,
        PropertyChangeListener listener)
    {
        support.removePropertyChangeListener(eventName, listener);
    }
}



