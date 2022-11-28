package shared.utils;

import java.beans.PropertyChangeListener;

/** Interface Subject creates and removes Listeners to be implemented by BackEndModel,
    FrontEndModel, Member, BookingViewModel, LoginViewController and LoginView
 * @author
 */

public interface Subject {
    void addListener(String eventName, PropertyChangeListener listener);
    void removeListener(String eventName, PropertyChangeListener listener);
}
