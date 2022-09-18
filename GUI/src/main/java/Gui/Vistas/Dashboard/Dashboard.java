package Gui.Vistas.Dashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Congreso.Registro;

// TODO : Sacar de paquete
import Gui.Vistas.Busqueda.Busqueda;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class Dashboard extends VBox implements Initializable {
    private     Registro    registro;

    @FXML
    private     Busqueda    cajaBusqueda;

    @FXML
    private     VBox        scrollBox;

    public Dashboard(Registro r) {
        super();
        this.registro = r; 
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/dashboard.fxml"));
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
    
    public void buscar() {
        String b = cajaBusqueda.getValue();
        System.out.println("hola " + b);
    }

    public VBox getScrollBox() {
        return this.scrollBox;
    }
}
