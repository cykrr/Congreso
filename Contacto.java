public class Contacto {
	private String nombre;
	private int fono;
	private int edad;
	
	public Contacto(String nombre, int fono, int edad) {
		this.nombre = nombre;
		this.fono = fono;
		this.edad = edad;
	}
	
	public void mostrarDatos() {
		System.out.println("Nombre: " + nombre);
		System.out.println("Teléfono: " + Integer.toString(fono));
		System.out.println("Edad: " + Integer.toString(edad));
		System.out.println();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getFono() {
		return fono;
	}
	
	public int getEdad() {
		return edad;
	}
}

