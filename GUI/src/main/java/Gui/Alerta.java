package Gui;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;

public class Alerta {
	private static ButtonType yesButton = new ButtonType("Si", ButtonBar.ButtonData.OK_DONE);
	private static ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
	
	/* Sobrecarga Metodos
	 * mostrarAlertaAdvertencia()
	 * */
	public static void mostrarAlertaAdvertencia(String header) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.showAndWait();		
	}
	
	public static boolean mostrarAlertaAdvertencia(String header, String headerText) {
		Alert alert = new Alert(AlertType.WARNING,
						header,
		                yesButton,
		                noButton);
		alert.setTitle("Cerrando Programa");
		alert.setHeaderText(headerText);
		Optional<ButtonType> result = alert.showAndWait();
		
		if(result.orElse(noButton) == yesButton)
			return true;
		return false;
	}
	
	/* FIN Sobrecarga Metodos
	 * mostrarAlertaAdvertencia()
	 * */
	
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