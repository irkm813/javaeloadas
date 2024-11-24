package com.example.javaeloadasbeadando;

import com.example.javaeloadasbeadando.util.PageLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    private StackPane contentPane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Main layout
        BorderPane root = new BorderPane();

        // Top menu
        MenuBar menuBar = createMenuBar();
        root.setTop(menuBar);

        // Content area
        contentPane = new StackPane();
        root.setCenter(contentPane);

        // Default content
        switchPage("WelcomePage");

        // Scene and stage setup
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("JavaFX App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        // File menu
        Menu fileMenu = new Menu("File");
        MenuItem welcomeItem = new MenuItem("WelcomePage");
        MenuItem exitItem = new MenuItem("Exit");

        welcomeItem.setOnAction(e -> switchPage("WelcomePage"));
        exitItem.setOnAction(e -> System.exit(0));
        fileMenu.getItems().addAll(welcomeItem, exitItem);

        // Adatbázis menü
        Menu adatbazisMenu = new Menu("Adatbázis");
        MenuItem olvasItem = new MenuItem("Olvas");
        MenuItem olvas2Item = new MenuItem("Olvas2");
        MenuItem irItem = new MenuItem("Ír");

        olvasItem.setOnAction(e -> switchPage("OlvasPage"));
        olvas2Item.setOnAction(e -> switchPage("Olvas2Page"));
        irItem.setOnAction(e -> switchPage("IrPage"));

        adatbazisMenu.getItems().addAll(olvasItem, olvas2Item, irItem);


        // Soap Kliens menü
        Menu soapKliensMenu = new Menu("Soap kliens");
        MenuItem letoltesItem = new MenuItem("Letöltés");
        MenuItem letoltes2Item = new MenuItem("Letöltés2");
        MenuItem grafikonItem = new MenuItem("Grafikon");

        letoltesItem.setOnAction(e -> switchPage("LetoltesPage"));
        letoltes2Item.setOnAction(e -> switchPage("Letoltes2Page"));
        grafikonItem.setOnAction(e -> switchPage("GrafikonPage"));

        soapKliensMenu.getItems().addAll(letoltesItem, letoltes2Item, grafikonItem);

        // Soap Kliens menü
        Menu parhuzamosMenu = new Menu("Párhuzamos");
        MenuItem parhuzamosItem = new MenuItem("Párhuzamos programvégrahajtás");

        parhuzamosItem.setOnAction(e -> switchPage("ParhuzamosPage"));

        parhuzamosMenu.getItems().addAll(parhuzamosItem);


        // Soap Kliens menü
        Menu forexMenu = new Menu("Forex");
        MenuItem szamlainfokItem = new MenuItem("Számlainformációk");
        MenuItem aktualisArakItem = new MenuItem("Aktuális Árak");
        MenuItem historikusArakItem = new MenuItem("Historikus Árak");
        MenuItem pozincioNyitasItem = new MenuItem("Pozició nyitás");
        MenuItem pozicioZarasItem = new MenuItem("Pozició Zárás");

        szamlainfokItem.setOnAction(e -> switchPage("SzamlaInfoPage"));
        aktualisArakItem.setOnAction(e->switchPage("AktualisArakPage"));
        historikusArakItem.setOnAction(e->switchPage("HistorikusArakPage"));
        pozincioNyitasItem.setOnAction(e->switchPage("PozincioNyitasPage"));
        pozicioZarasItem.setOnAction(e->switchPage("PozicioZarasPage"));

        forexMenu.getItems().addAll(szamlainfokItem,aktualisArakItem,historikusArakItem,pozincioNyitasItem,pozicioZarasItem);

        // Add menus to the menu bar
        menuBar.getMenus().addAll(fileMenu, adatbazisMenu, soapKliensMenu, forexMenu);

        return menuBar;
    }

    private void switchPage(String pageName) {
        // Load content dynamically from FXML
        contentPane.getChildren().clear();
        contentPane.getChildren().add(PageLoader.load(pageName));
    }
}