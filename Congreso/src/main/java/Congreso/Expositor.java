package Congreso;
public class Expositor extends Persona {
	private String descripcion;
	private String nacionalidad;
	private String ocupacion;
	
    public Expositor(String nombre, int edad, int fono){
    	super(nombre, edad, fono);
    }

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}
}
