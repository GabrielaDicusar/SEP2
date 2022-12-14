package shared.sharedObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A class handling the arraylist methods for the Training Session Class.
 * @author
 */
public class TrainingSessionList implements Serializable {
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
        if (trainingSession == null)
        {
            throw new IllegalArgumentException("The session is null");
        }
        else
        {
            sessions.add(trainingSession);
        }
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

    /**
     * Adds the member to the specific training session.
     * @param account
     * @param trainingSession
     * @return item
     */
    public TrainingSession addParticipant(Account account, TrainingSession trainingSession)
    {
        if (account == null || trainingSession == null)
        {
            throw new IllegalArgumentException("The parameter is null");
        }
        else {
            for (TrainingSession item : sessions)
            {
                if (item.equals(trainingSession))
                {
                    item.addMember(account);
                    return item;
                }
            }
            return null;
        }
    }

    /**
     * Returns training session be index.
     * @param i
     * @return
     */
    public TrainingSession getTrainingSessionByIndex(int i){
        return sessions.get(i);
    }

    /**
     * Returns the realtive size of the ArrayList.
     * @return size
     */
    public int size(){
        return sessions.size();
    }

    /**
     * Removes the specific training session.
     * @param session
     */
    public void remove(TrainingSession session){
        sessions.remove(session);
    }

    /**
     * Returns a boolean if the session is contained in the ArrayList.
     * @param session
     * @return boolean
     */
    public boolean contain(TrainingSession session){
        return sessions.contains(session);
    }
}
