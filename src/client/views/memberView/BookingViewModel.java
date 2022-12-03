package client.views.memberView;

import client.frontEndModel.FrontEndModelManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.sharedObjects.TrainingSession;
import shared.sharedObjects.TrainingSessionList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class BookingViewModel {

    FrontEndModelManager frontEndModelManager;
    public BookingViewModel(FrontEndModelManager frontEndModelManager) {
        this.frontEndModelManager = frontEndModelManager;
    }
}
