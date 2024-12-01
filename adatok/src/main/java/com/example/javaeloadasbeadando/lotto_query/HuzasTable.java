package com.example.javaeloadasbeadando.lotto_query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HuzasTable {
    public TableView<HuzasRecord> getTable() {
        // Táblázat létrehozása
        TableView<HuzasRecord> table = new TableView<>();

        // Oszlopok létrehozása
        TableColumn<HuzasRecord, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<HuzasRecord, Integer> evColumn = new TableColumn<>("Év");
        evColumn.setCellValueFactory(new PropertyValueFactory<>("ev"));

        TableColumn<HuzasRecord, Integer> hetColumn = new TableColumn<>("Hét");
        hetColumn.setCellValueFactory(new PropertyValueFactory<>("het"));

        TableColumn<HuzasRecord, String> szamokColumn = new TableColumn<>("Számok");
        szamokColumn.setCellValueFactory(new PropertyValueFactory<>("szamok"));

        TableColumn<HuzasRecord, String> talalatokColumn = new TableColumn<>("Találatok");
        talalatokColumn.setCellValueFactory(new PropertyValueFactory<>("talalatok"));

        // Oszlopok hozzáadása
        table.getColumns().addAll(idColumn, evColumn, hetColumn, szamokColumn, talalatokColumn);

        // Adatok betöltése
        ObservableList<HuzasRecord> huzasList = FXCollections.observableArrayList(HuzasDAO.getHuzasok());
        table.setItems(huzasList);

        return table;
    }
}

