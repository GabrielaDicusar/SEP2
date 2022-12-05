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
        listOfAccount.addAccount(new Account(1, "Adam", "@", "55", "sm", "member", "member"));
        listOfAccount.addAccount(new Account(1, "Chris", "@", "55", "sm", "member1", "member1"));
        listOfAccount.addAccount(new Account(2, "Diana", "@", "55", "sm", "manager", "manager"));
        listOfAccount.addAccount(new Account(3, "Lukasz", "@", "55", "sm", "trainer", "trainer"));
        listOfAccount.addAccount(new Account(3, "Gabriela", "@", "55", "sm", "trainer1", "trainer1"));
        listOfSessions.addSession(new TrainingSession("Yoga", LocalTime.of(9,0), 15, listOfAccount.getAccount("trainer", "trainer"), LocalDate.now()));
        listOfSessions.addSession(new TrainingSession("Fitness", LocalTime.of(10,0), 10, listOfAccount.getAccount("trainer1", "trainer1"), LocalDate.now()));
    }

    public TrainingSessionList getListOfSessions() {
        return listOfSessions;
    }

    @Override public Account verifyLogin(Account account)
    {
        return listOfAccount.getAccount(account.getUsername(), account.getPassword());
    }

    @Override
    public void addSession(TrainingSession session) {
        listOfSessions.addSession(session);
        System.out.println(listOfSessions.getTrainingSessions().size());
        support.firePropertyChange("SessionAdded", null, session);
    }

    public void addParticipant(Account account, TrainingSession trainingSession)
    {
        TrainingSession temp = listOfSessions.addParticipant(account, trainingSession);
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
    public TrainingSessionList getListOfSessionsAvailableForMember(Account account) {
        TrainingSessionList temp = new TrainingSessionList();
        for (int i = 0; i < listOfSessions.size(); i++)
        {
            if (listOfSessions.getTrainingSessionByIndex(i).getAssignedMembers().size() == 0)
            {
                temp.addSession(listOfSessions.getTrainingSessionByIndex(i));
            }
            else {
                if (!listOfSessions.getTrainingSessionByIndex(i).getAssignedMembers().contains(account)
                        && listOfSessions.getTrainingSessionByIndex(i).getParticipants() != 0)
                {
                    temp.addSession(listOfSessions.getTrainingSessionByIndex(i));
                }
            }
        }
        return temp;
    }
    @Override
    public TrainingSessionList getListOfSessionsBookedByMember(Account account) {
        TrainingSessionList temp = new TrainingSessionList();
        for (int i = 0; i < listOfSessions.size(); i++)
        {
            if (listOfSessions.getTrainingSessionByIndex(i).getAssignedMembers().contains(account))
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



