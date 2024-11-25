package com.example.javaeloadasbeadando.models;

import javafx.beans.property.SimpleStringProperty;

public class OandaData {
    private final SimpleStringProperty name;
    private final SimpleStringProperty value;

    public OandaData(String name, String value) {
        this.name = new SimpleStringProperty(name);
        this.value = new SimpleStringProperty(value);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getValue() {
        return value.get();
    }

    public void setValue(String value) {
        this.value.set(value);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty valueProperty() {
        return value;
    }
}
