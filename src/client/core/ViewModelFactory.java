package client.core;

import client.views.managerView.createSessionView.CreateSessionViewModel;
import client.views.managerView.editView.EditViewModel;
import client.views.managerView.registerTrainerView.RegisterTrainerViewModel;
import client.views.memberView.BookingViewModel;
import client.views.loginView.LoginViewModel;
import client.views.managerView.ManagerViewModel;
import client.views.memberView.availableToBookView.AvailableToBookViewModel;
import client.views.memberView.bookedSessionsView.BookedSessionsViewModel;
import client.views.registerView.RegisterViewModel;
import client.views.trainerView.TrainerViewModel;

/**
 * A class handling the viewModelFactory
 * @author Group 7
 */
public class ViewModelFactory {

    private ModelFactory modelFactory;
    private BookingViewModel bookingViewModel;
    private LoginViewModel loginViewModel;
    private ManagerViewModel managerViewModel;
    private TrainerViewModel trainerViewModel;
    private CreateSessionViewModel createViewModel;
    private EditViewModel editViewModel;
    private AvailableToBookViewModel availableToBookViewModel;
    private BookedSessionsViewModel bookedSessionsViewModel;
    private RegisterViewModel registerViewModel;
    private RegisterTrainerViewModel registerTrainerViewModel;

    /**
     * A constructor instantiating the modelFactory.
     * @param modelFactory the modelFactory
     */
    public ViewModelFactory(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }

    /**
     * Returns the bookingViewModel.
     * @return bookViewModel
     */
    public BookingViewModel getBookingViewModel() {
        if (bookingViewModel == null) {
            bookingViewModel = new BookingViewModel(modelFactory.getFrontEndModelManager());
        }
        return bookingViewModel;
    }

    /**
     * Returns the loginViewModel.
     * @return loginViewModel
     */
    public LoginViewModel getLoginViewModel(){
        if (loginViewModel == null){
            loginViewModel = new LoginViewModel(modelFactory);
        }
        return loginViewModel;
        }

    /**
     * Returns the managerViewModel
     * @return managerViewModel
     */
    public ManagerViewModel getManagerViewModel() {
        if (managerViewModel == null){
            managerViewModel = new ManagerViewModel(modelFactory.getFrontEndModelManager());
            editViewModel = new EditViewModel(modelFactory.getFrontEndModelManager());
    }
        return managerViewModel; }

    /**
     * Returns the trainerViewModel.
     * @return trainerViewModel
     */
    public TrainerViewModel getTrainerViewModel() {
        if (trainerViewModel == null){
            trainerViewModel = new TrainerViewModel(modelFactory.getFrontEndModelManager());
        }
        return trainerViewModel;
    }

    /**
     * Returns the createViewModel.
     * @return createViewModel
     */
    public CreateSessionViewModel getCreateSessionModel() {
        if (createViewModel == null){
            createViewModel = new CreateSessionViewModel(modelFactory.getFrontEndModelManager());
        }
        return createViewModel;
    }

    /**
     * Returns the registerViewModel.
     * @return registerViewModel
     */
    public RegisterViewModel getRegisterViewModel() {
        if (registerViewModel == null) {
            registerViewModel = new RegisterViewModel(modelFactory.getFrontEndModelManager());
        }
        return registerViewModel;
    }

    /**
     * Returns the registerTrainerViewModel.
     * @return registerTrainerViewModel
     */
    public RegisterTrainerViewModel getRegisterTrainerViewModel() {
        if (registerTrainerViewModel == null) {
            registerTrainerViewModel = new RegisterTrainerViewModel(modelFactory.getFrontEndModelManager());
        }
        return registerTrainerViewModel;
    }

    /**
     * Returns the editViewModel
     * @return editViewModel
     */
    public EditViewModel getEditViewModel() {
        if (editViewModel == null){
            editViewModel = new EditViewModel(modelFactory.getFrontEndModelManager());
        }
        return editViewModel;
    }

    /**
     * Returns the availableToBookViewModel.
     * @return availableToBookViewModel
     */
    public AvailableToBookViewModel getAvailableToBookViewModel() {
        if (availableToBookViewModel == null){
            availableToBookViewModel = new AvailableToBookViewModel(modelFactory.getFrontEndModelManager());
        }
        return availableToBookViewModel;
    }

    /**
     * Returns the bookedSessionViewModel.
     * @return bookedSessionViewModel.
     */
    public BookedSessionsViewModel getBookedSessionsViewModel() {
        if (bookedSessionsViewModel == null){
            bookedSessionsViewModel = new BookedSessionsViewModel(modelFactory.getFrontEndModelManager());
        }
        return bookedSessionsViewModel;
    }

    /**
     * Returns the modelFactory
     * @return modelFactory
     */
    public ModelFactory getModelFactory() {
        return modelFactory;
    }

}
