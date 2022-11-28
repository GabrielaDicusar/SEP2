package shared.sharedObjects;

import java.io.Serializable;

/**
 * A class containing the methods for creating objects for an Account implementing Serializable.
 * @author
 */

public class Account implements Serializable {

    private String userName;
    private String password;

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * Returns an object of title.
     * @return userName
     */

    public String getUserName() {
        return userName;
    }

    /**
     * Sets the object of title.
     * @param userName
     */

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns an object of title.
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the object of title.
     * @param password
     */

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns a String Object of userName and password.
     * @return userName, password
     */

    public String toString(){
        return userName + " "  + password;
    }

    /**
     * Checks if the account element is an object of Account and returns the specific username and password.
     * @param obj
     * @return
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
            return other.userName.equals(userName) && other.password.equals(password);
        }
    }
}
