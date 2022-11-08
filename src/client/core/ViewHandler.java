package client.core;

import javafx.scene.Scene;

public class ViewHandler {

    private final Scene LoginScene;
    private Scene loginScene;
    private Scene bookingScene;
    private ViewModelFactory viewModelFactory;

    public ViewHandler(ViewModelFactory viewModelFactory){
        this.LoginScene = loginScene;
        this.bookingScene = bookingScene;
        this.viewModelFactory = viewModelFactory;
    }

    public void start(){}

    private void loadFXML(String path){}

    public void openBookingView(){}

    public void openLoginView(){}

}
