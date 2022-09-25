package Gui;
import java.io.File;


import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Gui.Vistas.PopUp;
import Gui.Vistas.Asistente.LeerAsistente;
import Gui.Vistas.Dashboard.Dashboard;
import Gui.Vistas.LeerExpositor.LeerExpositor;
import Gui.Vistas.LeerPresentacion.LeerPresentacion;
import Gui.Vistas.VistaExpositor.VistaExpositor;
import Gui.Vistas.VistaPersona.VistaPersona;
import Gui.Vistas.VistaPresentacion.VistaPresentacion;
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
    private     Dashboard   dashboard;
    
    private Map<Presentacion, VistaPresentacion> mapaVistaPresentaciones;
    private Map<Persona, VistaPersona> mapaVistaPersonas;
    private Map<Expositor, VistaExpositor> mapaVistaExpositores;

    @FXML private HBox homeIcon;
    @FXML private HBox addIcon;
    @FXML private HBox detailIcon;

    @FXML private     VBox        vistaPrincipal;

    public void homeAction() {

    }

    public void addAction() {
        crearPresentacion();
    }

    public void detailAction() {
    }

    public void enCrearPresentacion(EventoPresentacion ep) {
        VistaPresentacion vp = new VistaPresentacion(ep.getPresentacion(), registro, stage, dashboard);
        mapaVistaPresentaciones.put(ep.getPresentacion(), vp);
        dashboard.getScrollBoxPresentaciones().getChildren().add(vp);
        dashboard.actualizarNumeroPresentaciones();
    }
    
    public void enEditarPresentacion(EventoPresentacion ep) {	
    	VistaPresentacion vpAntigua = mapaVistaPresentaciones.remove(ep.getPresentacionAntigua());
    	int index = dashboard.getScrollBoxPresentaciones().getChildren().indexOf(vpAntigua);
    	dashboard.getScrollBoxPresentaciones().getChildren().remove(index);
    	
        VistaPresentacion vpNueva = new VistaPresentacion(ep.getPresentacionNueva(), registro, stage, dashboard);
        if(vpAntigua.estaExtendida())
        	vpNueva.alternarVistaExtendida();
        
        mapaVistaPresentaciones.put(ep.getPresentacionNueva(), vpNueva);
    	dashboard.getScrollBoxPresentaciones().getChildren().add(index, vpNueva);
    }
    
    public void enEliminarPresentacion(EventoPresentacion ep) {
    	VistaPresentacion vp = mapaVistaPresentaciones.remove(ep.getPresentacion());
    	dashboard.getScrollBoxPresentaciones().getChildren().remove(vp);
    	dashboard.actualizarNumeroPresentaciones();
    }
    
    public void enCrearPersona(EventoPersona ep) {
        VistaPersona vp = new VistaPersona(ep.getPersona(), registro, stage, dashboard);
        mapaVistaPersonas.put(ep.getPersona(), vp);
        dashboard.getScrollBoxAsistentes().getChildren().add(vp);
        dashboard.actualizarNumeroAsistentes();
    }
    
    public void enEditarPersona(EventoPersona ep) {	
    	VistaPersona vpAntigua = mapaVistaPersonas.remove(ep.getPersonaAntigua());
    	int index = dashboard.getScrollBoxAsistentes().getChildren().indexOf(vpAntigua);
    	dashboard.getScrollBoxAsistentes().getChildren().remove(index);
    	
        VistaPersona vpNueva = new VistaPersona(ep.getPersonaNueva(), registro, stage, dashboard);
        if(vpAntigua.estaExtendida())
        	vpNueva.alternarVistaExtendida();
        
        mapaVistaPersonas.put(ep.getPersonaNueva(), vpNueva);
    	dashboard.getScrollBoxAsistentes().getChildren().add(index, vpNueva);
    }
    
    public void enEliminarPersona(EventoPersona ep) {
    	VistaPersona vp = mapaVistaPersonas.remove(ep.getPersona());
    	dashboard.getScrollBoxAsistentes().getChildren().remove(vp);
    	dashboard.actualizarNumeroAsistentes();
    }
    
    public void enCrearExpositor(EventoExpositor ee) {
        VistaExpositor ve = new VistaExpositor(ee.getExpositor(), registro, stage, dashboard);
        mapaVistaExpositores.put(ee.getExpositor(), ve);
        dashboard.getScrollBoxExpositores().getChildren().add(ve);
        dashboard.actualizarNumeroExpositores();
    }
    
    public void enEditarExpositor(EventoExpositor ee) {	
    	VistaExpositor veAntigua = mapaVistaExpositores.remove(ee.getExpositorAntiguo());
    	int index = dashboard.getScrollBoxExpositores().getChildren().indexOf(veAntigua);
    	dashboard.getScrollBoxExpositores().getChildren().remove(index);
    	
        VistaExpositor veNueva = new VistaExpositor(ee.getExpositorNuevo(), registro, stage, dashboard);
        if(veAntigua.estaExtendida())
        	veNueva.alternarVistaExtendida();
        
        mapaVistaExpositores.put(ee.getExpositorNuevo(), veNueva);
    	dashboard.getScrollBoxExpositores().getChildren().add(index, veNueva);
    }
    
    public void enEliminarExpositor(EventoExpositor ee) {
    	VistaExpositor ve = mapaVistaExpositores.remove(ee.getExpositor());
    	dashboard.getScrollBoxExpositores().getChildren().remove(ve);
    	dashboard.actualizarNumeroExpositores();
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
        
        mapaVistaPresentaciones = new HashMap<Presentacion, VistaPresentacion>();
        mapaVistaPersonas = new HashMap<Persona, VistaPersona>();
        mapaVistaExpositores = new HashMap<Expositor, VistaExpositor>();
        this.dashboard = new Dashboard(r);
    }

    /** @brief Método que se ejecuta luego de leer xml */
    public void initialize(URL url, ResourceBundle resources) {
        this.vistaPrincipal.getChildren().add(dashboard);
        VBox.setVgrow(dashboard, Priority.ALWAYS);

        configurarBarraLateral();
        agregarListeners();
        cargarRegistro();
    }

    /**
     * 
     */
    private void configurarBarraLateral() {
        agregarTooltips();
        addIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                crearPresentacion();
            }
        });
    }

private void agregarTooltips() {
        Tooltip.install(this.homeIcon, new Tooltip("Inicio"));
        Tooltip.install(this.addIcon, new Tooltip("Añadir Presentación"));
        Tooltip.install(this.detailIcon, new Tooltip("Vista detallada"));
    }

    private void cargarRegistro() {
        for (Presentacion p : registro.getPresentaciones()) {
            dashboard.fireEvent(new EventoPresentacion(EventoPresentacion.CREAR_PRESENTACION, p));
        }
        
        for (Persona p : registro.getAsistentes()) {
            dashboard.fireEvent(new EventoPersona(EventoPersona.CREAR_PERSONA, p));
        }
        
        for (Expositor e : registro.getExpositores()) {
            dashboard.fireEvent(new EventoExpositor(EventoExpositor.CREAR_EXPOSITOR, e));
        }
    }

    private void agregarListeners() {
        dashboard.addEventFilter(EventoPresentacion.CREAR_PRESENTACION, e-> {
            enCrearPresentacion(e);
        });
        
        dashboard.addEventFilter(EventoPresentacion.EDITAR_PRESENTACION, e-> {
            enEditarPresentacion(e);
        });
        
        dashboard.addEventFilter(EventoPresentacion.ELIMINAR_PRESENTACION, e-> {
            enEliminarPresentacion(e);
        });
        
        dashboard.addEventFilter(EventoPersona.CREAR_PERSONA, e-> {
            enCrearPersona(e);
        });
        
        dashboard.addEventFilter(EventoPersona.EDITAR_PERSONA, e-> {
            enEditarPersona(e);
        });
        
        dashboard.addEventFilter(EventoPersona.ELIMINAR_PERSONA, e-> {
            enEliminarPersona(e);
        });
        
        dashboard.addEventFilter(EventoExpositor.CREAR_EXPOSITOR, e-> {
            enCrearExpositor(e);
        });
        
        dashboard.addEventFilter(EventoExpositor.EDITAR_EXPOSITOR, e-> {
            enEditarExpositor(e);
        });
        
        dashboard.addEventFilter(EventoExpositor.ELIMINAR_EXPOSITOR, e-> {
            enEliminarExpositor(e);
        });
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

        LeerPresentacion lp = new LeerPresentacion(registro);
        PopUp popup = new PopUp(stage, lp);
        popup.setTitle("Crear presentación");
        
        retorno = (Presentacion)popup.showDialog();
        if (retorno != null) {
            registro.insertarPresentacion(retorno);
            dashboard.fireEvent(new EventoPresentacion(EventoPresentacion.CREAR_PRESENTACION, retorno));
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
        
        LeerExpositor le = new LeerExpositor(registro);
        PopUp popup = new PopUp(stage, le);
        popup.setTitle("Crear expositor");
        
        expositor = (Expositor) popup.showDialog();
        if(expositor != null) {
        	registro.insertarExpositor(expositor);
        	dashboard.fireEvent(new EventoExpositor(EventoExpositor.CREAR_EXPOSITOR, expositor));
        }
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

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }
}
