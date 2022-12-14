package server.mediator.TrainingSessionDAO;

import shared.sharedObjects.Account;
import shared.sharedObjects.TrainingSession;
import shared.sharedObjects.TrainingSessionList;

import java.sql.SQLException;
import java.time.LocalDate;

/**
 * An interface containing the methods for training sessions.
 * @author Group 7
 */
public interface TrainingSessionDAO
{
  void create(TrainingSession session) throws SQLException;
  TrainingSessionList getListOfSessionsBookedByMember(Account account);
  TrainingSessionList getListOfAllSessions();
  TrainingSession addParticipant(Account account, TrainingSession trainingSession);
  boolean isMemberInSession(Account account, TrainingSession trainingSession);
  void removeSession(Account account, TrainingSession trainingSession);
  TrainingSessionList getSessionsForManager(LocalDate date);
  void updateSession(TrainingSession session);
  void deleteSession(TrainingSession session);
  TrainingSession unassignTrainer(TrainingSession session);
}
