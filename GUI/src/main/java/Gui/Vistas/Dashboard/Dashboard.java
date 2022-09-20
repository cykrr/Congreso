package Gui.Vistas.Dashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import Congreso.Presentacion;
import Congreso.Registro;
import java.util.List;

// TODO : Sacar de paquete
import Gui.Vistas.Busqueda.Busqueda;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Dashboard extends VBox implements Initializable {
    @FXML Busqueda cajaBusqueda;
    @FXML Text contadorPresentaciones;
    @FXML Text contadorExpositores;
    @FXML Text contadorAsistentes;
    
    Registro registro;
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
    	contadorPresentaciones.setText(this.registro.getCantidadPresentaciones().toString());
    	contadorExpositores.setText(this.registro.getCantidadExpositores().toString());
    	contadorAsistentes.setText(this.registro.getCantidadAsistentes().toString());
    }
    
    public void buscar() {
        String b = cajaBusqueda.getValue();
        System.out.println("hola " + b);
    }
}
