package Gui;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alerta {
	
	public static void showAlert(String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText(message);
        alert.showAndWait();		
	}
	
}