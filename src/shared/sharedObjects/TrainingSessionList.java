package shared.sharedObjects;

import java.util.ArrayList;
import java.util.List;

public class TrainingSessionList {
    private List<TrainingSession> sessions;

    public TrainingSessionList()
    {
        sessions = new ArrayList<>();
    }

    public List<TrainingSession> getTrainingSessions() {
        return sessions;
    }
    public void addSession(TrainingSession trainingSession)
    {
        sessions.add(trainingSession);
    }

    public void removeTrainingSession(TrainingSession trainingSession)
    {
        sessions.remove(trainingSession);
    }

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
