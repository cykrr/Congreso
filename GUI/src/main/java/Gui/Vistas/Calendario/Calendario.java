package Gui.Vistas.Calendario;

import java.time.LocalDate;

import javafx.fxml.FXMLLoader;

import javafx.scene.control.DatePicker;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.Pane;

/* DatePicker pero solo la parte de adentro
 */
public class Calendario extends Pane {
    
    /** @brief Constructor para el datePicker
     * 
     * 	Envia al otro constructor la fecha actual del pc.
     * */
    public Calendario() {
        this(LocalDate.now());
    }
    
    /** @brief Constructor para el datePicker
     * 	Recibe la fecha actual e inicializa el DatePicker con esa fecha.
     * 	@param date Fecha actual del pc en formato LocalDate
     * */
    public Calendario(LocalDate date) {
        super();
        FXMLLoader fmxlloader = new FXMLLoader();
            fmxlloader.setRoot(this);
        DatePicker dp = new DatePicker(date);
        DatePickerSkin dps = new DatePickerSkin(dp);
        this.getChildren().add(dps.getPopupContent());
    }
}
