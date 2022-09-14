package Gui;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Congreso.Registro;

public class Controlador implements Initializable {

    Registro    registro;
    Stage       stage;

    public Controlador(Stage s) {
        this.stage = s; 
        this.registro = new Registro();
    }

    public void initialize(URL url, ResourceBundle resources) {

    }

    public void importar() {
        System.out.println("Importando");
        FileChooser fc = new FileChooser();
        fc.setTitle("Seleccione el archivo a importar");
        File file = fc.showOpenDialog(stage);

        // si el archivo no es CSV.
        if (!(file.getName().contains("csv"))) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeight(200);
            alert.setWidth(200);
            alert.setHeaderText("El archivo debe ser del tipo CSV");
            alert.showAndWait();
        }
        try {
            registro.importar(file.getPath());
        } catch (IOException e) {
            System.err.println("Error abriendo archivo");
        }
    }
}
