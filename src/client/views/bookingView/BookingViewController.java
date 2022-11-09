package client.views.bookingView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;

public class BookingViewController implements ViewController {

    private ViewHandler viewHandler;
    private BookingViewModel bookingViewModel;

    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory){
        this.viewHandler = viewHandler;
//        bookingViewModel = viewModelFactory.getBookingViewModel();
    }

}
