package Gui.Vistas.LeerExpositor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import Congreso.Persona;

import Gui.Vistas.PopUp;

public class LeerExpositor extends GridPane implements Initializable, PopUp.PopAble {

    // Elementos XML
    @FXML private TextField tfNombre, tfEdad, tfFono;
    @FXML private Button submit;

    // Valor de retorno
    private Persona expositor = null;

    public LeerExpositor()  {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/leerExpositor.fxml"));
        fxmlLoader.setController(this);
        Node n = null;

        try {
            n = fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.getChildren().add(n);
    }

    @Override
    public void initialize(URL url, ResourceBundle resources) {
    	
    }

    @Override
    public Object getValue() {
        return expositor;
    }

    @Override
    public void guardar() {
        String nombre = tfNombre.getText();
        int edad = Integer.parseInt(tfEdad.getText());
        int fono = Integer.parseInt(tfFono.getText());

        expositor = new Persona(nombre, edad, fono);
    }
}
