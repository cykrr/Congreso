import java.util.LinkedList;

public class Presentacion {
	private String nombre;
	private String fecha;
	private String hora;
	private int duracion;
	private String descripcion;
	private Persona expositor;
	private LinkedList<Persona> asistentes;
    
    public Presentacion(String nombre) {
    	this.nombre = nombre;
    	this.asistentes = new LinkedList<Persona>();
    }
    
    public void agregarAsistente(Persona asistente){
    	this.asistentes.add(asistente);
    }
    
	public void eliminarAsistente(Persona asistente) {
		this.asistentes.remove(asistente);
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
    
    public LinkedList<Persona> getAsistentes() {
    	return asistentes;
    }
    
    public void mostrar() {
    	System.out.println("Nombre: " + nombre);
    	System.out.println("Expositor: " + (expositor != null ? expositor.getNombre() : "No asignado"));
    	System.out.println("Descripción: " + (descripcion != null ? descripcion : "No asignada"));
    	System.out.println("Fecha: " + (fecha != null ? fecha : "No asignada"));
    	System.out.println("Hora: " + (hora != null ? hora : "No asignada"));
    	System.out.println("Duración: " + (duracion != 0 ? duracion : "No asignada"));
    	if(asistentes.size() < 1) {
    		System.out.println("Asistencia: Nadie ha confirmado su asistencia");
    	}
    	else {
    		System.out.println("Asistencia: ");
    		for(int i = 0; i < asistentes.size(); i++) {
    			System.out.println("	"+ asistentes.get(i).getNombre());
    		}
    	}
    }
    
    public void mostrarAsistentes() {
    	int cantidadAsistentes = asistentes.size();
    	if(cantidadAsistentes < 1) {
    		System.out.println("No hay asistentes para mostrar");
    		return;
    	}
    	
    	System.out.println(String.format("%-20s %-20s %-20s", "Nombre", "Edad", "Teléfono"));
    	for(int i = 0; i < cantidadAsistentes; i++) {
    		Persona p = asistentes.get(i);
    		System.out.println(String.format("%-20s %-20s %-20s", p.getNombre(), p.getEdad(), p.getFono()));
    	}    	
    	
    	System.out.println("Total de asistentes: " + cantidadAsistentes);
    }
}
