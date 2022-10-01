package Gui;

import java.io.IOException;

import Congreso.Registro;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.fxml.FXMLLoader;


public class Gui extends Application {
    /*! Ventana principal. Almacena la Escena principal (Scene) la cual se
     * genera en base al nodo raíz root
     */
    private     Stage       stage;      

    /*! Nodo principal. Almacena todos los componentes de la vista principal
     * @see GUI/src/main/resources/Vistas/root.fxml
     */
    private     VBox        root;       

    /*! Carga información del Registro
     *  @see Congreso.Registro*/
    private     Controlador controlador;

    /*!  Base de datos de la App */
    private     Registro    registro;   
    
    /**  Punto de inicio del GUI. Carga los ajustes del usuario usando la
     * clase Ajustes. Crea un controlador que se encargará de configurar la
     * ventana con la información correspondiente. Guarda el Stage en el
     * controlador y carga la raíz.
     *
     * @see Gui.Ajustes
     *
     */
    @Override
    public void start(Stage s) {
        System.out.println("Congreso GUI");

        this.registro = new Registro();

        System.out.println("Inicializando controlador");
        this.controlador = new Controlador(s, registro);

        this.stage = s;
        inflar();
        root.getStylesheets().add("/styles.css");
        s.sizeToScene();
        
        stage.setOnCloseRequest(event -> {
        	event.consume();
        	cerrar(stage);
        });
    }
    
    /*
     *  Carga una ventana de alerta cuando decide cerrar el programa, esta ventana
     *  deja al usuario decidir si guardar los cambios hechos que no se han guardado,
     *  si presiona no, el programa cierra sin guardar
     * */
    public void cerrar(Stage stage) {
    	boolean save = Alerta.mostrarAlertaAdvertencia("¿Deseas guardar los cambios hechos?", "Antes de cerrar del programa");
    	if(save) {
    		this.controlador.exportar();
    		System.out.println("\nGuardado Exitosamente");
    	}
    	stage.close();
    }

    /** 
     *  Carga la Vista raíz con los componentes
     * encontrados en el archivo GUI/src/main/resources/vistas/root.xml
     */
    private void inflar() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Gui.class.getResource("/vistas/root.fxml"));
        loader.setController(controlador);
        try {
            root = (VBox)loader.load();
            Scene s = new Scene(root);
            stage.setScene(s);
            stage.setWidth(480);
            stage.setHeight(360);
            stage.show();
        } catch(IOException e) {
            e.printStackTrace();
            System.err.println("Error: No se encontró el recurso");
            System.exit(1);
        }

    }
}
