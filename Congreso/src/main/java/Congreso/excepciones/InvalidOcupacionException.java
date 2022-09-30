package Congreso.excepciones;

public class InvalidOcupacionException extends Exception {
	
	public InvalidOcupacionException(String ocupacion) {
		super("La ocupación " + ocupacion + " contiene caracteres especiales");
	}
	
}