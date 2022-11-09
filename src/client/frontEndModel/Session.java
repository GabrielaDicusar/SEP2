package client.frontEndModel;

import java.util.ArrayList;

public class Session {

    private ArrayList<Session>sessions;

    public Session(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }
    public ArrayList<Session> getSessions() {
        return sessions;
    }
    public void setSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }
}
