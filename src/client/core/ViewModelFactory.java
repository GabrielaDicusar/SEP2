package client.core;

import client.views.bookingView.BookingViewModel;
import client.views.loginView.LoginViewModel;

public class ViewModelFactory {

    private ModelFactory modelFactory;
    private BookingViewModel bookingViewModel;
    private LoginViewModel loginViewModel;

    public ViewModelFactory(ModelFactory modelFactory){
        this.modelFactory = modelFactory;
        this.bookingViewModel = bookingViewModel;
        this.loginViewModel = loginViewModel;
    }

    public BookingViewModel getBookingViewModel() {
        return bookingViewModel;
    }
    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }
}
