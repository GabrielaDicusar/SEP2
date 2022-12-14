package client.core;

import client.views.ViewController;
import client.views.managerView.ManagerViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A class handling the viewHandler methods.
 * @author Group 7
 */
public class ViewHandler {

    private Stage stage;
    private Scene loginScene;
    private Scene bookingScene;
    private Scene managerScene;
    private Scene createSessionScene;
    private Scene availableToBookScene;
    private Scene bookedSessionsScene;
    private Scene registerMemberScene;
    private Scene registerTrainerScene;
    private Scene editSessionScene;
    private ViewModelFactory viewModelFactory;

    /**
     * A constructor instantiating the variables.
     * @param viewModelFactory the viewModelFactory
     */
    public ViewHandler(ViewModelFactory viewModelFactory){
        this.viewModelFactory = viewModelFactory;
    }

    /**
     * A start method.
     */
    public void start(){
        stage = new Stage();
        openLoginView();
    }

    /**
     * A parent FXML file to load the root.
     * @param path the path
     * @return root
     * @throws IOException
     */
    private Parent loadFXML(String path) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        Parent root = loader.load();

        ViewController ctrl = loader.getController();
        ctrl.init(this, viewModelFactory);
        return root;
    }

    /**
     * The method for switching to the member account GUI.
     */
    public void openBookingView(){ if (bookingScene == null) {
        try {
            Parent root = loadFXML("../views/memberView/BookingView.fxml");

            bookingScene = new Scene(root);
            stage.setTitle("Member Account");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        stage.setScene(bookingScene);
        stage.show();
    }

    /**
     * A method for switching to the booking a session GUI.
     */
    public void openAvailableToBookView(){ if (availableToBookScene == null) {
        try {
            Parent root = loadFXML("../views/memberView/availableToBookView/availableToBookView.fxml");

            availableToBookScene = new Scene(root);
            stage.setTitle("Booking a Session");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        stage.setScene(availableToBookScene);
        stage.show();
    }
    /**
     * A method for switching booked sessions GUI.
     */
    public void openBookedSessionsView(){ if (bookedSessionsScene == null) {
        try {
            Parent root = loadFXML("../views/memberView/bookedSessionsView/bookedSessionsView.fxml");

            bookedSessionsScene = new Scene(root);
            stage.setTitle("Booked Sessions");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        stage.setScene(bookedSessionsScene);
        stage.show();
    }
    /**
     * A method for switching to the Login GUI.
     */
    public void openLoginView(){
        if (loginScene == null) {
            try {
                Parent root = loadFXML("../views/loginView/LoginView.fxml");

                loginScene = new Scene(root);
                stage.setTitle("Login");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stage.setScene(loginScene);
        stage.show();
    }
    /**
     * A method for switching to the manager GUI.
     */
    public void openManagerView(){
        if (managerScene == null) {
            try {
                Parent root = loadFXML("../views/managerView/ManagerView.fxml");

                managerScene = new Scene(root);
                stage.setTitle("Manager Account");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stage.setScene(managerScene);
        stage.show();
    }
    /**
     * A method for switching to the create a session GUI.
     */
    public void openCreateSessionView(){
        if (createSessionScene == null) {
            try {
                Parent root = loadFXML("../views/managerView/createSessionView/CreateSessionView.fxml");

                createSessionScene = new Scene(root);
                stage.setTitle("Create Session");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stage.setScene(createSessionScene);
        stage.show();
    }
    /**
     * A method for switching to the register member GUI.
     */
    public void openRegisterMemberView(){
        if ( registerMemberScene== null) {
            try {
                Parent root = loadFXML("../views/registerView/RegisterView.fxml");

                 registerMemberScene = new Scene(root);
                stage.setTitle("Register account");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stage.setScene(registerMemberScene);
        stage.show();
    }
    /**
     * A method for switching to the register trainer GUI.
     */
    public void openRegisterTrainerView() {
        if ( registerTrainerScene== null) {
            try {
                Parent root = loadFXML("../views/managerView/registerTrainerView/RegisterTrainerView.fxml");

                registerTrainerScene = new Scene(root);
                stage.setTitle("Register trainer account");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stage.setScene(registerTrainerScene);
        stage.show();
    }
    /**
     * A method for switching to the trainer GUI.
     */
    public void openTrainerView(){
        if (managerScene == null) {
            try {
                Parent root = loadFXML("../views/trainerView/TrainerView.fxml");

                managerScene = new Scene(root);
                stage.setTitle("Trainer Account");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stage.setScene(managerScene);
        stage.show();
    }
    /**
     * A method for switching to the edit GUI.
     */
    public void openEditView(){
        if (editSessionScene == null) {
            try {
                Parent root = loadFXML("../views/managerView/editView/EditView.fxml");

                editSessionScene = new Scene(root);
                stage.setTitle("Edit Session");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stage.setScene(editSessionScene);
        stage.show();
    }

    /**
     * Returns a viewModelFactory
     * @return viewModelFactory.
     */
    public ViewModelFactory getViewModelFactory() {
        return viewModelFactory;
    }
}
