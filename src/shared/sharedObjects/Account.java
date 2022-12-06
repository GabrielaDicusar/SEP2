package shared.sharedObjects;

import java.io.Serializable;

public class Account implements Serializable {
    // login credentials, account type, name, email, phone number, address
    private String username;
    private String password;
    private int accountType;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;

    public Account( int accountType, String firstname, String lastname, String email, String phoneNumber, String username, String password)
    {
        this.username = username;
        this.password = password;
        this.accountType = accountType;
        this.firstname = firstname;
        this.lastname=lastname;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    public Account(String username, String password)
    {
        this.password = password;
        this.username = username;
        this.accountType = 0;
        this.firstname = null;
        this.lastname=null;
        this.phoneNumber = null;
        this.email = null;
    }

    public int getAccountType() {
        return accountType;
    }

    public String getFName() {
        return firstname;
    }
    public String getLName() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
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
        return firstname;
    }
}
