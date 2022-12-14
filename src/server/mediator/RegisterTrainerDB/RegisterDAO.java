package server.mediator.RegisterTrainerDB;

import shared.sharedObjects.Account;
/**
 * An interface containing the method to add a trainer account to the database.
 * @author Group 7
 */
public interface RegisterDAO
{
  void addAccount(Account account);
}
