package Gui.Vistas;

import java.io.IOException;

import Congreso.Registro;
import Gui.Vistas.Dashboard.Dashboard;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Vista extends VBox {  
    @FXML ImageView imgButtonMostrar, imgButtonEditar, imgButtonEliminar;
    @FXML GridPane paneVistaExtendida;
    
    private Dashboard dashboard;
    private Registro registro;
    private Stage stage;
    private VBox box;
    
    public interface Manipulable {
        public void editar();
        public void eliminar();
    }
    
    public Vista(Registro registro, Stage stage, Dashboard dashboard, String fileName) {
        super();
        this.dashboard = dashboard;
        this.registro = registro;
        this.stage = stage;
        this.box = this;
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fileName));
        fxmlLoader.setController(this);
        Node n = null;

        try {
            n = fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.getChildren().add(n);

        imgButtonMostrar.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                alternarVistaExtendida();
                event.consume();
            }
        });
        
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
        
        paneVistaExtendida.setManaged(false);
        paneVistaExtendida.setVisible(false);
    }
    
    public void alternarVistaExtendida() {
    	if(paneVistaExtendida.isVisible()) {
            paneVistaExtendida.setManaged(false);
            paneVistaExtendida.setVisible(false);
    	} else {
            paneVistaExtendida.setManaged(true);
            paneVistaExtendida.setVisible(true);
    	}
    }
    
    public boolean estaExtendida() {
    	return paneVistaExtendida.isVisible();
    }
    
    public Registro getRegistro() {
    	return registro;
    }
    
    public Dashboard getDashboard() {
    	return dashboard;
    }
    
    public Stage getStage() {
    	return stage;
    }
}