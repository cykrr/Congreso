package Gui;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Alerta {
	
	public static void showAlert(String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText(message);
        alert.showAndWait();		
	}
	
	public static boolean mostrarAlertaConfirmacion(String message) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmar");
		alert.setHeaderText(message);
		Optional<ButtonType> result = alert.showAndWait();
		
		if(result.get() == ButtonType.OK)
			return true;
		return false;
	}
}