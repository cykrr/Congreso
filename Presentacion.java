import java.time.LocalTime;

public class Presentacion {
	private String fecha;
	private int duracion;
	private Persona expositor;
    private int asistentesCantidad;
    private Persona [] asistentes;
    private String tema;
    private String descripcion;
    
    public Presentacion (Persona exhibitor, int duration, int cantAssistant, 
    					 String theme, String description, String date, Persona [] asistants){
    	this.expositor = exhibitor;
    	this.duracion = duration;
    	this.asistentesCantidad = cantAssistant;
    	this.asistentes = asistants;
    	this.tema = theme;
    	this.descripcion = description;
    	this.fecha = date;
    }
    
    public String getFecha() {return this.fecha;}
    public String getTema() {return this.tema;}
    public String getDescripcion() {return this.descripcion;}
    
    public Persona getExpositor() {return this.expositor;}
    public Persona[] getAsistentes() {return this.asistentes;}
    
    public int getDuracion() {return this.duracion;}
    public int getAsistentesCantidad() {return this.asistentesCantidad;}
}
