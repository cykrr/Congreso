package Gui.Vistas.Presentacion;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import Congreso.Persona;

/* (no-Javadoc) La vista presentación depende de la lista de expositores y la lista de asistentes 
 * valores que debería recibir como parámetros. */

public class VistaPresentacion extends GridPane {

    PresentacionControlador controlador;


    /* @param expositores InmutableList 
     * @param asistentes InmutableList 
     */

    public VistaPresentacion(List<Persona> expositores, List<Persona> asistentes)  {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
            "/vistas/presentacion.fxml"));
        controlador = new PresentacionControlador(expositores, asistentes);
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
