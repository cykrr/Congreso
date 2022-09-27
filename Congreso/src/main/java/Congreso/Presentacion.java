package Congreso;
import java.time.LocalTime;
import java.time.LocalDate;

import java.util.LinkedList;

public class Presentacion {
	private String nombre;
	private String descripcion;
	private Expositor expositor;
	private LocalDate fecha = LocalDate.of(1993,01, 01);;
	private LocalTime hora = LocalTime.of(0, 0);
	private int duracion;
	private LinkedList<Persona> asistentes;

    public Presentacion() {
    	this.asistentes = new LinkedList<Persona>();
    }
    
    public Presentacion(String nombre, Expositor expositor, LocalDate fecha, LocalTime hora, int duracion, String descripcion) {
    	this.nombre = nombre;
    	this.expositor = expositor;
    	this.fecha = fecha;
    	this.hora = hora;
    	this.duracion = duracion;
    	this.descripcion = descripcion;
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
    	String[] parts = fecha.split("-");
    	this.fecha = LocalDate.of(Integer.parseInt(parts[2]), 
    						      Integer.parseInt(parts[1]),
    							  Integer.parseInt(parts[0]));
    }

    public void setFecha(int dia, int mes, int ano) {
        this.fecha = LocalDate.of(ano, mes, dia);
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
    
    public void setHora(String hora) {
    	String[] parts = hora.split(":");
    	this.hora = LocalTime.of(Integer.parseInt(parts[0]),
    							 Integer.parseInt(parts[1]));
    }

    public void setHora(int hora) {
        this.hora = LocalTime.of(hora, 0);
    }

    public void setMinuto(int min) {
        this.hora = LocalTime.of(hora.getHour(), min);
    }
    
    public void setDuracion(int duracion) {
    	this.duracion = duracion;
    }

    public void setDia(int dia) {
        this.fecha = LocalDate.of(fecha.getYear(), fecha.getMonth(), dia);
    }

    public void setMes(int mes) {
        this.fecha = LocalDate.of(fecha.getYear(), mes, fecha.getDayOfMonth());
    }

    public void setAño(int año) {
        this.fecha = LocalDate.of(año, fecha.getMonth(), fecha.getDayOfMonth());
    }
    
    public void setExpositor(Expositor expositor) {
    	this.expositor = expositor;
    }

    public void setNombre(String n)  {
        this.nombre = n;
    }

    public void setAsistentes(LinkedList<Persona> asistentes) {
        this.asistentes = asistentes;
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
    
    public Expositor getExpositor() {
    	return expositor;
    }
    
    public int getDia() {
    	return fecha.getDayOfMonth();
    }
    
    public int getMes() {
    	return fecha.getMonthValue();
    }
    
    public int getAño() {
    	return fecha.getYear();
    }
    
    public LocalDate getFecha() {
    	return fecha;
    }
    
    public String getStringFecha() {
    	return Util.dateFormatterOutput.format(fecha);
    }
    
    public LocalTime getHora() {
    	return hora;
    }
    
    public String getStringHora() {
    	return Util.timeFormatterOutput.format(hora);
    }
    
    public int getHoraInicio() {
    	return 	hora.getHour();
    }
    public int getMinutoInicio() {
    	return 	hora.getMinute();
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
