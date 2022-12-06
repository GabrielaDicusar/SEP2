package server.mediator.LoginDB;

import org.postgresql.util.PSQLException;
import server.mediator.ConnectionDB;
import shared.sharedObjects.Account;

import java.sql.*;

import static java.sql.DriverManager.getConnection;

/**
 * Implementation of Data Access Object interface handling products.
 * It is created following the Singleton Design Pattern
 * @author dianasuzana
 * @version 1.0
 */

public class LoginDAOImpl implements LoginDAO
{

  public LoginDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }


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
