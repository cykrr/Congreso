package Gui.Vistas.LeerPresentacion;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import Congreso.Persona;
import Congreso.Presentacion;
import Congreso.Registro;
import Congreso.Util;

import Gui.Vistas.PopUp;

/* (no-Javadoc) La vista presentación depende de la lista de expositores y la lista de asistentes 
 * valores que debería recibir como parámetros. */

public class LeerPresentacion extends GridPane implements Initializable, PopUp.PopAble {


    // Elementos XML
    @FXML private ComboBox<Persona> comboExpositor;
    @FXML private TextField tfNombre, tfHora, tfDuracion, tfDescripcion;
    @FXML private DatePicker dpFecha;
    @FXML private Button submit;

    // Referencia al registro principal
    private Registro registro;

    // Valor de retorno
    private Presentacion p = null;


    /* Constructor de la vista
     * Requiere el registro para utilizarlo
     * en la inicialización */
    public LeerPresentacion(Registro r)  {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
            "/vistas/leerPresentacion.fxml"));
        fxmlLoader.setController(this);
        this.registro = r;
        Node n = null;

        try {
            n = fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.getChildren().add(n);
    }

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        p = new Presentacion();
        ObservableList<Persona> items = comboExpositor.getItems();
        for (Persona e : registro.getExpositores()) {

            items.add(e);
        }
    }

    @Override
    public Object getValue() {
        return this.p;
    }

    @Override
    public boolean guardar() {
        p = null;
        String nombre = tfNombre.getText();
        if (nombre != null && !nombre.equals("")) {
            p = new Presentacion(nombre);
        } else {
            return false;
        }
        LocalDate fecha = dpFecha.getValue();
        if (fecha != null) {
            p.setFecha(fecha);
        }

        Persona e = comboExpositor.getValue();
        if (e != null) {
            p.setExpositor(e);
        }
        
        String hora = tfHora.getText();
        LocalTime time = Util.parseTime(hora);
        p.setHora(time);
        
        String duracion = tfDuracion.getText();
        if(Util.isNumeric(duracion))
        	p.setDuracion(Integer.parseInt(duracion));
        
        String descripcion = tfDescripcion.getText();
        p.setDescripcion(descripcion);
        
        return true;
    }
}
