package client.views.memberView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.sharedObjects.TrainingSession;

public class BookingViewController implements ViewController {

    public Button btnBookSession;
    private ViewHandler viewHandler;
    private BookingViewModel bookingViewModel;

   public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory){
        this.viewHandler = viewHandler;
       bookingViewModel = viewModelFactory.getBookingViewModel();
    }

    public void onButtonPressed(ActionEvent actionEvent) {
       if (actionEvent.getSource() == btnBookSession)
       {
           viewHandler.openAvailableToBookView();
       }
    }
}
