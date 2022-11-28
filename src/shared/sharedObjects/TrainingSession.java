package shared.sharedObjects;

import java.util.Date;

/** A class containing the methods for creating objects for a Training Session
 * @author
 */
public class TrainingSession {
    private String title;
    private Date date;
    private int capacity;

    /**
     * Training Session constructor to instantiate title, date and capacity.
     * @param title
     * @param date
     * @param capacity
     */
    public TrainingSession(String title, Date date, int capacity) {
        this.title = title;
        this.date = date;
        this.capacity = capacity;
    }

    /**
     * Returns an object of title.
     * @return title
     */

    public String getTitle() {
        return title;
    }
    /**
     * Sets the object of title.
     * @param title
     */

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns an object of date.
     * @return date
     */

    public Date getDate() {
        return date;
    }

    /**
     * Sets the object of date.
     * @param date
     */

    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Returns an object of capacity.
     * @return capacity
     */

    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets the object of date.
     * @param capacity
     */

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
