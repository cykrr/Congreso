package Gui;
import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import Gui.Vistas.Presentacion.VistaPresentacion;

import Congreso.Persona;
import Congreso.Presentacion;
import Congreso.Registro;
import Congreso.Util;


public class Controlador implements Initializable {

    private     Registro    registro;
    private     Stage       stage;
    private     Ajustes     ajustes;

    public Controlador(Stage s) {
        System.out.println("Cargando ajustes");
        this.ajustes = new Ajustes(); 

        System.out.println("Ajustes guardados: "+ ajustes.carpeta);


        System.out.println("Inicializando registro");
        this.registro = new Registro();

        System.out.println("Cargando datos: ");
        try {
            registro.importar(ajustes.carpeta + "/Presentaciones.csv");
        } catch(IOException e) {
            e.printStackTrace();
            System.err.println("Error: revise su configuración");
        }


        this.stage = s; 
    }

    public void initialize(URL url, ResourceBundle resources) {
    }

    public void crearPresentacion() {
        Presentacion retorno = null;
        // TODO : Proteger expositores y asistentes de modificación
        // TODO : Añadir hora y duración de la presentación
        VBox root = new VBox();
        Scene dialogScene = new Scene(root);
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(this.stage);
        stage.setScene(dialogScene);

        VistaPresentacion vp = new VistaPresentacion(registro);

        Button b = new Button("Guardar");
        b.setOnAction(e-> {
            vp.guardar();
            stage.close();
        });

        root.getChildren().add(vp);
        root.getChildren().add(b);

        stage.showAndWait();
        retorno = vp.getValue();
        if (retorno != null)
            registro.insertarPresentacion(retorno);
    }

    public void importar() {
        System.out.println("Importando");
        FileChooser fc = new FileChooser();
        fc.setTitle("Seleccione el archivo a importar");
        File file;
        
        while(true) {
        	file = fc.showOpenDialog(stage);

        	// si no se elige ningún archivo
        	if(file == null)
        		return;

            // si el archivo no es CSV.
            if (!Util.getFileExtension(file).equals("csv")) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Error");
                alert.setWidth(200);
                alert.setHeaderText("El archivo debe ser del tipo CSV");
                alert.showAndWait();
            } else {
            	break;
            }
        }

        try {
            registro.importar(file.getPath());
        } catch (IOException e) {
            System.err.println("Error abriendo archivo");
        }
    }

    public void editarPreferencias() {
        TextInputDialog dialog = new TextInputDialog("Preferencias");
        dialog.setTitle("Preferencias");
        dialog.setContentText("Please enter your name:");
    }
}
