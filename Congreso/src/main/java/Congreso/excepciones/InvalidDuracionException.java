package Congreso.excepciones;

public class InvalidDuracionException extends Exception {
	
	public InvalidDuracionException(int duracion) {
		super("La duración " + duracion + " no se encuentra en el rango de 1 a 300");
	}
	
}