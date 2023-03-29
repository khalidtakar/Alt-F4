package app.Tests;

import app.Sale.SaleController;

public class SaleTests {
    SaleController saleController = new SaleController();

    public SaleTests(){}

    /**
     * Use this test sparingly,
     * API key has 1000 uses per month
     */
    public void testExchangeRate(){
        System.out.println("Exchange rate from USD to GBP: ");
        System.out.println(saleController.getExchangeRate("GBP"));

        System.out.println("\n");
    }
}
