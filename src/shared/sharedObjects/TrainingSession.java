package shared.sharedObjects;

import java.util.Date;

public class TrainingSession {
    private String title;
    private Date date;
    private int capacity;

    public TrainingSession(String title, Date date, int capacity) {
        this.title = title;
        this.date = date;
        this.capacity = capacity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
