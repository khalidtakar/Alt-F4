package app.Account;

public class Advisor {
    private int advisorID;
    private String username;

    public Advisor(int advisorID, String username) {
        this.advisorID = advisorID;
        this.username = username;
    }

    public Advisor() {
    }

    public int getAdvisorID() {
        return advisorID;
    }

    public String getUsername() {
        return username;
    }
}
