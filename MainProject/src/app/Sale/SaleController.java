package app.Sale;

import app.System.System;
import app.System.SystemController;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;


public class SaleController {
    protected ArrayList<Sale> sales;
    protected SaleSQLHelper saleSQLHelper = new SaleSQLHelper();

    private static final String MAINCURRENCY = "USD";
    private final String CURRENCYAPIKEY = "cc5ba27a62204b1599c09a045029e3cf";

    public SaleController(){}


    /**
     * Returns the exchange rate from USD to a localCurrency
     * @param localCurrency currency code eg "USD", "GBP"
     * @return exchange rate double
     */
    public double getExchangeRate(String localCurrency){
        double exchangeRate = 0;
        try {
            //Make url and make connection
            URL url = new URL("https://openexchangerates.org/api/latest.json?app_id=" + CURRENCYAPIKEY
                    + "&base=" + MAINCURRENCY
                    + "&symbols=" + localCurrency);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            //Get json
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            //Read currency conversion from json
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse JSON response and extract conversion rate
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONObject rates = jsonResponse.getJSONObject("rates");
            exchangeRate = rates.getDouble(localCurrency);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return exchangeRate;
    }

    /**
     *  gets all sales
     * @return returns arraylist of Sale instances from database
     */
    public ArrayList<Sale> getAllSales(){
        return saleSQLHelper.getAllSales();
    }

    /**
     * Gets all sales that contain advisor ID
     * @param advID advisor ID
     * @return ArrayList of Sale instances
     */
    public ArrayList<Sale> getAdvisorsSales(int advID){
        ArrayList<Sale> sales = getAllSales();
        ArrayList<Sale> advisorsSales = new ArrayList<>();

        for(Sale i : sales){
            if(i.getAdvisorID() == advID) {
                advisorsSales.add(i);
            }
        }

        return advisorsSales;
    }

    /**
     * Gets sale by sale ID
     * @param saleID sale id
     * @return Sale
     */
    public Sale getSaleByID(int saleID){
        return saleSQLHelper.saleByID(saleID);
    }

    /**
     * Gets all completed sales associated with advisor ID
     * @param advID advisor Id
     * @return ArrayList of completed Sale instances for advisor
     */
    public ArrayList<Sale> getAdvisorsCompletedSales(int advID){
        ArrayList<Sale> advisorsSales = getAdvisorsSales(advID);
        ArrayList<Sale> completedSales = new ArrayList<>();

        for(Sale i : advisorsSales){
            if(i.isPaid()) {
                completedSales.add(i);
            }
        }

        return completedSales;
    }

    /**
     * Gets all late sales associated with advisor ID
     * @param advID advisor Id
     * @return ArrayList of late Sale instances for advisor
     */
    public ArrayList<Sale> getAdvisorsLateSales(int advID){
        ArrayList<Sale> advisorsSales = getAdvisorsSales(advID);
        ArrayList<Sale> lateSales = new ArrayList<>();

        for(Sale i : advisorsSales){
            if(!i.isPaid()) {
                lateSales.add(i);
            }
        }

        return lateSales;
    }

    /**
     * update sale database state with current state of Sale class instance
     * @param sale Sale instance
     */
    public void updateSale(Sale sale){
        saleSQLHelper.updateSale(sale);
    }

    /**
     * Add new sale to database
     * @param advisorID advisor ID
     * @param customerEmail customer email
     * @param paymentType payment type
     * @param price price
     * @param cardNo card number
     * @param paymentProvider card provider eg. VISA
     * @param localCurrency local currency eg. GBP, USD
     * @param isPaid is paid?
     * @param ticket Ticket instance associated with sale
     */
    public void newSale(int advisorID, String customerEmail, String paymentType, double price, Long cardNo, String paymentProvider, String localCurrency, boolean isPaid, Ticket ticket){
        Sale sale = new Sale();
        double priceUSD;

        sale.setAdvisorID(advisorID);
        sale.setCustomerEmail(customerEmail);
        sale.setDateSold(Date.valueOf(LocalDate.now()));
        sale.setPaymentType(paymentType);
        sale.setCardNo(cardNo);
        sale.setPaymentProvider(paymentProvider);
        sale.setPriceLocal(price);
        sale.setLocalCurrency(localCurrency);
        if(localCurrency != "USD"){
            double exchangeRate = getExchangeRate(localCurrency);
            sale.setExchangeRate(exchangeRate);
            sale.setDomestic(false);
            sale.setPriceUSD(price / exchangeRate );
            priceUSD = price / exchangeRate;
        }else{
            sale.setExchangeRate(1);
            sale.setDomestic(true);
            sale.setPriceUSD(price);
            priceUSD = price;
        }
        CustomerController customerController = new CustomerController();
        Customer customer = customerController.getCustomerByEmail(customerEmail);

        FlexibleDiscountController flexibleDiscountController = new FlexibleDiscountController();
        ArrayList<FlexibleDiscount> discounts = flexibleDiscountController.getFlexibleDiscountsForCustomer(customerEmail);

        FlexibleDiscount greatest = new FlexibleDiscount("",0,0,0);
        try {
            double discount = calculateDiscounts(customerEmail, discounts, price);
            java.lang.System.out.println(discount);
            sale.setSaleDiscountAmount(priceUSD * 0.01 * discount);
            java.lang.System.out.println(discount);
        }catch (Exception e){
            e.printStackTrace();
            sale.setSaleDiscountAmount(0);
        }

        System system = new System();
        SystemController systemController = new SystemController(system);
        system = systemController.getLoad();

        sale.setTaxAmount(system.getTaxRate() * 0.01 * price);
        sale.setSaleCommissionAmount(priceUSD * (0.01 * system.getCommissionRate()));
        sale.setPaid(isPaid);
        if(isPaid){
            sale.setDatePaid(Date.valueOf(LocalDate.now()));
        }

        sale.setRefunded(false);

        int saleID = saleSQLHelper.addNewSale(sale);

        TicketController ticketController = new TicketController();
        ticket.setSaleID(saleID);
        ticketController.updateTicket(ticket);

        try {
            customer.setSpentThisMonth(customer.getSpentThisMonth() + priceUSD);
            customer.setDiscountToRefundOrReturn(customer.getDiscountToRefundOrReturn() + (price * 0.01 * greatest.getDiscountRate()));
            customerController.updateCustomer(customer);
        }catch (Exception e){

        }
    }

    /**
     * return best discount for given customer
     * @param customerEmail customer email
     * @param discounts discounts arraylist associated with customer
     * @param price sale price
     * @return best discount rate
     */
    public double calculateDiscounts(String customerEmail, ArrayList<FlexibleDiscount> discounts, double price) {
        FlexibleDiscountController flexibleDiscountController = new FlexibleDiscountController();
        CustomerController customerController = new CustomerController();

        Customer customer = customerController.getCustomerByEmail(customerEmail);

        FlexibleDiscount greatest = new FlexibleDiscount("",0,0,0);
        for (FlexibleDiscount i : discounts) {
            if (((i.getUpperBoundary() == 0) && i.getLowerBoundary() == 0) && (i.getDiscountRate() > greatest.getDiscountRate())) {
                greatest = i;
            }
            if (((i.getUpperBoundary() == 0) && i.getLowerBoundary() < customer.getSpentThisMonth()) && (i.getDiscountRate() > greatest.getDiscountRate())) {
                greatest = i;
            }
            if ((customer.getSpentThisMonth() <= i.getUpperBoundary())
                    && (customer.getSpentThisMonth() >= i.getLowerBoundary())
                    && (i.getDiscountRate() > greatest.getDiscountRate())) {
                greatest = i;
            }
        }

        return greatest.getDiscountRate();
    }

    /**
     * Update late payment sale to complete
     * @param sale Sale instance
     * @param cardNo card number
     * @param provider card provider eg. VISA
     * @param paymentType payment type eg. cash/ card
     */
    public void makeLatePayment(Sale sale, Long cardNo, String provider,String paymentType){
        sale.setCardNo(cardNo);
        sale.setPaymentProvider(provider);
        sale.setPaymentType(paymentType);
        sale.setDatePaid(Date.valueOf(LocalDate.now()));
        sale.setPaid(true);

        saleSQLHelper.updateSale(sale);
    }

    /**
     * Complete a late payment ticket without payment (used for late refunds)
     * @param sale late Sale instance
     */
    public void cancelLatePayment(Sale sale){
        sale.setPaid(true);
        sale.setRefunded(true);
        sale.setDatePaid(Date.valueOf(LocalDate.now()));
        saleSQLHelper.updateSale(sale);
    }
}
