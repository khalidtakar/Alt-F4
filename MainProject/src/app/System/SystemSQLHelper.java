package app.System;

import app.JDBC;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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

                LocalDate lastBackup = system.getLastBackup().toLocalDate();
                LocalDate today = LocalDate.now();
                long daysSinceLastBackup = ChronoUnit.DAYS.between(lastBackup, today);
                system.setDaysSinceLastBackup((int)daysSinceLastBackup);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return system;
    }

    /**
     * updates systemSettings in DB to current state in app
     * @param system instance of system with the latest settings
     */
    public void updateSettings(System system){
        sql="UPDATE SystemSettings " +
                "SET commissionRate = ?," +
                "taxRate = ?," +
                "autoBackupFreqDays = ?," +
                "lastBackup = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, system.getCommissionRate());
            preparedStatement.setDouble(2, system.getTaxRate());
            preparedStatement.setInt(3, system.getAutoBackupFreqDays());
            preparedStatement.setDate(4, system.getLastBackup());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
