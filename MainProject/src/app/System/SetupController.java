package app.System;

import java.io.*;
import java.util.Properties;

public class SetupController {
    protected static String ADMINUSERNAME = "in2018g11_a";
    protected static String ADMINPASSWORD = "zj81TlQV";
    protected static String USERUSERNAME = "in2018g11_d";
    protected static String USERPASSWORD = "qj3GNH0I";
    protected static String url;
    protected static String serverName;
    protected static String portNumber;
    protected static String dbName;

    private String propertiesPath = "systemSetup.properties";


    public SetupController(){
        loadSetup();
    }

    public void updateSetup(String serverName,
                            String url,
                            String portNumber,
                            String dbName) {
        try (OutputStream output = new FileOutputStream(propertiesPath)) {
            Properties properties = new Properties();

            properties.setProperty("serverName", serverName);
            properties.setProperty("url", url);
            properties.setProperty("portNumber", portNumber);
            properties.setProperty("dbName", dbName);


            properties.store(output, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadSetup(){
        try (InputStream input = new FileInputStream(propertiesPath)){
            Properties properties = new Properties();

            properties.load(input);

            url = properties.getProperty("url");
            serverName = properties.getProperty("serverName");
            portNumber = properties.getProperty("portNumber");
            dbName = properties.getProperty("dbName");

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } ;
    }

    public String getADMINUSERNAME() {
        return ADMINUSERNAME;
    }

    public String getADMINPASSWORD() {
        return ADMINPASSWORD;
    }

    public String getUSERUSERNAME() {
        return USERUSERNAME;
    }

    public String getUSERPASSWORD() {
        return USERPASSWORD;
    }

    public String getUrl() {
        return url;
    }

    public String getServerName() {
        return serverName;
    }

    public String getPortNumber() {
        return portNumber;
    }

    public String getDbName() {
        return dbName;
    }

    public static void setUrl(String url) {
        SetupController.url = url;
    }

    public static void setServerName(String serverName) {
        SetupController.serverName = serverName;
    }

    public static void setPortNumber(String portNumber) {
        SetupController.portNumber = portNumber;
    }

    public static void setDbName(String dbName) {
        SetupController.dbName = dbName;
    }
}
