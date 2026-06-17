module universite_paris8.iut.rdias.towerdefense {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens universite_paris8.iut.rdias.towerdefense to javafx.fxml;
    exports universite_paris8.iut.rdias.towerdefense;
    exports universite_paris8.iut.rdias.towerdefense.controller;

    opens universite_paris8.iut.rdias.towerdefense.controller to javafx.fxml;
}