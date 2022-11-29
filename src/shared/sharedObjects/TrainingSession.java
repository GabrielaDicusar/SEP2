package shared.sharedObjects;

import java.io.Serializable;
import java.util.Date;

/** A class containing the methods for creating objects for a Training Session
 * @author
 */
public class TrainingSession implements Serializable {
    private String type;
    private String time;
    private int participants;
    private Account trainer;

    /**
     * Training Session constructor to instantiate title, date and capacity.
     * @param type
     * @param time
     * @param participants
     */
    public TrainingSession(String type, String time, int participants, Account trainer) {
        this.type = type;
        this.time = time;
        this.participants = participants;
        this.trainer = trainer;
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

    public String getTrainer() {
        return trainer.getName();
    }

    public void setTrainer(Account trainer) {
        this.trainer = trainer;
    }
}
