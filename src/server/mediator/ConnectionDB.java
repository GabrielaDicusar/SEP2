package server.mediator;

import java.sql.*;

/**
 * A class for establishing a connection to the database.
 */
public class ConnectionDB {

  /**
   * Private constructor instantiating the postgres driver.
   * @throws SQLException
   */
  private ConnectionDB() throws SQLException {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  /**
   * Returns the connection url address.
   * @return the database connection url
   * @throws SQLException
   */
  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres","gabriela13"  );

  }

}
