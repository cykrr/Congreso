package Gui.Vistas.ListaPresentaciones;

import java.net.URL;
import java.util.ResourceBundle;

import Congreso.Presentacion;
import Congreso.Registro;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LPControlador implements Initializable {

    private Registro registro;
    private Stage stage;
    private Scene scene;
    private VBox root;

    /* Controlador de la vista ListaPresentaciones
     * @param r Registro del programa */
    public LPControlador(Registro r) {
        this.registro = r;
    }

    /* Añadir Vistas Presentaciones a
     * ListaPresentaciones */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (Presentacion p : registro.getPresentaciones()) {
            // VBox n = new Presentacion(p);

        }
    }
}
