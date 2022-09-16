package Congreso;
public class Expositor extends Persona {
	private String nacionalidad;
	private String ocupacion;
	
    public Expositor(String nombre, int edad, int fono, String correo, String nacionalidad, String ocupacion) {
    	super(nombre, edad, fono, correo);
    	this.nacionalidad = nacionalidad;
    	this.ocupacion = ocupacion;
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
