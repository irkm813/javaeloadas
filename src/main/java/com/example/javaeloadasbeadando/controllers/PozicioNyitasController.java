package com.example.javaeloadasbeadando.controllers;

import com.oanda.v20.ContextBuilder;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.order.MarketOrderRequest;
import com.oanda.v20.order.OrderCreateRequest;
import com.oanda.v20.order.OrderCreateResponse;
import com.oanda.v20.primitives.InstrumentName;
import com.oanda.v20.trade.Trade;
import javafx.fxml.FXML;
import com.oanda.v20.Context;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class PozicioNyitasController {

    static Context ctx;
    static AccountID accountId;
    @FXML
    private Label welcomeText;

    @FXML
    private ComboBox<String> devizaBox;
    @FXML
    private ComboBox<String> devizaMennyiseg;
    @FXML
    private ComboBox<String> devizaIrany;

    String[] currencyPairs = {"EUR:USD", "USD:JPY", "GBP:USD", "USD:CHF"};
    String[] iranyok = {"LONG","SHORT"};
    String[] value = {"10","20","30","40","50"};

    @FXML
    public void initialize() {
        // Az opciók hozzáadása a legördülő menühöz
        devizaBox.getItems().addAll(currencyPairs);
        devizaIrany.getItems().addAll(iranyok);
        devizaMennyiseg.getItems().addAll(value);
    }

    @FXML
    protected void onClick() throws IOException {

        welcomeText.setText("");

        Properties properties = new Properties();
        InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");

        properties.load(input);

        String apiKey = properties.getProperty("api.key");
        String userId = properties.getProperty("user.id");

        String par = devizaBox.getSelectionModel().getSelectedItem().replace(":","_");
        String irany = devizaIrany.getValue().toString();
        String mennyiseg = devizaMennyiseg.getValue().toString();

        try {
            ctx = new ContextBuilder("https://api-fxpractice.oanda.com").setToken(apiKey).setApplication("StepByStepOrder").build();
            accountId = new AccountID(userId);
            if(true) Nyitás(par,mennyiseg,irany);
            welcomeText.setText(welcomeText.getText() + "\n" +"Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void Nyitás(String devizaPar,String mennyiseg, String irany){
        welcomeText.setText(welcomeText.getText() + "\n" +"Place a Market Order");
        InstrumentName instrument = new InstrumentName(devizaPar);

        int deviza = Integer.parseInt(mennyiseg);

        if (irany == "SHORT") {
            deviza = 0-deviza;
        }

        try {
            OrderCreateRequest request = new OrderCreateRequest(accountId);
            MarketOrderRequest marketorderrequest = new MarketOrderRequest();
            marketorderrequest.setInstrument(instrument);
            // Ha pozitív, akkor LONG, ha negatív, akkor SHORT:
            marketorderrequest.setUnits(deviza);
            request.setOrder(marketorderrequest);
            OrderCreateResponse response = ctx.order.create(request);
            welcomeText.setText(welcomeText.getText() + "\n" +"tradeId: "+response.getOrderFillTransaction().getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}