module com.example.agravisit {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;

    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires com.fasterxml.jackson.core;

    opens com.example.agravisit to javafx.fxml;
    opens com.example.agravisit.model to com.fasterxml.jackson.databind;
    opens com.example.agravisit.service to com.fasterxml.jackson.databind;

    exports com.example.agravisit;
}