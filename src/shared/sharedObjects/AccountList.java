package shared.sharedObjects;

import java.util.ArrayList;
import java.util.List;
/**
 * A class handling the arraylist methods for the Account Class.
 * @author
 */
public class AccountList {
    private List<Account> accounts;

    /**
     * Account List constructor to instantiate accounts.
     */
    public AccountList()
    {
        accounts = new ArrayList<>();
    }

    /**
     * Searches the Account Arraylist for the specific session and returns an object.
     * @return accounts
     */

    public List<Account> getAccounts() {
        return accounts;
    }

    /**
     * Adds a training session to the Account Arraylist.
     * @param account
     */

    public void addAccount(Account account)
    {
        accounts.add(account);
    }

    /**
     * Removes a training session to the Account Arraylist.
     * @param account
     */

    public void removeAccount(Account account)
    {
        accounts.remove(account);
    }

    /**
     * Checks if the account element exists and returns the specific object is the case is true.
     * @param account
     * @return accountElement
     */

    public Account getAccount(Account account)
    {
        for (Account accountElement : accounts)
        {
            if (accountElement.equals(account))
            {
                return accountElement;
            }
        }
        return null;
    }


}
