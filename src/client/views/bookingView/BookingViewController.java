package client.views.bookingView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.frontEndModel.Session;
import client.views.ViewController;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;

public class BookingViewController implements ViewController {

    public DatePicker datePicker;
    public TableView Timetable;
    public Button btnBookSession;
    public Button btnRemoveBooking;
    private ViewHandler viewHandler;
    private BookingViewModel bookingViewModel;

   /* public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory){
        this.viewHandler = viewHandler;
//        bookingViewModel = viewModelFactory.getBookingViewModel();
    }

    */
}
