package com.mycompany.currency.converter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/currency")
public class CurrencyConverter {

    // Exchange rates for currency conversion
    private static final Map<String, Double> exchangeRates = new HashMap<>();

    static {
        // Sample exchange rates
        exchangeRates.put("USD_TO_INR", 83.0);  // USD to INR
        exchangeRates.put("EUR_TO_INR", 90.0);  // EUR to INR
        exchangeRates.put("USD_TO_EUR", 0.85);  // USD to EUR
    }

    @GET
    @Path("/convert")
    @Produces(MediaType.TEXT_PLAIN) // Change to plain text
    public Response convertCurrency(@QueryParam("from") String from,
                                    @QueryParam("to") String to,
                                    @QueryParam("amount") double amount) {
        String key = from.toUpperCase() + "_TO_" + to.toUpperCase();

        // Check if the conversion rate exists
        if (!exchangeRates.containsKey(key)) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Exchange rate for " + key + " not available.")
                    .build();
        }

        // Get the conversion rate and calculate the result
        double rate = exchangeRates.get(key);
        double convertedAmount = amount * rate;

        // Prepare the result in plain text format
        String result = "Amount: " + amount + " " + from + " is converted to " + convertedAmount + " " + to;

        return Response.ok(result).build();
    }
}
