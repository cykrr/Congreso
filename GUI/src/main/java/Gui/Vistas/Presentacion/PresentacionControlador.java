package Gui.Vistas.Presentacion;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import Congreso.Persona;
import Congreso.Presentacion;
import Congreso.Registro;
import Congreso.Util;

public class PresentacionControlador implements Initializable {

    @FXML private ComboBox<Persona> expositorEntrada;
    @FXML private TextField nombreEntrada;
    @FXML private DatePicker fechaEntrada;
    @FXML private Button submit;
    private Registro registro;

    private Presentacion p;

    public PresentacionControlador(Registro r) {
        this.registro = r;
    }
    
    // Persona a crear
    @Override
    public void initialize(URL url, ResourceBundle resources) {
        p = new Presentacion();
        ObservableList<Persona> items = expositorEntrada.getItems();
        for (Persona e : registro.getExpositores()) {

            items.add(e);
        }
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

    public void setExpositor() {
        System.out.println("Estableciendo expositor: " + expositorEntrada.getValue());
        p.setExpositor(expositorEntrada.getValue());
    }
     
}
