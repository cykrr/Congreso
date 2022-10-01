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
import Congreso.excepciones.InvalidCorreoException;
import Congreso.excepciones.InvalidEdadException;
import Congreso.excepciones.InvalidFonoException;
import Congreso.excepciones.InvalidNombreException;
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
    private boolean editando = false;

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
    	editando = true;
    	
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
        
        if(registro.buscarExpositor(nombre) != null && editando == false) {
        	Alerta.mostrarAlertaAdvertencia("Ya existe un expositor con el nombre ingresado");
        	return false;
        }
        
        int edad;
        long fono;
        
        try {
        	edad = Integer.parseInt(strEdad);
        } catch(NumberFormatException e) {
        	Alerta.mostrarAlertaAdvertencia("La edad ingresada no es válida");
        	return false;
        }
        
        try {
        	fono = Long.parseLong(strFono);
        } catch(NumberFormatException e) {
        	Alerta.mostrarAlertaAdvertencia("El teléfono ingresado no es válido");
        	return false;
        }
        
        try {
			expositor = new Expositor(nombre, edad, fono, correo, pais, ocupacion);
			return true;
		} catch (InvalidNombreException e) {
			Alerta.mostrarAlertaAdvertencia("El nombre no puede contener caracteres especiales");
		} catch (InvalidEdadException e) {
			Alerta.mostrarAlertaAdvertencia("La edad debe estar en el rango de 18 a 100");
		} catch (InvalidFonoException e) {
			Alerta.mostrarAlertaAdvertencia("El teléfono debe tener entre 8 y 12 dígitos");
		} catch (InvalidCorreoException e) {
			Alerta.mostrarAlertaAdvertencia("El correo ingresado no es válido");
		}
        
        return false;
    }
    
    public void setHeader(String text) {
    	txtHeader.setText(text);
    }
}
