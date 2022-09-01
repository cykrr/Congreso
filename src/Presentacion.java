import java.time.LocalTime;
import java.time.LocalDate;

import java.util.LinkedList;

public class Presentacion {
	private String nombre;
	private String fecha;
	private String hora;
	private int duracion;
	private String descripcion;
	private Persona expositor;

    private LocalDate localDate = LocalDate.of(1993,01, 01);
    private LocalTime localTime = LocalTime.of(0, 0);
    private LinkedList<Persona> asistentes;

    
    public Presentacion(String nombre) {
    	this.nombre = nombre;
    	this.asistentes = new LinkedList<Persona>();
    }
    
    public void agregarAsistente(Persona asistente){
    	this.asistentes.add(asistente);
    }
    
    public void setDescripcion(String descripcion) {
    	this.descripcion = descripcion;
    }
    
    public void setFecha(String fecha) {
    	this.fecha = fecha;
    }

    public void setFecha(int dia, int mes, int ano) {
        this.localDate = LocalDate.of(ano, mes, dia);
    }
    
    public void setHora(String hora) {
    	this.hora = hora;
    }

    public void setHora(int hora) {
        localTime = LocalTime.of(hora, 0);
    }

    public void setMinuto(int min) {
        localTime = LocalTime.of(localTime.getHour(), min);
    }
    
    public void setDuracion(int duracion) {
    	this.duracion = duracion;
    }

    public void setDia(int dia) {
        this.localDate = LocalDate.of(localDate.getYear(), localDate.getMonth(), dia);
    }

    public void setMes(int mes) {
        this.localDate = LocalDate.of(localDate.getYear(), mes, localDate.getDayOfMonth());
    }

    public void setAno(int ano) {
        this.localDate = LocalDate.of(ano, localDate.getMonth(), localDate.getDayOfMonth());
    }
    
    public void setExpositor(Persona expositor) {
    	this.expositor = expositor;
    }
    
    public String getNombre() {
    	return nombre;
    }

	// public int getId() {
		// return this.ID;
	// }
    
    public void setAsistentes(String asistentes) {
        if (asistentes.charAt(0) != '\"') {
            System.err.println("Error: setAsistentes() Esperado string" +
                    "que inicia con '\"'.");
            System.exit(1);
            asistentes = asistentes.substring(1, asistentes.length() - 1);
            // this.asistentes = CSVTokener.csvArray(new CSVTokener(asistentes));
        }

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

}
