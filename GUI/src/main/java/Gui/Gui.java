package Gui;

import java.io.IOException;

import Congreso.Registro;
import Gui.Vistas.Dashboard.Dashboard;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.fxml.FXMLLoader;


public class Gui extends Application {

    private     Stage       stage;
    private     VBox        root;
    private     Controlador controlador;
    private     Registro    registro;
    
    /* Punto de inicio del GUI
     * @see javafx.application.Application#start(javafx.stage.Stage)
     * Carga los ajustes del usuario usando la clase Ajustes.
     * Crea un controlador que se encargará de configurar la ventana
     * con la información correspondiente. Guarda el Stage en el
     * controlador y carga el archivo raíz (res/vistas/root.xml)
     */
    @Override
    public void start(Stage s) {
        System.out.println("Congreso GUI");

        this.registro = new Registro();

        System.out.println("Inicializando controlador");
        this.controlador = new Controlador(s, registro);

        this.stage = s;
        inflar();
        Dashboard d = new Dashboard(registro);
        this.root.getChildren().add(d);
        VBox.setVgrow(d, Priority.ALWAYS);
        d.getStylesheets().add("/styles.css");
    }

    /** 
     * Carga la Vista raíz con los componentes
     * encontrados en el archivo /vista/root.xml
     */
    private void inflar() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Gui.class.getResource("/vistas/root.fxml"));
        loader.setController(controlador);
        // System.out.println((Gui.class.getResource("/vistas/root.fxml")));
        try {
            root = (VBox)loader.load();
            Scene s = new Scene(root);
            stage.setScene(s);
            stage.setWidth(1280);
            stage.setHeight(720);
            stage.show();
        } catch(IOException e) {
            e.printStackTrace();
            System.err.println("Error: No se encontró el recurso");
            System.exit(1);
        }

    }
}
