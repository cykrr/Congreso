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

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    public long getFono() {
    	return this.fono;
    }

	public void mostrarDatos() {
		System.out.println("Nombre: " + nombre);
		System.out.println("Teléfono: " + fono);
		System.out.println("Edad: " + edad);
	}
}
