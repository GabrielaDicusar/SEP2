package server.mediator.AccountDB;

import shared.sharedObjects.Account;

import java.util.ArrayList;
/**
 * A class containing an execution method for ServerImpl.
 * @author Group 7
 */
public interface AccountDAO
{
  void addAccount(Account account);
  ArrayList<Account> getTrainers();
}

