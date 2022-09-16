package Gui;
import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Gui.Vistas.PopUp;
import Gui.Vistas.LeerExpositor.LeerExpositor;
import Gui.Vistas.LeerPresentacion.LeerPresentacion;

import Congreso.Persona;
import Congreso.Presentacion;
import Congreso.Registro;
import Congreso.Util;


/* Inicializa la clase raíz de la ventana */
public class Controlador implements Initializable {

    
    private     Registro    registro; // Referencia a la base de datos del programa
    private     Stage       stage;    // Ventana principal
    private     Ajustes     ajustes;  // Ajustes del programa

    // Constructor se ejecuta antes de inflar
    public Controlador(Stage s) {
        System.out.println("Cargando ajustes");
        this.ajustes = new Ajustes(); 

        System.out.println("Ajustes guardados: "+ ajustes.carpeta);


        System.out.println("Inicializando registro");
        this.registro = new Registro();

        System.out.println("Cargando datos: ");
        try {
            registro.importar(ajustes.carpeta + "/Presentaciones.csv");
        } catch(IOException e) {
            e.printStackTrace();
            System.err.println("Error: revise su configuración");
        }


        this.stage = s; 
    }

    
    // initialize se ejecuta luego de inflar.
    public void initialize(URL url, ResourceBundle resources) {
    }

    /* Crea una nueva presentación por medio
     * de un PopUp y la añade al registro
     */
    public void crearPresentacion() {
        Presentacion retorno = null;
        // TODO : Proteger expositores y asistentes de modificación
        // TODO : Añadir hora y duración de la presentación

        LeerPresentacion lp = new LeerPresentacion(registro);
        PopUp popup = new PopUp(stage, lp);
        popup.setTitle("Crear presentación");
        
        retorno = (Presentacion)popup.showDialog();
        if (retorno != null)
            registro.insertarPresentacion(retorno);
        System.out.println(retorno);
    }
    
    /* Crea un nuevo expositor por medio
     * de un PopUp y lo añade al registro
     */
    public void crearExpositor() {
        Persona expositor = null;
        
        LeerExpositor le = new LeerExpositor();
        PopUp popup = new PopUp(stage, le);
        popup.setTitle("Crear expositor");
        
        expositor = (Persona) popup.showDialog();
        if(expositor != null)
        	registro.insertarPersona(expositor);
    }

    /* Importa un archivo por medio de un
     * file picker */
    public void importar() {
        System.out.println("Importando");
        FileChooser fc = new FileChooser();
        fc.setTitle("Seleccione el archivo a importar");
        File file;
        while(true) {
        	file = fc.showOpenDialog(stage);

        	// si no se elige ningún archivo
        	if(file == null)
        		return;

            // TODO : Mejor protección ante archivos no csv
            if (!Util.getFileExtension(file).equals("csv")) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("El archivo debe ser del tipo CSV");
                alert.showAndWait();
            } else {
            	break;
            }
        }

        try {
            registro.importar(file.getPath());
        } catch (IOException e) {
            System.err.println("Error abriendo archivo");
        }
    }
    
    public void exportar() {
    	try {
			registro.exportar();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    // TODO : Añadir más ajustes
    // TODO : Guardar de manera persistente la carpeta
    // TODO : Revisar si la carpeta existe antes de guardar
    public void editarPreferencias() {
        TextInputDialog dialog = new TextInputDialog("Preferencias");
        dialog.setTitle("Preferencias");
        dialog.setContentText("Please enter your name:");
    }
}
