package Congreso.excepciones;

public class InvalidFonoException extends Exception {
	
	public InvalidFonoException(long fono) {
		super("El teléfono " + fono + " debe tener entre 8 y 12 dígitos");
	}
	
}