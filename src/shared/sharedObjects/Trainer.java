package shared.sharedObjects;

public class Trainer {
    private String name;
    private String surname;

    public Trainer(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getFullName(){
        return name + " " + surname;
    }
}
