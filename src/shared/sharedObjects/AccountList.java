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
    public Account getAccount(String username, String password)
    {
        for (Account item : accounts)
        {
            if (item.getUsername().equals(username) && item.getPassword().equals(password))
            {
                return item;
            }
        }
        return null;
    }

    public int getAccountType(Account account){
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).equals(account)){
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
