package client.views.managerView.editView;

import client.frontEndModel.FrontEndModelManager;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EditViewModel implements PropertyChangeListener
{

  private FrontEndModelManager frontEndModelManager;

  public EditViewModel(FrontEndModelManager frontEndModelManager) {
    this.frontEndModelManager = frontEndModelManager;
  }

  public FrontEndModelManager getFrontEndModelManager() {
    return frontEndModelManager;
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {

  }
}
