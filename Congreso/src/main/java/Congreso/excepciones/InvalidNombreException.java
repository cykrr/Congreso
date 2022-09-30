package Congreso.excepciones;

public class InvalidNombreException extends Exception {
	
	public InvalidNombreException(String nombre) {
		super("El nombre " + nombre + " contiene caracteres especiales");
	}
	
}