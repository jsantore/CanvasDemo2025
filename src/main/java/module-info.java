module bsu.comp152.canvasdemo2025 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens bsu.comp152.canvasdemo2025 to javafx.fxml;
    exports bsu.comp152.canvasdemo2025;
}