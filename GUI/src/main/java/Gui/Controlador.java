package Gui;

import java.io.IOException;
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
import Gui.Vistas.Busquedas.BusquedaPorEdad;
import Gui.Vistas.Busquedas.BusquedaPorFecha;
import Gui.Vistas.Dashboard.Dashboard;
import Gui.Vistas.LeerAsistente.LeerAsistente;
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
	
    private Registro registro;
    private Stage stage;
    private Ajustes ajustes;
    private Dashboard dashboard;
    private BusquedaPorEdad busquedaEdad;
    private BusquedaPorFecha busquedaFecha;
    
    @FXML private HBox homeIcon;
    @FXML private HBox addIcon;
    @FXML private HBox buscarEdadIcon;
    @FXML private HBox buscarFechaIcon;
    @FXML private VBox vistaPrincipal;
    
    /** Se utilizan mapas para acceso rápido a las vistas
     * según el dato que contienen.
     */
    private Map<Presentacion, VistaPrincipalPresentacion> mapaVistaPresentaciones;
    private Map<Persona, VistaPrincipalPersona> mapaVistaPersonas;
    private Map<Expositor, VistaPrincipalExpositor> mapaVistaExpositores;

    public void mostrarVentanaDashboard() {
        this.vistaPrincipal.getChildren().clear();
        this.vistaPrincipal.getChildren().add(dashboard);
    }

    /** Crea una presentación a partir del botón ubicado
     * en la barra lateral izquierda.
     */
    public void addAction() {
        crearPresentacion();
    }

    /** Cambia la ventana a BusquedaPorEdad. */
    public void mostrarVentanaBusquedaEdad() {
        this.vistaPrincipal.getChildren().clear();
        if (busquedaEdad == null)
            busquedaEdad = new BusquedaPorEdad(registro);
        this.vistaPrincipal.getChildren().add(busquedaEdad);
    }
    
    /** Cambia la ventana a BusquedaPorFecha. */
    public void mostrarVentanaBusquedaFecha() {
        this.vistaPrincipal.getChildren().clear();
        if (busquedaFecha == null)
            busquedaFecha = new BusquedaPorFecha(registro);
        this.vistaPrincipal.getChildren().add(busquedaFecha);
    }
    
    /** Actualiza las vistas al crear una presentación. */
    public void enCrear(EventoPresentacion ep) {
        VistaPrincipalPresentacion vp = new VistaPrincipalPresentacion(ep.getPresentacion(), registro, stage, dashboard);
        mapaVistaPresentaciones.put(ep.getPresentacion(), vp);
        dashboard.getScrollBoxPresentaciones().getChildren().add(vp);
        dashboard.actualizarNumeroPresentaciones();
    }
    
    /** Actualiza las vistas al crear un asistente. */
    public void enCrear(EventoPersona ep) {
        VistaPrincipalPersona vp = new VistaPrincipalPersona(ep.getPersona(), registro, stage, dashboard);
        mapaVistaPersonas.put(ep.getPersona(), vp);
        dashboard.getScrollBoxAsistentes().getChildren().add(vp);
        dashboard.actualizarNumeroAsistentes();
    }
    
    /** Actualiza las vistas al crear un expositor. */
    public void enCrear(EventoExpositor ee) {
        VistaPrincipalExpositor ve = new VistaPrincipalExpositor(ee.getExpositor(), registro, stage, dashboard);
        mapaVistaExpositores.put(ee.getExpositor(), ve);
        dashboard.getScrollBoxExpositores().getChildren().add(ve);
        dashboard.actualizarNumeroExpositores();
    }
    
    /** Actualiza las vistas al editar una presentación. */
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
    
    /** Actualiza las vistas al editar un asistente. */
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
    
    /** Actualiza las vistas al editar un expositor. */
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
    
    /** Actualiza las vistas al eliminar una presentación. */
    public void enEliminar(EventoPresentacion ep) {
    	VistaPrincipalPresentacion vp = mapaVistaPresentaciones.remove(ep.getPresentacion());
    	dashboard.getScrollBoxPresentaciones().getChildren().remove(vp);
    	dashboard.actualizarNumeroPresentaciones();
    }
    
    /** Actualiza las vistas al eliminar un asistente. */
    public void enEliminar(EventoPersona ep) {
    	VistaPrincipalPersona vp = mapaVistaPersonas.remove(ep.getPersona());
    	dashboard.getScrollBoxAsistentes().getChildren().remove(vp);
    	dashboard.actualizarNumeroAsistentes();
    	actualizarAsistentesEnPresentaciones();
    }
    
    /** Actualiza las vistas al eliminar un expositor. */
    public void enEliminar(EventoExpositor ee) {
    	VistaPrincipalExpositor ve = mapaVistaExpositores.remove(ee.getExpositor());
    	dashboard.getScrollBoxExpositores().getChildren().remove(ve);
    	dashboard.actualizarNumeroExpositores();
    }
    
    /** Actualiza las vistas al modificar un asistente
     * de una presentación.
     */
    public void enModificarAsistentes(EventoPresentacion ep) {
        VistaPrincipalPresentacion vp = mapaVistaPresentaciones.get(ep.getPresentacion());
        vp.actualizarAsistentes();
    }
    
    /** Actualiza las vistas de presentaciones al modificar
     * datos de un asistente.
     */
    public void actualizarAsistentesEnPresentaciones() {
    	for(VistaPrincipalPresentacion vp : mapaVistaPresentaciones.values())
    		vp.actualizarAsistentes();
    }
    
    /** Actualiza las vistas de presentaciones al modificar
     * datos de un expositor.
     */
    public void actualizarExpositorEnPresentaciones() {
    	for(VistaPrincipalPresentacion vp : mapaVistaPresentaciones.values())
    		vp.actualizarExpositor();
    }

    /** Constructor principal, inicializa colecciones y atributos
     * y carga datos con el método importar de Registro.
     * @param stage
     * @param registro
     */
    public Controlador(Stage stage, Registro registro) {
        System.out.println("Cargando ajustes");
        this.ajustes = new Ajustes(); 

        System.out.println("Ajustes guardados: "+ ajustes.carpeta);

        System.out.println("Inicializando registro");
        this.registro = registro;

        System.out.println("Cargando datos: ");
        registro.importar(ajustes.carpeta + "/Presentaciones.csv",
        				  ajustes.carpeta + "/Expositores.csv",
        				  ajustes.carpeta + "/Asistentes.csv");
        
        mapaVistaPresentaciones = new HashMap<Presentacion, VistaPrincipalPresentacion>();
        mapaVistaPersonas = new HashMap<Persona, VistaPrincipalPersona>();
        mapaVistaExpositores = new HashMap<Expositor, VistaPrincipalExpositor>();
        this.dashboard = new Dashboard(registro);
        this.stage = stage;
    }

    /** Carga el contenido de la ventana principal
     * una vez inicializado el Dashboard.
     */
    public void initialize(URL url, ResourceBundle resources) {
        this.vistaPrincipal.getChildren().add(dashboard);
        VBox.setVgrow(dashboard, Priority.ALWAYS);

        configurarBarraLateral();
        agregarListeners();
        agregarShortcuts();
        cargarRegistro();
    }

    /** Configura la barra lateral de la ventana
     * para actuar conforme a los click en los 
     * iconos que presenta.
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

    /** Carga los datos del registro para mostrarlos en el Dashboard. */
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

    /** Establece el método a ejecutar luego de gatillar un evento. */
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

    /** Crea una presentación por medio de un Pop-up
     * y lo añade al registro.
     */
    public void crearPresentacion() {
        Presentacion retorno = null;

        LeerPresentacion lp = new LeerPresentacion(registro, stage);
        lp.setTitle("Crear presentación");
        
        retorno = (Presentacion) lp.showDialog();
        if (retorno != null) {
            registro.insertarPresentacion(retorno);
            dashboard.fireEvent(new EventoPresentacion(EventoPresentacion.CREAR_PRESENTACION, retorno));
        }
    }
    
    /** Crea un expositor por medio de un Pop-up
     * y lo añade al registro.
     */
    public void crearExpositor() {
        Expositor expositor = null;
        
        LeerExpositor le = new LeerExpositor(registro, stage);
        le.setTitle("Crear expositor");
        
        expositor = (Expositor) le.showDialog();
        if(expositor != null) {
        	registro.insertarExpositor(expositor);
        	dashboard.fireEvent(new EventoExpositor(EventoExpositor.CREAR_EXPOSITOR, expositor));
        }
    }
    
    /** Crea un asistente por medio de un Pop-up
     * y lo añade al registro.
     */
    public void crearAsistente() {
    	Persona asistente = null;
    	
    	LeerAsistente la = new LeerAsistente(registro, stage);
    	la.setTitle("Crear asistente");
    	
    	asistente = (Persona) la.showDialog();
    	if(asistente != null) {
    		registro.insertarAsistente(asistente);
    		dashboard.fireEvent(new EventoPersona(EventoPersona.CREAR_PERSONA, asistente));
        }
    }
    
    /** Exporta datos a los archivos csv correspondientes */
    public void exportar() {
    	registro.exportar(ajustes.carpeta + "/Presentaciones.csv",
    					  ajustes.carpeta + "/Expositores.csv",
    					  ajustes.carpeta + "/Asistentes.csv");
    }
    
    
    /** Cierra el programa sin guardar los datos */
    public void cerrarAplicacion() {
    	System.out.println("\nCerrado sin guardar");
    	System.exit(0);
    }
    
    /** Genera un archivo txt con la información guardada en las colecciones */
    public void generarReporte() {
    	try {
    		registro.generarReporte(ajustes.carpeta + "/reporte.txt");
    		Alerta.mostrarAlertaInformacion("Reporte generado con exíto");
    	} catch(IOException e) {
    		Alerta.mostrarAlertaError("Ocurrió un error al generar el reporte", null);
    		e.printStackTrace();
    	}
    }
    
    /**
     * Inicia y ejecuta evento de teclado.
     * Usa la lectura de teclado para reaccionar a ciertas 
     * combinaciones de teclas, sin nececidad de hacer
     * click para iniciar ciertas acciones
     *   [Ctrl + R]: Generar Reporte
     *   [Ctrl + S]: Guardar Cambios
     *   [Ctrl + Q]: Salir Sin Guardar
     *   [Ctrl + P]: Crear Presentacion
     *   [Ctrl + E]: Crear Expositor
     *   [Ctrl + A]: Crear Asistente
     * */
    public void agregarShortcuts() {
        stage.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
        	
        	KeyCombination keyGenerarReporte = new KeyCodeCombination(
            		KeyCode.R, KeyCombination.CONTROL_DOWN);
        	
        	KeyCombination keyGuardarCambios = new KeyCodeCombination(
            		KeyCode.S, KeyCombination.CONTROL_DOWN);
        	KeyCombination keyCerrarAplicacion = new KeyCodeCombination(
            		KeyCode.Q, KeyCombination.CONTROL_DOWN);
            
            KeyCombination keyCrearPresentacion = new KeyCodeCombination(
            		KeyCode.P, KeyCombination.CONTROL_DOWN);
            KeyCombination keyCrearExpositor = new KeyCodeCombination(
            		KeyCode.E, KeyCombination.CONTROL_DOWN);
            KeyCombination keyCrearAsistente = new KeyCodeCombination(
            		KeyCode.A, KeyCombination.CONTROL_DOWN);
            
            
            public void handle(KeyEvent ke) {
                if (keyGenerarReporte.match(ke)) {
                    generarReporte();
                    ke.consume();
                }
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
                if (keyGuardarCambios.match(ke)) {
                    exportar();
                    ke.consume();
                }
            }
        });  	
    }
    
}
