public class Presentacion {
	private String nombre;
	private String fecha;
	private int duracion;
	private String descripcion;
	private Persona expositor;
    
    public Presentacion(String nombre) {
    	this.nombre = nombre;
    }
    
    public void setDescripcion(String descripcion) {
    	this.descripcion = descripcion;
    }
    
    public void setFecha(String fecha) {
    	this.fecha = fecha;
    }
    
    public void setDuracion(int duracion) {
    	this.duracion = duracion;
    }
    
    public void setExpositor(Persona expositor) {
    	this.expositor = expositor;
    }
    
    public String getNombre() {
    	return nombre;
    }
    
    public void mostrar() {
    	System.out.println("Nombre: " + nombre);
    	System.out.println("Expositor: " + (expositor != null ? expositor.getNombre() : "No asignado"));
    	System.out.println("Descripción: " + (descripcion != null ? descripcion : "No asignada"));
    	System.out.println("Fecha: " + (fecha != null ? fecha : "No asignada"));
    	System.out.println("Duración: " + (duracion != 0 ? duracion : "No asignada"));
    }
}
