package Gui;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import Gui.Vistas.PopUp;
import Gui.Vistas.Dashboard.Dashboard;
import Gui.Vistas.Detalle.BusquedaPorFecha;
import Gui.Vistas.LeerAsistente.LeerAsistente;
import Gui.Vistas.Detalle.BusquedaPorEdad;
import Gui.Vistas.LeerExpositor.LeerExpositor;
import Gui.Vistas.LeerPresentacion.LeerPresentacion;
import Gui.Vistas.VistaExpositor.VistaPrincipalExpositor;
import Gui.Vistas.VistaPersona.VistaPrincipalPersona;
import Gui.Vistas.VistaPresentacion.VistaPrincipalPresentacion;
import Congreso.Expositor;
import Congreso.Persona;
import Congreso.Presentacion;
import Congreso.Registro;


/** Inicializa la clase raíz de la ventana */
public class Controlador implements Initializable {
	
    private     Registro    registro; // Referencia a la base de datos del programa
    private     Stage       stage;    // Ventana principal
    private     Ajustes     ajustes;  // Ajustes del programa
    private     Dashboard   dashboard;
    private     BusquedaPorEdad   busquedaEdad;
    private     BusquedaPorFecha   busquedaFecha;
    
    private Map<Presentacion, VistaPrincipalPresentacion> mapaVistaPresentaciones;
    private Map<Persona, VistaPrincipalPersona> mapaVistaPersonas;
    private Map<Expositor, VistaPrincipalExpositor> mapaVistaExpositores;
    
    @FXML private HBox homeIcon;
    @FXML private HBox addIcon;
    @FXML private HBox buscarEdadIcon;
    @FXML private HBox buscarFechaIcon;

    @FXML private     VBox        vistaPrincipal;

    public void mostrarVentanaDashboard() {
        this.vistaPrincipal.getChildren().clear();
        this.vistaPrincipal.getChildren().add(dashboard);
    }

    public void addAction() {
        crearPresentacion();
    }

    public void mostrarVentanaBusquedaEdad() {
        this.vistaPrincipal.getChildren().clear();
        if (busquedaEdad == null)
            busquedaEdad = new BusquedaPorEdad(registro);
        this.vistaPrincipal.getChildren().add(busquedaEdad);
    }
    
    public void mostrarVentanaBusquedaFecha() {
        this.vistaPrincipal.getChildren().clear();
        if (busquedaFecha == null)
            busquedaFecha = new BusquedaPorFecha(registro);
        this.vistaPrincipal.getChildren().add(busquedaFecha);
    }
    
    /**
     * Sobrecarga Metodos:
     * enCrear()
     * enEditar()
     * enEliminar()
     * */
    public void enCrear(EventoPresentacion ep) {
        VistaPrincipalPresentacion vp = new VistaPrincipalPresentacion(ep.getPresentacion(), registro, stage, dashboard);
        mapaVistaPresentaciones.put(ep.getPresentacion(), vp);
        dashboard.getScrollBoxPresentaciones().getChildren().add(vp);
        dashboard.actualizarNumeroPresentaciones();
    }
    
    public void enCrear(EventoPersona ep) {
        VistaPrincipalPersona vp = new VistaPrincipalPersona(ep.getPersona(), registro, stage, dashboard);
        mapaVistaPersonas.put(ep.getPersona(), vp);
        dashboard.getScrollBoxAsistentes().getChildren().add(vp);
        dashboard.actualizarNumeroAsistentes();
    }
    
    public void enCrear(EventoExpositor ee) {
        VistaPrincipalExpositor ve = new VistaPrincipalExpositor(ee.getExpositor(), registro, stage, dashboard);
        mapaVistaExpositores.put(ee.getExpositor(), ve);
        dashboard.getScrollBoxExpositores().getChildren().add(ve);
        dashboard.actualizarNumeroExpositores();
    }
    
    public void enEditar(EventoPresentacion ep) {	
    	VistaPrincipalPresentacion vpAntigua = mapaVistaPresentaciones.remove(ep.getPresentacionAntigua());
    	int index = dashboard.getScrollBoxPresentaciones().getChildren().indexOf(vpAntigua);
    	dashboard.getScrollBoxPresentaciones().getChildren().remove(index);
    	
        VistaPrincipalPresentacion vpNueva = new VistaPrincipalPresentacion(ep.getPresentacionNueva(), registro, stage, dashboard);
        if(vpAntigua.estaExtendida()) {
        	vpNueva.alternarVistaExtendida();
        }
        
        mapaVistaPresentaciones.put(ep.getPresentacionNueva(), vpNueva);
    	dashboard.getScrollBoxPresentaciones().getChildren().add(index, vpNueva);
    }
    
    public void enEditar(EventoPersona ep) {	
    	VistaPrincipalPersona vpAntigua = mapaVistaPersonas.remove(ep.getPersonaAntigua());
    	int index = dashboard.getScrollBoxAsistentes().getChildren().indexOf(vpAntigua);
    	dashboard.getScrollBoxAsistentes().getChildren().remove(index);
    	
        VistaPrincipalPersona vpNueva = new VistaPrincipalPersona(ep.getPersonaNueva(), registro, stage, dashboard);
        if(vpAntigua.estaExtendida())
        	vpNueva.alternarVistaExtendida();
        
        mapaVistaPersonas.put(ep.getPersonaNueva(), vpNueva);
    	dashboard.getScrollBoxAsistentes().getChildren().add(index, vpNueva);
    	dashboard.actualizarNumeroAsistentes();
    	actualizarAsistentesEnPresentaciones();
    }
    
    public void enEditar(EventoExpositor ee) {	
    	VistaPrincipalExpositor veAntigua = mapaVistaExpositores.remove(ee.getExpositorAntiguo());
    	int index = dashboard.getScrollBoxExpositores().getChildren().indexOf(veAntigua);
    	dashboard.getScrollBoxExpositores().getChildren().remove(index);
    	
        VistaPrincipalExpositor veNueva = new VistaPrincipalExpositor(ee.getExpositorNuevo(), registro, stage, dashboard);
        if(veAntigua.estaExtendida())
        	veNueva.alternarVistaExtendida();
        
        mapaVistaExpositores.put(ee.getExpositorNuevo(), veNueva);
    	dashboard.getScrollBoxExpositores().getChildren().add(index, veNueva);
    	actualizarExpositorEnPresentaciones();
    }
    
    public void enEliminar(EventoPresentacion ep) {
    	VistaPrincipalPresentacion vp = mapaVistaPresentaciones.remove(ep.getPresentacion());
    	dashboard.getScrollBoxPresentaciones().getChildren().remove(vp);
    	dashboard.actualizarNumeroPresentaciones();
    }
    
    public void enEliminar(EventoPersona ep) {
    	VistaPrincipalPersona vp = mapaVistaPersonas.remove(ep.getPersona());
    	dashboard.getScrollBoxAsistentes().getChildren().remove(vp);
    	dashboard.actualizarNumeroAsistentes();
    	actualizarAsistentesEnPresentaciones();
    }
    
    public void enEliminar(EventoExpositor ee) {
    	VistaPrincipalExpositor ve = mapaVistaExpositores.remove(ee.getExpositor());
    	dashboard.getScrollBoxExpositores().getChildren().remove(ve);
    	dashboard.actualizarNumeroExpositores();
    }
    /**
     * FIN Sobrecarga Metodos:
     * enCrear()
     * enEditar()
     * enEliminar()
     * */
    
    public void enModificarAsistentes(EventoPresentacion ep) {
        VistaPrincipalPresentacion vp = mapaVistaPresentaciones.get(ep.getPresentacion());
        vp.actualizarAsistentes();
    }
    
    public void actualizarAsistentesEnPresentaciones() {
    	for(VistaPrincipalPresentacion vp : mapaVistaPresentaciones.values())
    		vp.actualizarAsistentes();
    }
    
    public void actualizarExpositorEnPresentaciones() {
    	for(VistaPrincipalPresentacion vp : mapaVistaPresentaciones.values())
    		vp.actualizarExpositor();
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
        
        mapaVistaPresentaciones = new HashMap<Presentacion, VistaPrincipalPresentacion>();
        mapaVistaPersonas = new HashMap<Persona, VistaPrincipalPersona>();
        mapaVistaExpositores = new HashMap<Expositor, VistaPrincipalExpositor>();
        this.dashboard = new Dashboard(r);
        this.stage = s;
    }

    /** @brief Método que se ejecuta luego de leer xml */
    public void initialize(URL url, ResourceBundle resources) {
        this.vistaPrincipal.getChildren().add(dashboard);
        VBox.setVgrow(dashboard, Priority.ALWAYS);

        configurarBarraLateral();
        agregarListeners();
        agregarShortcuts();
        cargarRegistro();
    }

    /**
     * 
     */
    private void configurarBarraLateral() {
        agregarTooltips();
        buscarEdadIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                mostrarVentanaBusquedaEdad();
            }
        });
        homeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                mostrarVentanaDashboard();
            }
        });
        addIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                crearPresentacion();
            }
        });
        buscarFechaIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                mostrarVentanaBusquedaFecha();
            }
        });
    }

    private void agregarTooltips() {
        Tooltip.install(this.homeIcon, new Tooltip("Inicio"));
        Tooltip.install(this.addIcon, new Tooltip("Añadir Presentación"));
        Tooltip.install(this.buscarEdadIcon, new Tooltip("Busqueda asistentes por edad"));
        Tooltip.install(this.buscarFechaIcon, new Tooltip("Busqueda presentaciones por fecha"));
    }

    private void cargarRegistro() {
    	Iterator<Presentacion> itPresentaciones = registro.getPresentaciones();
    	Iterator<Expositor> itExpositores = registro.getExpositores();
    	Iterator<Persona> itAsistentes = registro.getAsistentes();
    	
    	while(itPresentaciones.hasNext()) {
            dashboard.fireEvent(new EventoPresentacion(EventoPresentacion.CREAR_PRESENTACION, itPresentaciones.next()));
    	}
    	
        while(itExpositores.hasNext()) {
            dashboard.fireEvent(new EventoExpositor(EventoExpositor.CREAR_EXPOSITOR, itExpositores.next()));
        }
        
        while(itAsistentes.hasNext()) {
            dashboard.fireEvent(new EventoPersona(EventoPersona.CREAR_PERSONA, itAsistentes.next()));
        }
    }

    private void agregarListeners() {
        dashboard.addEventFilter(EventoPresentacion.CREAR_PRESENTACION, e-> {
            enCrear(e);
        });
        
        dashboard.addEventFilter(EventoPresentacion.EDITAR_PRESENTACION, e-> {
            enEditar(e);
        });
        
        dashboard.addEventFilter(EventoPresentacion.ELIMINAR_PRESENTACION, e-> {
            enEliminar(e);
        });
        
        dashboard.addEventFilter(EventoPresentacion.MODIFICAR_ASISTENTES, e-> {
        	enModificarAsistentes(e);
        });
        
        dashboard.addEventFilter(EventoPersona.CREAR_PERSONA, e-> {
            enCrear(e);
        });
        
        dashboard.addEventFilter(EventoPersona.EDITAR_PERSONA, e-> {
            enEditar(e);
        });
        
        dashboard.addEventFilter(EventoPersona.ELIMINAR_PERSONA, e-> {
            enEliminar(e);
        });
        
        dashboard.addEventFilter(EventoExpositor.CREAR_EXPOSITOR, e-> {
            enCrear(e);
        });
        
        dashboard.addEventFilter(EventoExpositor.EDITAR_EXPOSITOR, e-> {
            enEditar(e);
        });
        
        dashboard.addEventFilter(EventoExpositor.ELIMINAR_EXPOSITOR, e-> {
            enEliminar(e);
        });
    }

    /** @brief genera un Popup para crear presentación.
     *<p>
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
    
    public void exportar(String carpeta) {
        registro.exportar(carpeta + "/Presentaciones.csv",
				  		  carpeta + "/Expositores.csv",
				  		  carpeta + "/Asistentes.csv");
    }
    
    public void exportar() {
        exportar(ajustes.carpeta);
    }
    
    public void cerrarAplicacion() {
    	stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }
    
    public void agregarShortcuts() {
        stage.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
        	
            KeyCombination keyCrearPresentacion = new KeyCodeCombination(
            		KeyCode.P, KeyCombination.CONTROL_DOWN);
            KeyCombination keyCrearExpositor = new KeyCodeCombination(
            		KeyCode.A, KeyCombination.CONTROL_DOWN);
            KeyCombination keyCrearAsistente = new KeyCodeCombination(
            		KeyCode.E, KeyCombination.CONTROL_DOWN);
            KeyCombination keyCerrarAplicacion = new KeyCodeCombination(
            		KeyCode.Q, KeyCombination.CONTROL_DOWN);
            
            public void handle(KeyEvent ke) {
                if (keyCrearPresentacion.match(ke)) {
                    crearPresentacion();
                    ke.consume();
                }
                if (keyCrearExpositor.match(ke)) {
                    crearExpositor();
                    ke.consume();
                }
                if (keyCrearAsistente.match(ke)) {
                    crearAsistente();
                    ke.consume();
                }
                if (keyCerrarAplicacion.match(ke)) {
                    cerrarAplicacion();
                    ke.consume();
                }
            }
        });  	
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }
}
