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
    /*! @brief Ventana principal: 
     *
     * Almacena la Escena principal (Scene) la cual se
     * genera en base al nodo raíz root
     */
    private     Stage       stage;      

    /*! @brief Nodo principal 
     *
     * Almacena todos los componentes de la vista principal
     * (root.fxml)
     */
    private     VBox        root;       

    /*! @brief Carga información del Registro
     *  @see Congreso.Registro*/
    private     Controlador controlador;

    /*! @brief Base de datos de la App */
    private     Registro    registro;   
    
    /** @brief Punto de inicio del GUI
     *
     * Carga los ajustes del usuario usando la clase Ajustes.
     * Crea un controlador que se encargará de configurar la ventana
     * con la información correspondiente. Guarda el Stage en el
     * controlador y carga la raíz.
     *
     * @see Gui.Ajustes
     *
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
     * @brief Carga la Vista raíz con los componentes
     * encontrados en el archivo res/vistas/root.xml
     */
    private void inflar() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Gui.class.getResource("/vistas/root.fxml"));
        loader.setController(controlador);
        try {
            root = (VBox)loader.load();
            Scene s = new Scene(root);
            stage.setScene(s);
            stage.setWidth(480);
            stage.setHeight(360);
            stage.show();
        } catch(IOException e) {
            e.printStackTrace();
            System.err.println("Error: No se encontró el recurso");
            System.exit(1);
        }

    }
}
