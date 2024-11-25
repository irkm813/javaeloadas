package com.example.javaeloadasbeadando.lotto_query;

import com.example.javaeloadasbeadando.utils.DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HuzasDAO {
    public static List<HuzasRecord> getHuzasok() {
        List<HuzasRecord> huzasList = new ArrayList<>();
        String huzasQuery = "SELECT id, ev, het FROM huzas LIMIT 30";
        String szamQuery = "SELECT szam FROM huzott WHERE huzasid = ?";
        String talalatQuery = "SELECT talalat, ertek FROM nyeremeny WHERE huzasid = ? ORDER BY talalat ASC";

        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement huzasStmt = conn.prepareStatement(huzasQuery);
             ResultSet huzasRs = huzasStmt.executeQuery()) {

            while (huzasRs.next()) {
                int huzasId = huzasRs.getInt("id");

                // Nyerőszámok összegyűjtése
                List<String> szamok = new ArrayList<>();
                try (PreparedStatement szamStmt = conn.prepareStatement(szamQuery)) {
                    szamStmt.setInt(1, huzasId);
                    ResultSet szamRs = szamStmt.executeQuery();
                    while (szamRs.next()) {
                        szamok.add(String.valueOf(szamRs.getInt("szam")));
                    }
                }
                String szamokAsString = String.join(", ", szamok);

                // Találatok összegyűjtése
                List<String> talalatok = new ArrayList<>();
                try (PreparedStatement talalatStmt = conn.prepareStatement(talalatQuery)) {
                    talalatStmt.setInt(1, huzasId);
                    ResultSet talalatRs = talalatStmt.executeQuery();
                    while (talalatRs.next()) {
                        int talalat = talalatRs.getInt("talalat");
                        long ertek = talalatRs.getLong("ertek");
                        String talalatText = (talalat == 7 ? "5+1-es találat: " : talalat + " találat: ")
                                + String.format("%,d HUF", ertek);
                        talalatok.add(talalatText);
                    }
                }

                // Ha nincs találat, alapértelmezett érték
                if (talalatok.isEmpty()) {
                    talalatok.add("Ezen a héten nem volt találat!");
                }

                // Új rekordokat generálunk minden találathoz
                boolean first = true;
                for (String talalat : talalatok) {
                    huzasList.add(new HuzasRecord(
                            first ? huzasId : null, // Az ID csak az első sorban jelenjen meg
                            first ? huzasRs.getInt("ev") : null, // Év csak az első sorban
                            first ? huzasRs.getInt("het") : null, // Hét csak az első sorban
                            first ? szamokAsString : null, // Számok csak az első sorban
                            talalat
                    ));
                    first = false; // A következő sorok üresek lesznek az ID, Év, Hét mezőkben
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return huzasList;
    }

    public static List<HuzasRecord> getHuzasokByFilters(String ev, String talalat, boolean csakEredmenyes, boolean csakNagyOsszeg) {
        List<HuzasRecord> huzasList = new ArrayList<>();
        StringBuilder huzasQuery = new StringBuilder("SELECT id, ev, het FROM huzas WHERE 1=1");
        if (ev != null && !ev.isEmpty()) {
            huzasQuery.append(" AND ev = ?");
        }

        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement huzasStmt = conn.prepareStatement(huzasQuery.toString())) {

            int paramIndex = 1;
            if (ev != null && !ev.isEmpty()) {
                huzasStmt.setInt(paramIndex++, Integer.parseInt(ev));
            }

            try (ResultSet huzasRs = huzasStmt.executeQuery()) {
                while (huzasRs.next()) {
                    int huzasId = huzasRs.getInt("id");

                    // Nyerőszámok összegyűjtése
                    List<String> szamok = new ArrayList<>();
                    try (PreparedStatement szamStmt = conn.prepareStatement(
                            "SELECT szam FROM huzott WHERE huzasid = ?")) {
                        szamStmt.setInt(1, huzasId);
                        ResultSet szamRs = szamStmt.executeQuery();
                        while (szamRs.next()) {
                            szamok.add(String.valueOf(szamRs.getInt("szam")));
                        }
                    }
                    String szamokAsString = String.join(", ", szamok);

                    // Találatok összegyűjtése
                    List<String> talalatok = new ArrayList<>();
                    try (PreparedStatement talalatStmt = conn.prepareStatement(
                            "SELECT talalat, ertek FROM nyeremeny WHERE huzasid = ? ORDER BY talalat ASC")) {
                        talalatStmt.setInt(1, huzasId);
                        ResultSet talalatRs = talalatStmt.executeQuery();
                        while (talalatRs.next()) {
                            int talalatValue = talalatRs.getInt("talalat");
                            long ertek = talalatRs.getLong("ertek");

                            // Szűrő a találatok alapján
                            // Ha "Nincs szűrő" van kiválasztva, akkor nincs szűrés a találatok számát illetően
                            if (talalat != null && !talalat.equals("Nincs szűrő")) {
                                if (!(talalatValue + " találat").equals(talalat)
                                        && !(talalat.equals("5+1 találat") && talalatValue == 7)) {
                                    continue;
                                }
                            }

                            // Csak eredményes húzások szűrése
                            if (csakEredmenyes && ertek == 0) continue;

                            // Csak nagy összegű nyeremények szűrése, ha a checkbox be van pipálva
                            if (csakNagyOsszeg && ertek <= 10000000) continue;  // Csak a 10 millió forint feletti nyeremények

                            String talalatText = (talalatValue == 7 ? "5+1 találat: " : talalatValue + " találat: ")
                                    + String.format("%,d HUF", ertek);
                            talalatok.add(talalatText);
                        }
                    }

                    // Ha nincs találat és "Csak eredményes húzások" van kiválasztva, ugorjuk át
                    if (talalatok.isEmpty() && csakEredmenyes) continue;

                    // Ha "Nincs szűrő" van kiválasztva, akkor azok a rekordok is megjelennek, ahol nincs találat
                    if (talalat.equals("Nincs szűrő")) {
                        // Ha nincs találat, akkor a "Ezen a héten nem volt találat!" üzenetet adjuk hozzá
                        if (talalatok.isEmpty()) {
                            talalatok.add("Ezen a héten nem volt találat!");
                        }
                    }

                    // Rekordok hozzáadása
                    boolean first = true;
                    for (String talalatText : talalatok) {
                        huzasList.add(new HuzasRecord(
                                first ? huzasId : null,
                                first ? huzasRs.getInt("ev") : null,
                                first ? huzasRs.getInt("het") : null,
                                first ? szamokAsString : null,
                                talalatText
                        ));
                        first = false;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return huzasList;
    }







}