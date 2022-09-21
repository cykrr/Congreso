package Gui.Vistas.Dashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Congreso.Registro;

// TODO : Sacar de paquete
import Gui.Vistas.Busqueda.Busqueda;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Dashboard extends VBox implements Initializable {

    @FXML Text contadorPresentaciones;
    @FXML Text contadorExpositores;
    @FXML Text contadorAsistentes;



    private     Registro    registro;

    @FXML
    private     Busqueda    cajaBusqueda;

    @FXML
    private     ScrollPane        scroll;

    @FXML
    private     VBox        scrollBox;

    @FXML 
    private     VBox        botonPresentaciones;
    @FXML
    private     VBox        botonExpositores;
    @FXML
    private     VBox        botonAsistentes;

    VBox seleccion;

    @FXML void click(MouseEvent me) {
        System.out.println("beep");
    }


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
        seleccion = botonPresentaciones;
        seleccion.getStyleClass().add("selected");
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);
        botonPresentaciones.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                if (seleccion != botonPresentaciones) {
                    seleccion.getStyleClass().remove("selected");
                    seleccion = botonPresentaciones;
                    seleccion.getStyleClass().add("selected");
                }
            }
        });
        botonExpositores.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                if (seleccion != botonExpositores) {
                    seleccion.getStyleClass().remove("selected");
                    seleccion = botonExpositores;
                    seleccion.getStyleClass().add("selected");
                }
            }
        });
        botonAsistentes.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                if (seleccion != botonAsistentes) {
                    seleccion.getStyleClass().remove("selected");
                    seleccion = botonAsistentes;
                    seleccion.getStyleClass().add("selected");
                }
            }
        });
    }
    
    public void buscar() {
        String b = cajaBusqueda.getValue();
        System.out.println("hola " + b);
    }

    public VBox getScrollBox() {
        return this.scrollBox;
    }
    
    public void actualizarNumeroPresentaciones() {
    	contadorPresentaciones.setText(this.registro.getCantidadPresentaciones().toString());
    }
}
