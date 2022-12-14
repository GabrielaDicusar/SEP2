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

/**
 *  A controller class for the bookingViewModel.
 * @author group 7
 */
public class BookingViewController implements ViewController {

    public Button btnBookSession;
    public Button bookedSessionsBtn;
    private ViewHandler viewHandler;
    private BookingViewModel bookingViewModel;
    /**
     * An init method for the viewHandler and viewModelFactory
     */
   public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory){
        this.viewHandler = viewHandler;
       bookingViewModel = viewModelFactory.getBookingViewModel();
    }

    /**
     * An action when the button is pressed.
     * @param actionEvent the actionEvent
     */
    public void onButtonPressed(ActionEvent actionEvent) {
       if (actionEvent.getSource() == btnBookSession)
       {
           viewHandler.openAvailableToBookView();
       }
       else if (actionEvent.getSource() == bookedSessionsBtn)
       {
           viewHandler.openBookedSessionsView();
       }
    }
}
