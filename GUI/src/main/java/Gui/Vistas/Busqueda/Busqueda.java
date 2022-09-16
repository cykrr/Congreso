package Gui.Vistas.Busqueda;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class Busqueda extends HBox implements Initializable {
    public Busqueda()
    {
        // TODO : Convertir importar en una clase abstracta que recibe como parametro
        // el tipo de ventana y la ruta al xml.
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/busqueda.fxml"));
        fxmlLoader.setController(this);
        Node n = null;

        try {
            n = fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.getChildren().add(n);
    }


    public void initialize(URL url, ResourceBundle rb)
    {
    
    }

    public String getValue() {
        return "";
    }
}
