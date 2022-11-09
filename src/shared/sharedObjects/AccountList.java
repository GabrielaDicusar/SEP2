package shared.sharedObjects;

import java.util.ArrayList;
import java.util.List;

public class AccountList {
    private List<Account> accounts;

    public AccountList()
    {
        accounts = new ArrayList<>();
    }

    public List<Account> getAccounts() {
        return accounts;
    }
    public void addAccount(Account account)
    {
        accounts.add(account);
    }

    public void removeAccount(Account account)
    {
        accounts.remove(account);
    }

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
