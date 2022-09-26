package Gui.Vistas.Detalle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Congreso.Persona;
import Congreso.Registro;
import Gui.Alerta;
import Gui.Vistas.VistaPersona.VistaPersona;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Detalle extends VBox implements Initializable {

    @FXML private TextField textBox;
    @FXML private VBox scrollBox;
    private Registro r;
    public Detalle(Registro r)
    {
        super();
        this.r = r;
        // TODO : Convertir importar en una clase abstracta que recibe como parametro
        // el tipo de ventana y la ruta al xml.
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/detalle.fxml"));
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

    public void textInput() {
        String t = this.textBox.getText();
        int edad;
        try {
            edad = Integer.parseInt(t);
        } catch(NumberFormatException e) {
            Alerta.mostrarAlertaAdvertencia("Ingrese un número.");
            return;
        }
        scrollBox.getChildren().clear();
        for (Persona a : r.getAsistentes()) {
            if (a.getEdad() > edad) {
                VistaPersona vp = new VistaPersona(a, r);
                scrollBox.getChildren().add(vp);
            }
        }

    }
}
