package client.views.managerView.createSessionView;

import client.frontEndModel.FrontEndModelManager;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CreateSessionViewModel implements PropertyChangeListener
{
  private FrontEndModelManager frontEndModelManager;

  public CreateSessionViewModel(FrontEndModelManager frontEndModelManager) {
    this.frontEndModelManager = frontEndModelManager;
  }

  public FrontEndModelManager getFrontEndModelManager() {
    return frontEndModelManager;
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {

  }
}
