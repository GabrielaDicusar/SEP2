package client.core;

import client.views.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {

    private Stage stage;
    private Scene loginScene;
    private Scene bookingScene;
    private Scene managerScene;
    private Scene createSessionScene;
    private ViewModelFactory viewModelFactory;

    public ViewHandler(ViewModelFactory viewModelFactory){
        this.viewModelFactory = viewModelFactory;
    }

    public void start(){
        stage = new Stage();
        openLoginView();
    }

    private Parent loadFXML(String path) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        Parent root = loader.load();

        ViewController ctrl = loader.getController();
        ctrl.init(this, viewModelFactory);
        return root;
    }

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

    public ViewModelFactory getViewModelFactory() {
        return viewModelFactory;
    }
}
