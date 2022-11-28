package client.views.managerView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import client.views.managerView.ManagerViewModel;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;

public class ManagerViewController implements ViewController
{
  public DatePicker datePicker;
  public TableView Timetable;
  public Button btnCreateSession;
  public Button btnEditSession;
  public Button btnRemoveBooking;
  private ViewHandler viewHandler;
  private ManagerViewModel managerViewModel;

  @Override
  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory){
    this.viewHandler = viewHandler;
    managerViewModel = viewModelFactory.getManagerViewModel();
  }

}
