package Congreso;
import java.time.LocalTime;
import java.time.LocalDate;

import java.util.LinkedList;

/* Presentación a ser almacenada en la base de datos del congreso.
 */
public class Presentacion {
    /*! Nombre de la presentación */
	private String nombre; 

    /*! Descripción de la presentación */
	private String descripcion; 

    /*! Expositor de la presentación */
	private Expositor expositor; 

    /*! Fecha de la presentación */
	private LocalDate fecha = LocalDate.of(1993,01, 01); 

    /*! Hora de la presentación */
	private LocalTime hora = LocalTime.of(0, 0); 

    /*! duración de la presentación */
	private int duracion;

    /*! Lista de asistentes de la presentación */
	private LinkedList<Persona> asistentes;

    /** Constructor principal, inicializa la lista de
     * asistentes (sin asistentes).
     * @see Congreso.Persona
      */
    public Presentacion() {
    	this.asistentes = new LinkedList<Persona>();
    }
    
    /** Constructor general para el tipo presentación
     * inicializa todos los atributos.
     * 
     * @param nombre Nombre de la presentación
     * @param expositor Referencia al expositor de la presentación
     * @param fecha Fecha de la presentación
     * @param hora Hora de la presentación
     * @param duracion Duración de la presentación
     * @param descripcion Descripción de la presentación
     * 
     * @see Congreso.Expositor
     * @see Congreso.Persona
     */
    public Presentacion(String nombre, Expositor expositor, LocalDate fecha,
            LocalTime hora, int duracion, String descripcion) {
    	this.nombre = nombre;
    	this.expositor = expositor;
    	this.fecha = fecha;
    	this.hora = hora;
    	this.duracion = duracion;
    	this.descripcion = descripcion;
    	this.asistentes = new LinkedList<Persona>();
    }
    
    /** Agrega un asistente de tipo Persona a la presentación insertándolo en
     * su lista de asistentes.
     * @param asistente Asistente a ser añadido.
     */
    public void agregarAsistente(Persona asistente){
    	this.asistentes.add(asistente);
    }
    
    /** Dada una referencia a un asistente, lo elimina de la lista de
     *  asistentes.
     *  @param asistente Asistente a ser eliminado.
     */
    public void eliminarAsistente(Persona asistente) {
      this.asistentes.remove(asistente);
	  }
    
    /** Dado un string, establece la descripción de la presentación
     * @param descripcion Descripción a establecer.
     */
    public void setDescripcion(String descripcion) {
    	this.descripcion = descripcion;
    }
    
    /** Dado un string de fecha establece la fecha de la presentación
     * -- Método desactualizado (sólo se utiliza en CLI/MenuEditar.java) --
     */
    public void setFecha(String fecha) {
    	String[] parts = fecha.split("-");
    	this.fecha = LocalDate.of(Integer.parseInt(parts[2]), 
    						      Integer.parseInt(parts[1]),
    							  Integer.parseInt(parts[0]));
    }

    /** Dado dia, mes y año como enteros, actualiza la fecha de la presentación
     * -- Método desactualizado (no se utiliza) --
     */
    public void setFecha(int dia, int mes, int ano) {
        this.fecha = LocalDate.of(ano, mes, dia);
    }

    /** Actualiza la fecha de la presentación 
     * @param fecha Fecha a establecer
     * @see LocalDate
    */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    /** Actualiza la hora de la presentación
     * @param hora Hora a establecer
     * @see LocalTime
     */
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
    
    /* Sólo se utiliza en menuEditar */
    public void setHora(String hora) {
    	String[] parts = hora.split(":");
    	this.hora = LocalTime.of(Integer.parseInt(parts[0]),
    							 Integer.parseInt(parts[1]));
    }

    /** Establece la hora de la presentación. Utilizado al importar archivos al
     * Registro.
     * @see Registro
     */
    public void setHora(int hora) {
        this.hora = LocalTime.of(hora, 0);
    }

    /** Establece el minuto de la presentación. Utilizado al importar archivos 
     * al Registro.
     * @see Registro
     */
    public void setMinuto(int min) {
        this.hora = LocalTime.of(hora.getHour(), min);
    }
    
    /** Establece la duración de la presentación. Utilizado al importar archivos 
     * al Registro.
     * @see Registro
     */
    public void setDuracion(int duracion) {
    	this.duracion = duracion;
    }

    /** Establece el día de la presentación. Utilizado al importar archivos 
     * al Registro.
     * @see Registro
     */
    public void setDia(int dia) {
        this.fecha = LocalDate.of(fecha.getYear(), fecha.getMonth(), dia);
    }

    /** Establece el mes de la presentación. Utilizado al importar archivos 
     * al Registro.
     * @see Registro
     */
    public void setMes(int mes) {
        this.fecha = LocalDate.of(fecha.getYear(), mes, fecha.getDayOfMonth());
    }

    /** Establece el año de la presentación. Utilizado al importar archivos 
     * al Registro.
     * @see Registro
     */
    public void setAño(int año) {
        this.fecha = LocalDate.of(año, fecha.getMonth(), fecha.getDayOfMonth());
    }
    
    /** Establece el Expositor de la presentación. Utilizado al importar archivos 
     * al Registro.
     * @see Registro
     * @param expositor Referencia al expositor de la presentación
     */
    public void setExpositor(Expositor expositor) {
    	this.expositor = expositor;
    }

    /** Establece el nombre de la presentación. Utilizado al importar archivos 
     * al Registro.
     * @see Registro
     * @param n Nombre de la presentación.
     */
    public void setNombre(String n)  {
        this.nombre = n;
    }

    /** Establece la lista de asistentes de la presentación. Utilizado al importar archivos 
     * al Registro.
     * 
     * @see Registro
     * @param asistentes Nueva lista de asistentes.
     */
    public void setAsistentes(LinkedList<Persona> asistentes) {
        this.asistentes = asistentes;
    }

    /** Dado el nombre de un asistente, revisa si este asistirá a la
     *  presentación.
     *  @param nombre Nombre del asistente a buscar
     *  @return Referencia al asistente || null
     */
    public Persona buscarAsistente(String nombre) {
		for(int i = 0; i < asistentes.size(); i++) {
			Persona persona = asistentes.get(i);
			if(nombre.equals(persona.getNombre()))
				return persona;
		}
		return null;
    }
    
    /** Muestra la presentación actual
     * -- Método desactualizado. Sólo se usa en CLI. --
     */
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

    /** Muestra los asistentes de la presentación
     *  -- Método desactualizado. Sólo se utiliza en CLI --
     */

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
