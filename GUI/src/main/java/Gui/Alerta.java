package Gui;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Alerta {
	
	public static void mostrarAlertaAdvertencia(String header) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.showAndWait();		
	}
	
	public static boolean mostrarAlertaConfirmacion(String header) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmar");
		alert.setHeaderText(header);
		Optional<ButtonType> result = alert.showAndWait();
		
		if(result.get() == ButtonType.OK)
			return true;
		return false;
	}
	
	public static void mostrarAlertaError(String header, String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(header);
		alert.setContentText(message);
		alert.showAndWait();
	}
}