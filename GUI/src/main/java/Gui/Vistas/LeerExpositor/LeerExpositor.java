package Gui.Vistas.LeerExpositor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import Congreso.Expositor;
import Congreso.Registro;
import Congreso.Util;
import Gui.Alerta;
import Gui.Vistas.PopUp;

public class LeerExpositor extends GridPane implements Initializable, PopUp.PopAble {

    // Elementos XML
    @FXML private TextField tfNombre, tfEdad, tfFono, tfCorreo, tfPais, tfOcupacion;
    @FXML private Button submit;
    @FXML private Text txtHeader;

    // Valor de retorno
    private Expositor expositor = null;
    private Registro registro;

    public LeerExpositor(Registro registro)  {
        super();
        this.registro = registro;
        
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
    
    public LeerExpositor(Registro registro, Expositor e) {
    	this(registro);
    	tfNombre.setText(e.getNombre());
    	tfEdad.setText(Integer.toString(e.getEdad()));
    	tfFono.setText(Long.toString(e.getFono()));
    	tfCorreo.setText(e.getCorreo());
    	tfPais.setText(e.getPais());
    	tfOcupacion.setText(e.getOcupacion());
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
        
        if(!Util.isAlphaOrSpace(pais)) {
        	Alerta.mostrarAlertaAdvertencia("El país ingresado no es válido");
        	return false;
        }
        
        if(!Util.isAlphaOrSpace(ocupacion)) {
        	Alerta.mostrarAlertaAdvertencia("La ocupación ingresada no es válida");
        	return false;
        }
        
        int edad = Integer.parseInt(strEdad);
        int fono = Integer.parseInt(strFono);
        
        expositor = new Expositor(nombre, edad, fono, correo, pais, ocupacion);
        return true;
    }
    
    public void setHeader(String text) {
    	txtHeader.setText(text);
    }
}
