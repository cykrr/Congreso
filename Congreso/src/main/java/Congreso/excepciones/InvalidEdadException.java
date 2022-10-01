package Congreso.excepciones;

public class InvalidEdadException extends Exception {
	
	public InvalidEdadException(int edad, int min, int max) {
		super("La edad " + edad + " no se encuentra en el rango de " + min + " a " + max);
	}
	
}