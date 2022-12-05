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


    public String toString(){
        return type + " " + date + " " + time + " " + participants;
    }
}
