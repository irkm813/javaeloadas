package com.example.javaeloadasbeadando.controllers;

import com.example.javaeloadasbeadando.utils.MnbHelper;
import com.mnbapi.MNBArfolyamServiceSoapGetExchangeRatesStringFaultFaultMessage;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.util.Map;

public class Letoltes2Controller {
    @FXML
    public ComboBox currencyComboBox;
    @FXML
    public javafx.scene.control.DatePicker startDatePicker;
    @FXML
    public RadioButton toggleFieldButton;
    @FXML
    public Button SaveButton;
    @FXML
    public Label hourLabel;

    MnbHelper mnbHelper = new MnbHelper();

    @FXML
    void initialize() {
        currencyComboBox.getItems().addAll(mnbHelper.getCurrencies());
    }

    @FXML
    void onClick() throws MNBArfolyamServiceSoapGetExchangeRatesStringFaultFaultMessage {
        String currency = currencyComboBox.getSelectionModel().getSelectedItem().toString();
        String startDate = startDatePicker.getValue().toString();
        System.out.println(mnbHelper.getExchangeRate(currency, startDate));

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        fileChooser.setInitialFileName("MNB.txt");
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {

            try (BufferedWriter writer = Files.newBufferedWriter(file.toPath())) {
                writer.write(mnbHelper.getExchangeRate(currency, startDate));
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }
}