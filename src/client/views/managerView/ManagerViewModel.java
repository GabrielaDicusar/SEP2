package client.views.managerView;

import client.frontEndModel.FrontEndModelManager;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ManagerViewModel implements PropertyChangeListener
{
  private FrontEndModelManager frontEndModelManager;
  public ManagerViewModel(FrontEndModelManager frontEndModelManager) {
    this.frontEndModelManager = frontEndModelManager;
  }

  public FrontEndModelManager getFrontEndModelManager() {
    return frontEndModelManager;
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {

  }
}
