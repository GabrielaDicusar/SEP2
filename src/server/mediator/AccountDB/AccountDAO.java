package server.mediator.AccountDB;



public interface AccountDAO
{

  String addAccount(String username, String password, String firstname,
      String lastname, String email, String phonenumber);
}

