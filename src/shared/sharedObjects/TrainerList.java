package shared.sharedObjects;

import java.io.Serializable;
import java.util.ArrayList;

public class TrainerList implements Serializable {
    private ArrayList<Trainer> trainers;

    public TrainerList(){
        trainers = new ArrayList<>();
    }

    public void addTrainor(Trainer trainer){
        trainers.add(trainer);
    }

    public void removeTrainer(Trainer trainer){
        trainers.remove(trainer);
    }

    public ArrayList getTrainers(){
        return trainers;
    }
}
