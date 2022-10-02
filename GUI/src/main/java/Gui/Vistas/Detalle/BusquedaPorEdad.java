package Gui.Vistas.Detalle;
import java.io.IOException;
import java.util.Iterator;

import Congreso.Persona;
import Congreso.Registro;
import Gui.Alerta;
import Gui.Vistas.VistaPersona.VistaDetallePersona;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/* Cambia la ventana a una busqueda de asistentes por edad */
public class BusquedaPorEdad extends VBox {

    @FXML private TextField textBox;
    @FXML private VBox scrollBox;
    private Registro r;
    
    /* Recibe y carga el FXML que corresponde y el atributo registro con los asistentes */
    public BusquedaPorEdad(Registro r)
    {
        super();
        this.r = r;
        // TODO : Convertir importar en una clase abstracta que recibe como parametro
        // el tipo de ventana y la ruta al xml.
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/BusquedaPorEdad.fxml"));
        fxmlLoader.setController(this);
        Node n = null;

        try {
            n = fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.getChildren().add(n);
    }
    
    /* Comrueba si el texto ingresado corresponde a una edad int
     * si lo ingresado no es una edad tirara un error, en caso 
     * de ser una edad ira comprobando y guardando aquellos 
     * asistentes que tengan edad mayor o igual a la ingresada 
     * dentro del scrollBox.
     *  */
    public void textInput() {
        String t = this.textBox.getText();
        int edad;
        try {
            edad = Integer.parseInt(t);
        } catch(NumberFormatException e) {
            Alerta.mostrarAlertaAdvertencia("Ingrese un número.");
            return;
        }
        scrollBox.getChildren().clear();
        
        Iterator<Persona> itAsistentes = r.getAsistentes();
        
        while(itAsistentes.hasNext()) {
        	Persona a = itAsistentes.next();
            if (a.getEdad() > edad) {
                VistaDetallePersona vp = new VistaDetallePersona(a);
                scrollBox.getChildren().add(vp);
            }
        }
        
    }
    
}
