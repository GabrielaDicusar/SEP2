package server.mediator.TrainingSessionDAO;

import server.mediator.ConnectionDB;
import shared.sharedObjects.TrainingSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class TrainingSessionDAOImpl implements TrainingSessionDAO
{
  /**
   * A function that creates a new training session
   * @param session
   * @throws SQLException
   */
  @Override public void create(TrainingSession session) throws SQLException
  {
    try (Connection connection = ConnectionDB.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO \"TrainingSessions\"(\"Session_type\" ,\"Session_trainer\")VALUES(?,?);");

      statement.setString(1, session.getType());
      statement.setString(2, session.getTrainer());

      statement.executeUpdate();
  }
}
}
