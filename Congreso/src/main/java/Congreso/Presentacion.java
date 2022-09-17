package Congreso;
import java.time.LocalTime;
import java.time.format.*;
import java.time.LocalDate;

import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

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

    public Presentacion() {
    	this.asistentes = new LinkedList<Persona>();
    }
    
    public Presentacion(String nombre) {
        this();
    	this.nombre = nombre;
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
    	String[] parts = fecha.split("-");
    	this.localDate = LocalDate.of(Integer.parseInt(parts[2]), 
    								  Integer.parseInt(parts[1]),
    								  Integer.parseInt(parts[0]));
    }

    public void setFecha(int dia, int mes, int ano) {
        this.localDate = LocalDate.of(ano, mes, dia);
        this.fecha = Util.dateFormatter.format(localDate);
    }

    public void setFecha(LocalDate fecha) {
        this.localDate = fecha;
        this.fecha = Util.dateFormatter.format(fecha);
    }
    
    public void setHora(LocalTime hora) {
        this.localTime = hora;
    }
    
    public void setHora(String hora) {
    	this.hora = hora;
    	String[] parts = fecha.split(":");
    	this.localTime = LocalTime.of(Integer.parseInt(parts[0]),
    								  Integer.parseInt(parts[1]));
    }

    public void setHora(int hora) {
        this.localTime = LocalTime.of(hora, 0);
        this.hora = (localTime.toString());
    }

    public void setMinuto(int min) {
        this.localTime = LocalTime.of(localTime.getHour(), min);
        this.hora = (localTime.toString());
    }
    
    public void setDuracion(int duracion) {
    	this.duracion = duracion;
    }

    public void setDia(int dia) {
        this.localDate = LocalDate.of(localDate.getYear(), localDate.getMonth(), dia);
        this.fecha = Util.dateFormatter.format(localDate);
    }

    public void setMes(int mes) {
        this.localDate = LocalDate.of(localDate.getYear(), mes, localDate.getDayOfMonth());
        this.fecha = Util.dateFormatter.format(localDate);
    }

    public void setAno(int ano) {
        this.localDate = LocalDate.of(ano, localDate.getMonth(), localDate.getDayOfMonth());
        this.fecha = Util.dateFormatter.format(localDate);
    }
    
    public void setExpositor(Persona expositor) {
    	this.expositor = expositor;
    }

    public void setNombre(String n)  {
        this.nombre = n;
    }

	// public int getId() {
		// return this.ID;
	// }
    
    public void setAsistentes(String asistentes, Map<String,Persona>personas) {
        if (asistentes.charAt(0) != '\"') {
            System.err.println("Error: setAsistentes() Esperado string" +
                    "que inicia con '\"'.");
            System.exit(1);
        }

        asistentes = asistentes.substring(1, asistentes.length() - 1);
        
        LinkedList<String> listaAsistentes = CSVTokener.csvArray(new CSVTokener(asistentes));

        for (String nombreAsistente : listaAsistentes) {
            Persona busqueda_nombre = personas.get(nombreAsistente);
            if (busqueda_nombre == null) {
                /*System.err.println("Error: el asistente \"" + nombreAsistente + "\" no figura" +
                        " en la base de datos");
                System.err.println("No se añadirá a la base de datos");*/
            } else {
                this.agregarAsistente(busqueda_nombre);
            }
        }

    }

    public Persona buscarAsistente(String nombre) {
		for(int i = 0; i < asistentes.size(); i++) {
			Persona persona = asistentes.get(i);
			if(nombre.equals(persona.getNombre()))
				return persona;
		}
		return null;
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

    @Override
    public String toString() {
        return this.nombre;
    }
    
    public String getNombre() {
    	return nombre;
    }
    
    public Persona getExpositor() {
    	return expositor;
    }
    
    public int getDia() {
    	return localDate.getDayOfMonth();
    }
    
    public int getMes() {
    	return localDate.getMonthValue();
    }
    
    public int getAño() {
    	return localDate.getYear();
    }
    
    public int getDuracion() {
    	return duracion;
    }
    
    public String getDescripcion() {
    	return descripcion;
    }
    
    public LinkedList<Persona> getAsistentes() {
    	return asistentes;
    }
}
