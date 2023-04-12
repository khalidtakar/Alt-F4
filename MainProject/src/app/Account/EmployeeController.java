package app.Account;

import app.Main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Implements employee access to system
 */
public class EmployeeController {
    private EmployeeSQLHelper employeeSQLHelper = new EmployeeSQLHelper();
    private Employee employee;

    private Main main;

    public EmployeeController(Main main, Employee employee){
        this.main = main;
        this.employee = employee;
    }

    public EmployeeController(Employee employee){
        this.employee = employee;
    }

    /**
     * Looks up employee
     * initialises employee role
     * sets Employee.typeOfEmployee to role name
     * @param username employee email
     * @param password plaintext password
     * @return instance of Employee if found in DB
     */
    public void login(String username, String password){
        //Hash plain text password and lookup employee in database
        String passwordHash = doHashing(username, password);

        //Make temporary employee load instance to maintain original employee reference
        //Get and set data from temporary to main employee
        Employee employeeLoad = employeeSQLHelper.findEmployee(username, passwordHash);
        employee.setName(employeeLoad.getName());
        employee.setEmail(employeeLoad.getEmail());

        //attempts to initialise all roles for the given email
        employee.setAdvisor(employeeSQLHelper.checkAdvisorEmail(employee.getEmail()));
        employee.setAdministrator(employeeSQLHelper.checkAdministratorEmail(employee.getEmail()));
        employee.setManager(employeeSQLHelper.checkManagerEmail(employee.getEmail()));

        //for the role which was initialied (is no longer null)
        //the name of it is assigned to typeOfEmployee
        if(main != null) {
            if (employee.getAdvisor() != null) {
                main.goToMainPageAdvisor(employee);
            } else if (employee.getAdministrator() != null) {
                main.goToMainPageAdmin(employee);
            } else if (employee.getManager() != null) {
                main.goToMainPageManager(employee);
            }
        }
    }

    public void changePassword(String password){
        employee.setPasswordHash(doHashing(employee.getEmail(), password));
        employeeSQLHelper.changePassword(employee);
    }


    /**Hashing algorithm, takes plain text and returns a hash to be stored/compared
     * with/in database
     * @param salt unique string to be used as salt, preferably email
     * @param password plain text password to be hashed
     * @return [64 character] SHA-256 hashed password, can be "safely" stored in database
     */
    public static String doHashing (String salt, String password) {
        final String PEPPER = "qFg@qVSdgS7#+a)nDgfR";
        String hash = null;

        try {
            //select hashing algorithm
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            //append salt and pepper
            messageDigest.update(salt.getBytes());
            messageDigest.update(PEPPER.getBytes());

            //hash the password
            byte[] bytes = messageDigest.digest(password.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for (byte aByte : bytes) {
                stringBuilder.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            hash = stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hash;
    }
}
