package com.example.javaeloadasbeadando.controllers;

import com.oanda.v20.ContextBuilder;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.trade.Trade;
import com.oanda.v20.trade.TradeCloseRequest;
import com.oanda.v20.trade.TradeSpecifier;
import javafx.fxml.FXML;
import com.oanda.v20.Context;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PozicioZarasController {
    static Context ctx;
    static AccountID accountId;
    @FXML
    public ComboBox idBox;
    @FXML
    private Label welcomeText;
    @FXML
    public void initialize() throws IOException, ExecuteException, RequestException {

        Properties properties = new Properties();
        InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");

        properties.load(input);

        String apiKey = properties.getProperty("api.key");
        String userId = properties.getProperty("user.id");

        ctx = new ContextBuilder("https://api-fxpractice.oanda.com").setToken(apiKey).setApplication("StepByStepOrder").build();
        accountId = new AccountID(userId);

        List<String> lehetsegesertekek = Nyitottlistaz();

        idBox.getItems().addAll(lehetsegesertekek);
    }

    @FXML
    protected void onClick() {
        try {
            String id = (String) idBox.getSelectionModel().getSelectedItem();

            if(true) Zárás(id);
            welcomeText.setText(welcomeText.getText() + "\n" +"Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void Zárás(String id){
        welcomeText.setText(welcomeText.getText() + "\n" +"Close a Trade");
        try {
            String tradeId=id; // a zárni kívánt tradeId
            ctx.trade.close(new TradeCloseRequest(accountId, new TradeSpecifier(tradeId)));
            welcomeText.setText(welcomeText.getText() + "\n" +"tradeId: "+tradeId);

            List<String> lehetsegesertekek = Nyitottlistaz();
            idBox.getItems().removeAll();
            idBox.getItems().addAll(lehetsegesertekek);
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    List<String> Nyitottlistaz() throws ExecuteException, RequestException {

        List<String> valami = new ArrayList<String>();

        List<Trade> trades = ctx.trade.listOpen(accountId).getTrades();
        for(Trade trade: trades)
            valami.add(trade.getId().toString());

        return valami;
    }
}