module com.proyecto3evaluacion.respawnix {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.xml.bind;
    requires java.sql;
    requires jbcrypt;


    opens com.proyecto3evaluacion.respawnix to javafx.fxml;
    exports com.proyecto3evaluacion.respawnix;
    exports com.proyecto3evaluacion.respawnix.controller;
    opens com.proyecto3evaluacion.respawnix.controller to javafx.fxml;
    opens com.proyecto3evaluacion.respawnix.baseDatos to java.xml.bind;
    opens com.proyecto3evaluacion.respawnix.model to java.xml.bind;
}