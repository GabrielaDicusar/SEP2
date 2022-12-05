package server.mediator.AccountDB;

import server.mediator.ConnectionDB;


import java.sql.*;

public class AccountDAOImpl implements AccountDAO
{
  public AccountDAOImpl()
  {
    try {
      DriverManager.registerDriver(new org.postgresql.Driver());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  @Override public String addAccount(String username, String password,
      String firstname, String lastname, String email, String phonenumber)
  {
    try (Connection connection = ConnectionDB.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("SELECT * from \"Accounts\" where \"username\"=?;");
      statement.setString(1, username);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        connection.close();
        return "Username is already taken";
      } else {
        PreparedStatement statement2 = connection.prepareStatement("INSERT INTO \"Accounts\"(\"username\", \"password\", \"firstname\", \"lastname\", \"email\", \"phonenumber\") VALUES (?,?,?,?,?,?);");
        statement2.setString(1, username);
        statement2.setString(2, password);
        statement2.setString(3, firstname);
        statement2.setString(4, lastname);
        statement2.setString(5, email);
        statement2.setString(6, phonenumber);
        statement2.executeUpdate();
        connection.close();

        return "User created successfully";
      }
    } catch (SQLException throwable) {

      return throwable.getMessage();
  }
}

}
