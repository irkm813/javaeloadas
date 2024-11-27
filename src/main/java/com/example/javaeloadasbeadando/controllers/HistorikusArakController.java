package com.example.javaeloadasbeadando.controllers;

import com.oanda.v20.Context;
import com.oanda.v20.ContextBuilder;
import com.oanda.v20.instrument.Candlestick;
import com.oanda.v20.instrument.InstrumentCandlesRequest;
import com.oanda.v20.instrument.InstrumentCandlesResponse;
import com.oanda.v20.primitives.DateTime;
import com.oanda.v20.primitives.InstrumentName;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.oanda.v20.instrument.CandlestickGranularity.H1;

public class HistorikusArakController {
    @FXML
    private LineChart dateValueChart;
    @FXML
    private ComboBox<String> devizaBox;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;

    @FXML
    private Label resultArea;

    String[] currencyPairs = {"EUR:USD", "USD:JPY", "GBP:USD", "USD:CHF"};

    @FXML
    public void initialize() {
        // Az opciók hozzáadása a legördülő menühöz
        devizaBox.getItems().addAll(currencyPairs);
    }

    public int onClick(ActionEvent actionEvent) throws IOException {

        LineChart.Series<String, Number> series = new LineChart.Series<>();
        series.setName("Dátum/Érték adatsor");

        if (startDatePicker.getValue() == null || endDatePicker.getValue() == null || devizaBox.getValue() == null) {
            resultArea.setText("Egyik érték sem hagyható üresen!");
            return 1;
        }

        Properties properties = new Properties();
        InputStream input = HistorikusArakController.class.getClassLoader().getResourceAsStream("config.properties");

        properties.load(input);
        String apiKey = properties.getProperty("api.key");
        Context ctx = new Context("https://api-fxpractice.oanda.com",apiKey);;

        String chosenValues = devizaBox.getSelectionModel().getSelectedItem().replace(":","_");
        DateTime startDate = new DateTime(startDatePicker.getValue().toString());
        DateTime endDate = new DateTime (endDatePicker.getValue().toString());

        dateValueChart.getData().clear();

        try {

            // Konvertálás LocalDateTime formátumba a requesthez

            InstrumentCandlesRequest request = new InstrumentCandlesRequest(new InstrumentName(chosenValues));
            request.setGranularity(H1);
            request.setFrom(startDate);
            request.setTo(endDate);

            // Kérés végrehajtása
            InstrumentCandlesResponse resp = ctx.instrument.candles(request);

            // Adatok kiíratása
            for (Candlestick candle : resp.getCandles()) {

                series.getData().add(new LineChart.Data<>(candle.getTime().toString(),candle.getMid().getC().doubleValue()));

                System.out.println(candle.getTime() + "\t" + candle.getMid().getC());
            }

            dateValueChart.getData().add(series);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return 0;
    }
}
