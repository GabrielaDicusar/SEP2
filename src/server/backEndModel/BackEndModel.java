package server.backEndModel;

import server.mediator.AccountDB.AccountDAO;
import server.mediator.AccountDB.AccountDAOImpl;
import server.mediator.LoginDB.LoginDAO;
import server.mediator.LoginDB.LoginDAOImpl;
import server.mediator.RegisterTrainerDB.RegisterDAO;
import server.mediator.RegisterTrainerDB.RegisterDAOImpl;
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
    private AccountDAO accountDAO;
    private RegisterDAO registerDAO;

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
        accountDAO=new AccountDAOImpl();
        registerDAO = new RegisterDAOImpl();
        listOfAccount.addAccount(new Account(3, "Chris", "Hunt", "@", "2323", "asda", "sada"));
        listOfSessions.addSession(new TrainingSession("Yoga", "12:00", 2, new Account(3, "Chris", "Hunt", "@", "2323", "asda", "sada"), LocalDate.now()));
    }

    /**
     * Returns the list of sessions
     * @return list of sessions
     */
    public TrainingSessionList getListOfSessions() {
        return listOfSessions;
    }

    /**
     * Verifies the account login username and password.
     * @param account the account
     * @return the account password
     */
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

    /**
     * Adds the training session to the database.
     * @param session the training session
     */
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

    /**
     * Adds the participant to the database.
     * @param account the account
     * @param trainingSession the training session
     */
    public void addParticipant(Account account, TrainingSession trainingSession)
    {
        TrainingSession temp = trainingSessionDAO.addParticipant(account, trainingSession);
        support.firePropertyChange("ParticipantAdded", trainingSession, temp);
    }

    /**
     * Returns the account from the database.
     * @return account
     */
    @Override
    public ArrayList getTrainersList(){
        return accountDAO.getTrainers();
    }

    @Override public void createAccount(Account account)
    {
        if(account.getAccountType() == 1)
        {
            accountDAO.addAccount(account);
        }
        else
        {
            registerDAO.addAccount(account);
        }
    }

    /**
     * verifies the availability of the training session.
     * @param session the session
     * @return boolean
     */
    @Override
    public boolean verifyAvailabilityOfSession(TrainingSession session) {
        TrainingSessionList temp = trainingSessionDAO.getListOfAllSessions();
        for (int i = 0; i < temp.size(); i++) {
            if(session.getDate().isBefore(LocalDate.now())){
                return false;
            }
            else if (session.getTime().equals(temp.getTrainingSessionByIndex(i).getTime()))
            {
                if(session.getDate().equals(temp.getTrainingSessionByIndex(i).getDate()))
                {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the avaialble sessions for a specific member.
     * @param account the account
     * @param date the date
     * @return temp
     */
    @Override
    public TrainingSessionList getListOfSessionsAvailableForMember(Account account, LocalDate date) {
        TrainingSessionList temp1 = trainingSessionDAO.getListOfAllSessions();
        TrainingSessionList temp2 = trainingSessionDAO.getListOfAllSessions();
        for (int i = 0; i < trainingSessionDAO.getListOfSessionsBookedByMember(account).size(); i++) {
            if(temp1.contain(trainingSessionDAO.getListOfSessionsBookedByMember(account).getTrainingSessionByIndex(i))){
                temp2.remove(trainingSessionDAO.getListOfSessionsBookedByMember(account).getTrainingSessionByIndex(i));
            }
        }
        for (int i = 0; i < temp1.size(); i++) {
            if(!temp1.getTrainingSessionByIndex(i).getDate().equals(date)){
                temp2.removeTrainingSession(temp1.getTrainingSessionByIndex(i));
            }
        }
        return temp2;
    }
    @Override
    public boolean isMemberInSession(Account account, TrainingSession trainingSession)
    {
        return trainingSessionDAO.isMemberInSession(account, trainingSession);
    }

    @Override
    public void removeSession(Account account, TrainingSession trainingSession) {
        trainingSessionDAO.removeSession(account, trainingSession);
    }

    @Override
    public TrainingSessionList getSessionsForManager(LocalDate parse) {
        return trainingSessionDAO.getSessionsForManager(parse);
    }

    @Override
    public void updateSession(TrainingSession session) {
        System.out.println("Back Model update session " + session.toString());
        trainingSessionDAO.updateSession(session);
        support.firePropertyChange("UpdateSession", null, session);
    }

    @Override
    public void deleteSession(TrainingSession session) {
        trainingSessionDAO.deleteSession(session);
        support.firePropertyChange("SessionDeleted", null, session);
    }

    @Override
    public void unassignTrainer(TrainingSession session) {
//        trainingSessionDAO.unassignTrainer(session);
        support.firePropertyChange("UnassignedTrainer", null,trainingSessionDAO.unassignTrainer(session));
        System.out.println(trainingSessionDAO.unassignTrainer(session));
        System.out.println("backModel fired event unassigned with new value");
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

    @Override
    public TrainingSessionList getTrainingSessionsForTrainer(Account account, LocalDate date) {
            TrainingSessionList temp2 = new TrainingSessionList();
            for (int i = 0; i < trainingSessionDAO.getListOfAllSessions().size(); i++) {
                if(trainingSessionDAO.getListOfAllSessions().getTrainingSessionByIndex(i).getDate().equals(date) &&
                        !trainingSessionDAO.getListOfAllSessions().getTrainingSessionByIndex(i).getDate().isBefore(LocalDate.now()) &&
                        trainingSessionDAO.getListOfAllSessions().getTrainingSessionByIndex(i).getTrainerAccount().equals(account)){
                    temp2.addSession(trainingSessionDAO.getListOfAllSessions().getTrainingSessionByIndex(i));
                }
            }
            return temp2;
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



