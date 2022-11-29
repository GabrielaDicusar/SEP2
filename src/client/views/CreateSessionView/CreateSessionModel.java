package client.views.CreateSessionView;

import client.frontEndModel.FrontEndModelManager;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CreateSessionModel implements PropertyChangeListener
{
  private FrontEndModelManager frontEndModelManager;

  public CreateSessionModel(FrontEndModelManager frontEndModelManager) {
    this.frontEndModelManager = frontEndModelManager;
  }

  public FrontEndModelManager getFrontEndModelManager() {
    return frontEndModelManager;
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {

  }
}
