package Gui.Vistas;

import Congreso.Registro;
import Gui.Vistas.Dashboard.Dashboard;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VistaPrincipal extends Vista {  
    @FXML ImageView imgButtonEditar, imgButtonEliminar;
    
    private Dashboard dashboard;
    private Registro registro;
    private Stage stage;
    private VBox box;
    
    /** Inicia los metodos con sus nombres respectivos que usaran todas las sub-clases de esta clase */
    public interface Manipulable {
        public void editar();
        public void eliminar();
    }
    
    /** @brief Constructor de la clase VistaPrincipal.
     * 
     * Muestra una clase con su informacion y con iconos habilitados para actuar sobre ella.
     * Utiliza button para alternar vista extendida.
     * @param fileName Es el atributo String que contiene el path del FXML que se cargara.
     * */
    public VistaPrincipal(Registro registro, Stage stage, Dashboard dashboard, String fileName) {
        super(fileName);
        this.dashboard = dashboard;
        this.registro = registro;
        this.stage = stage;
        this.box = this;
        
        imgButtonEditar.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ((Manipulable)box).editar();
                event.consume();
            }
        });
        
        imgButtonEliminar.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ((Manipulable)box).eliminar();
                event.consume();
            }
        });
        
    }
    
    /**
     * @return Retorna el registro de clase Registro.
     * */
    public Registro getRegistro() {
    	return registro;
    }
    
    /**
     * @return Retorna el dashboard de clase Dashboard.
     * */
    public Dashboard getDashboard() {
    	return dashboard;
    }
    
    /**
     * @return Retorna el stage de clase Stage.
     * */
    public Stage getStage() {
    	return stage;
    }
}
