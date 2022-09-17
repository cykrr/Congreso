package Gui.Vistas.Calendario;

import java.time.LocalDate;

import javafx.fxml.FXMLLoader;

import javafx.scene.control.DatePicker;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.Pane;

/* DatePicker pero solo la parte de adentro
 */
public class Calendario extends Pane {
    ControladorCalendario controlador;
    public Calendario() {
        this(LocalDate.now());
    }

    public Calendario(LocalDate date) {
        super();
        controlador = new ControladorCalendario();
        FXMLLoader fmxlloader = new FXMLLoader();
            fmxlloader.setRoot(this);
            fmxlloader.setController(controlador);
        DatePicker dp = new DatePicker(date);
        DatePickerSkin dps = new DatePickerSkin(dp);
        this.getChildren().add(dps.getPopupContent());
    }
}
