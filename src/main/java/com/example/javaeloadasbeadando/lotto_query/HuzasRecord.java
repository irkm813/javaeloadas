package com.example.javaeloadasbeadando.lotto_query;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public class HuzasRecord {
    private Integer id; // Az int helyett Integer, hogy lehessen null érték
    private Integer ev;
    private Integer het;
    private String szamok;
    private String talalatok;

    // Alapértelmezett konstruktor
    public HuzasRecord() {}

    // Paraméterezett konstruktor
    public HuzasRecord(Integer id, Integer ev, Integer het, String szamok, String talalatok) {
        this.id = id;
        this.ev = ev;
        this.het = het;
        this.szamok = szamok;
        this.talalatok = talalatok;
    }

    // Getterek
    public Integer getId() {
        return id;
    }

    public Integer getEv() {
        return ev;
    }

    public Integer getHet() {
        return het;
    }

    public String getSzamok() {
        return szamok;
    }

    public String getTalalatok() {
        return talalatok;
    }

    // Setterek
    public void setId(Integer id) {
        this.id = id;
    }

    public void setEv(Integer ev) {
        this.ev = ev;
    }

    public void setHet(Integer het) {
        this.het = het;
    }

    public void setSzamok(String szamok) {
        this.szamok = szamok;
    }

    public void setTalalatok(String talalatok) {
        this.talalatok = talalatok;
    }

    // Találatok listába bontása
    public List<String> getTalalatokList() {
        if (talalatok != null && !talalatok.isEmpty()) {
            return Arrays.asList(talalatok.split(","));
        }
        return Collections.emptyList();
    }
}
