package shared.sharedObjects;

import java.io.Serializable;

/** A class containing the methods for creating objects for an Account.
 * @author Group 7
 */
public class Account implements Serializable {
    // login credentials, account type, name, email, phone number, address
    private String username;
    private String password;
    private int accountType;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;

    /**
     * Constructor to instantiate accountType, firstname, lastname, email, phonenumber, username, password.
     * @param accountType the account type
     * @param firstname the firstname
     * @param lastname the lastname
     * @param email the email address
     * @param phoneNumber the phonenumber
     * @param username the username
     * @param password the password
     */
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

    /**
     * Constructor instantiating values to the account parameters.
     * @param username the username
     * @param password the password
     */
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

    /**
     * Returns the type of account.
     * @return accountType
     */
    public int getAccountType() {
        return accountType;
    }

    /**
     * Returns the account firstname.
     * @return firstname
     */
    public String getFName() {
        return firstname;
    }
    /**
     * Returns the account lastname.
     * @return lastname
     */
    public String getLName() {
        return lastname;
    }
    /**
     * Returns the account username.
     * @return username
     */
    public String getUsername() {
        return username;
    }
    /**
     * Returns the account email.
     * @return email
     */
    public String getEmail()
    {
        return email;
    }
    /**
     * Returns the account phonenumber.
     * @return phonenumber
     */
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    /**
     * Returns the account password.
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Checks for the specific objects in the class and returns them.
     * @param obj the compared object
     * @return boolean
     */
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

    /**
     * Returns a string object of firstname.
     * @return firstname
     */
    public String toString(){
        return firstname;
    }
}
