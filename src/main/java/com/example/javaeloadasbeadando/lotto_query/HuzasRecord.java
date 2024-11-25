package com.example.javaeloadasbeadando.lotto_query;

public class HuzasRecord {
    private int id;
    private int ev;
    private int het;
    private String szamok; // Az összes szám szövegként, vesszővel elválasztva
    private String talalatok; // Találatok szövegként, vesszővel elválasztva

    public HuzasRecord(int id, int ev, int het, String szamok, String talalatok) {
        this.id = id;
        this.ev = ev;
        this.het = het;
        this.szamok = szamok;
        this.talalatok = talalatok;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getEv() {
        return ev;
    }

    public int getHet() {
        return het;
    }

    public String getSzamok() {
        return szamok;
    }

    public String getTalalatok() {
        return talalatok;
    }
}

