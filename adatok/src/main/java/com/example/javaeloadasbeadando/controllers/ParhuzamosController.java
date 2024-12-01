package com.example.javaeloadasbeadando.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParhuzamosController {

    @FXML
    private Button startButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Button stopButton;
    @FXML
    private Label labelOne;
    @FXML
    private Label labelTwo;
    @FXML
    private Label statusLabel;

    private ExecutorService executorService;
    private boolean paused = false;
    private int counter1 = 0;
    private int counter2 = 0;

    @FXML
    public void initialize() {
        startButton.setOnAction(event -> restartParallelExecution());
        pauseButton.setOnAction(event -> togglePause());
        stopButton.setOnAction(event -> stopParallelExecution());

        // Alapértelmezett állapot
        Platform.runLater(() -> statusLabel.setText("Állapot: Leállítva"));
    }

    private void restartParallelExecution() {
        // Előző ExecutorService leállítása, ha futott
        if (executorService != null) {
            executorService.shutdownNow();
        }

        // Számlálók nullázása
        counter1 = 0;
        counter2 = 0;

        // Számlálók alapértelmezett szövegének beállítása
        Platform.runLater(() -> {
            labelOne.setText("Első számláló: 0");
            labelTwo.setText("Második számláló: 0");
            statusLabel.setText("Állapot: Fut");
        });

        // Új ExecutorService létrehozása
        executorService = Executors.newFixedThreadPool(2);

        // Első számláló (1 másodpercenként frissül)
        executorService.submit(() -> {
            while (!executorService.isShutdown()) {
                if (!paused) {
                    int finalCounter1 = counter1++;
                    Platform.runLater(() -> labelOne.setText("Első számláló: " + finalCounter1));
                }
                try {
                    Thread.sleep(1000); // 1 másodperc
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        // Második számláló (2 másodpercenként frissül)
        executorService.submit(() -> {
            while (!executorService.isShutdown()) {
                if (!paused) {
                    int finalCounter2 = counter2++;
                    Platform.runLater(() -> labelTwo.setText("Második számláló: " + finalCounter2));
                }
                try {
                    Thread.sleep(2000); // 2 másodperc
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        // Szünet állapot visszaállítása
        paused = false;
        pauseButton.setText("Szünet");
    }

    private void togglePause() {
        if (executorService == null || executorService.isShutdown()) {
            return; // Ha nincs futó számláló, a szünet nem értelmezhető
        }
        paused = !paused;
        Platform.runLater(() -> statusLabel.setText(paused ? "Állapot: Szünet" : "Állapot: Fut"));
        pauseButton.setText(paused ? "Folytatás" : "Szünet");
    }

    private void stopParallelExecution() {
        if (executorService != null) {
            executorService.shutdownNow();
        }

        // Számlálók alapértelmezett szövegének beállítása
        Platform.runLater(() -> {
            labelOne.setText("Első számláló: 0");
            labelTwo.setText("Második számláló: 0");
            statusLabel.setText("Állapot: Leállítva");
        });

        // Szünet állapot alaphelyzetbe állítása
        paused = false;
        pauseButton.setText("Szünet");
    }
}
