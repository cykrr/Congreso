package Congreso;
import java.time.LocalTime;
import java.time.LocalDate;

import java.util.LinkedList;

import Congreso.excepciones.InvalidDuracionException;
import Congreso.excepciones.InvalidNombreException;
import Congreso.excepciones.NullExpositorException;

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
     * @throws InvalidNombreException 
     * @throws NullExpositorException 
     * @throws InvalidDuracionException 
     * 
     * @see Congreso.Expositor
     * @see Congreso.Persona
     */
    public Presentacion(String nombre, Expositor expositor, LocalDate fecha, LocalTime hora, int duracion, String descripcion) 
    		throws InvalidNombreException, NullExpositorException, InvalidDuracionException {
    	setNombre(nombre);
    	setExpositor(expositor);
    	setFecha(fecha);
    	setHora(hora);
    	setDuracion(duracion);
    	setDescripcion(descripcion);
    	
    	this.asistentes = new LinkedList<Persona>();
    }
    
    /** Agrega un asistente de tipo Persona a la presentación insertándolo en
     * su lista de asistentes.
     * @param asistente Asistente a ser añadido.
     */
    public void agregarAsistente(Persona asistente){
    	this.asistentes.add(asistente);
    }
    
    /** Elimina un asistente de tipo Persona en la presentación insertándolo .
     * @param asistente Asistente a ser eliminado.
     */
    public void removerAsistente(Persona asistente){
    	this.asistentes.remove(asistente);
    }
    
	public boolean contieneAsistente(Persona asistente) {
		return asistentes.contains(asistente);
	}
    
    /** Dada una referencia a un asistente, lo elimina de la lista de
     *  asistentes.
     *  @param asistente Asistente a ser eliminado.
     */
    public void eliminarAsistente(Persona asistente) {
      this.asistentes.remove(asistente);
    }
    
    /** 
     * @return Un String con el nombre de la presentacion-
     */
    @Override
    public String toString() {
        return this.nombre;
    }
    
    // Setter y Getter
    
    /** Establece el nombre de la presentación. Utilizado al importar archivos 
     * al Registro.
     * @see Registro
     * @param n Nombre de la presentación.
     */
    public void setNombre(String nombre) throws InvalidNombreException {
    	if(!Util.isAlphaOrSpace(nombre))
    		throw new InvalidNombreException(nombre);
    	
        this.nombre = nombre;
    }
    
    /** 
     * @return Un String con el nombre de la presentacion.
     */
    public String getNombre() {
    	return nombre;
    }
    
    
    /** Establece el Expositor de la presentación. Utilizado al importar archivos 
     * al Registro.
     * @see Registro
     * @param expositor Referencia al expositor de la presentación
     */
    public void setExpositor(Expositor expositor) throws NullExpositorException {
    	if(expositor == null)
    		throw new NullExpositorException(nombre);
    	
    	this.expositor = expositor;
    }
    
    /** 
     * @return Un atributo clase Expositor con la informacion del expositor.
     */
    public Expositor getExpositor() {
    	return expositor;
    }
    
    
    /** Actualiza la fecha de la presentación 
     * @param fecha Fecha a establecer
     * @see LocalDate
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    /** 
     * @return Un atributo int con el número del dia de la presentacion.
     */
    public int getDia() {
    	return fecha.getDayOfMonth();
    }
    
    /** 
     * @return Un atributo int con el número del mes de la presentacion.
     */
    public int getMes() {
    	return fecha.getMonthValue();
    }
    
    /** 
     * @return Un atributo int con el número del año de la presentacion.
     */
    public int getAño() {
    	return fecha.getYear();
    }
    
    /** 
     * @return Un atributo LocalDate con la fecha presentacion.
     */
    public LocalDate getFecha() {
    	return fecha;
    }
    
    /** 
     * @return Un atributo String con la fecha presentacion.
     */
    public String getStringFecha() {
    	return Util.dateFormatterOutput.format(fecha);
    }
    
    /** Actualiza la hora de la presentación
     * @param hora Hora a establecer
     * @see LocalTime
     */
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
    
    /** 
     * @return Un atributo LocalTime con la hora de inicio de la presentacion.
     */
    public LocalTime getHora() {
    	return hora;
    }
    
    /** 
     * @return Un atributo String con la hora de inicio de la presentacion.
     */
    public String getStringHora() {
    	return Util.timeFormatterOutput.format(hora);
    }
    
    /** 
     * @return Un atributo int con el número de la hora de inicio de la presentacion.
     */
    public int getHoraInicio() {
    	return 	hora.getHour();
    }
    
    /** 
     * @return Un atributo int con el número del minuto de inicio de la presentacion.
     */
    public int getMinutoInicio() {
    	return 	hora.getMinute();
    }
    
    /** Establece la duración de la presentación. Utilizado al importar archivos 
     * al Registro.
     * @see Registro
     */
    public void setDuracion(int duracion) throws InvalidDuracionException {
    	if(duracion < 1 || duracion > 300)
    		throw new InvalidDuracionException(duracion);
    	
    	this.duracion = duracion;
    }
    
    /** 
     * @return Un atributo int con el número de la duracion en minutos de la presentacion.
     */
    public int getDuracion() {
    	return duracion;
    }
    
    /** Dado un string, establece la descripción de la presentación
     * @param descripcion Descripción a establecer.
     */
    public void setDescripcion(String descripcion) {
    	this.descripcion = descripcion;
    }
    
    /** 
     * @return Un atributo String con la descripcion de la presentacion.
     */
    public String getDescripcion() {
    	return descripcion;
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
    
    /** 
     * @return Una coleccion LinkedList con los asistentes de la presentacion.
     */
    public LinkedList<Persona> getAsistentes() {
    	return asistentes;
    }

}
