package app.Account;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Implements employee access to system
 */
public class EmployeeController {
    private EmployeeSQLHelper employeeSQLHelper = new EmployeeSQLHelper();
    private Employee employee;

    public EmployeeController(){}

    /**
     * Looks up employee
     * initialises employee role
     * sets Employee.typeOfEmployee to role name
     * @param username employee email
     * @param password plaintext password
     * @return instance of Employee if found in DB
     */
    public Employee login(String username, String password){
        String passwordHash = doHashing(password);
        employee = employeeSQLHelper.findEmployee(username, passwordHash);

        //attempts to initialise all roles for the given email
        employee.setAdvisor(employeeSQLHelper.checkAdvisorEmail(employee.getEmail()));
        employee.setAdministrator(employeeSQLHelper.checkAdministratorEmail(employee.getEmail()));
        employee.setManager(employeeSQLHelper.checkManagerEmail(employee.getEmail()));

        //for the role which was initialied (is no longer null)
        //the name of it is assigned to typeOfEmployee
        if(employee.getAdvisor() != null){
            employee.setTypeOfEmployee("advisor");
        }else if(employee.getAdministrator() != null){
            employee.setTypeOfEmployee("administrator");
        }else if(employee.getManager() != null){
            employee.setTypeOfEmployee("manager");
        }

        return employee;
    }


    /**Hashing algorithm, takes plain text and returns a hash to be stored/compared
     * with/in database
     * @param password plain text password to be hashed
     * @return [64 character] SHA-256 hashed password, can be "safely" stored in database
     */
    public static String doHashing (String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            messageDigest.update(password.getBytes());

            byte[] resultByteArray = messageDigest.digest();

            StringBuilder sb = new StringBuilder();

            for (byte b : resultByteArray) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
