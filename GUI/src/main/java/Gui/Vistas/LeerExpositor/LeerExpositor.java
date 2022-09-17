package Gui.Vistas.LeerExpositor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import Congreso.Expositor;
import Congreso.Persona;
import Congreso.Util;
import Gui.Alerta;
import Gui.Vistas.PopUp;

public class LeerExpositor extends GridPane implements Initializable, PopUp.PopAble {

    // Elementos XML
    @FXML private TextField tfNombre, tfEdad, tfFono, tfCorreo, tfPais, tfOcupacion;
    @FXML private Button submit;

    // Valor de retorno
    private Expositor expositor = null;

    public LeerExpositor()  {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/leerExpositor.fxml"));
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
        return expositor;
    }

    @Override
    public boolean guardar() {
        String nombre = tfNombre.getText().trim();
        String correo = tfCorreo.getText().trim();
        String pais = tfPais.getText().trim();
        String ocupacion = tfOcupacion.getText().trim();
        String strEdad = tfEdad.getText().trim();
        String strFono = tfFono.getText().trim();
        
        if(nombre.isEmpty() || correo.isEmpty() || pais.isEmpty() || ocupacion.isEmpty() || strEdad.isEmpty() || strFono.isEmpty()) {
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
        
        if(!Util.isAlphaOrSpace(pais)) {
        	Alerta.showAlert("El país ingresado no es válido");
        	return false;
        }
        
        if(!Util.isAlphaOrSpace(ocupacion)) {
        	Alerta.showAlert("La ocupación ingresada no es válida");
        	return false;
        }
        
        int edad = Integer.parseInt(strEdad);
        int fono = Integer.parseInt(strFono);
        
        expositor = new Expositor(nombre, edad, fono, correo, pais, ocupacion);
        return true;
    }
}
