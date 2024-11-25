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
        String huzasQuery = "SELECT id, ev, het FROM huzas LIMIT 15";
        String szamQuery = "SELECT szam FROM huzott WHERE huzasid = ?";
        String talalatQuery = "SELECT COALESCE(" +
                "GROUP_CONCAT(" +
                "CASE WHEN talalat = 7 THEN '5+1-es találat: ' " +
                "ELSE CONCAT(talalat, ' találat: ') END, " +
                "FORMAT(ertek, 0), ' HUF' ORDER BY talalat ASC SEPARATOR ', '), " +
                "'Ezen a héten nem volt találat!') AS talalatok " +
                "FROM nyeremeny WHERE huzasid = ?";


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
                String talalatokAsString = "";
                try (PreparedStatement talalatStmt = conn.prepareStatement(talalatQuery)) {
                    talalatStmt.setInt(1, huzasId);
                    ResultSet talalatRs = talalatStmt.executeQuery();
                    if (talalatRs.next()) {
                        talalatokAsString = talalatRs.getString("talalatok");
                    }
                }

                // Új rekord hozzáadása a listához
                huzasList.add(new HuzasRecord(
                        huzasId,
                        huzasRs.getInt("ev"),
                        huzasRs.getInt("het"),
                        szamokAsString,
                        talalatokAsString
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return huzasList;
    }
}
