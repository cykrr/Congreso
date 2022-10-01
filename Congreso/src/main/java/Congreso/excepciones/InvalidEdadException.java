package Congreso.excepciones;

public class InvalidEdadException extends Exception {
	
	public InvalidEdadException(int edad) {
		super("La edad " + edad + " no se encuentra en el rango de 1 a 100");
	}
}