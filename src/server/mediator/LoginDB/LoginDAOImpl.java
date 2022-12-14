package server.mediator.LoginDB;

import org.postgresql.util.PSQLException;
import server.mediator.ConnectionDB;
import shared.sharedObjects.Account;

import java.sql.*;

import static java.sql.DriverManager.getConnection;

/**
 * Implementation of Data Access Object interface handling products.
 * It is created following the Singleton Design Pattern
 * @author Group 7
 * @version 1.0
 */

public class LoginDAOImpl implements LoginDAO
{
  /**
   * Login method for the database
   * @throws SQLException
   */
  public LoginDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  /**
   * Validates the username and password in the database.
   * @param username The username provided by the user
   * @param password The password provided by the user
   * @return null
   * @throws SQLException
   */
  @Override public Account login(String username, String password)
      throws SQLException
  {
    try (Connection connection = ConnectionDB.getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM account WHERE username=? and password=?;");
      statement.setString(1, username);
      statement.setString(2, password);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next())
      {
        String firstname = resultSet.getString("firstname");
        String lastname = resultSet.getString("lastname");
        String email = resultSet.getString("email");
        String phonenumber = resultSet.getString("phonenumber");
        int type = resultSet.getInt("account_type");
        String login = resultSet.getString("username");
        String pass = resultSet.getString("password");

        connection.close();
        return new Account(type, firstname, lastname, email, phonenumber, login, pass);
      }
      else
      {
        connection.close();
        return null;
      }
    }
    catch (PSQLException e)
    {
      throw  new RuntimeException(e);
    }
  }
}
