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
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Year;
import java.util.*;

public class ModositController {

    @FXML
    private ComboBox<Integer> recordComboBox;

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
        loadRecords();
        initializeYearComboBox();

        // Rekord adatok betöltése a kiválasztott rekord alapján
        recordComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                loadRecordData(newValue);
            }
        });

        // Hét mező dinamikus frissítése az adott év alapján
        evComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                hetComboBox.setItems(FXCollections.observableArrayList(getWeeksInYear(newValue)));
            }
        });

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

    private void initializeYearComboBox() {
        int currentYear = Year.now().getValue();
        List<Integer> years = new ArrayList<>();
        for (int i = 1988; i <= currentYear; i++) {
            years.add(i);
        }
        evComboBox.setItems(FXCollections.observableArrayList(years));
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

    private List<Talalat> talalatokList = new ArrayList<>();
    private List<Integer> existingNumbers = new ArrayList<>();

    public class Talalat {
        private int id;
        private int darab;
        private int ertek;

        public Talalat(int id, int darab, int ertek) {
            this.id = id;
            this.darab = darab;
            this.ertek = ertek;
        }

        // Getterek és setterek
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getDarab() {
            return darab;
        }

        public void setDarab(int darab) {
            this.darab = darab;
        }

        public int getErtek() {
            return ertek;
        }

        public void setErtek(int ertek) {
            this.ertek = ertek;
        }

        @Override
        public String toString() {
            return "Talalat{" +
                    "selected=" + id +
                    ", darab=" + darab +
                    ", ertek=" + ertek +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true; // Azonos referencia esetén igaz
            if (o == null || getClass() != o.getClass()) return false; // Null vagy eltérő osztály esetén hamis
            Talalat talalat = (Talalat) o; // Típuskonverzió
            return id == talalat.id && darab == talalat.darab && ertek == talalat.ertek; // Mezők összehasonlítása
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, darab, ertek); // Hash-kód generálása a mezőkből
        }
    }


    private void loadRecords() {
        List<Integer> recordIds = new ArrayList<>();
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT id FROM huzas")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                recordIds.add(rs.getInt("id"));
            }
            log("Információ:", "Rekordok betöltve.");
            log("Végrehajtott MySQL parancs:", "SELECT id FROM huzas");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Hiba", "Nem sikerült betölteni a rekordokat.");
            log("Hiba történt:", e.getMessage());
        }
        recordComboBox.setItems(FXCollections.observableArrayList(recordIds));
    }

    private void loadRecordData(int recordId) {
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT ev, het FROM huzas WHERE id = ?")) {
            stmt.setInt(1, recordId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                evComboBox.setValue(rs.getInt("ev"));
                hetComboBox.setItems(FXCollections.observableArrayList(getWeeksInYear(rs.getInt("ev"))));
                hetComboBox.setValue(rs.getInt("het"));
            }
            log("Végrehajtott MySQL parancs:", "SELECT ev, het FROM huzas WHERE id = " + recordId);

            // Számok betöltése és eltárolása
            PreparedStatement numbersStmt = conn.prepareStatement("SELECT szam FROM huzott WHERE huzasid = ?");
            numbersStmt.setInt(1, recordId);
            ResultSet numbersRs = numbersStmt.executeQuery();
            existingNumbers.clear(); // Frissítjük a meglévő számok listáját
            List<String> numbers = new ArrayList<>();
            while (numbersRs.next()) {
                int number = numbersRs.getInt("szam");
                existingNumbers.add(number);
                numbers.add(String.valueOf(number));
            }
            szamokField.setText(String.join(", ", numbers));
            log("Végrehajtott MySQL parancs:", "SELECT szam FROM huzott WHERE huzasid = " + recordId);

            // Találatok betöltése
            PreparedStatement talalatStmt = conn.prepareStatement("SELECT talalat, darab, ertek FROM nyeremeny WHERE huzasid = ?");
            talalatStmt.setInt(1, recordId);
            ResultSet talalatRs = talalatStmt.executeQuery();

            // Alapállapotba állítjuk a mezőket
            resetTalalatFields();
            talalatokList.clear();

            while (talalatRs.next()) {
                int talalatId = talalatRs.getInt("talalat");
                int darab = talalatRs.getInt("darab");
                int ertek = talalatRs.getInt("ertek");


                // Találatok mezőinek kitöltése az ID alapján
                switch (talalatId) {
                    case 3 -> {
                        talalat3Check.setSelected(true);
                        darab3Field.setText(String.valueOf(darab));
                        ertek3Field.setText(String.valueOf(ertek));
                    }
                    case 4 -> {
                        talalat4Check.setSelected(true);
                        darab4Field.setText(String.valueOf(darab));
                        ertek4Field.setText(String.valueOf(ertek));
                    }
                    case 5 -> {
                        talalat5Check.setSelected(true);
                        darab5Field.setText(String.valueOf(darab));
                        ertek5Field.setText(String.valueOf(ertek));
                    }
                    case 6 -> {
                        talalat6Check.setSelected(true);
                        darab6Field.setText(String.valueOf(darab));
                        ertek6Field.setText(String.valueOf(ertek));
                    }
                    case 7 -> {
                        talalat7Check.setSelected(true);
                        darab7Field.setText(String.valueOf(darab));
                        ertek7Field.setText(String.valueOf(ertek));
                    }
                }
                talalatokList.add(new Talalat(talalatId, darab, ertek));
            }

            log("Végrehajtott MySQL parancs:", "SELECT talalat, darab, ertek FROM nyeremeny WHERE huzasid = " + recordId);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Hiba", "Nem sikerült betölteni a rekord adatait.");
            log("Hiba történt:", e.getMessage());
        }
    }

    private void resetTalalatFields() {
        // Reseteljük a találatok mezőit és a checkboxokat
        talalat3Check.setSelected(false);
        darab3Field.clear();
        ertek3Field.clear();
        darab3Field.setDisable(true);
        ertek3Field.setDisable(true);

        talalat4Check.setSelected(false);
        darab4Field.clear();
        ertek4Field.clear();
        darab4Field.setDisable(true);
        ertek4Field.setDisable(true);

        talalat5Check.setSelected(false);
        darab5Field.clear();
        ertek5Field.clear();
        darab5Field.setDisable(true);
        ertek5Field.setDisable(true);

        talalat6Check.setSelected(false);
        darab6Field.clear();
        ertek6Field.clear();
        darab6Field.setDisable(true);
        ertek6Field.setDisable(true);

        talalat7Check.setSelected(false);
        darab7Field.clear();
        ertek7Field.clear();
        darab7Field.setDisable(true);
        ertek7Field.setDisable(true);
    }

    @FXML
    private void handleSave() {
        Integer recordId = recordComboBox.getValue();
        Integer ev = evComboBox.getValue();
        Integer het = hetComboBox.getValue();
        String szamokText = szamokField.getText();

        if (recordId == null || ev == null || het == null || szamokText.isEmpty()) {
            log("Hiba", "Minden mezőt ki kell tölteni!");
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
            return;
        }

        // **ELLENŐRIZZÜK, TÖRTÉNT-E VÁLTOZÁS**
        List<Talalat> newTalalatList = new ArrayList<>();
        processTalalatRow(3, darab3Field, ertek3Field, talalat3Check, newTalalatList);
        processTalalatRow(4, darab4Field, ertek4Field, talalat4Check, newTalalatList);
        processTalalatRow(5, darab5Field, ertek5Field, talalat5Check, newTalalatList);
        processTalalatRow(6, darab6Field, ertek6Field, talalat6Check, newTalalatList);
        processTalalatRow(7, darab7Field, ertek7Field, talalat7Check, newTalalatList);

        List<Integer> newNumbers = new ArrayList<>();
        for (String szam : szamok) {
            newNumbers.add(Integer.parseInt(szam.trim()));
        }

        // Találatok változásellenőrzése
        boolean evChanged = !ev.equals(evComboBox.getValue());
        boolean hetChanged = !het.equals(hetComboBox.getValue());
        boolean numbersChanged = !existingNumbers.equals(newNumbers);
        boolean talalatChanged = !talalatokList.equals(newTalalatList);


        if (!evChanged && !hetChanged && !numbersChanged && !talalatChanged) {
            log("Információ", "Nincs változás. Az adatok megegyeznek az adatbázisban tárolt értékekkel.");
            return;
        }

        // **FRISSÍTÉS CSAK HA VAN VÁLTOZÁS**
        try (Connection conn = DatabaseHelper.connect()) {
            if (evChanged) {
                // Csak az év frissítése
                String updateEvQuery = "UPDATE huzas SET ev = ? WHERE id = ?";
                PreparedStatement updateEvStmt = conn.prepareStatement(updateEvQuery);
                updateEvStmt.setInt(1, ev);
                updateEvStmt.setInt(2, recordId);
                updateEvStmt.executeUpdate();
                log("Végrehajtott MySQL parancs:", updateEvQuery.replace("?", "%s").formatted(ev, recordId));
            }

            if (hetChanged) {
                // Csak a hét frissítése
                String updateHetQuery = "UPDATE huzas SET het = ? WHERE id = ?";
                PreparedStatement updateHetStmt = conn.prepareStatement(updateHetQuery);
                updateHetStmt.setInt(1, het);
                updateHetStmt.setInt(2, recordId);
                updateHetStmt.executeUpdate();
                log("Végrehajtott MySQL parancs:", updateHetQuery.replace("?", "%s").formatted(het, recordId));
            }

            if (numbersChanged) {
                List<Integer> numbersToDelete = new ArrayList<>(existingNumbers);
                numbersToDelete.removeAll(newNumbers); // Azok a számok, amik az eredetiben vannak, de az újban nincsenek

                List<Integer> numbersToAdd = new ArrayList<>(newNumbers);
                numbersToAdd.removeAll(existingNumbers); // Azok a számok, amik az új listában vannak, de az eredetiben nincsenek
                if (!numbersToDelete.isEmpty()) {
                    String deleteNumbersQuery = "DELETE FROM huzott WHERE huzasid = ? AND szam = ?";
                    PreparedStatement deleteNumbersStmt = conn.prepareStatement(deleteNumbersQuery);

                    for (Integer numberToDelete : numbersToDelete) {
                        deleteNumbersStmt.setInt(1, recordId);
                        deleteNumbersStmt.setInt(2, numberToDelete);
                        deleteNumbersStmt.addBatch();
                    }
                    deleteNumbersStmt.executeBatch();
                    for (Integer numberToDelete : numbersToDelete) {
                        log("Végrehajtott MySQL parancs:",
                                "DELETE FROM huzott WHERE huzasid = %s AND szam = %s".formatted(recordId, numberToDelete));
                    }
                }

                if (!numbersToAdd.isEmpty()) {
                    String insertNumberQuery = "INSERT INTO huzott (huzasid, szam) VALUES (?, ?)";
                    PreparedStatement insertNumberStmt = conn.prepareStatement(insertNumberQuery);

                    for (Integer numberToAdd : numbersToAdd) {
                        insertNumberStmt.setInt(1, recordId);
                        insertNumberStmt.setInt(2, numberToAdd);
                        insertNumberStmt.addBatch();
                    }
                    insertNumberStmt.executeBatch();
                    for (Integer numberToAdd : numbersToAdd) {
                        log("Végrehajtott MySQL parancs:",
                                "INSERT INTO huzott (huzasid, szam) VALUES (%s, %s)".formatted(recordId, numberToAdd));
                    }
                }
            }

            if (talalatChanged) {

                // 1. Törlendő elemek
                List<Talalat> talalatToDelete = new ArrayList<>(talalatokList);
                talalatToDelete.removeIf(talalat ->
                        newTalalatList.stream().anyMatch(newTalalat -> newTalalat.getId() == talalat.getId())
                );

                // 2. Hozzáadandó elemek
                List<Talalat> talalatToAdd = new ArrayList<>(newTalalatList);
                talalatToAdd.removeIf(newTalalat ->
                        talalatokList.stream().anyMatch(talalat -> talalat.getId() == newTalalat.getId())
                );

                // 3. Módosítandó elemek
                List<Talalat> talalatToUpdate = new ArrayList<>();
                for (Talalat existingTalalat : talalatokList) {
                    newTalalatList.stream()
                            .filter(newTalalat -> newTalalat.getId() == existingTalalat.getId())
                            .filter(newTalalat ->
                                    newTalalat.getDarab() != existingTalalat.getDarab() ||
                                            newTalalat.getErtek() != existingTalalat.getErtek()
                            )
                            .forEach(talalatToUpdate::add);
                }

                // Törlés
                if (!talalatToDelete.isEmpty()) {
                    String deleteQuery = "DELETE FROM nyeremeny WHERE huzasid = ? AND talalat = ?";
                    PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery);

                    for (Talalat talalat : talalatToDelete) {
                        deleteStmt.setInt(1, recordId); // A megfelelő huzasid
                        deleteStmt.setInt(2, talalat.getId()); // A megfelelő talalat ID
                        deleteStmt.addBatch();
                        log("Végrehajtott MySQL parancs:",
                                "DELETE FROM nyeremeny WHERE huzasid = %s AND talalat = %s"
                                        .formatted(recordId, talalat.getId()));
                    }
                    deleteStmt.executeBatch();
                }

                // Hozzáadás
                if (!talalatToAdd.isEmpty()) {
                    String insertQuery = "INSERT INTO nyeremeny (huzasid, talalat, darab, ertek) VALUES (?, ?, ?, ?)";
                    PreparedStatement insertStmt = conn.prepareStatement(insertQuery);

                    for (Talalat talalat : talalatToAdd) {
                        insertStmt.setInt(1, recordId); // A megfelelő huzasid
                        insertStmt.setInt(2, talalat.getId()); // Talalat ID
                        insertStmt.setInt(3, talalat.getDarab()); // Darab
                        insertStmt.setInt(4, talalat.getErtek()); // Ertek
                        insertStmt.addBatch();
                        log("Végrehajtott MySQL parancs:",
                                "INSERT INTO nyeremeny (huzasid, talalat, darab, ertek) VALUES (%s, %s, %s, %s)"
                                        .formatted(recordId, talalat.getId(), talalat.getDarab(), talalat.getErtek()));
                    }
                    insertStmt.executeBatch();
                }

                // Módosítás
                if (!talalatToUpdate.isEmpty()) {
                    String updateQuery = "UPDATE nyeremeny SET darab = ?, ertek = ? WHERE huzasid = ? AND talalat = ?";
                    PreparedStatement updateStmt = conn.prepareStatement(updateQuery);

                    for (Talalat talalat : talalatToUpdate) {
                        updateStmt.setInt(1, talalat.getDarab()); // Új darab érték
                        updateStmt.setInt(2, talalat.getErtek()); // Új ertek érték
                        updateStmt.setInt(3, recordId); // A megfelelő huzasid
                        updateStmt.setInt(4, talalat.getId()); // Talalat ID
                        updateStmt.addBatch();
                        log("Végrehajtott MySQL parancs:",
                                "UPDATE nyeremeny SET darab = %s, ertek = %s WHERE huzasid = %s AND talalat = %s"
                                        .formatted(talalat.getDarab(), talalat.getErtek(), recordId, talalat.getId()));
                    }
                    updateStmt.executeBatch();
                }
            }

            log("Információ", "A rekord sikeresen módosítva!");
        } catch (Exception e) {
            e.printStackTrace();
            log("Hiba:", "Nem sikerült a rekord módosítása: " + e.getMessage());
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
    private void processTalalatRow(int id, TextField darabField, TextField ertekField, CheckBox checkBox, List<Talalat> talalatList) {
        if (checkBox.isSelected()) {
            int darab = darabField.getText().isEmpty() ? 0 : Integer.parseInt(darabField.getText());
            int ertek = ertekField.getText().isEmpty() ? 0 : Integer.parseInt(ertekField.getText());
            talalatList.add(new Talalat(id, darab, ertek));
        }
    }


    @FXML
    private void handleCancel() {
        recordComboBox.setValue(null);
        evComboBox.setValue(null);
        hetComboBox.setValue(null);
        szamokField.clear();
        resetTalalatFields();
        log("Törlés:", "A mezők alaphelyzetbe állítva.");
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