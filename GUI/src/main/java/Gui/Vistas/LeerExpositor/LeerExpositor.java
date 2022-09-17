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
import Congreso.Expositor;
import Congreso.Persona;

import Gui.Vistas.PopUp;

public class LeerExpositor extends GridPane implements Initializable, PopUp.PopAble {

    // Elementos XML
    @FXML private TextField tfNombre, tfEdad, tfFono, tfCorreo, tfPais, tfOcupacion;
    @FXML private Button submit;

    // Valor de retorno
    private Expositor expositor = null;

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
    public boolean guardar() {
        String nombre = tfNombre.getText();
        String correo = tfCorreo.getText();
        String pais = tfPais.getText();
        String ocupacion = tfOcupacion.getText();
        Integer edad = null;
        Integer fono = null;

        // TODO : Verificar que los campos no estén vacíos
        try {
            edad = Integer.parseInt(tfEdad.getText());
        } catch (NumberFormatException e) {
            edad = Integer.valueOf(0);
        } try {
            fono = Integer.parseInt(tfFono.getText());
        } catch (NumberFormatException e) {
            fono = Integer.valueOf(0);
        }


        expositor = new Expositor(nombre, edad, fono, correo, pais, ocupacion);
        return true;
    }
}
