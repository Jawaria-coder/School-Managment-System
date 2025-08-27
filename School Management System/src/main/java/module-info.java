module com.example.projectnew {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.projectnew to javafx.fxml;
    exports com.example.projectnew;
}