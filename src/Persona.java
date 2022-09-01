public class Persona {
	private String nombre;
	private int edad;
	private long fono; // Número telefónico

	public Persona(String nombre, int edad, long fono){
		this.nombre = nombre;
		this.edad = edad;
		this.fono = fono;
	}

	public String getNombre() {
		return this.nombre;
	}

	public int getEdad() {
		return this.edad;
	}

	public long getFono() {
		return this.fono;
	}
}
