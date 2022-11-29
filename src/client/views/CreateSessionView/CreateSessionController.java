package client.views.CreateSessionView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class CreateSessionController implements ViewController
{
 public TextField textTypeField;
 public TextField btnCapacityField;
 public ComboBox ComboTrainer;
 public DatePicker datePicker1;
 public ComboBox ComboTime;
 private ViewHandler viewHandler;
 private CreateSessionModel createViewModel;


  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {
   this.viewHandler = viewHandler;
   createViewModel = viewModelFactory.getCreateSessionModel();
  }
}
