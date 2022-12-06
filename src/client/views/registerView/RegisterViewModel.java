package client.views.registerView;

import client.frontEndModel.FrontEndModel;
import client.frontEndModel.FrontEndModelManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.sharedObjects.Account;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RegisterViewModel implements PropertyChangeListener
{
  private FrontEndModelManager frontEndModelManager;

  private StringProperty firstName,lastName,eMail, phoneNumber, username, password, errorLabel;

  public RegisterViewModel (FrontEndModelManager frontEndModelManager) {
    initializeAllProperties();
   this.frontEndModelManager = frontEndModelManager;
  }
  public FrontEndModel getFrontEndModel() {
    return getFrontEndModel();
  }

  /**
   * A method that will return first name
   * @return firstname
   */
  public StringProperty getFirstNameProperty()
  {
    return firstName;
  }

  /**
   * A method that will return last name
   * @return last name
   */
  public StringProperty getLastNameProperty()
  {
    return lastName;
  }

  /**
   * A method that will return e-mail
   * @return firstname
   */
  public StringProperty geteMailProperty()
  {
    return eMail;
  }
  /**
   * A method that will return e-mail
   * @return firstname
   */
  public StringProperty getPhoneNumberProperty()
  {
    return phoneNumber;
  }

  /**
   * A method that will return username
   * @return username
   */
  public StringProperty getUserNameProperty()
  {
    return username;
  }

  /**
   * A method that will return password
   * @return password
   */
  public StringProperty getPasswordProperty()
  {
    return password;
  }
  /**
   * A method that will return error label
   * @return error message
   */
  public StringProperty errorLabelProperty()
  {
    return errorLabel;
  }
  /**
   * A function that will initialize all the variables
   */
  private void initializeAllProperties()
  {
    firstName = new SimpleStringProperty();
    lastName = new SimpleStringProperty();
    eMail = new SimpleStringProperty();
    phoneNumber = new SimpleStringProperty();
    username = new SimpleStringProperty();
    password = new SimpleStringProperty();
    errorLabel = new SimpleStringProperty();
  }


  public void createAccount()
  {
    if (username.get() == null || username.get().isEmpty())
    {
      errorLabel.set("Fields cannot be empty..");

    }
    else if (firstName.get() == null || firstName.get().isEmpty()
        || lastName.get().isEmpty()|| lastName.get()==null)
    {
      errorLabel.set("Fields cannot be empty..");

    }
    else if (password.get() == null || password.get().isEmpty())
    {
      errorLabel.set("Fields cannot be empty..");

    }
    else if (eMail.get() == null || eMail.get().isEmpty())
    {
      errorLabel.set("Fields cannot be empty..");

    }
    else if (phoneNumber.get() == null || phoneNumber.get().isEmpty())
    {
      errorLabel.set("Fields cannot be empty..");

    }
    else
    {
      frontEndModelManager.createAccount(
          new Account(1, firstName.get(), lastName.get(), eMail.get(),
              phoneNumber.get(), username.get(), password.get()));

    }

  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {

  }
}
