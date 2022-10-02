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
	// Elementos XML
    @FXML GridPane paneVistaExtendida;
    @FXML ImageView imgButtonMostrar;
    
    /** @brief Constructor de la clase Vista.
     * 
     * Muestra una clase con su informacion y con iconos habilitados para actuar sobre ella.
     * Utiliza button para alternar vista extendida.
     * @param fileName Es el atributo String que contiene el path del FXML que se cargara.
     * */
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
    
    /** 
     * Alterna la vista volviendo visible invinsible al panel seleccionado si esta visible. 
     * Y si no esta Visible, vuelve visible el panel seleccionado
     *  */
    public void alternarVistaExtendida() {
    	if(paneVistaExtendida.isVisible()) {
            paneVistaExtendida.setManaged(false);
            paneVistaExtendida.setVisible(false);
    	} else {
            paneVistaExtendida.setManaged(true);
            paneVistaExtendida.setVisible(true);
    	}
    }
    
    /** 
     * @return Un boolean que sera true si el panel de la vista extendida esta visible, y false en caso contrario.
     *  */
    public boolean estaExtendida() {
    	return paneVistaExtendida.isVisible();
    }
}
