package shared.sharedObjects;

import java.util.ArrayList;

public class AccountList {
    private ArrayList<Account> accounts;

    public AccountList(){
        accounts = new ArrayList();
    }

    public void addAccount(Account a){
        accounts.add(a);
    }

    public void removeAccount(Account a){
        //
    }
    public Account getAccount(LoginCredentials loginCredentials)
    {
        for (Account account : accounts)
        {
            if (account.getLoginCredentials().equals(loginCredentials))
            {
                return account;
            }
        }
        return null;
    }

    public int getAccountType(LoginCredentials lc){
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getLoginCredentials().equals(lc)){
                return accounts.get(i).getAccountType();
            }
        }
        return -1;
    }

    public int size(){
        return accounts.size();
    }

    public Account getAccount(int i){
         return accounts.get(i);
    }
}
