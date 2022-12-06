package server.mediator;

import java.sql.*;

public class ConnectionDB {


  private ConnectionDB() throws SQLException {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static Connection getConnection() throws SQLException {


    return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres","JasperMond@y1"  );

  }

}
