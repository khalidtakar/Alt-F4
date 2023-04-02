package app.System;


import java.io.*;
import java.sql.Date;
import java.time.LocalDate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class SystemController {
    private SystemSQLHelper systemSQLHelper = new SystemSQLHelper();
    private System system;

    private static final String DBNAME = "in2018g11";
    private static final String USERNAME = "in2018g11_a";
    private static final String PASSWORD = "zj81TlQV";
    private static final String SERVER = "smcse-stuproj00.city.ac.uk";
    private static final String PORT = "3306";

    public SystemController(System system) {
        this.system = system;
    }

    /**
     * Loads all system settings from the database
     * @return instance of SystemSettings
     */
    public void load(){
        System loadedSystem = systemSQLHelper.load();
        system.setLastBackup(loadedSystem.getLastBackup());
        system.setCommissionRate(loadedSystem.getCommissionRate());
        system.setTaxRate(loadedSystem.getTaxRate());
        system.setAutoBackupFreqDays(loadedSystem.getAutoBackupFreqDays());
    }

    /**
     * Sets a new commission rate for the system in the DB
     * @param commissionRate double
     * @return updated system settings
     */
    public void setCommissionRate(double commissionRate){
        system.setCommissionRate(commissionRate);
        systemSQLHelper.updateSettings(system);
    }

    /**
     * Sets a new tax rate for the system in the DB
     * @param taxRate double, in DB stored as decimal(5,2)
     * @return updated system settings
     */
    public void setTaxRate(double taxRate){
        system.setTaxRate(taxRate);
        systemSQLHelper.updateSettings(system);
    }

    /**
     * Sets a new auto backup frequency for the system in the DB
     * @param autoBackupFreqDays int how often you want system to auto backup
     * @return updated system settings
     */
    public void setAutoBackupFreqDays(int autoBackupFreqDays){
        system.setAutoBackupFreqDays(autoBackupFreqDays);
        systemSQLHelper.updateSettings(system);
    }

    /**
     * creates a .sql file in backup folder,
     * with statements to reconstruct database in its current state
     */
    public void backup() {
        try {
            //Creating Path to save backup and date-time format to use in the file name
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy.HH.mm.ss");
            String backupDate = dateFormat.format(new java.util.Date());
            String backupPath = "backup" + File.separator + "backup_" + backupDate + ".sql";

            //Create cmd command to run mysqldump
            String executeCmd = "mysqldump --host " + SERVER
                    + " --port " + PORT
                    + " --user " + USERNAME
                    + " --password=" + PASSWORD
                    + " --skip-column-statistics "
                    + DBNAME
                    + " > \"" + backupPath + "\"";

            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", executeCmd);
            builder.redirectErrorStream(true);
            Process process = builder.start();

            //Read the output and error streams from the process
            InputStream errorStream = process.getErrorStream();
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));

            //Print any error messages from the process
            String line;
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

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        LocalDate now = LocalDate.now();
        Date sqlNow = java.sql.Date.valueOf(now);

        system.setLastBackup(sqlNow);
        systemSQLHelper.updateSettings(system);
    }

    /**
     * Restores database using .sql dump file
     * @param filePath path to sql dump file
     */
    public static void restore(String filePath) {
        try {
            //Create cmd command to execute a .sql file
            String executeCmd = "mysql --host " + SERVER
                    + " --port " + PORT
                    + " --user " + USERNAME
                    + " --password=" + PASSWORD
                    + " " + DBNAME
                    + " < \"" + filePath + "\"";

            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", executeCmd);
            builder.redirectErrorStream(true);
            Process process = builder.start();

            //Read the output and error streams from the process
            InputStream errorStream = process.getErrorStream();
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));

            //Print any error messages from the process
            String line;
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
