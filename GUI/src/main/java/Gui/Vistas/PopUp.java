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
	
    public interface PopAble {
        boolean guardar();
        Object getValue();
    }
    
    @FXML private Text txtHeader;

    private VBox root;
    private Scene dialog;
    private Stage stage;

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

    public Object showDialog()  {
        stage.setScene(dialog);
        stage.sizeToScene();
        stage.showAndWait();
        
        return ((PopAble)this).getValue();
    }
    
    public void setTitle(String title) {
    	stage.setTitle(title);
    }
    
    public void setHeader(String header) {
    	txtHeader.setText(header);
    }
    
}
