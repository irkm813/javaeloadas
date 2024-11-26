package com.example.javaeloadasbeadando.controllers;

import com.example.javaeloadasbeadando.lotto_query.HuzasRecord;
import com.example.javaeloadasbeadando.models.OandaData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.account.AccountSummary;
import com.oanda.v20.Context;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SzamlaInfoController {
    public Button showButton;
    public TableView SzamlaInfotableView;

    @FXML
    private TextArea welcomeText;

    @FXML
    private TableColumn<OandaData, String> nevColumn;

    @FXML
    private TableColumn<OandaData, String> ertekColumn;

    // Adatok listája
    private final ObservableList<OandaData> dataList = FXCollections.observableArrayList();

    @FXML
    protected void onShowButtonClick() throws IOException {

        // Oszlopok összekapcsolása a Data osztály mezőivel
        nevColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ertekColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        showButton.setVisible(false);

        Properties properties = new Properties();
        InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");

        properties.load(input);

        String apiKey = properties.getProperty("api.key");
        String userId = properties.getProperty("user.id");

        try {
            Context ctx = new Context("https://api-fxpractice.oanda.com",apiKey);
            AccountSummary summary = ctx.account.summary(new AccountID(userId)).getAccount();
            String stringPart = summary.toString();
            String[] pairs = stringPart.split(",");


            // Táblázathoz adatok hozzáadása
            SzamlaInfotableView.setItems(dataList);

            for (String pair : pairs) {
                // Szétválasztjuk '=' alapján
                String[] keyValue = pair.split("=");
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();

                addData(key, value);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addData(String name, String value) {
        dataList.add(new OandaData(name, value));
    }
}