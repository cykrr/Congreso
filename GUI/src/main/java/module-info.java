module Gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.prefs;
    requires Congreso;
    exports Gui to javafx.graphics;

    opens Gui.Vistas.Dashboard to javafx.fxml;
    opens Gui to javafx.fxml;
    opens Gui.Vistas to javafx.fxml;
    opens Gui.Vistas.VistaPresentacion to javafx.fxml;
    opens Gui.Vistas.VistaExpositor to javafx.fxml;
    opens Gui.Vistas.VistaPersona to javafx.fxml;
    opens Gui.Vistas.Busquedas to javafx.fxml;
    opens Gui.Vistas.LeerAsistente to javafx.fxml;
    opens Gui.Vistas.LeerPresentacion to javafx.fxml;
    opens Gui.Vistas.LeerExpositor to javafx.fxml;
}
