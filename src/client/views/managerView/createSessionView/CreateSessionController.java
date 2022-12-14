package client.views.managerView.createSessionView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.scene.control.*;
import shared.sharedObjects.Account;
import shared.sharedObjects.TrainingSession;

import java.time.LocalTime;

public class CreateSessionController implements ViewController
{
 public TextField textTypeField;
 public TextField btnCapacityField;
 public ComboBox comboTrainer;
 public DatePicker datePicker1;
 public ComboBox comboTime;
 public Button btnCreate;
 private ViewHandler viewHandler;
 private CreateSessionViewModel viewModel;


  @Override
  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
   this.viewHandler = viewHandler;
   viewModel = viewModelFactory.getCreateSessionModel();
   viewModel.loadListOfTrainers();
   comboTrainer.setItems(viewModel.getTrainers());
   viewModel.loadTimeList();
   comboTime.setItems(viewModel.getTimeSlots());
  }

  public void onSaveButton(){
   if(textTypeField.getText() != null && datePicker1.getValue() != null && btnCapacityField.getText() != null && comboTrainer.getSelectionModel().getSelectedItem() != null && comboTime.getSelectionModel().getSelectedItem() != null) {
    System.out.println("fields are not empty");
    TrainingSession session = new TrainingSession(textTypeField.getText(), (String) comboTime.getSelectionModel().getSelectedItem(), (Integer) Integer.valueOf(btnCapacityField.getText()), (Account) comboTrainer.getSelectionModel().getSelectedItem(), datePicker1.getValue());
    System.out.println(session.toString());
    if (viewModel.verifyAvailabilityOfSession(session)) {
     textTypeField.clear();
     btnCapacityField.clear();
     Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
             "You have successfully created a training session");
     alert.setHeaderText(null);
     alert.showAndWait();
     viewHandler.openManagerView();
     viewModel.addSession(session);
    } else if (!viewModel.verifyAvailabilityOfSession(session)) {
     Alert alert = new Alert(Alert.AlertType.ERROR,
             "Wrong date compatibility");
     alert.setHeaderText(null);
     alert.showAndWait();
    }
   } else{
    System.out.println("fields are empty");
    Alert alert = new Alert(Alert.AlertType.ERROR,
            "Empty fields");
    alert.setHeaderText(null);
    alert.showAndWait();
   }
  }
}
