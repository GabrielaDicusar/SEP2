package client.views.managerView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import client.views.managerView.ManagerViewModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import shared.sharedObjects.Account;
import shared.sharedObjects.LoginCredentials;
import shared.sharedObjects.TrainingSession;

public class ManagerViewController implements ViewController
{
  public DatePicker datePicker;
  public TableView Timetable;
  public Button btnCreateSession;
  public Button btnEditSession;
  public Button btnRemoveSession;
  private ViewHandler viewHandler;
  private ManagerViewModel managerViewModel;

  @Override
  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory){
    this.viewHandler = viewHandler;
    managerViewModel = viewModelFactory.getManagerViewModel();
  }

  public void onButtonPressed(ActionEvent actionEvent) {
    managerViewModel.getFrontEndModelManager().addSession(new TrainingSession("Fitness", "14:00", 10, new Account(new LoginCredentials("manager", "manager"), 3, "Temp", "123@gmail.com", "23232323", "Somewhere")));
  }
}
