package Gui.Vistas.Busqueda;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class Busqueda extends HBox implements Initializable {
    @FXML TextField field;

    private ObjectProperty<EventHandler<KeyEvent>> propertyOnAction; 

    public Busqueda()
    {
        super();
        propertyOnAction = new SimpleObjectProperty<EventHandler<KeyEvent>>();

        // TODO : Convertir importar en una clase abstracta que recibe como parametro
        // el tipo de ventana y la ruta al xml.
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/busqueda.fxml"));
        fxmlLoader.setController(this);
        Node n = null;

        try {
            n = fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.getChildren().add(n);

        this.setOnKeyPressed( new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER))
                    onActionProperty().get().handle(ke);
            }

        });
    }


    public void initialize(URL url, ResourceBundle rb)
    {
    
    }

    public String getValue() {
        return field.getText();
    }

    public final void setOnAction(EventHandler<KeyEvent> handler) {
        propertyOnAction.set(handler);
    }

    public final EventHandler<KeyEvent> getOnAction() {
            return propertyOnAction.get();

        }    

    public final ObjectProperty<EventHandler<KeyEvent>> onActionProperty() {
            return propertyOnAction;
        }
}
