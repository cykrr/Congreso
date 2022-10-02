package Gui.Vistas.Busquedas;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Iterator;

import Congreso.Presentacion;
import Congreso.Registro;
import Congreso.Util;
import Gui.Alerta;
import Gui.Vistas.VistaPresentacion.VistaDetallePresentacion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;

/* Cambia la ventana a una busqueda de presentacion por fecha */
public class BusquedaPorFecha extends VBox {
	// Elementos XML
    @FXML private VBox scrollBox;
    @FXML private DatePicker dpFecha;
    
    // Referencia al registro principal
    private Registro r;
    
    /**	@brief Constructor de la vista
     * 
     * Carga el archivo fxml que corresponde para abrirlo como ventana.
     * @param registro Es el atributo de clase Registro que contiene todas las presentaciones.
     * */
    public BusquedaPorFecha(Registro r)
    {
        super();
        this.r = r;
        // TODO : Convertir importar en una clase abstracta que recibe como parametro
        // el tipo de ventana y la ruta al xml.
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/BusquedaPorFecha.fxml"));
        fxmlLoader.setController(this);
        Node n = null;

        try {
            n = fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.getChildren().add(n);
    }
    
    /** 
     * Comrueba si el texto ingresado corresponde a una fecha LocalDate con el formato necesario
     * si lo ingresado no es una fecha tirara un error, en caso 
     * de ser una fecha va comprobando guardando aquellas 
     * presentaciones cuaya fecha corresponda a la ingresada o sea
     * posterior a ella dentro del scrollBox.
     *  */
    public void textInput() {
        String t = this.dpFecha.getEditor().getText().trim();
        LocalDate fecha = this.dpFecha.getValue();
        try {
            fecha = Util.parseDate(t);
        } catch(DateTimeParseException | NullPointerException e) {
            Alerta.mostrarAlertaAdvertencia("Ingrese una fecha válida.");
            return;
        }
        
        scrollBox.getChildren().clear();
        
        Iterator<Presentacion> itPresentaciones = r.getPresentaciones();
        while(itPresentaciones.hasNext()) {
        	Presentacion p = itPresentaciones.next();
        	
            if (p.getFecha().isEqual(fecha) || p.getFecha().isAfter(fecha)) {
                VistaDetallePresentacion vp = new VistaDetallePresentacion(p);
                scrollBox.getChildren().add(vp);
            }
        }
        
    }
    
}
