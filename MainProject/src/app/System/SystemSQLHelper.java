package app.System;

import app.JDBC;

import java.sql.SQLException;

public class SystemSQLHelper extends JDBC {
    public SystemSQLHelper() {}

    /**
     * Loads all system settings from the database
     * @return latest copy of system settings
     */
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

    /**
     * update commissionRate in database
     * @param commissionRate in DB stored as decimal(5,2)
     */
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

    /**
     * update tax rate in database
     * @param taxRate in DB stored as decimal(5,2)
     */
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

    /**
     * update auto backup frequency in database
     * @param autoBackupFreqDays
     */
    public void setAutoBackupFreqDays(int autoBackupFreqDays){
        sql="UPDATE SystemSettings " +
                "SET autoBackupFreqDays = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, autoBackupFreqDays);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
