package app.Sale;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class SaleController {
    protected ArrayList<Sale> sales;
    protected SaleSQLHelper saleSQLHelper = new SaleSQLHelper();

    private static final String MAINCURRENCY = "USD";
    private final String CURRENCYAPIKEY = "cc5ba27a62204b1599c09a045029e3cf";

    public SaleController(){}

//    public Sale makeNewSale(int advID,
//                            int priceLocal,
//                            boolean isDomestic,
//                            boolean isPaid){
//        Sale sale = null;
//
//        return sale;
//    }
//
//    public Sale makeNewSale(int advID,
//                            int cardNo,
//                            String paymentProvider,
//                            int priceLocal,
//                            boolean isDomestic,
//                            boolean isPaid){
//        Sale sale = null;
//
//        return sale;
//    }

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

    public ArrayList<Sale> getAllSales(){
        return saleSQLHelper.getAllSales();
    }

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

    public ArrayList<Sale> getAdvisorsCompletedSales(int advID){
        ArrayList<Sale> advisorsSales = getAdvisorsSales(advID);
        ArrayList<Sale> completedSales = new ArrayList<>();

        for(Sale i : advisorsSales){
            if(i.isPaid() == true) {
                completedSales.add(i);
            }
        }

        return completedSales;
    }

    public ArrayList<Sale> getAdvisorsLateSales(int advID){
        ArrayList<Sale> advisorsSales = getAdvisorsSales(advID);
        ArrayList<Sale> lateSales = new ArrayList<>();

        for(Sale i : advisorsSales){
            if(i.isPaid() == false) {
                lateSales.add(i);
            }
        }

        return lateSales;
    }
}
