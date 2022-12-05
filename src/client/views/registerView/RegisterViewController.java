package client.views.registerView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;

public class RegisterViewController implements ViewController
{
  private ViewHandler viewHandler;
  private RegisterViewModel registerViewModel;

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    registerViewModel = viewModelFactory.getRegisterViewModel();
  }
}
