package server.mediator.AccountDB;

import server.mediator.ConnectionDB;


import java.sql.*;
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

  @Override public String addAccount(String firstname, String lastname, String email, String phonenumber, int account_type, String username, String password)
  {
    try (Connection connection = ConnectionDB.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("SELECT * from sep2schema.account where username=?;");
      statement.setString(1, username);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        connection.close();
        return "Username is already taken";
      } else {
        PreparedStatement statement2 = connection.prepareStatement("INSERT INTO sep2schema.account(\"firstname\", \"lastname\", \"email\", \"phonenumber\", \"account_type\", \"username\", \"password\") VALUES (?,?,?,?,?,?,?);");
        statement2.setString(1, firstname);
        statement2.setString(2, lastname);
        statement2.setString(3, email);
        statement2.setString(4, phonenumber);
        statement2.setInt(5, account_type);
        statement2.setString(5, username);
        statement2.setString(6, password);
        statement2.executeUpdate();
        connection.close();

        return "User created successfully";
      }
    } catch (SQLException throwable) {

      return throwable.getMessage();
  }
}

}
