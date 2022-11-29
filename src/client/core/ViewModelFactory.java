package client.core;

import client.views.CreateSessionView.CreateSessionModel;
import client.views.memberView.BookingViewModel;
import client.views.loginView.LoginViewModel;
import client.views.managerView.ManagerViewModel;
import client.views.trainerView.TrainerViewModel;

public class ViewModelFactory {

    private ModelFactory modelFactory;
    private BookingViewModel bookingViewModel;
    private LoginViewModel loginViewModel;
    private ManagerViewModel managerViewModel;
    private TrainerViewModel trainerViewModel;
    private CreateSessionModel createViewModel;

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


    public ManagerViewModel getManagerViewModel() {
        if (managerViewModel == null){
            managerViewModel = new ManagerViewModel(modelFactory.getFrontEndModelManager());
    }
        return managerViewModel; }

    public TrainerViewModel getTrainerViewModel() {
        if (trainerViewModel == null){
            trainerViewModel = new TrainerViewModel(modelFactory.getFrontEndModelManager());
        }
        return trainerViewModel;
    }

    public CreateSessionModel getCreateSessionModel() {
        if (createViewModel == null){
            createViewModel = new CreateSessionModel(modelFactory.getFrontEndModelManager());
        }
        return createViewModel;
    }
    public ModelFactory getModelFactory() {
        return modelFactory;
    }

}
