package app.Account;

/**
 * Stores administrator ID
 */
public class Administrator {
    private int admID;
    private String username;

    public Administrator(int admID, String username) {
        this.admID = admID;
        this.username = username;
    }

    public Administrator() {
    }

    public int getAdmID() {
        return admID;
    }

    public String getUsername() {
        return username;
    }
}
