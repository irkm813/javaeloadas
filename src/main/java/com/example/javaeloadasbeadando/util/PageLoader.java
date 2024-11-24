package com.example.javaeloadasbeadando.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import java.io.IOException;

public class PageLoader {
    public static Node load(String fxmlFile) {
        try {
            return FXMLLoader.load(PageLoader.class.getResource("/pages/" + fxmlFile+".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}