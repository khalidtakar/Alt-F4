package app.Account;

/**
 * Stores employee currently using the app
 */
public class Employee {
    private String email;
    private String name;
    private String passwordHash;
    private String typeOfEmployee;

    private Administrator administrator = null;
    private Manager manager = null;
    private Advisor advisor = null;

    public Employee(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public Employee(String email, String name, Advisor advisor){
        this.email = email;
        this.name = name;
        this.advisor = advisor;
    }

    public Employee(){}

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
        typeOfEmployee = "administrator";
    }

    public void setManager(Manager manager) {
        this.manager = manager;
        typeOfEmployee = "manager";
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
        typeOfEmployee = "advisor";
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public Manager getManager() {
        return manager;
    }

    public Advisor getAdvisor() {
        return advisor;
    }

    public String getTypeOfEmployee() {
        return typeOfEmployee;
    }

    public void setTypeOfEmployee(String typeOfEmployee) {
        this.typeOfEmployee = typeOfEmployee;
    }
}
