import java.util.LinkedList;

public class Presentacion {
	private String nombre;
	private String fecha;
	private String hora;
	private int duracion;
	private String descripcion;
	private Persona expositor;
	static private LinkedList<Persona> asistencias;
    
    public Presentacion(String nombre) {
    	this.nombre = nombre;
    	this.asistencias = new LinkedList<Persona>();
    }
    
    public void setAsistencia(Persona asistira){
    	this.asistencias.add(asistira);
    }
    
    public void setDescripcion(String descripcion) {
    	this.descripcion = descripcion;
    }
    
    public void setFecha(String fecha) {
    	this.fecha = fecha;
    }
    
    public void setHora(String hora) {
    	this.hora = hora;
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
    	System.out.println("Hora: " + (hora != null ? hora : "No asignada"));
    	System.out.println("Duración: " + (duracion != 0 ? duracion : "No asignada"));
    	if(asistencias.size() < 1) {
    		System.out.println("Asistencia: Nadie ha confirmado su asistencia");
    	}
    	else {
    		System.out.println("Asistencia: ");
    		for(int i = 0; i < asistencias.size(); i++) {
    			System.out.println("	"+ asistencias.get(i).getNombre());
    		}
    	}
    }
}
