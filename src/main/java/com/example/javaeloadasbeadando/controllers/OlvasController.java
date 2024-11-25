package com.example.javaeloadasbeadando.controllers;

import com.example.javaeloadasbeadando.lotto_query.HuzasDAO;
import com.example.javaeloadasbeadando.lotto_query.HuzasRecord;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableRow;


import java.util.List;

public class OlvasController {

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
        // Oszlopok inicializálása
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        evColumn.setCellValueFactory(new PropertyValueFactory<>("ev"));
        hetColumn.setCellValueFactory(new PropertyValueFactory<>("het"));
        szamokColumn.setCellValueFactory(new PropertyValueFactory<>("szamok"));
        talalatokColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTalalatok()));

        // Táblázat adatok betöltése
        ObservableList<HuzasRecord> huzasList = FXCollections.observableArrayList();
        for (HuzasRecord record : HuzasDAO.getHuzasok()) {
            List<String> talalatokList = record.getTalalatokList();
            boolean first = true;
            for (String talalat : talalatokList) {
                huzasList.add(new HuzasRecord(
                        first ? record.getId() : null,
                        first ? record.getEv() : null,
                        first ? record.getHet() : null,
                        first ? record.getSzamok() : null,
                        talalat
                ));
                first = false;
            }
        }
        tableView.setItems(huzasList);

        // Egyedi sor stílusok beállítása
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

        // Táblázat automatikus oszlopméretezés
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Scene tulajdonságok figyelése a dinamikus méretezéshez
        tableView.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                tableView.prefWidthProperty().bind(newScene.widthProperty());
                tableView.prefHeightProperty().bind(newScene.heightProperty());
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
}
