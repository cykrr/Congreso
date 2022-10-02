package Gui.Vistas.LeerAsistente;

import Congreso.Persona;
import Congreso.Registro;
import Congreso.Util;
import Congreso.excepciones.InvalidCorreoException;
import Congreso.excepciones.InvalidEdadException;
import Congreso.excepciones.InvalidFonoException;
import Congreso.excepciones.InvalidNombreException;
import Gui.Alerta;
import Gui.Vistas.PopUp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/* Clase encargada de mostrar la ventana en caso de crear o editar asistente */
public class LeerAsistente extends PopUp implements PopUp.PopAble {

    // Elementos XML
    @FXML private TextField tfNombre, tfEdad, tfFono, tfCorreo;
    @FXML private Button submit;
    @FXML private Text txtHeader;
    
    // Referencia al registro principal
    private Registro registro;
    
    // Valor de retorno
    private Persona asistente = null;
    private boolean editando = false;
    
    /**	@brief Constructor de la vista
     * 
     * Carga el archivo fxml que corresponde para abrirlo como ventana.
     * @param registro Es el atributo de clase Registro que contiene todos los asistentes.
     * @param stage Atributo con el escenario que se muestra en ventana al agregar o editar un asistente.
     * */
    public LeerAsistente(Registro registro, Stage stage)  {
        super(stage, "/vistas/leerAsistente.fxml");
        this.registro = registro;
    }
    
    /**	@brief Constructor de la vista
     * 
     * Inicia los atributos de el asistente como TextField para mostrarlos por ventana de no estar vacios.
     * Carga el archivo fxml que corresponde para abrirlo como ventana.
     * @param registro Es el atributo de clase Registro que contiene todos los asistentes.
     * @param stage Atributo con el escenario que se muestra en ventana al agregar o editar un asistente.
     * @param expositor Es el expositor cuya informacion se visualiza.
     * */
    public LeerAsistente(Registro registro, Stage stage, Persona p) {
    	this(registro, stage);
    	editando = true;
    	
    	tfNombre.setText(p.getNombre());
    	tfEdad.setText(Integer.toString(p.getEdad()));
    	tfFono.setText(Long.toString(p.getFono()));
    	tfCorreo.setText(p.getCorreo());
    }
    
    /**
     * @return El expositor casteado como Object
     * */
    @Override
    public Object getValue() {
        return asistente;
    }


    /*
     * Lee lo ingresado por el usuario para el asistente, confirma si existe algun 
     * error o valor no valido ingresado, en caso de haber, se abre una 
     * ventana de error con un mensaje de lo ingresado erroneamente, si no hay error, 
     * guarda la informacion agregando o editando un asistente. 
     * */
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
        
        if(registro.buscarAsistente(nombre) != null && editando == false) {
        	Alerta.mostrarAlertaAdvertencia("Ya existe un asistente con el nombre ingresado");
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
        
        int edad = Integer.parseInt(strEdad);
        long fono = Long.parseLong(strFono);
        
        try {
			asistente = new Persona(nombre, edad, fono, correo);
			return true;
		} catch (InvalidNombreException e) {
			Alerta.mostrarAlertaAdvertencia("El nombre no puede contener caracteres especiales");
		} catch (InvalidEdadException e) {
			Alerta.mostrarAlertaAdvertencia("La edad debe estar en el rango de 1 a 100");
		} catch (InvalidFonoException e) {
			Alerta.mostrarAlertaAdvertencia("El teléfono debe tener entre 8 y 12 dígitos");
		} catch (InvalidCorreoException e) {
			Alerta.mostrarAlertaAdvertencia("El correo ingresado no es válido");
		}
        return false;
    }
    
    /**
     * Muestra comencabezado el texto recibido.
     * @param texto Es un atributo String con un mensaje que se usara de encabezado en la ventana.
     * */
    public void setHeader(String text) {
    	txtHeader.setText(text);
    }
}
