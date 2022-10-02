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
    
    /**	@brief Constructor de la vista
     * 
     * Carga el archivo fxml que corresponde para abrirlo como ventana.
     * @param registro Es el atributo de clase Registro que contiene todas las colecciones con la informacion a mostrar.
     * */
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
    
    /** Carga el contenido de la ventana principal para el Dashboard. */
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
    
    /**
     * Alterna la vista camnbiando el boton y el scrollBox
     * @param boton Se transformaria en el nuevo boton seleccionado en la pantalla.
     * @param scrollBox Se convierte en la nueva vista de lista en la ventana.
     * */
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
    
    /**
     * @return Vbox con las presentaciones
     * */
    public VBox getScrollBoxPresentaciones() {
        return this.boxPresentaciones;
    }
    
    /**
     * @return Vbox con los asistentes
     * */
    public VBox getScrollBoxAsistentes() {
        return this.boxAsistentes;
    }
    
    /**
     * @return Vbox con los expositores
     * */
    public VBox getScrollBoxExpositores() {
        return this.boxExpositores;
    }
    
    /** Actualiza el titulo de la listaview con un text predeterminado como "Presentaciones". */
    public void actualizarTitulo(){
    	txtTitulo.setText("Presentaciones");
    }
    
    /**
     * Actualiza el titulo de la listaview con un text usando el atributo recibido.
     * @param String que corresponde al texto que actuara como titulo de la listaview.
     * */
    public void actualizarTitulo(String title){
    	txtTitulo.setText(title);
    }
    
    /** Actualiza el número que cuenta las presentaciones guardadas.*/
    public void actualizarNumeroPresentaciones() {
    	contadorPresentaciones.setText(this.registro.getCantidadPresentaciones().toString());
    }
    
    /** Actualiza el número que cuenta los asistentes guardados.*/
    public void actualizarNumeroAsistentes() {
    	contadorAsistentes.setText(this.registro.getCantidadAsistentes().toString());
    }
    
    /** Actualiza el número que cuenta los asistentes guardados.*/
    public void actualizarNumeroExpositores() {
    	contadorExpositores.setText(this.registro.getCantidadExpositores().toString());
    }
}