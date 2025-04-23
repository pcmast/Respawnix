module com.proyecto3evaluacion.respawnix {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.proyecto3evaluacion.respawnix to javafx.fxml;
    exports com.proyecto3evaluacion.respawnix;
    exports com.proyecto3evaluacion.respawnix.controller;
    opens com.proyecto3evaluacion.respawnix.controller to javafx.fxml;
}