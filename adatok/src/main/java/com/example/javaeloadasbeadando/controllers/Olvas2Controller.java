package com.example.javaeloadasbeadando.controllers;

import com.example.javaeloadasbeadando.lotto_query.HuzasDAO;
import com.example.javaeloadasbeadando.lotto_query.HuzasRecord;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;

import java.util.List;

public class Olvas2Controller {

    @FXML
    private TextField evTextField;

    @FXML
    private ComboBox<String> talalatComboBox;

    @FXML
    private ToggleGroup toggleGroup;

    @FXML
    private RadioButton osszesRadioButton;

    @FXML
    private RadioButton csakEredmenyesRadioButton;

    @FXML
    private CheckBox nagyOsszegCheckBox;

    @FXML
    private TableView<HuzasRecord> tableView;

    @FXML
    private TableColumn<HuzasRecord, Integer> idColumn;

    @FXML
    private TableColumn<HuzasRecord, Integer> evColumn;

    @FXML
    private TableColumn<HuzasRecord, Integer> hetColumn;

    @FXML
    private TableColumn<HuzasRecord, String> szamokColumn;

    @FXML
    private TableColumn<HuzasRecord, String> talalatokColumn;

    @FXML
    public void initialize() {
        talalatComboBox.setItems(FXCollections.observableArrayList(
                "Nincs szűrő", "3 találat", "4 találat", "5 találat", "6 találat", "5+1 találat"
        ));
        talalatComboBox.setValue("Nincs szűrő");
        toggleGroup = new ToggleGroup();  // Ez szükséges, ha nem történik meg az automatikus injektálás

        // Rádiógombok összekapcsolása a ToggleGroup-bal
        osszesRadioButton.setToggleGroup(toggleGroup);
        csakEredmenyesRadioButton.setToggleGroup(toggleGroup);



        // Egyéb inicializációk
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        evColumn.setCellValueFactory(new PropertyValueFactory<>("ev"));
        hetColumn.setCellValueFactory(new PropertyValueFactory<>("het"));
        szamokColumn.setCellValueFactory(new PropertyValueFactory<>("szamok"));
        talalatokColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTalalatok()));

        // Alapértelmezett oszlopszélességek
        setDynamicColumnWidth(szamokColumn);
        setDynamicColumnWidth(talalatokColumn);

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tableView.setRowFactory(tv -> new TableRow<HuzasRecord>() {
            @Override
            protected void updateItem(HuzasRecord record, boolean empty) {
                super.updateItem(record, empty);
                if (record == null || empty) {
                    setStyle(""); // Alapértelmezett stílus üres sor esetén
                } else if (record.getId() != null) {
                    // Ha az ID nem null, erősebb fekete vonal
                    setStyle("-fx-border-color: black; -fx-border-width: 1px 0 0 0;");
                } else {
                    // Alapértelmezett stílus a többi sorhoz
                    setStyle("");
                }
            }
        });
    }



    private void setDynamicColumnWidth(TableColumn<HuzasRecord, String> column) {
        column.setCellFactory(tc -> {
            TableCell<HuzasRecord, String> cell = new TableCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item);
                        // Dinamikus szélesség kalkuláció
                        double width = this.getFont().getSize() * item.length() * 0.6;
                        if (column.getPrefWidth() < width) {
                            column.setPrefWidth(width + 20); // Ráhagyás
                        }
                    }
                }
            };
            return cell;
        });
    }

    @FXML
    private void onFilterButtonClicked() {
        String ev = evTextField.getText().trim(); // Év szűrő

        // Ha az év mező üres, jeleníts meg egy figyelmeztetést
        if (ev.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Figyelmeztetés");
            alert.setHeaderText(null);
            alert.setContentText("Kérlek, add meg az évszámot!");
            alert.showAndWait();
            return; // Ne folytasd a szűrést, ha nincs megadott év
        }

        // Ellenőrizzük, hogy a szöveg csak számokat tartalmaz
        try {
            int evSzam = Integer.parseInt(ev); // Átalakítjuk számra

            // Ellenőrizzük, hogy az év érvényes-e (1910 és 2100 között)
            if (evSzam < 1910 || evSzam > 2100) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Figyelmeztetés");
                alert.setHeaderText(null);
                alert.setContentText("Az évnek 1910 és 2100 között kell lennie!");
                alert.showAndWait();
                return; // Ha az év nem megfelelő, ne folytassuk a szűrést
            }

        } catch (NumberFormatException e) {
            // Ha nem számot adtak meg
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Figyelmeztetés");
            alert.setHeaderText(null);
            alert.setContentText("Kérlek, adj meg egy érvényes számot!");
            alert.showAndWait();
            return; // Ha nem szám, ne folytassuk a szűrést
        }

        String talalat = talalatComboBox.getValue(); // Találat típusa
        boolean csakEredmenyes = csakEredmenyesRadioButton.isSelected(); // Csak eredményes húzások szűrője
        boolean csakNagyOsszeg = nagyOsszegCheckBox.isSelected(); // Csak nagy összegű nyeremények checkbox állapota

        if (talalat == null) {
            talalat = "Nincs szűrő"; // Alapértelmezett érték
        }

        // Lekérdezzük a szűrt rekordokat
        List<HuzasRecord> filteredRecords = HuzasDAO.getHuzasokByFilters(ev, talalat, csakEredmenyes, csakNagyOsszeg);

        // Ha nincs találat, akkor beállítjuk az üzenetet, hogy nincs találat
        if (filteredRecords.isEmpty()) {
            tableView.setPlaceholder(new Label("Nincs találat a keresésnek megfelelően."));
            tableView.setItems(FXCollections.observableArrayList()); // Üres lista beállítása
        } else {
            // Táblázat frissítése az új rekordokkal
            tableView.setPlaceholder(new Label("")); // Üzenet eltávolítása, ha van találat
            tableView.setItems(FXCollections.observableArrayList(filteredRecords)); // Az új adatok beállítása
        }
    }





}
