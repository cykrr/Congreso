package Gui.Vistas;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopUp extends GridPane {
	
	/** Interfaz para las subclases de PopUp */
    public interface PopAble {
    	
    	/** Guarda el objeto generado por la vista hija */
        boolean guardar();
        
        /** Retorna el objeto generado por la vista hija */
        Object getValue();
    }
    
    /* Encabezado */
    @FXML private Text txtHeader;

    /* Contenedor del Pop-up */
    private VBox root;
    
    /* Instancia de ventana Pop-up */
    private Scene dialog;
    
    /* Ventana padre del Pop-up */
    private Stage stage;
    
    /** Constructor principal, genera un Pop-up a partir
     * del nombre de la vista hija pasada como parámetro
     * @param stage
     * @param resourceName
     */
    public PopUp(Stage stage, String resourceName) {  
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resourceName));
        fxmlLoader.setController(this);
        Node n = null;

        try {
            n = fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.stage = new Stage();

        this.stage.initModality(Modality.APPLICATION_MODAL);
        this.stage.initOwner(stage);
        this.stage.setResizable(false);

        Button b = new Button("Guardar");

        root = new VBox(n, b);
        root.getChildren().addAll();

        dialog = new Scene(root);

        b.setOnAction(e-> {
        	boolean success = ((PopAble)this).guardar();
                if(success)
                	this.stage.close();
        });

        VBox.setMargin(b, new Insets(0, 0, 15, 15));
    }

    /** Muestra el Pop-up por pantalla.
     * Al cerrarse, retorna el objeto
     * generado por la vista hija.
     */
    public Object showDialog()  {
        stage.setScene(dialog);
        stage.sizeToScene();
        stage.showAndWait();
        
        return ((PopAble)this).getValue();
    }
    
    /** Establece el título de la ventana */
    public void setTitle(String title) {
    	stage.setTitle(title);
    }
    
    /** Establece el encabezado de la ventana */
    public void setHeader(String header) {
    	txtHeader.setText(header);
    }
    
}
