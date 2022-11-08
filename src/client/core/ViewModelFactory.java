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
        return (bookingViewModel = bookingViewModel == null ?
                new BookingViewModel(modelFactory.getClientFactory().getClient()) :
                bookingViewModel);
    }
    public LoginViewModel getLoginViewModel() {
        if (loginViewModel == null)
            loginViewModel = new LoginViewModel();
        return loginViewModel;
    }
}
