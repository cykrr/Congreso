package Congreso.excepciones;

public class InvalidPaisException extends Exception {
	
	public InvalidPaisException(String pais) {
		super("El país " + pais + " contiene caracteres especiales");
	}
	
}