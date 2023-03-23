package app.Account;

import app.JDBC;

import java.sql.SQLException;


public class EmployeeSQLHelper extends JDBC {

    public EmployeeSQLHelper(){}

    /**
     * Looks up for existence of employee and correctly matching password in database,
     * if found employee is initialised and returned
     * @param username employee email
     * @param passwordHash hashed password (use hashing algorithm in employee controller)
     * @return Employee instance
     */
    public Employee findEmployee(String username, String passwordHash){
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

            if(resultSet.next()) {
                employee = new Employee(resultSet.getString("email"),
                        resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return employee;
    }

}
