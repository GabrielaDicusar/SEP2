package client.core;

import client.views.bookingView.BookingViewModel;
import client.views.loginView.LoginViewModel;

public class ViewModelFactory {

    private ModelFactory modelFactory;
    private BookingViewModel bookingViewModel;
    private LoginViewModel loginViewModel;

    public ViewModelFactory(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }

    public BookingViewModel getBookingViewModel() {
        if (bookingViewModel == null) {
            bookingViewModel = new BookingViewModel(modelFactory.getFrontEndModelManager());
        }
        return bookingViewModel;
    }

    public LoginViewModel getLoginViewModel(){
        if (loginViewModel == null){
            loginViewModel = new LoginViewModel(modelFactory);
        }
        return loginViewModel;
        }
}
