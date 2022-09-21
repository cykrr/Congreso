package Gui.Vistas.Asistente;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Congreso.Persona;
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

public class LeerAsistente extends GridPane implements Initializable, PopUp.PopAble {

    // Elementos XML
    @FXML private TextField tfNombre, tfEdad, tfFono, tfCorreo;
    @FXML private Button submit;

    // Valor de retorno
    private Persona asistente = null;

    public LeerAsistente()  {
        super();
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
            Alerta.showAlert("No pueden quedar campos vacíos");
            return false;
        }
        
        if(!Util.isAlphaOrSpace(nombre)) {
        	Alerta.showAlert("El nombre ingresado no es válido");
        	return false;
        }
        
        if(!Util.isNumeric(strEdad)) {
        	Alerta.showAlert("La edad ingresada no es válida");
        	return false;
        }
        
        if(!Util.isNumeric(strFono)) {
        	Alerta.showAlert("El teléfono ingresado no es válido");
        	return false;
        }
        
        if(!Util.validateEmail(correo)) {
        	Alerta.showAlert("El email ingresado no es válido");
        	return false;
        }
        
        int edad = Integer.parseInt(strEdad);
        int fono = Integer.parseInt(strFono);
        
        asistente = new Persona(nombre, edad, fono, correo);
        return true;
    }
}
