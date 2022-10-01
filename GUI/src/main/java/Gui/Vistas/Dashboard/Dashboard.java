package Gui.Vistas.Dashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Congreso.Registro;

// TODO : Sacar de paquete
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
    @FXML Text txtTitulo;


    private     Registro    registro;

    @FXML
    private     ScrollPane        scroll;

    @FXML
    private     VBox        boxPresentaciones;
    
    @FXML
    private     VBox        boxAsistentes;
    
    @FXML
    private     VBox        boxExpositores;

    @FXML 
    private     VBox        botonPresentaciones;
    @FXML
    private     VBox        botonExpositores;
    @FXML
    private     VBox        botonAsistentes;

    private VBox seleccionBoton;
    private VBox seleccionBox;

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
    public void initialize(URL url, ResourceBundle rb) {
    	contadorPresentaciones.setText(this.registro.getCantidadPresentaciones().toString());
    	contadorExpositores.setText(this.registro.getCantidadExpositores().toString());
    	contadorAsistentes.setText(this.registro.getCantidadAsistentes().toString());
    	actualizarTitulo();
    	
        seleccionBoton = botonPresentaciones;
        seleccionBoton.getStyleClass().add("selected");
        
        seleccionBox = boxPresentaciones;
        boxAsistentes.setVisible(false);
        boxAsistentes.setManaged(false);
        boxExpositores.setVisible(false);
        boxExpositores.setManaged(false);
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);
        
        botonPresentaciones.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
            	alternarVista(botonPresentaciones, boxPresentaciones);
            	actualizarTitulo("Presentaciones");
            }
        });
        
        botonAsistentes.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
            	alternarVista(botonAsistentes, boxAsistentes);
            	actualizarTitulo("Asistentes");
            }
        });
        
        botonExpositores.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
            	alternarVista(botonExpositores, boxExpositores);
            	actualizarTitulo("Expositores");
            }
        });
    }
    
    public void alternarVista(VBox boton, VBox scrollBox) {
    	if(seleccionBoton == boton)
    		return;
    	
    	seleccionBoton.getStyleClass().remove("selected");
    	seleccionBoton = boton;
    	seleccionBoton.getStyleClass().add("selected");
    	
        seleccionBox.setVisible(false);
        seleccionBox.setManaged(false);
        seleccionBox = scrollBox;
        seleccionBox.setVisible(true);
        seleccionBox.setManaged(true);
    }

    public VBox getScrollBoxPresentaciones() {
        return this.boxPresentaciones;
    }
    
    public VBox getScrollBoxAsistentes() {
        return this.boxAsistentes;
    }
    
    public VBox getScrollBoxExpositores() {
        return this.boxExpositores;
    }

    public void actualizarTitulo(){
    	txtTitulo.setText("Presentaciones");
    }
    
    public void actualizarTitulo(String title){
    	txtTitulo.setText(title);
    }
    
    public void actualizarNumeroPresentaciones() {
    	contadorPresentaciones.setText(this.registro.getCantidadPresentaciones().toString());
    }
    
    public void actualizarNumeroAsistentes() {
    	contadorAsistentes.setText(this.registro.getCantidadAsistentes().toString());
    }
    
    public void actualizarNumeroExpositores() {
    	contadorExpositores.setText(this.registro.getCantidadExpositores().toString());
    }
}