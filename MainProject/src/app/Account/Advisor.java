package app.Account;

public class Advisor {
    private int advisorID;
    private String username;

    public Advisor(int advisorID, String username) {
        this.advisorID = advisorID;
        this.username = username;
    }

    public int getAdvisorID() {
        return advisorID;
    }
}
