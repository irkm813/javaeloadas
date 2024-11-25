package com.example.javaeloadasbeadando.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
//import com.oanda.v20.account.AccountID;
//import com.oanda.v20.account.AccountSummary;
//import com.oanda.v20.Context;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SzamlaInfoController {
    @FXML
    private TextArea welcomeText;
    @FXML
    protected void onHelloButtonClick() throws IOException {

        Properties properties = new Properties();
        InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");

        properties.load(input);

        String apiKey = properties.getProperty("api.key");
        String userId = properties.getProperty("user.id");

        //try {
        //    Context ctx = new Context("https://api-fxpractice.oanda.com",apiKey);
        //    AccountSummary summary = ctx.account.summary(new
        //            AccountID(userId)).getAccount();
        //    welcomeText.setText(summary.toString());
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
    }
}