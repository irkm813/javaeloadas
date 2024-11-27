package com.example.javaeloadasbeadando.controllers;

import com.oanda.v20.ContextBuilder;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.account.AccountID;
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

public class NyitottPoziciokController {

    static Context ctx;
    static AccountID accountId;
    @FXML
    public ComboBox idBox;
    @FXML
    private Label welcomeText;
    @FXML
    protected void initialize() throws IOException {

        Properties properties = new Properties();
        InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");

        properties.load(input);

        String apiKey = properties.getProperty("api.key");
        String userId = properties.getProperty("user.id");

        try {
            ctx = new
                    ContextBuilder("https://api-fxpractice.oanda.com").setToken(apiKey).setApplication("StepByStepOrder").build();
            accountId =new AccountID(userId);
            if(true) NyitotttradekKiír();
            welcomeText.setText(welcomeText.getText() + "\n" +"Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void NyitotttradekKiír() throws ExecuteException, RequestException {
        welcomeText.setText(welcomeText.getText() + "\n" +"Nyitott tradek:");
        List<Trade> trades = ctx.trade.listOpen(accountId).getTrades();
        welcomeText.setText(welcomeText.getText() + "id\t"+"instruments\t"+"openedat\t"+"currentunits\t"+"price\t");
        for(Trade trade: trades)
            welcomeText.setText(welcomeText.getText() + "\n"+trade.getId()+"\t"+trade.getInstrument()+"\t"+trade.getOpenTime()+"\t"+trade.getCurrentUnits()+"\t"+trade.getPrice()+"\t"+trade.getUnrealizedPL());
    }
}
