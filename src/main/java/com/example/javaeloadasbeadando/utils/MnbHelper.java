package com.example.javaeloadasbeadando.utils;

import com.mnbapi.MNBArfolyamServiceSoap;
import com.mnbapi.MNBArfolyamServiceSoapGetExchangeRatesStringFaultFaultMessage;
import com.mnbapi.MNBArfolyamServiceSoapImpl;
import javafx.scene.chart.XYChart;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.*;

public class MnbHelper {

    private final MNBArfolyamServiceSoap service;

    public MnbHelper() {
        MNBArfolyamServiceSoapImpl impl = new MNBArfolyamServiceSoapImpl();
        this.service = impl.getCustomBindingMNBArfolyamServiceSoap();
    }

    public Map<String, String> getAllInfo() {

        Map<String, String> allInfo = new HashMap<>();

        try {
            allInfo.put("Info", service.getInfo());
            allInfo.put("Currencies", service.getCurrencies());
            allInfo.put("Exchange Rate", service.getCurrentExchangeRates());
            allInfo.put("Data Interval", service.getDateInterval());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allInfo;
    }


    public List<String> getCurrencies() {

        List<String> penznemek = new ArrayList<>();

        try {
            // Az XML fájl betöltése
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            String currXML = service.getCurrencies();
            InputStream inputStream = new ByteArrayInputStream(currXML.getBytes(StandardCharsets.UTF_8));

            Document doc = builder.parse(inputStream);

            // Gyökérelem lekérése
            Element root = doc.getDocumentElement();

            // Pénznemek listájának feldolgozása
            NodeList currencies = doc.getElementsByTagName("Curr");
            for (int i = 0; i < currencies.getLength(); i++) {
                Element currency = (Element) currencies.item(i);
                penznemek.add(currency.getTextContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return penznemek;
    }

    public String getExchangeRate(String currency, String startDate) throws MNBArfolyamServiceSoapGetExchangeRatesStringFaultFaultMessage {
        String exch = service.getExchangeRates(startDate, startDate, currency);
        return exch;
    }

    public List<XYChart.Data<String, Number>> getExchangeRates(String currency, String startDate, String endDate) throws MNBArfolyamServiceSoapGetExchangeRatesStringFaultFaultMessage, ParserConfigurationException {

        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        List<XYChart.Data<String, Number>> dataList = new ArrayList<>();

        // Parse the input dates
        DateTime startDateTime = new DateTime(startDate);
        DateTime endDateTime = new DateTime (endDate);

        // Create a list to store the result
        List<String> dates = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // Create a DocumentBuilder
        DocumentBuilder builder = factory.newDocumentBuilder();


        // Loop through the dates from start to end
        while (!startDateTime.isAfter(endDateTime)) {
            try {
                // Format the current date
                String currentDate = startDateTime.toString(formatter);

                // Get exchange rate for the current date
                String response = service.getExchangeRates(currentDate, currentDate, currency);
                Document document = builder.parse(new ByteArrayInputStream(response.getBytes()));
                document.getDocumentElement().normalize();

                NodeList rateNodes = document.getElementsByTagName("Rate");
                if (rateNodes.getLength() > 0) {
                    Node rateNode = rateNodes.item(0);

                    // Parse the exchange rate
                    NumberFormat format = NumberFormat.getInstance(Locale.GERMANY);
                    Number parsedNumber = format.parse(rateNode.getTextContent());
                    double value = parsedNumber.doubleValue();

                    // Add data to the list
                    dataList.add(new XYChart.Data<>(currentDate, value));
                }

                // Increment the date by one day
                startDateTime = startDateTime.plusDays(1);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(dataList);
        return dataList;
    }
}
