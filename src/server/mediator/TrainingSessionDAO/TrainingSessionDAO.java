package server.mediator.TrainingSessionDAO;

import shared.sharedObjects.Account;
import shared.sharedObjects.TrainingSession;
import shared.sharedObjects.TrainingSessionList;

import java.sql.SQLException;
import java.time.LocalDate;

public interface TrainingSessionDAO
{
  void create(TrainingSession session) throws SQLException;
  TrainingSessionList getListOfSessionsBookedByMember(Account account);
}
