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
            Parent root = loadFXML("../views/bookingView/BookingView.fxml");

            bookingScene = new Scene(root);
            stage.setTitle("Booking");

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

    public ViewModelFactory getViewModelFactory() {
        return viewModelFactory;
    }
}
