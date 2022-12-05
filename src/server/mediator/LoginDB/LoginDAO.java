package server.mediator.LoginDB;

import shared.sharedObjects.Account;

import java.sql.SQLException;

/**
 * Interface for Database Access Object accessing Accounts
 * @author dianasuzana
 * @version 1.0
 */
public interface LoginDAO
{
  /**
   * Connects to the database and tries to log in with the passed credentials.
   * @param username The username provided by the user
   * @param password The password provided by the user
   * @throws SQLException if something is wrong with the database
   * @return
   */
  Account login(String username, String password) throws SQLException;
}
