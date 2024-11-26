package com.example.javaeloadasbeadando.controllers;

import com.example.javaeloadasbeadando.utils.DatabaseHelper;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class TorolController {

    @FXML
    private ComboBox<Integer> recordComboBox;

    @FXML
    private TextArea logBox;

    @FXML
    public void initialize() {
        loadRecords();
        recordComboBox.valueProperty().addListener(recordComboBoxListener);
    }

    private final ChangeListener<Integer> recordComboBoxListener = (observable, oldValue, newValue) -> {
        if (newValue != null) {
            log("Információ", "Kiválasztott rekord ID: " + newValue);
        }
    };


    private void loadRecords() {
        List<Integer> recordIds = new ArrayList<>();
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT id FROM huzas")) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                recordIds.add(rs.getInt("id"));
            }
            recordComboBox.setItems(FXCollections.observableArrayList(recordIds));

            log("Információ:", "Rekordok betöltve.");
            log("Végrehajtott MySQL parancs:", "SELECT id FROM huzas");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Hiba", "Nem sikerült betölteni a rekordokat.");
            log("Hiba:", e.getMessage());
        }
    }


    @FXML
    private void handleDelete() {
        Integer selectedId = recordComboBox.getValue();
        if (selectedId == null) {
            showAlert("Hiba:", "Kérlek válassz ki egy rekordot a törléshez.");
            return;
        }
        try (Connection conn = DatabaseHelper.connect()) {
            // 1. Töröljük a nyeremeny táblából a kapcsolódó rekordokat
            String deleteNyeremenyQuery = "DELETE FROM nyeremeny WHERE huzasid = ?";
            try (PreparedStatement deleteNyeremenyStmt = conn.prepareStatement(deleteNyeremenyQuery)) {
                deleteNyeremenyStmt.setInt(1, selectedId);
                int nyeremenyDeleted = deleteNyeremenyStmt.executeUpdate();
                log("Információ", nyeremenyDeleted + " rekord törölve a nyeremeny táblából.");
            }

            // 2. Töröljük a huzott táblából a kapcsolódó rekordokat
            String deleteHuzottQuery = "DELETE FROM huzott WHERE huzasid = ?";
            try (PreparedStatement deleteHuzottStmt = conn.prepareStatement(deleteHuzottQuery)) {
                deleteHuzottStmt.setInt(1, selectedId);
                int huzottDeleted = deleteHuzottStmt.executeUpdate();
                log("Információ", huzottDeleted + " rekord törölve a huzott táblából.");
            }

            // 3. Töröljük a huzas táblából a rekordot
            String deleteHuzasQuery = "DELETE FROM huzas WHERE id = ?";
            try (PreparedStatement deleteHuzasStmt = conn.prepareStatement(deleteHuzasQuery)) {
                deleteHuzasStmt.setInt(1, selectedId);
                int huzasDeleted = deleteHuzasStmt.executeUpdate();
                if (huzasDeleted > 0) {
                    log("Siker", "A(z) " + selectedId + " azonosítójú rekord törölve a huzas táblából.");
                    recordComboBox.setValue(null);
                    recordComboBox.getItems().clear();
                    loadRecords();
                } else {
                    log("Információ", "Nem történt törlés. A rekord nem található.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Hiba", "Nem sikerült a rekord törlése.");
            log("Hiba", e.getMessage());
        }
    }


    @FXML
    private void handleCancel() {
        recordComboBox.setValue(null);
        log("Információ:", "A kiválasztott rekord törlésének művelete megszakítva.");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void log(String title, String message) {
        logBox.appendText(title + "\n" + message + "\n\n");
        logBox.positionCaret(logBox.getLength());
    }
}
