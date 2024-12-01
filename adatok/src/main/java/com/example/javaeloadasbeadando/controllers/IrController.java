package com.example.javaeloadasbeadando.controllers;

import com.example.javaeloadasbeadando.utils.DatabaseHelper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.Year;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;


public class IrController {

    @FXML
    private ComboBox<Integer> evComboBox;

    @FXML
    private ComboBox<Integer> hetComboBox;

    @FXML
    private TextField szamokField;

    @FXML
    private TextArea logBox;

    @FXML
    private CheckBox talalat3Check, talalat4Check, talalat5Check, talalat6Check, talalat7Check;
    @FXML
    private TextField darab3Field, ertek3Field, darab4Field, ertek4Field, darab5Field, ertek5Field, darab6Field, ertek6Field, darab7Field, ertek7Field;

    @FXML
    public void initialize() {
        // Évek betöltése 1988-tól az aktuális évig
        int currentYear = Year.now().getValue();
        List<Integer> years = new ArrayList<>();
        for (int i = 1988; i <= currentYear; i++) {
            years.add(i);
        }
        evComboBox.setItems(FXCollections.observableArrayList(years));

        // Hét mező dinamikus frissítése az adott év alapján
        evComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                hetComboBox.setItems(FXCollections.observableArrayList(getWeeksInYear(newValue)));
            }
        });

        // Alapértelmezett értékek beállítása
        if (!years.isEmpty()) {
            evComboBox.setValue(currentYear); // Aktuális év kiválasztása
            hetComboBox.setItems(FXCollections.observableArrayList(getWeeksInYear(currentYear)));
        }

        setupCheckBoxBehavior(talalat3Check, darab3Field, ertek3Field);
        setupCheckBoxBehavior(talalat4Check, darab4Field, ertek4Field);
        setupCheckBoxBehavior(talalat5Check, darab5Field, ertek5Field);
        setupCheckBoxBehavior(talalat6Check, darab6Field, ertek6Field);
        setupCheckBoxBehavior(talalat7Check, darab7Field, ertek7Field);
    }

    private void setupCheckBoxBehavior(CheckBox checkBox, TextField darabField, TextField ertekField) {
        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            darabField.setDisable(!newValue);
            ertekField.setDisable(!newValue);
            if (!newValue) {
                darabField.clear();
                ertekField.clear();
            }
        });
    }

    private List<Integer> getWeeksInYear(int year) {
        List<Integer> weeks = new ArrayList<>();
        int maxWeeks = Year.of(year).atDay(1).lengthOfYear() == 366 ? 53 : 52;
        int currentYear = Year.now().getValue();
        int currentWeek = LocalDate.now().get(java.time.temporal.IsoFields.WEEK_OF_WEEK_BASED_YEAR);

        if (year == currentYear) {
            maxWeeks = Math.min(maxWeeks, currentWeek);
        }

        for (int i = 1; i <= maxWeeks; i++) {
            weeks.add(i);
        }
        return weeks;
    }

    @FXML
    private void handleSave() {
        Integer ev = evComboBox.getValue();
        Integer het = hetComboBox.getValue();
        String szamokText = szamokField.getText();

        if (ev == null || het == null || szamokText.isEmpty()) {
            showAlert("Hiba", "Minden mezőt ki kell tölteni!");
            return;
        }

        // Ellenőrizd a számok formátumát
        String[] szamok = szamokText.split(",");

        try {
            Set<Integer> uniqueNumbers = new HashSet<>(); // Egyedi számok tárolására
            for (String szam : szamok) {
                int number = Integer.parseInt(szam.trim());
                if (number < 1 || number > 45) {
                    showAlert("Hiba", "Minden számnak 1 és 45 között kell lennie!");
                    return;
                }
                if (!uniqueNumbers.add(number)) { // Ha a szám már létezett a halmazban
                    showAlert("Hiba", "Minden számot csak egyszer adhat meg!");
                    return;
                }
            }
        } catch (NumberFormatException e) {
            showAlert("Hiba", "Csak számokat adhat meg, vesszővel elválasztva!");
            return;
        }

        if (szamok.length != 6) {
            showAlert("Hiba", "Pontosan 6 számot kell megadni, vesszővel elválasztva!");
            return;
        }


        if (!validateTalalatFields()) {
            return; // Ha az ellenőrzés hibás, kilépünk
        }

        try (Connection conn = DatabaseHelper.connect()) {
            // Húzás mentése
            String huzasInsertQuery = "INSERT INTO huzas (ev, het) VALUES (?, ?)";
            PreparedStatement huzasStmt = conn.prepareStatement(huzasInsertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            huzasStmt.setInt(1, ev);
            huzasStmt.setInt(2, het);
            huzasStmt.executeUpdate();

            log("Végrehajtott MySQL parancs:", huzasInsertQuery.replace("?", "%s").formatted(ev, het));

            int huzasId;
            try (var generatedKeys = huzasStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    huzasId = generatedKeys.getInt(1);
                } else {
                    throw new Exception("Nem sikerült a húzás ID-t lekérni.");
                }
            }

            // Számok mentése
            String szamInsertQuery = "INSERT INTO huzott (huzasid, szam) VALUES (?, ?)";
            PreparedStatement szamStmt = conn.prepareStatement(szamInsertQuery);
            for (String szam : szamok) {
                int number = Integer.parseInt(szam.trim());
                szamStmt.setInt(1, huzasId);
                szamStmt.setInt(2, number);
                szamStmt.addBatch();
                log("Végrehajtott MySQL parancs:",
                        szamInsertQuery.replaceFirst("\\?", String.valueOf(huzasId))
                                .replaceFirst("\\?", String.valueOf(number)));
            }
            szamStmt.executeBatch();

            // Találatok mentése
            String talalatInsertQuery = "INSERT INTO nyeremeny (huzasid, talalat, darab, ertek) VALUES (?, ?, ?, ?)";
            PreparedStatement talalatStmt = conn.prepareStatement(talalatInsertQuery);

            saveTalalatIfChecked(conn, talalatStmt, huzasId, talalat3Check, 3, darab3Field, ertek3Field);
            saveTalalatIfChecked(conn, talalatStmt, huzasId, talalat4Check, 4, darab4Field, ertek4Field);
            saveTalalatIfChecked(conn, talalatStmt, huzasId, talalat5Check, 5, darab5Field, ertek5Field);
            saveTalalatIfChecked(conn, talalatStmt, huzasId, talalat6Check, 6, darab6Field, ertek6Field);
            saveTalalatIfChecked(conn, talalatStmt, huzasId, talalat7Check, 7, darab7Field, ertek7Field);

            log("Mentés sikerült", "Új rekord hozzáadva!");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Hiba", "Nem sikerült az adatok mentése.");
            log("Hiba történt:", e.getMessage());
        }

    }

    private boolean validateTalalatFields() {
        // Ellenőrzés minden találathoz
        return validateField(talalat3Check, darab3Field, ertek3Field, 3) &&
                validateField(talalat4Check, darab4Field, ertek4Field, 4) &&
                validateField(talalat5Check, darab5Field, ertek5Field, 5) &&
                validateField(talalat6Check, darab6Field, ertek6Field, 6) &&
                validateField(talalat7Check, darab7Field, ertek7Field, 7);
    }

    private boolean validateField(CheckBox checkBox, TextField darabField, TextField ertekField, int talalatId) {
        // Csak akkor ellenőrizzük, ha a checkbox be van pipálva
        if (checkBox.isSelected()) {
            // Ellenőrizzük, hogy a mezők nem üresek
            if (darabField.getText().trim().isEmpty() || ertekField.getText().trim().isEmpty()) {
                showAlert("Hiba", talalatId + " találat mezői nem lehetnek üresek!");
                return false;
            }
            try {
                // Ellenőrizzük, hogy számot tartalmaznak
                Integer.parseInt(darabField.getText().trim());
                Integer.parseInt(ertekField.getText().trim());
            } catch (NumberFormatException e) {
                showAlert("Hiba", talalatId + " találat mezőiben csak szám szerepelhet!");
                return false;
            }
        }
        return true; // Ha nincs probléma, érvényes
    }


    private void saveTalalatIfChecked(Connection conn, PreparedStatement stmt, int huzasId, CheckBox checkBox, int talalatId, TextField darabField, TextField ertekField) throws Exception {
        if (checkBox.isSelected()) {
            int darab = Integer.parseInt(darabField.getText().trim());
            int ertek = Integer.parseInt(ertekField.getText().trim());
            stmt.setInt(1, huzasId);
            stmt.setInt(2, talalatId);
            stmt.setInt(3, darab);
            stmt.setInt(4, ertek);
            stmt.executeUpdate();

            log("Végrehajtott MySQL parancs:",
                    String.format("INSERT INTO nyeremeny (huzasid, talalat, darab, ertek) VALUES (%d, %d, %d, %d)", huzasId, talalatId, darab, ertek));
        }
    }



    @FXML
    private void handleCancel() {
        evComboBox.setValue(Year.now().getValue());
        hetComboBox.setValue(null);
        szamokField.clear();
        log("Törlés", "A mezők alaphelyzetbe állítva.");
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
