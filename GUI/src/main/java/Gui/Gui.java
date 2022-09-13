package Gui;

import java.io.IOException;

import Congreso.Registro;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class Gui extends Application {

    private     Stage       stage;
    private     VBox        root;
    private     Registro    r;
    
    @Override
    public void start(Stage s) {
        System.out.println("Congreso GUI");
        this.stage = s;
        inflar();
    }

    private void inflar() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Gui.class.getResource("/vistas/root.fxml"));
        // System.out.println((Gui.class.getResource("/vistas/root.fxml")));
        try {
            root = (VBox)loader.load();
            Scene s = new Scene(root);
            stage.setScene(s);
            stage.show();
        } catch(IOException e) {
            e.printStackTrace();
            System.err.println("Error: No se encontró el recurso");
            System.exit(1);
        }

    }
}
