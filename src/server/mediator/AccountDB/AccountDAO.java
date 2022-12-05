package server.mediator.AccountDB;



public interface AccountDAO
{

  String addAccount(String firstname, String lastname, String email, String phonenumber, int account_type, String username, String password);
}

