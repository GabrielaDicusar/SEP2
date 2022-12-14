package server.mediator.RegisterTrainerDB;

import server.mediator.ConnectionDB;
import shared.sharedObjects.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * A class containing the methods for registering a trainer in the database.
 * @author Group 7
 */
public class RegisterDAOImpl implements RegisterDAO
{
  /**
   * Adds the trainer account to the database.
   * @param account the trainer account
   */
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
        statement2.setInt(5, 3);
        statement2.setString(6, account.getUsername());
        statement2.setString(7, account.getPassword());
        statement2.executeUpdate();
        connection.close();


      }
    } catch (SQLException throwable) {

      throw new RuntimeException(throwable);
    }
  }
}
