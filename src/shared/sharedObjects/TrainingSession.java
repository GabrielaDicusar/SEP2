package shared.sharedObjects;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** A class containing the methods for creating objects for a Training Session
 * @author
 */
public class TrainingSession implements Serializable {
    private String type;
    private LocalTime time;
    private int participants;
    private Account trainer;
    private List<Account> assignedMembers;
    private LocalDate date;

    /**
     * Training Session constructor to instantiate title, date and capacity.
     * @param type
     * @param time
     * @param participants
     */
    public TrainingSession(String type, LocalTime time, int participants, Account trainer, LocalDate date) {
        this.type = type;
        this.time = time;
        this.participants = participants;
        this.trainer = trainer;
        assignedMembers = new ArrayList<>();
        this.date = date;
    }
    public void addMember(Account account)
    {
        assignedMembers.add(account);
    }

    public List<Account> getAssignedMembers() {
        return assignedMembers;
    }
    public Account getAccount(LoginCredentials loginCredentials)
    {
        for (Account account : assignedMembers)
        {
            if (account.getLoginCredentials().equals(loginCredentials))
            {
                return account;
            }
        }
        return null;
    }

    /**
     * Returns an object of title.
     * @return title
     */

    public String getType() {
        return type;
    }
    /**
     * Sets the object of title.
     * @param type
     */

    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns an object of date.
     * @return date
     */

    public LocalTime getTime() {
        return time;
    }

    public LocalDate getDate(){
        return date;
    }

    /**
     * Sets the object of date.
     * @param time
     */

    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Returns an object of capacity.
     * @return capacity
     */

    public int getParticipants() {
        return participants;
    }


    /**
     * Sets the object of date.
     * @param participants
     */

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public String getTrainer() {
        return trainer.getName();
    }

    public void setTrainer(Account trainer) {
        this.trainer = trainer;
    }


    public String toString(){
        return type + " " + date + " " + time + " " + participants;
    }
}
