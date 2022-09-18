package Gui.Vistas.VPresentacion;

import Congreso.Presentacion;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Vpresentacion extends VBox {
    public Vpresentacion(Presentacion p) {
        super();
        this.getChildren().add(new Text(p.getNombre()));
    }
}
