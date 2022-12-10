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
import java.time.format.DateTimeFormatter;


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
        for (int i = 0; i < temp.size(); i++)
        {
            if (temp.getTrainingSessionByIndex(i).getDate().isBefore(LocalDate.now()))
            {
                temp.removeTrainingSession(temp.getTrainingSessionByIndex(i));
            }
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
    public TrainingSession addParticipant
            (Account account, TrainingSession trainingSession)
    {
        TrainingSession temp = new TrainingSession
                (null, null, 0, null, null);
        try (Connection connection = ConnectionDB.getConnection())
        {
            PreparedStatement insert = connection.prepareStatement
                    ("insert into BookedSession (session_id, account_id) values (?, ?);");
            PreparedStatement sessionId = connection.prepareStatement
                    ("select session_id from TrainingSession where time = ? and date = ?;");
            sessionId.setString(1, trainingSession.getTime());
            sessionId.setString(2, trainingSession.getDate().toString());

            PreparedStatement accountId = connection.prepareStatement
                    ("select account_id from Account where username = ? and password = ?;");
            accountId.setString(1, account.getUsername());
            accountId.setString(2, account.getPassword());

            ResultSet resultSessionId = sessionId.executeQuery();
            ResultSet resultAccountId = accountId.executeQuery();

            PreparedStatement isAccountThere = connection.prepareStatement
                    ("select * from BookedSession where account_id = ? and session_id = ?;");

                if (resultAccountId.next() && resultSessionId.next())
                {
                    isAccountThere.setInt(1 ,resultAccountId.getInt(1));
                    isAccountThere.setInt(2 ,resultSessionId.getInt(1));
                    ResultSet checkIF = isAccountThere.executeQuery();
                    if (checkIF.next()) {
                        connection.close();
                    }
                    else
                    {
                        insert.setInt(1, resultSessionId.getInt(1));
                        insert.setInt(2, resultAccountId.getInt(1));
                    }
                }
                insert.executeUpdate();

                PreparedStatement capacity = connection.prepareStatement
                        ("update TrainingSession " +
                        "set capacity = capacity-1 " +
                        "where session_id in " +
                        "(select session_id from BookedSession where account_id = ? " +
                        "and BookedSession.session_id = ?)");
                capacity.setInt(1, resultAccountId.getInt(1));
                capacity.setInt(2, resultSessionId.getInt(1));

                capacity.executeUpdate();

                PreparedStatement returnTraining = connection.prepareStatement
                        ("select * from TrainingSession where time = ? and date = ?;");
                returnTraining.setString(1, trainingSession.getTime());
                returnTraining.setString(2, trainingSession.getDate().toString());
                ResultSet resultTraining = returnTraining.executeQuery();
                if (resultTraining.next())
                {
                    String type = resultTraining.getString("type");
                    String time = resultTraining.getString("time");
                    int participants = resultTraining.getInt("capacity");
                    LocalDate date = LocalDate.parse(resultTraining.getString("date"));
                    Account trainer;

                    PreparedStatement getTrainer = connection.prepareStatement
                            ("Select * from account where account_id = ?;");
                    getTrainer.setInt(1, resultTraining.getInt("trainer_id"));
                    ResultSet resultTrainer = getTrainer.executeQuery();
                    if (resultTrainer.next())
                    {
                        trainer = new Account(resultTrainer.getInt("account_type"),
                                resultTrainer.getString("firstname"),
                                resultTrainer.getString("lastname"),
                                resultTrainer.getString("email"),
                                resultTrainer.getString("phonenumber"),
                                resultTrainer.getString("username"),
                                resultTrainer.getString("password"));
                        temp = new TrainingSession(type, time, participants, trainer, date);
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
    public boolean isMemberInSession(Account account, TrainingSession trainingSession)
    {
        int account_id = 0;
        int session_id = 0;
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement getMember = connection.prepareStatement("Select account_id from account where username = ? and password = ?;");
            getMember.setString(1, account.getUsername());
            getMember.setString(2, account.getPassword());

            ResultSet resultGetMember = getMember.executeQuery();

            if (resultGetMember.next())
            {
                account_id = resultGetMember.getInt(1);
            }

            PreparedStatement getSessionId = connection.prepareStatement("Select session_id from trainingsession where time = ? and date = ?;");
            getSessionId.setString(1, trainingSession.getTime());
            getSessionId.setString(2, trainingSession.getDate().toString());

            ResultSet resultGetSessionId = getSessionId.executeQuery();
            if(resultGetSessionId.next())
            {
                session_id = resultGetSessionId.getInt(1);
            }

            PreparedStatement isMemberInSession = connection.prepareStatement("SELECT * from bookedsession where account_id = ? and session_id = ?;");
            isMemberInSession.setInt(1, account_id);
            isMemberInSession.setInt(2, session_id);

            ResultSet isMember = isMemberInSession.executeQuery();

            if (isMember.next())
            {
                return true;
            }
            else
            {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public TrainingSessionList getSessionsForManager(LocalDate date)
    {
        TrainingSessionList temp = getListOfAllSessions();
        TrainingSessionList removed = getListOfAllSessions();

        int size = temp.size();
        for (TrainingSession item : temp.getTrainingSessions())
        {
           if (item.getDate().isBefore(date) || item.getDate().isAfter(date))
           {
               removed.removeTrainingSession(item);
           }
        }
        return removed;
    }

    @Override
    public void updateSession(TrainingSession session) {
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement updateSession = connection.prepareStatement("update trainingsession " +
                    "set type = ?, trainer_id = ?, capacity = ? " +
                    "where date = ? and time = ?;");
            updateSession.setString(1, session.getType());

            PreparedStatement getTrainer = connection.prepareStatement("select account_id from account where username = ? and password = ?;");
            getTrainer.setString(1, session.getTrainerAccount().getUsername());
            getTrainer.setString(2, session.getTrainerAccount().getPassword());
            ResultSet trainer = getTrainer.executeQuery();
            if (trainer.next())
            {
                updateSession.setInt(2, trainer.getInt(1));
            }
            else
            {
                connection.close();
            }
            updateSession.setInt(3, session.getParticipants());
            updateSession.setString(4, session.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-d")));
            updateSession.setString(5, session.getTime());
            updateSession.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteSession(TrainingSession session) {
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement updateSession = connection.prepareStatement("delete from trainingsession where time = ? and date = ?;");
            updateSession.setString(1, session.getTime());
            updateSession.setString(2, session.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-d")));
            updateSession.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        @Override
    public void removeSession(Account account, TrainingSession trainingSession) {
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement deleteSession = connection.prepareStatement("delete from BookedSession where session_id = ? and account_id = ?;");
            PreparedStatement sessionId = connection.prepareStatement
                    ("select session_id from TrainingSession where time = ? and date = ?;");
            sessionId.setString(1, trainingSession.getTime());
            sessionId.setString(2, trainingSession.getDate().toString());

            PreparedStatement accountId = connection.prepareStatement
                    ("select account_id from Account where username = ? and password = ?;");
            accountId.setString(1, account.getUsername());
            accountId.setString(2, account.getPassword());

            ResultSet resultSessionId = sessionId.executeQuery();
            ResultSet resultAccountId = accountId.executeQuery();
            if (resultSessionId.next() && resultAccountId.next())
            {
                deleteSession.setInt(1, resultSessionId.getInt(1));
                deleteSession.setInt(2, resultAccountId.getInt(1));
            }
            deleteSession.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
