package server.mediator.TrainingSessionDAO;

import server.mediator.ConnectionDB;
import shared.sharedObjects.Account;
import shared.sharedObjects.TrainingSession;
import shared.sharedObjects.TrainingSessionList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;


public class TrainingSessionDAOImpl implements TrainingSessionDAO {
    /**
     * A function that creates a new training session
     *
     * @param session
     * @throws SQLException
     */
    @Override
    public void create(TrainingSession session) throws SQLException {
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * from trainingsession where date=? and time =?;");
            statement.setString(1, session.getDate().toString());
            statement.setString(2, session.getTime());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                connection.close();
            } else {
                int account_id = 0;
                PreparedStatement statement3 = connection.prepareStatement("SELECT * from account where username=? and password=?;");
                statement3.setString(1, session.getTrainerUsername());
                statement3.setString(2, session.getTrainerPassword());
                ResultSet resultSetTrainer = statement3.executeQuery();
                if (resultSetTrainer.next()) {
                    account_id = resultSetTrainer.getInt("account_id");

                    PreparedStatement statement2 = connection.prepareStatement("INSERT INTO trainingsession(\"type\", \"time\", \"date\",\"trainer_id\",\"capacity\") VALUES (?,?,?,?,?);");
                    statement2.setString(1, session.getType());
                    statement2.setString(2, session.getTime());
                    statement2.setString(3, session.getDate().toString());
                    statement2.setInt(4, account_id);
                    statement2.setInt(5, session.getParticipants());


                    statement2.executeUpdate();
                    connection.close();
                } else {
                    connection.close();
                }
            }
        } catch (SQLException throwable) {

            throw new RuntimeException(throwable);
        }
    }

    @Override
    public TrainingSessionList getListOfSessionsBookedByMember(Account account) {
        TrainingSessionList temp = new TrainingSessionList();
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from TrainingSession where session_id in (select session_id from BookedSession where account_id in (select account_id from Account where username = ? and password = ?));");
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            String type;
            String time;
            int participants = 0;
            Account trainer;
            LocalDate date;
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                type = resultSet.getString("type");
                time = resultSet.getString("time");
                participants = resultSet.getInt("capacity");
                date = LocalDate.parse(resultSet.getString("date"));
                PreparedStatement statement2 = connection.prepareStatement("Select * from account where account_id = ?;");
                statement2.setInt(1, resultSet.getInt("trainer_id"));
                ResultSet resultSet1 = statement2.executeQuery();
                if (resultSet1.next())
                {
                    trainer = new Account(resultSet1.getInt("account_type"), resultSet1.getString("firstname"), resultSet1.getString("lastname"), resultSet1.getString("email"), resultSet1.getString("phonenumber"), resultSet1.getString("username"), resultSet1.getString("password"));
                    temp.addSession(new TrainingSession(type, time, participants, trainer, date));
                }
                else
                {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return temp;
    }

    @Override
    public TrainingSessionList getListOfAllSessions() {
        TrainingSessionList temp = new TrainingSessionList();
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from trainingsession");
            ResultSet resultSet = preparedStatement.executeQuery();

            String type;
            String time;
            int participants = 0;
            Account trainer;
            LocalDate date;

            while (resultSet.next())
            {
                type = resultSet.getString("type");
                time = resultSet.getString("time");
                participants = resultSet.getInt("capacity");
                date = LocalDate.parse(resultSet.getString("date"));
                PreparedStatement statement2 = connection.prepareStatement("Select * from account where account_id = ?;");
                statement2.setInt(1, resultSet.getInt("trainer_id"));
                ResultSet resultSet1 = statement2.executeQuery();
                if (resultSet1.next())
                {
                    trainer = new Account(resultSet1.getInt("account_type"), resultSet1.getString("firstname"), resultSet1.getString("lastname"), resultSet1.getString("email"), resultSet1.getString("phonenumber"), resultSet1.getString("username"), resultSet1.getString("password"));
                    temp.addSession(new TrainingSession(type, time, participants, trainer, date));
                }
                else
                {
                    connection.close();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return temp;
    }
}
