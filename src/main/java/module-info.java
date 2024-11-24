module com.example.javaeloadasbeadando {
    requires javafx.fxml;
    requires javafx.controls;

    opens com.example.javaeloadasbeadando to javafx.fxml;
    exports com.example.javaeloadasbeadando;
    exports com.example.javaeloadasbeadando.controllers;

    opens com.example.javaeloadasbeadando.controllers to javafx.fxml;
}