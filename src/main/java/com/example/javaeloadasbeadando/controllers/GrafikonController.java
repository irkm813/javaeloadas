package com.example.javaeloadasbeadando.controllers;

import com.example.javaeloadasbeadando.utils.MnbHelper;
import com.mnbapi.MNBArfolyamServiceSoapGetExchangeRatesStringFaultFaultMessage;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

public class GrafikonController {
    @FXML
    public ComboBox currencyComboBox;
    @FXML
    public javafx.scene.control.DatePicker startDatePicker;
    @FXML
    public javafx.scene.control.DatePicker endDatePicker;
    @FXML
    public RadioButton toggleFieldButton;
    @FXML
    public Button SaveButton;

    @FXML
    private LineChart dateValueChart;

    MnbHelper mnbHelper = new MnbHelper();

    @FXML
    void initialize() {
        currencyComboBox.getItems().addAll(mnbHelper.getCurrencies());
    }

    @FXML
    void onClick() throws MNBArfolyamServiceSoapGetExchangeRatesStringFaultFaultMessage, ParserConfigurationException {

        LineChart.Series<String, Number> series = new LineChart.Series<>();
        series.setName("Dátum/Érték adatsor");

        String currency = currencyComboBox.getSelectionModel().getSelectedItem().toString();
        String startDate = startDatePicker.getValue().toString();
        String endDate = endDatePicker.getValue().toString();

        List<XYChart.Data<String, Number>> exchangeRates = mnbHelper.getExchangeRates(currency, startDate, endDate);
        series.getData().addAll(exchangeRates);

        dateValueChart.getData().add(series);

    }
}
