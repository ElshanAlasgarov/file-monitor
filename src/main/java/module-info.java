module com.az.edu.turing.filemonitor {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.az.edu.turing.filemonitor.gui to javafx.graphics;
    opens com.az.edu.turing.filemonitor.model to javafx.base;
}
