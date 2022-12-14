package shared.sharedObjects;

import java.util.ArrayList;

/** A class handling the arraylist methods for the Account Class.
 * @author Group 7
 */
public class AccountList {
    private ArrayList<Account> accounts;

    /**
     * Account List constructor to instantiate accounts.
     */

    public AccountList(){
        accounts = new ArrayList();
    }

    /**
     * Adds the account to the AccountList.
     * @param a the account
     */
    public void addAccount(Account a){
        accounts.add(a);
    }

    /**
     * Removes the account from the AccountList.
     * @param a the account
     */
    public void removeAccount(Account a){
        //
    }

    /**
     * Returns the account username and password.
     * @param username the account username
     * @param password the account password
     * @return item
     */
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

    /**
     * Returns the id value of the account.
     * @param account the account id
     * @return account type id
     */
    public int getAccountType(Account account){
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).equals(account)){
                return accounts.get(i).getAccountType();
            }
        }
        return -1;
    }

    /**
     * Returns the size of the AccountList
     * @return size
     */
    public int size(){
        return accounts.size();
    }

    /**
     * Returns the account by index.
     * @param i the account by index
     * @return i
     */
    public Account getAccount(int i){
         return accounts.get(i);
    }
}
