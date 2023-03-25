package app.System;

import app.JDBC;

import java.sql.SQLException;

public class SystemSQLHelper extends JDBC {
    public SystemSQLHelper() {}

    public System load(){
        System system = null;
        sql = "SELECT " +
                "commissionRate," +
                "taxRate," +
                "autoBackupFreqDays," +
                "lastBackup " +
                "FROM SystemSettings";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            //for each row found, initialise a new Ticket and add to arraylist
            if(resultSet.next()) {
                system = new System(
                        resultSet.getDouble("commissionRate"),
                        resultSet.getDouble("taxRate"),
                        resultSet.getInt("autoBackupFreqDays"),
                        resultSet.getDate("lastBackup"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return system;
    }

    public void setCommissionRate(double commissionRate){
        sql="UPDATE SystemSettings " +
                "SET commissionRate = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, commissionRate);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void setTaxRate(double taxRate){
        sql="UPDATE SystemSettings " +
                "SET taxRate = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, taxRate);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void setAutoBackupFreqDays(double autoBackupFreqDays){
        sql="UPDATE SystemSettings " +
                "SET autoBackupFreqDays = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, autoBackupFreqDays);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
