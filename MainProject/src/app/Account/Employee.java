package app.Account;

/**
 * Stores employee currently using the app
 */
public class Employee {
    private String email;
    private String name;

    public Employee(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
