package client.views;

import client.core.ViewHandler;
import client.core.ViewModelFactory;

/**
 * An Interface handling the init method for viewHandler and viewModelFactory.
 * @author Group 7
 */
public interface ViewController {

     void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory);
}
