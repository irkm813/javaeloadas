package com.example.javaeloadasbeadando.controllers;

import com.example.javaeloadasbeadando.utils.MnbHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;


public class LetoltesController {
    @FXML
    public Label textLabel1;
    @FXML
    public Label textLabel2;
    @FXML
    public Label textLabel3;

    @FXML
    public void initialize() {

        MnbHelper helper = new MnbHelper();
        Map<String, String> allInfo = new HashMap<>();

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        fileChooser.setInitialFileName("MNB.txt");
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {

            allInfo = helper.getAllInfo();
            System.out.println(allInfo);

            try (BufferedWriter writer = Files.newBufferedWriter(file.toPath())) {
                for (Map.Entry<String, String> entry : allInfo.entrySet()) {
                    writer.write(entry.getKey());
                    writer.newLine();
                    writer.write(entry.getValue());
                    writer.write("\n\n\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            textLabel1.setText("Fájl mentése sikeres!");
        }
    }
}
