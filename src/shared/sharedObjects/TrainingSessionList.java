package shared.sharedObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * A class handling the arraylist methods for the Training Session Class.
 * @author
 */
public class TrainingSessionList {
    private List<TrainingSession> sessions;

    /**
     * Training Session List constructor to instantiate sessions.
     */
    public TrainingSessionList()
    {
        sessions = new ArrayList<>();
    }

    /**
     * Searches the Training Session Arraylist for the specific session and returns an object.
     * @return Sessions
     */
    public List<TrainingSession> getTrainingSessions() {
        return sessions;
    }

    /**
     *  Adds a training session to the Training Session Arraylist.
     * @param trainingSession
     */
    public void addSession(TrainingSession trainingSession)
    {
        sessions.add(trainingSession);
    }

    /**
     * Removes a training session from the Training Session Arraylist.
     * @param trainingSession
     */
    public void removeTrainingSession(TrainingSession trainingSession)
    {
        sessions.remove(trainingSession);
    }

    /**
     * Checks if the training session element exists and returns the specific object is the case is true.
     * @param trainingSession
     * @return null
     */
    public TrainingSession getTrainingSession(TrainingSession trainingSession)
    {
        for (TrainingSession trainingSessionElement : sessions)
        {
            if (trainingSessionElement.equals(trainingSession))
            {
                return trainingSessionElement;
            }
        }
        return null;
    }
}
