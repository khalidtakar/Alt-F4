package app.Account;

import app.JDBC;
import app.Sale.Ticket;

import java.sql.SQLException;
import java.util.ArrayList;


public class EmployeeSQLHelper extends JDBC {

    public EmployeeSQLHelper() {
    }

    /**
     * Looks up for existence of employee and correctly matching password in database,
     * if found employee is initialised and returned
     *
     * @param username     employee email
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
     *
     * @param username
     * @return Manager instance
     */
    public Manager checkManagerEmail(String username) {
        Manager manager = null;

        sql = "SELECT manID " +
                "FROM Manager " +
                "WHERE email = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
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
     *
     * @param username
     * @return Administrator instance
     */
    public Administrator checkAdministratorEmail(String username) {
        Administrator administrator = null;

        sql = "SELECT admID " +
                "FROM Administrator " +
                "WHERE email = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
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
     *
     * @param username
     * @return Advisor instance
     */
    public Advisor checkAdvisorEmail(String username) {
        Advisor advisor = null;

        sql = "SELECT advID " +
                "FROM Advisor " +
                "WHERE email = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                advisor = new Advisor(resultSet.getInt("advID"), username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return advisor;
    }


    /**
     * get all advisors
     * @return Arraylist of Employee objects that are advisors
     */
    public ArrayList<Employee> getAdvisors() {
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

    /**
     * change password for employee
     * @param employee employee
     */
    public void changePassword(Employee employee) {
        sql = "UPDATE Employee SET " +
                "password = ? " +
                "WHERE email = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getPasswordHash());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * add advisor to database
     * @param email advisor email
     * @param name name
     * @param passwordHash password hash, need to use doHashing() function
     */
    public void addAdvisor(String email, String name, String passwordHash){
        String sql1 = "INSERT INTO Employee (email, " +
                "password, " +
                "name) " +
                "VALUES (?, ?, ?)";

        String sql2 = "INSERT INTO Advisor (email) " +
                "VALUES (?)";

        try {
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, passwordHash);
            preparedStatement.setString(3, name);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}

