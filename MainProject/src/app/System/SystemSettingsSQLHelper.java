package app.System;

import app.JDBC;

import java.sql.SQLException;

public class SystemSettingsSQLHelper extends JDBC {
    public SystemSettingsSQLHelper() {}

    public static SystemSettings load(){
        SystemSettings systemSettings = null;
        sql = "SELECT localCurrency," +
                "commissionRate," +
                "taxRate," +
                "autoBackupFreqDays," +
                "lastBackup," +
                "exchangeRate " +
                "FROM SystemSettings";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            //for each row found, initialise a new Ticket and add to arraylist
            if(resultSet.next()) {
                systemSettings = new SystemSettings(
                        resultSet.getString("localCurrency"),
                        resultSet.getDouble("commissionRate"),
                        resultSet.getDouble("taxRate"),
                        resultSet.getDouble("exchangeRate"),
                        resultSet.getInt("autoBackupFreqDays"),
                        resultSet.getDate("lastBackup"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return systemSettings;
    }
}
