package server.backEndModel;

import server.mediator.LoginDB.LoginDAO;
import server.mediator.LoginDB.LoginDAOImpl;
import server.mediator.TrainingSessionDAO.TrainingSessionDAO;
import server.mediator.TrainingSessionDAO.TrainingSessionDAOImpl;
import shared.sharedObjects.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * A class containing the methods for the BackEndModel, whilst implementing BackEndModelManager.
 * @author
 */

public class BackEndModel implements BackEndModelManager
{
    private PropertyChangeSupport support;
    private TrainingSessionList listOfSessions;
    private AccountList listOfAccount;
    private LoginDAO loginDAO;
    private TrainingSessionDAO trainingSessionDAO;

    /**
     * BackEndModel constructor to instantiate support, listOfAccount, listOfSessions.
     */
    public BackEndModel() throws SQLException
    {
        support = new PropertyChangeSupport(this);
        listOfAccount = new AccountList();
        listOfSessions = new TrainingSessionList();
        loginDAO = new LoginDAOImpl();
        trainingSessionDAO = new TrainingSessionDAOImpl();
        listOfAccount.addAccount(new Account(3, "Chris", "Hunt", "@", "2323", "asda", "sada"));
        listOfSessions.addSession(new TrainingSession("Yoga", "12:00", 2, new Account(3, "Chris", "Hunt", "@", "2323", "asda", "sada"), LocalDate.now()));
    }

    public TrainingSessionList getListOfSessions() {
        return listOfSessions;
    }

    @Override public Account verifyLogin(Account account)
    {
       try {
           return loginDAO.login(account.getUsername(), account.getPassword());
       }
       catch (SQLException e)
       {
           e.printStackTrace();
       }
       return null;
    }

    @Override
    public void addSession(TrainingSession session) {
        try {
            trainingSessionDAO.create(session);
            System.out.println(listOfSessions.getTrainingSessions().size());
            support.firePropertyChange("SessionAdded", null, session);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
    public TrainingSessionList getListOfSessionsAvailableForMember(Account account, LocalDate date) {
        TrainingSessionList temp = new TrainingSessionList();
        for (int i = 0; i < listOfSessions.size(); i++)
        {
            if (listOfSessions.getTrainingSessionByIndex(i).getDate().equals(date))
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
        }
        return temp;
    }
    @Override
    public TrainingSessionList getListOfSessionsBookedByMember(Account account) {
        TrainingSessionList temp = trainingSessionDAO.getListOfSessionsBookedByMember(account);
        for (int i = 0; i < temp.size(); i++)
        {
            System.out.println(temp.getTrainingSessions().get(i));
        }
        return temp;
    }

    @Override public void addListener(String eventName, PropertyChangeListener listener)
    {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override public void removeListener(String eventName, PropertyChangeListener listener)
    {
        support.removePropertyChangeListener(eventName, listener);
    }
}



