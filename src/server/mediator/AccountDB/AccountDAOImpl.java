package server.mediator.AccountDB;

import server.mediator.ConnectionDB;
import shared.sharedObjects.Account;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author dianasuzana
 * @version 1.0
 */
public class AccountDAOImpl implements AccountDAO
{

  /**
   * A constructor that will initialize the with the db driver
   */
  public AccountDAOImpl()
  {
    try {
      DriverManager.registerDriver(new org.postgresql.Driver());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override public void addAccount(Account account)
  {
    try (Connection connection = ConnectionDB.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("SELECT * from account where username=?;");
      statement.setString(1, account.getUsername());
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        connection.close();

      } else {
        PreparedStatement statement2 = connection.prepareStatement("INSERT INTO account(\"firstname\", \"lastname\", \"email\", \"phonenumber\", \"account_type\", \"username\", \"password\") VALUES (?,?,?,?,?,?,?);");
        statement2.setString(1, account.getFName());
        statement2.setString(2, account.getLName());
        statement2.setString(3, account.getEmail());
        statement2.setString(4, account.getPhoneNumber());
        statement2.setInt(5, 1);
        statement2.setString(6, account.getUsername());
        statement2.setString(7, account.getPassword());
        statement2.executeUpdate();
        connection.close();


      }
    } catch (SQLException throwable) {

      throw new RuntimeException(throwable);
  }
}

  @Override
  public ArrayList<Account> getTrainers() {
    ArrayList<Account> temp = new ArrayList<>();
    try (Connection connection = ConnectionDB.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("SELECT * from account where account_type = 3;");
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next())
      {
        String firstname = resultSet.getString("firstname");
        String lastname = resultSet.getString("lastname");
        String email = resultSet.getString("email");
        String phonenumber = resultSet.getString("phonenumber");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        temp.add(new Account(3, firstname, lastname, email, phonenumber, username, password));
      }
      return temp;

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

}
