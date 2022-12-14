package shared.sharedObjects;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** A class containing the methods for creating objects for a Training Session
 * @author Group 7
 */
public class TrainingSession implements Serializable {
    private String type;
    private String time;
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
    public TrainingSession(String type, String time, int participants, Account trainer, LocalDate date) {
        this.type = type;
        this.time = time;
        this.participants = participants;
        this.trainer = trainer;
        assignedMembers = new ArrayList<>();
        this.date = date;
    }

    /**
     * Adds a member to a Training Session
     * @param account
     */
    public void addMember(Account account)
    {
        if (assignedMembers.size() == 0)
        {
            assignedMembers.add(account);
            participants--;
        }
        else
        {
            for (int i = 0; i < assignedMembers.size(); i++)
            {
                if (!assignedMembers.get(i).equals(account))
                {
                    if (participants != 0)
                    {
                        assignedMembers.add(account);
                        participants--;
                        break;
                    }
                }
            }
        }

    }

    /**
     * Returns the assigned member.
     * @return assigned member
     */
    public List<Account> getAssignedMembers() {
        return assignedMembers;
    }

    public Account getAccount(Account account)
    {
        for (Account temp : assignedMembers)
        {
            if (temp.equals(account))
            {
                return temp;
            }
        }
        return null;
    }

    /**
     * Returns the account by index.
     * @param i
     * @return index
     */
    public Account getAccountById(int i)
    {
        return assignedMembers.get(i);
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

    public String getTime() {
        return time;
    }

    public LocalDate getDate(){
        return date;
    }

    /**
     * Sets the object of date.
     * @param time
     */

    public void setTime(String time) {
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

    /**
     * Returns the username of the specified trainer.
     * @return trainer username
     */
    public String getTrainerUsername() {
        return trainer.getUsername();
    }
    /**
     * Returns the password of the specified trainer.
     * @return trainer password
     */
    public String getTrainerPassword() {
        return trainer.getPassword();
    }
    /**
     * Returns the firstname and lastname of the specified trainer.
     * @return trainer firstname, lastname
     */
    public String getTrainer() {
        return trainer.getFName() + " " + trainer.getLName();
    }
    /**
     * Returns the account of the specified trainer.
     * @return trainer account
     */
    public Account getTrainerAccount()
    {
        return trainer;
    }

    /**
     * Sets the trainer to the account.
     * @param trainer
     */
    public void setTrainer(Account trainer) {
        this.trainer = trainer;
    }

    /**
     * Checks if the object exists in the class and returns them.
     * @param obj
     * @return type, time, date, participant and assigned member
     */
    public boolean equals(Object obj)
    {
        if (!(obj instanceof TrainingSession))
        {
            return false;
        }
        else
        {
            TrainingSession other = (TrainingSession) obj;
            return other.type.equals(type) && other.time.equals(time) && other.date.equals(date) && other.participants == participants
                    && other.assignedMembers.equals(assignedMembers);
        }
    }

    /**
     * Returns a combined string object of type, date, time and participant.
     * @return type, date, time, participants
     */
    public String toString(){
        return type + " " + date + " " + time + " " + participants;
    }
}
