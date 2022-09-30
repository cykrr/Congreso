package Congreso.excepciones;

public class NullExpositorException extends Exception {
	
	public NullExpositorException(String nombrePresentacion) {
		super("Error al agregar expositor a la presentación " + nombrePresentacion + ". El expositor es nulo.");
	}
	
}