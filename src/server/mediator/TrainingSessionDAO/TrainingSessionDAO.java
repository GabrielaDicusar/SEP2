package server.mediator.TrainingSessionDAO;

import shared.sharedObjects.TrainingSession;

import java.sql.SQLException;

public interface TrainingSessionDAO
{
  void create(TrainingSession session) throws SQLException;
}
