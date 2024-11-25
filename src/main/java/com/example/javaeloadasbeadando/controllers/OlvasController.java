package com.example.javaeloadasbeadando.controllers;

import javafx.scene.control.TableCell;
import com.example.javaeloadasbeadando.lotto_query.HuzasDAO;
import com.example.javaeloadasbeadando.lotto_query.HuzasRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
        // Oszlopok beállítása
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        evColumn.setCellValueFactory(new PropertyValueFactory<>("ev"));
        hetColumn.setCellValueFactory(new PropertyValueFactory<>("het"));
        szamokColumn.setCellValueFactory(new PropertyValueFactory<>("szamok"));
        talalatokColumn.setCellValueFactory(new PropertyValueFactory<>("talalatok"));

        // Dinamikus oszlop szélesség beállítása a szöveghez
        setDynamicColumnWidth(talalatokColumn);

        // Adatok betöltése
        ObservableList<HuzasRecord> huzasList = FXCollections.observableArrayList(HuzasDAO.getHuzasok());
        tableView.setItems(huzasList);
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
