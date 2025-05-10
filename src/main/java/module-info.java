module org.biblio {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.biblio.controllers to javafx.fxml;
    opens org.biblio to javafx.fxml;
    opens org.biblio.model to javafx.base;

    exports org.biblio;
}