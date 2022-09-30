package Congreso.excepciones;

public class InvalidCorreoException extends Exception {
	
	public InvalidCorreoException(String correo) {
		super("El correo " + correo + " no es válido");
	}
	
}