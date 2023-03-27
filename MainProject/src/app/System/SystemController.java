package app.System;

import javax.swing.*;
import java.io.*;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class SystemController {
    private SystemSQLHelper systemSQLHelper = new SystemSQLHelper();

    public SystemController() {}

    /**
     * Loads all system settings from the database
     * @return instance of SystemSettings
     */
    public System load(){
        return systemSQLHelper.load();
    }

    /**
     * Sets a new commission rate for the system in the DB
     * @param commissionRate double
     * @return updated system settings
     */
    public System setCommissionRate(double commissionRate){
        systemSQLHelper.setCommissionRate(commissionRate);
        return this.load();
    }

    /**
     * Sets a new tax rate for the system in the DB
     * @param taxRate double, in DB stored as decimal(5,2)
     * @return updated system settings
     */
    public System setTaxRate(double taxRate){
        systemSQLHelper.setTaxRate(taxRate);
        return this.load();
    }

    /**
     * Sets a new auto backup frequency for the system in the DB
     * @param autoBackupFreqDays int how often you want system to auto backup
     * @return updated system settings
     */
    public System setAutoBackupFreqDays(int autoBackupFreqDays){
        systemSQLHelper.setAutoBackupFreqDays(autoBackupFreqDays);
        return this.load();
    }

    /**
     * creates a .sql file in backup folder,
     * with statements to reconstruct database in its current state
     */
    public static void backup() {
        try {
            //Creating Database Constraints
            String DBNAME = "in2018g11";
            String USERNAME = "in2018g11_a";
            String PASSWORD = "zj81TlQV";
            String SERVER = "smcse-stuproj00.city.ac.uk";
            String PORT = "3306";

            //Creating Path Constraints for backup saving
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy.HH.mm.ss");
            String backupDate = dateFormat.format(new Date());
            String backupPath = "backup" + File.separator + "backup_" + backupDate + ".sql";

            //Used to create a cmd command
            String executeCmd = "mysqldump --host " + SERVER + " --port " + PORT + " --user " + USERNAME + " --password=" + PASSWORD + " --skip-column-statistics " + DBNAME + " > \"" + backupPath + "\"";

            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", executeCmd);
            builder.redirectErrorStream(true);
            Process process = builder.start();

            //Read the output and error streams from the process
            InputStream inputStream = process.getInputStream();
            InputStream errorStream = process.getErrorStream();
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputStream));
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));

            //Print any output from the process
            String line;
            while ((line = inputReader.readLine()) != null) {
                java.lang.System.out.println(line);
            }

            //Print any error messages from the process
            while ((line = errorReader.readLine()) != null) {
                java.lang.System.err.println(line);
            }

            //Wait for the process to finish
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                java.lang.System.out.println("Backup complete.");
            } else {
                java.lang.System.err.println("Backup failed with exit code " + exitCode);
            }

        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Restores database using .sql dump file
     * @param filePath path to sql dump file
     */
    public static void restore(String filePath) {
        try {
            //Creating database constraints
            String DBNAME = "in2018g11";
            String USERNAME = "in2018g11_a";
            String PASSWORD = "zj81TlQV";
            String SERVER = "smcse-stuproj00.city.ac.uk";
            String PORT = "3306";

            //Used to create a cmd command
            String executeCmd = "mysql --host " + SERVER + " --port " + PORT + " --user " + USERNAME + " --password=" + PASSWORD + " " + DBNAME + " < \"" + filePath + "\"";

            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", executeCmd);
            builder.redirectErrorStream(true);
            Process process = builder.start();

            //Read the output and error streams from the process
            InputStream inputStream = process.getInputStream();
            InputStream errorStream = process.getErrorStream();
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputStream));
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));

            //Print any output from the process
            String line;
            while ((line = inputReader.readLine()) != null) {
                java.lang.System.out.println(line);
            }

            //Print any error messages from the process
            while ((line = errorReader.readLine()) != null) {
                java.lang.System.err.println(line);
            }

            //Wait for the process to finish
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                java.lang.System.out.println("Restore complete.");
            } else {
                java.lang.System.err.println("Restore failed with exit code " + exitCode);
            }

        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
