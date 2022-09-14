package Gui.Vistas.Presentacion;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class VistaPresentacion extends GridPane {

    PresentacionControlador controlador;


    public VistaPresentacion()  {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
            "/vistas/presentacion.fxml"));
        controlador = new PresentacionControlador();
        fxmlLoader.setController(controlador);
        Node n = null;

        try {
            n = fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.getChildren().add(n);
    }
}
