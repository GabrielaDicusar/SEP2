package client.views.managerView.editView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class EditViewController implements ViewController
{
  public TextField textTypeField1;
  public TextField btnCapacityField1;
  public ComboBox ComboTrainer1;
  public DatePicker datePicker2;
  public ComboBox ComboTime1;
  public Button btnSave;
  public Button btnCancel1;
  public Button btnDelete1;
  private ViewHandler viewHandler;
  private EditViewModel editViewModel;

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    editViewModel = viewModelFactory.getEditViewModel();
  }
}
