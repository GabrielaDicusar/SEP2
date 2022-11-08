package client.core;

public class ViewHandler {

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

    public void openMainView(){}

}
