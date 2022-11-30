package shared.sharedObjects;

import java.io.Serializable;

public class Account implements Serializable {
    // login credentials, account type, name, email, phone number, address
    private LoginCredentials loginCredentials;
    private int accountType;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;

    public Account(LoginCredentials loginCredentials, int accountType, String name, String email, String phoneNumber, String address)
    {
        this.accountType = accountType;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.loginCredentials = loginCredentials;
    }

    public int getAccountType() {
        return accountType;
    }

    public String getName() {
        return name;
    }

    public LoginCredentials getLoginCredentials() {
        return loginCredentials;
    }

    public String toString(){
        return name;
    }
}
