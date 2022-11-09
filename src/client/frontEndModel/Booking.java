package client.frontEndModel;

import java.util.ArrayList;
import java.util.Date;

public class Booking {

    private Date date;
    private BookingList bookingList = new BookingList();
    private Session session;
    private boolean isBooked;
    private boolean isAvailable;

    public Booking(Date date, Session session){
        this.date = date;
        this.session = session;
        this.isBooked = false;
        this.isAvailable = true;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void addBooking(Booking booking){
        this.bookingList.addBooking(booking);
    }
    public void removeBooking(Booking booking){
        this.bookingList.removeBooking(booking);
    }
    public BookingList getAllBookings() {
        return bookingList;
    }
}
