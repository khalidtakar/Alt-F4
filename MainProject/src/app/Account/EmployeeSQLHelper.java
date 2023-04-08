package app.Account;

import app.JDBC;
import app.Sale.Ticket;

import java.sql.SQLException;
import java.util.ArrayList;


public class EmployeeSQLHelper extends JDBC {

    public EmployeeSQLHelper(){}

    /**
     * Looks up for existence of employee and correctly matching password in database,
     * if found employee is initialised and returned
     * @param username employee email
     * @param passwordHash hashed password (use hashing algorithm in employee controller)
     * @return Employee instance
     */
    public Employee findEmployee(String username, String passwordHash) {
        Employee employee = null;

        sql = "SELECT email," +
                "name " +
                "FROM Employee " +
                "WHERE email = ?" +
                "AND password = ?";

        try {
            //1 = 1st "?", 2 = 2nd "?"
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, passwordHash);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                employee = new Employee(resultSet.getString("email"),
                        resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return employee;
    }



    /**
     * checks if the email has this role, if yes then initialises role class
     * @param username
     * @return Manager instance
     */
    public Manager checkManagerEmail(String username){
        Manager manager = null;

        sql = "SELECT manID " +
                "FROM Manager " +
                "WHERE email = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                manager = new Manager(resultSet.getInt("manID"), username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return manager;
    }

    /**
     * checks if the email has this role, if yes then initialises role class
     * @param username
     * @return Administrator instance
     */
    public Administrator checkAdministratorEmail(String username){
        Administrator administrator = null;

        sql = "SELECT admID " +
                "FROM Administrator " +
                "WHERE email = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                administrator = new Administrator(resultSet.getInt("admID"), username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return administrator;
    }

    /**
     * checks if the email has this role, if yes then initialises role class
     * @param username
     * @return Advisor instance
     */
    public Advisor checkAdvisorEmail(String username){
        Advisor advisor = null;

        sql = "SELECT advID " +
                "FROM Advisor " +
                "WHERE email = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                advisor = new Advisor(resultSet.getInt("advID"), username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return advisor;
    }

    public ArrayList<Employee> getAdvisors(){
        ArrayList<Employee> advisors = new ArrayList<>();

        sql = "SELECT a.advID, " +
                "a.email, " +
                "e.name " +
                "FROM Advisor a, Employee e " +
                "WHERE a.email = e.email";

        try {
            statement = connection.createStatement();
            resultSet = preparedStatement.executeQuery(sql);

            //for each row found, initialise a new Employee which is an advisor and add to arraylist
            while (resultSet.next()) {
                advisors.add(new Employee(resultSet.getString("email"),
                        resultSet.getString("name"),
                        new Advisor(resultSet.getInt("advID"),
                                resultSet.getString("email"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return advisors;
    }
}

