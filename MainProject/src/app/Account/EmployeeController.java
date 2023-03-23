package app.Account;

import java.security.MessageDigest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Implements employee access to system
 */
public class EmployeeController {
    private EmployeeSQLHelper accountSQLHelper = new EmployeeSQLHelper();
    private Employee employee;

    private String username;
    private String passwordHash;


    public EmployeeController(){}

    /**
     *
     * @param username employee email
     * @param password plaintext password
     * @return instance of Employee if found in DB
     */
    public Employee login(String username, String password){

        this.username = username;
        passwordHash = doHashing(password);

        employee = accountSQLHelper.findEmployee(username, passwordHash);
        return employee;
    }

    /**Hashing algorithm, takes plain text and returns a hash to be stored/compared
     * with/in database
     * @param password plain text password to be hashed
     * @return [64 character] SHA-256 hashed password, can be "safely" stored in database
     */
    private static String doHashing (String password) {
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
