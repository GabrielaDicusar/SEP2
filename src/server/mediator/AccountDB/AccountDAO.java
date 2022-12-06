package server.mediator.AccountDB;

import shared.sharedObjects.Account;

import java.util.ArrayList;

public interface AccountDAO
{
  void addAccount(Account account);
  ArrayList<Account> getTrainers();
}

