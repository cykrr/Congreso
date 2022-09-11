package Gui;
import Congreso.Registro;
import javafx.application.Application;
import javafx.stage.Stage;

public class Gui extends Application {

    Stage stage;
    
    @Override
    public void start(Stage s) {
        Registro r;
        System.out.println("Congreso GUI");
        this.stage = s;
    }
}
