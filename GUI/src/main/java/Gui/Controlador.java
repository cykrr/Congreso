package Gui;
import java.io.File;


import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Gui.Vistas.PopUp;
import Gui.Vistas.Asistente.LeerAsistente;
import Gui.Vistas.Dashboard.Dashboard;
// import Gui.Vistas.Asistente.LeerAsistente;
import Gui.Vistas.LeerExpositor.LeerExpositor;
import Gui.Vistas.LeerPresentacion.LeerPresentacion;
import Gui.Vistas.VPersona.VPersona;
import Gui.Vistas.VPresentacion.Vpresentacion;
import Congreso.Expositor;
import Congreso.Persona;
import Congreso.Presentacion;
import Congreso.Registro;
import Congreso.Util;


/** @brief Inicializa la clase raíz de la ventana */
public class Controlador implements Initializable {
	
    private     Registro    registro; // Referencia a la base de datos del programa
    private     Stage       stage;    // Ventana principal
    private     Ajustes     ajustes;  // Ajustes del programa
    private     Dashboard   child;
    
    private Map<Presentacion, Vpresentacion> mapaVpresentaciones;
    private Map<Persona, VPersona> mapaVpersonas;

    public void enCrearPresentacion(EventoPresentacion ep) {
        Vpresentacion vp = new Vpresentacion(ep.getPresentacion(), registro, stage, child);
        mapaVpresentaciones.put(ep.getPresentacion(), vp);
        child.getScrollBoxPresentaciones().getChildren().add(vp);
        child.actualizarNumeroPresentaciones();
    }
    
    public void enEditarPresentacion(EventoPresentacion ep) {	
    	Vpresentacion vpAntigua = mapaVpresentaciones.remove(ep.getPresentacionAntigua());
    	int index = child.getScrollBoxPresentaciones().getChildren().indexOf(vpAntigua);
    	child.getScrollBoxPresentaciones().getChildren().remove(index);
    	
        Vpresentacion vpNueva = new Vpresentacion(ep.getPresentacionNueva(), registro, stage, child);
        if(vpAntigua.estaExtendida())
        	vpNueva.alternarVistaExtendida();
        
        mapaVpresentaciones.put(ep.getPresentacionNueva(), vpNueva);
    	child.getScrollBoxPresentaciones().getChildren().add(index, vpNueva);
    }
    
    public void enEliminarPresentacion(EventoPresentacion ep) {
    	Vpresentacion vp = mapaVpresentaciones.remove(ep.getPresentacion());
    	child.getScrollBoxPresentaciones().getChildren().remove(vp);
    	child.actualizarNumeroPresentaciones();
    }
    
    public void enCrearPersona(EventoPersona ep) {
        VPersona vp = new VPersona(ep.getPersona(), registro, stage, child);
        mapaVpersonas.put(ep.getPersona(), vp);
        child.getScrollBoxAsistentes().getChildren().add(vp);
        child.actualizarNumeroAsistentes();
    }
    
    public void enEditarPersona(EventoPersona ep) {	
    	VPersona vpAntigua = mapaVpersonas.remove(ep.getPersonaAntigua());
    	int index = child.getScrollBoxAsistentes().getChildren().indexOf(vpAntigua);
    	child.getScrollBoxAsistentes().getChildren().remove(index);
    	
        VPersona vpNueva = new VPersona(ep.getPersonaNueva(), registro, stage, child);
        if(vpAntigua.estaExtendida())
        	vpNueva.alternarVistaExtendida();
        
        mapaVpersonas.put(ep.getPersonaNueva(), vpNueva);
    	child.getScrollBoxAsistentes().getChildren().add(index, vpNueva);
    }
    
    public void enEliminarPersona(EventoPersona ep) {
    	VPersona vp = mapaVpersonas.remove(ep.getPersona());
    	child.getScrollBoxAsistentes().getChildren().remove(vp);
    	child.actualizarNumeroAsistentes();
    }

    /** @brief Constructor se ejecuta antes de leer xml*/
    public Controlador(Stage s, Registro r) {
        System.out.println("Cargando ajustes");
        this.ajustes = new Ajustes(); 

        System.out.println("Ajustes guardados: "+ ajustes.carpeta);


        System.out.println("Inicializando registro");
        this.registro = r;

        System.out.println("Cargando datos: ");
        registro.importar(ajustes.carpeta + "/Presentaciones.csv",
        				  ajustes.carpeta + "/Expositores.csv",
        				  ajustes.carpeta + "/Asistentes.csv");
        
        mapaVpresentaciones = new HashMap<Presentacion, Vpresentacion>();
        mapaVpersonas = new HashMap<Persona, VPersona>();
    }

    /** @brief Método que se ejecuta luego de leer xml */
    public void initialize(URL url, ResourceBundle resources) {
        child.addEventFilter(EventoPresentacion.CREAR_PRESENTACION, e-> {
            enCrearPresentacion(e);
        });
        
        child.addEventFilter(EventoPresentacion.EDITAR_PRESENTACION, e-> {
            enEditarPresentacion(e);
        });
        
        child.addEventFilter(EventoPresentacion.ELIMINAR_PRESENTACION, e-> {
            enEliminarPresentacion(e);
        });
        
        child.addEventFilter(EventoPersona.CREAR_PERSONA, e-> {
            enCrearPersona(e);
        });
        
        child.addEventFilter(EventoPersona.EDITAR_PERSONA, e-> {
            enEditarPersona(e);
        });
        
        child.addEventFilter(EventoPersona.ELIMINAR_PERSONA, e-> {
            enEliminarPersona(e);
        });

        for (Presentacion p : registro.getPresentaciones()) {
            child.fireEvent(new EventoPresentacion(EventoPresentacion.CREAR_PRESENTACION, p));
        }
        
        for (Persona p : registro.getAsistentes()) {
            child.fireEvent(new EventoPersona(EventoPersona.CREAR_PERSONA, p));
        }
    }

    /** @brief genera un Popup para crear presentación
     *
     * Crea una nueva presentación por medio
     * de un PopUp y la añade al registro.
     *
     * Es posible invocar esta función desde la barra menú de la
     * aplicación
     */
    public void crearPresentacion() {
        Presentacion retorno = null;
        // TODO : Proteger expositores y asistentes de modificación
        // TODO : Añadir hora y duración de la presentación

        LeerPresentacion lp = new LeerPresentacion(registro);
        PopUp popup = new PopUp(stage, lp);
        popup.setTitle("Crear presentación");
        
        retorno = (Presentacion)popup.showDialog();
        if (retorno != null) {
            registro.insertarPresentacion(retorno);
            child.fireEvent(new EventoPresentacion(EventoPresentacion.CREAR_PRESENTACION, retorno));
            System.out.println(retorno);
        } else {
            System.err.println("no hay presentacion");
        }
    }
    
    /** @brief Crea un nuevo expositor
     *
     * por medio de un PopUp y lo añade al registro
     */
    public void crearExpositor() {
        Expositor expositor = null;
        
        LeerExpositor le = new LeerExpositor();
        PopUp popup = new PopUp(stage, le);
        popup.setTitle("Crear expositor");
        
        expositor = (Expositor) popup.showDialog();
        if(expositor != null)
        	registro.insertarExpositor(expositor);
    }
    
    /** @brief Crea un nuevo asistente
    *
    * por medio de un PopUp y lo añade al registro
    */
    public void crearAsistente() {
    	Persona asistente = null;
    	
    	LeerAsistente la = new LeerAsistente(registro);
    	PopUp popup = new PopUp(stage, la);
    	popup.setTitle("Crear asistente");
    	
    	asistente = (Persona) popup.showDialog();
    	if(asistente != null) {
    		registro.insertarAsistente(asistente);

        }
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

        
        /*
        try {
            registro.importar(file.getPath());
        } catch (IOException e) {
            System.err.println("Error abriendo archivo");
        }
        */
    }
    
    public void exportar() {
        registro.exportar(ajustes.carpeta + "/savedPresentaciones.csv",
				  		  ajustes.carpeta + "/savedExpositores.csv",
				  		  ajustes.carpeta + "/savedAsistentes.csv");
    }

    // TODO : Añadir más ajustes
    // TODO : Guardar de manera persistente la carpeta
    // TODO : Revisar si la carpeta existe antes de guardar
    public void editarPreferencias() {
        TextInputDialog dialog = new TextInputDialog("Preferencias");
        dialog.setTitle("Preferencias");
        dialog.setContentText("Please enter your name:");
    }

    public void setChild(Dashboard child) {
        this.child = child;
    }
}