module com.example.javaeloadasbeadando {
    requires javafx.fxml;
    requires javafx.controls;
    requires jakarta.xml.ws;
    requires gson;
    requires httpcore;
    requires httpclient;
    requires java.sql;
    requires org.joda.time;

    opens com.oanda.v20;
    opens com.oanda.v20.account;
    opens com.oanda.v20.pricing;
    opens com.oanda.v20.pricing_common;
    opens com.oanda.v20.order;
    opens com.oanda.v20.instrument;
    opens com.oanda.v20.transaction;
    opens com.oanda.v20.trade;

    exports com.oanda.v20.primitives;
    exports com.oanda.v20.transaction;
    exports com.oanda.v20.pricing_common;
    exports com.oanda.v20.order;
    exports com.oanda.v20.trade;
    exports com.example.javaeloadasbeadando;
    exports com.example.javaeloadasbeadando.controllers;
    exports com.example.javaeloadasbeadando.models;

    opens com.mnbapi to jakarta.xml.bind, com.sun.xml.ws, com.sun.xml.bind;
    opens com.example.javaeloadasbeadando.lotto_query to javafx.base;
    opens com.example.javaeloadasbeadando to javafx.fxml;
    opens com.example.javaeloadasbeadando.controllers to javafx.fxml;
    opens com.example.javaeloadasbeadando.models to javafx.fxml;
}