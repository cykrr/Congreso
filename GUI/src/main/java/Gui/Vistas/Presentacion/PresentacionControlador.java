package Gui.Vistas.Presentacion;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import Congreso.Persona;
import Congreso.Presentacion;
import Congreso.Util;

public class PresentacionControlador implements Initializable {

    @FXML private ComboBox<String> expositorEntrada;
    @FXML private TextField nombreEntrada;
    @FXML private DatePicker fechaEntrada;
    @FXML private Button submit;

    private List<Persona> expositores;
    private List<Persona> asistentes;
    private Presentacion p;

    public PresentacionControlador(List<Persona> expositores, List<Persona> asistentes) {
        this.expositores = expositores;
        this.asistentes = asistentes;
    }
    
    // Persona a crear
    @Override
    public void initialize(URL url, ResourceBundle resources) {
        p = new Presentacion();
        expositoresEntrada.getItems().addAll("Hola", "123");
    }

    public void setName() {
        System.out.println("Estableciendo nombre de la presentación: " + nombreEntrada.getText());
        p.setNombre(nombreEntrada.getText());
    }

    public void setFecha() {
        LocalDate fecha = fechaEntrada.getValue();
        p.setFecha(fecha);
        System.out.println("Estableciendo fecha de la presentación: " + Util.dateFormatter.format(fecha));
    }
     
}
