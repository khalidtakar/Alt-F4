package app.Account;

public class AdministratorController {
    Employee employee;
    Administrator administrator;
    AdministratorSQLHelper administratorSQLHelper;

    public AdministratorController (){
        administratorSQLHelper = new AdministratorSQLHelper();
    }
    

}
