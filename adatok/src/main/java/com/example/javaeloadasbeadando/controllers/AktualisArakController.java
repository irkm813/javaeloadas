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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;


public class AktualisArakController {

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Label resultArea;

    String[] currencyPairs = {"EUR:USD", "USD:JPY", "GBP:USD", "USD:CHF"};

    @FXML
    public void initialize() {
        // Az opciók hozzáadása a legördülő menühöz
        comboBox.getItems().addAll(currencyPairs);
    }

    @FXML
    public int onClick(ActionEvent actionEvent) throws IOException {

        if (comboBox.getSelectionModel().getSelectedItem() == null) {
            resultArea.setText("Az érték nem hagyható üresen!");
            return 1;
        }

        String chosenValues = comboBox.getSelectionModel().getSelectedItem().replace(":","_");
        String val1 = chosenValues.split("_")[0];
        String val2 = chosenValues.split("_")[1];

        System.out.println(chosenValues);

        Properties properties = new Properties();
        InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");

        properties.load(input);

        String apiKey = properties.getProperty("api.key");
        String userId = properties.getProperty("user.id");

        Context ctx = new ContextBuilder("https://api-fxpractice.oanda.com").setToken(apiKey).setApplication("javabeadando").build();

        AccountID accountId = new AccountID(userId);

        try {
            PricingGetRequest request = new PricingGetRequest(accountId, List.of(chosenValues));
            PricingGetResponse resp = ctx.pricing.get(request);
            request.setSince(resp.getTime());
            for (ClientPrice price : resp.getPrices())
                resultArea.setText("1 "+val1+" értéke: "+price.getAsks().get(1).getPrice() + val2 +"\n");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
