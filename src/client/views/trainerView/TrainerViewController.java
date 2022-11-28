package client.views.trainerView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.managerView.ManagerViewModel;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;

public class TrainerViewController

{
  public DatePicker datePicker;
  public TableView Timetable;
  public Button btnCancelBooking;
  private ViewHandler viewHandler;
  private TrainerViewModel trainerViewModel;

  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    trainerViewModel = viewModelFactory.getTrainerViewModel();
  }
}
