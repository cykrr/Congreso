package Gui.Vistas.LeerExpositor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Congreso.Expositor;
import Congreso.Registro;
import Congreso.excepciones.InvalidCorreoException;
import Congreso.excepciones.InvalidEdadException;
import Congreso.excepciones.InvalidFonoException;
import Congreso.excepciones.InvalidNombreException;
import Gui.Alerta;
import Gui.Vistas.PopUp;

/* Clase encargada de mostrar la ventana en caso de crear o editar expositor */
public class LeerExpositor extends PopUp implements PopUp.PopAble {

    // Elementos XML
    @FXML private TextField tfNombre, tfEdad, tfFono, tfCorreo, tfPais, tfOcupacion;
    @FXML private Button submit;
    @FXML private Text txtHeader;

    // Valor de retorno
    private Expositor expositor = null;
    private Registro registro;
    private boolean editando = false;
    
    /*	@brief Constructor de la vista
     * Carga el archivo fxml que corresponde para
     * abrirlo como ventana.
     * */
    public LeerExpositor(Registro registro, Stage stage)  {
        super(stage, "/vistas/leerExpositor.fxml");
        this.registro = registro;
    }
    
    /*
     * Constructor que inicia los atributos TextField
     * que se mostraran en la ventana
     * */
    public LeerExpositor(Registro registro, Stage stage, Expositor expositor) {
    	this(registro, stage);
    	this.expositor = expositor;
    	editando = true;
    	
    	tfNombre.setText(expositor.getNombre());
    	tfEdad.setText(Integer.toString(expositor.getEdad()));
    	tfFono.setText(Long.toString(expositor.getFono()));
    	tfCorreo.setText(expositor.getCorreo());
    	tfPais.setText(expositor.getPais());
    	tfOcupacion.setText(expositor.getOcupacion());
    }

    @Override
    /* Retorna el atributo expositor de clase Expositor casteada en Object */
    public Object getValue() {
        return expositor;
    }

    @Override
    /*
     * Lee lo ingresado por el usuario para el expositor, confirma si existe algun 
     * error o valor no valido ingresado, en caso de haber, se abre una 
     * ventana de error con un mensaje de lo ingresado erroneamente, si no hay error, 
     * guarda la informacion agregando o editando un expositor. 
     * */
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
    
    /* Recibe un String para utilizar como texto en la ventana. */
    public void setHeader(String text) {
    	txtHeader.setText(text);
    }
}
