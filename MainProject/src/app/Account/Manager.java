package app.Account;

/**
 * Stores manager ID
 */
public class Manager {
    private int manID;
    private String username;

    public Manager(int manID, String username) {
        this.manID = manID;
        this.username = username;
    }

    public Manager() {
    }

    public int getManID() {
        return manID;
    }

    public String getUsername() {
        return username;
    }
}

