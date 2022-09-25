package Gui.Vistas.Asistente;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Congreso.Persona;
import Congreso.Registro;
import Congreso.Util;
import Gui.Alerta;
import Gui.Vistas.PopUp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class LeerAsistente extends GridPane implements Initializable, PopUp.PopAble {

    // Elementos XML
    @FXML private TextField tfNombre, tfEdad, tfFono, tfCorreo;
    @FXML private Button submit;
    @FXML private Text txtHeader;

    // Valor de retorno
    private Persona asistente = null;
    private Registro registro;

    public LeerAsistente(Registro registro)  {
        super();
        this.registro = registro;
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/leerAsistente.fxml"));
        fxmlLoader.setController(this);
        Node n = null;

        try {
            n = fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.getChildren().add(n);
    }
    
    public LeerAsistente(Registro registro, Persona p) {
    	this(registro);
    	tfNombre.setText(p.getNombre());
    	tfEdad.setText(Integer.toString(p.getEdad()));
    	tfFono.setText(Long.toString(p.getFono()));
    	tfCorreo.setText(p.getCorreo());
    }

    @Override
    public void initialize(URL url, ResourceBundle resources) {
    	
    }

    @Override
    public Object getValue() {
        return asistente;
    }

    @Override
    public boolean guardar() {
        String nombre = tfNombre.getText().trim();
        String correo = tfCorreo.getText().trim();
        String strEdad = tfEdad.getText().trim();
        String strFono = tfFono.getText().trim();
        
        if(nombre.isEmpty() || correo.isEmpty() || strEdad.isEmpty() || strFono.isEmpty()) {
            Alerta.mostrarAlertaAdvertencia("No pueden quedar campos vacíos");
            return false;
        }
        
        if(!Util.isAlphaOrSpace(nombre)) {
        	Alerta.mostrarAlertaAdvertencia("El nombre ingresado no es válido");
        	return false;
        }
        
        if(!Util.isNumeric(strEdad)) {
        	Alerta.mostrarAlertaAdvertencia("La edad ingresada no es válida");
        	return false;
        }
        
        if(!Util.isNumeric(strFono)) {
        	Alerta.mostrarAlertaAdvertencia("El teléfono ingresado no es válido");
        	return false;
        }
        
        if(!Util.validateEmail(correo)) {
        	Alerta.mostrarAlertaAdvertencia("El email ingresado no es válido");
        	return false;
        }
        
        int edad = Integer.parseInt(strEdad);
        int fono = Integer.parseInt(strFono);
        
        asistente = new Persona(nombre, edad, fono, correo);
        return true;
    }
    
    public void setHeader(String text) {
    	txtHeader.setText(text);
    }
}
