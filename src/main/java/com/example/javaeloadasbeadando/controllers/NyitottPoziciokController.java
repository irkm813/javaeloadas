package com.example.javaeloadasbeadando.controllers;

import com.example.javaeloadasbeadando.models.NyitottPoziciokData;
import com.example.javaeloadasbeadando.models.OandaData;
import com.oanda.v20.ContextBuilder;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.trade.Trade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import com.oanda.v20.Context;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class NyitottPoziciokController {

    static Context ctx;
    static AccountID accountId;

    @FXML
    private TableView nyitottPoziciokTableView;

    @FXML
    public ComboBox idBox;
    @FXML
    private Label welcomeText;

    @FXML
    private TableColumn<NyitottPoziciokData, String> idColumn;
    @FXML
    private TableColumn<NyitottPoziciokData, String> instrumentsColumn;
    @FXML
    private TableColumn<NyitottPoziciokData, String> openedAtColumn;
    @FXML
    private TableColumn<NyitottPoziciokData, String> currentUnitsColumn;
    @FXML
    private TableColumn<NyitottPoziciokData, String> priceColumn;
    @FXML
    private TableColumn<NyitottPoziciokData, String> unrealizedPlColumn;

    // Adatok listája
    private final ObservableList<NyitottPoziciokData> dataList = FXCollections.observableArrayList();

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

            // Oszlopok összekötése a NyitottPoziciokData osztály mezőivel
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            instrumentsColumn.setCellValueFactory(new PropertyValueFactory<>("instrument"));
            openedAtColumn.setCellValueFactory(new PropertyValueFactory<>("openedAt"));
            currentUnitsColumn.setCellValueFactory(new PropertyValueFactory<>("currentUnits"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

            if(true) NyitotttradekKiír();
            welcomeText.setText(welcomeText.getText() + "\n" +"Done");

            nyitottPoziciokTableView.setItems(dataList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void NyitotttradekKiír() throws ExecuteException, RequestException {
        welcomeText.setText(welcomeText.getText() + "\n" +"Nyitott tradek:");
        List<Trade> trades = ctx.trade.listOpen(accountId).getTrades();
        welcomeText.setText(welcomeText.getText() + "id\t"+"instruments\t"+"openedat\t"+"currentunits\t"+"price\t");
        for(Trade trade: trades)
            dataList.add(new NyitottPoziciokData(
                    trade.getId().toString(),
                    trade.getInstrument().toString(),
                    trade.getOpenTime().toString(),
                    trade.getCurrentUnits().toString(),
                    trade.getPrice().toString()));
    }
}
