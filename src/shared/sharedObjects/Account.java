package shared.sharedObjects;

import java.io.Serializable;

public class Account implements Serializable {
    // login credentials, account type, name, email, phone number, address
    private String username;
    private String password;
    private int accountType;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;

    public Account( int accountType, String name, String email, String phoneNumber, String address, String username, String password)
    {
        this.username = username;
        this.password = password;
        this.accountType = accountType;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }
    public Account(String username, String password)
    {
        this.password = password;
        this.username = username;
        this.accountType = 0;
        this.name = null;
        this.phoneNumber = null;
        this.address = null;
        this.email = null;
    }

    public int getAccountType() {
        return accountType;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof Account))
        {
            return false;
        }
        else
        {
            Account other = (Account) obj;
            return other.password.equals(password) && other.username.equals(username);
        }
    }

    public String toString(){
        return name;
    }
}
