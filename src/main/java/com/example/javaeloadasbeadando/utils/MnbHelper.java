package com.example.javaeloadasbeadando.utils;

import com.mnbapi.MNBArfolyamServiceSoap;
import com.mnbapi.MNBArfolyamServiceSoapImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.HashMap;
import java.util.Map;

public class MnbHelper {

    private final MNBArfolyamServiceSoap service;

    public MnbHelper() {
        MNBArfolyamServiceSoapImpl impl = new MNBArfolyamServiceSoapImpl();
        this.service = impl.getCustomBindingMNBArfolyamServiceSoap();
    }

    public Map<String, String> getAllInfo() {

        Map<String, String> allInfo = new HashMap<>();

        try {
            allInfo.put("Info",service.getInfo());
            allInfo.put("Currencies",service.getCurrencies());
            allInfo.put("Exchange Rate",service.getCurrentExchangeRates());
            allInfo.put("Data Interval",service.getDateInterval());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allInfo;
    }

    public static String XmlParser(String xml,String tagname) {

        String penznemek = "";

        try {
            // Az XML fájl betöltése
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xml);

            // Gyökérelem lekérése
            Element root = doc.getDocumentElement();
            System.out.println("Gyökérelem: " + root.getNodeName());

            // Pénznemek listájának feldolgozása
            NodeList currencies = doc.getElementsByTagName(tagname);
            for (int i = 0; i < currencies.getLength(); i++) {
                Element currency = (Element) currencies.item(i);
                penznemek += currency.getTextContent()+", ";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return penznemek;
    }
}
