package main.java;

import javafx.application.Application;
import javafx.stage.Stage;
import Congreso.java.Registro;

public class Gui extends Application {

    Stage stage;
    
    @Override
    public void start(Stage s) {
        Registro r;
        System.out.println("Congreso GUI");
        this.stage = s;
    }
}
