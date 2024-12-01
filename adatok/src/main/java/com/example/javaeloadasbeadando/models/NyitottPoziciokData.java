package com.example.javaeloadasbeadando.models;

public class NyitottPoziciokData {

    public NyitottPoziciokData(String id, String instrument, String openTime, String currentUnits, String price) {
        this.id = id;
        this.instrument = instrument;
        this.openTime = openTime;
        this.currentUnits = currentUnits;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCurrentUnits() {
        return currentUnits;
    }

    public void setCurrentUnits(String currentUnits) {
        this.currentUnits = currentUnits;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }



    private String id;
    private String instrument;
    private String openTime;
    private String currentUnits;
    private String price;

}
