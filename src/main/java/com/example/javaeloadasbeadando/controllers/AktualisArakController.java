package com.example.javaeloadasbeadando.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.oanda.v20.Context;
import com.oanda.v20.ContextBuilder;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.pricing.ClientPrice;
import com.oanda.v20.pricing.PricingGetRequest;
import com.oanda.v20.pricing.PricingGetResponse;
import com.oanda.v20.primitives.DateTime;
import javafx.fxml.FXML;


public class AktualisArakController {

    @FXML
    protected void onCLick() throws IOException {

        Properties properties = new Properties();
        InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");

        properties.load(input);

        String apiKey = properties.getProperty("api.key");
        String userId = properties.getProperty("user.id");

        Context ctx = new ContextBuilder("https://api-fxpractice.oanda.com").setToken(apiKey).setApplication("javabeadando").build();

        AccountID accountId = new AccountID(userId);

        List<String> instruments = new ArrayList<>( Arrays.asList("EUR_USD", "USD_JPY",
                "GBP_USD", "USD_CHF"));
        try {
            PricingGetRequest request = new PricingGetRequest(accountId, instruments);
            DateTime since = null;
            while (true) {
                if (since != null)
                {
                    System.out.println("Polling since " + since);
                    request.setSince(since);
                }
                PricingGetResponse resp = ctx.pricing.get(request);
                for (ClientPrice price : resp.getPrices())
                    System.out.println(price);
                since = resp.getTime();
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
