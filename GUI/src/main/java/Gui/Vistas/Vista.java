package Gui.Vistas;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Vista extends VBox {  
    @FXML GridPane paneVistaExtendida;
    @FXML ImageView imgButtonMostrar;

    public Vista(String fileName)  {
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
}
