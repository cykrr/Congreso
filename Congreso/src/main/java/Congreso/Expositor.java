package Congreso;
public class Expositor extends Persona {
	private String pais;
	private String ocupacion;
	
    public Expositor(String nombre, int edad, int fono, String correo, String nacionalidad, String ocupacion) {
    	super(nombre, edad, fono, correo);
    	this.pais = pais;
    	this.ocupacion = ocupacion;
    }

	public String getPais() {
		return pais;
	}

	public void setNacionalidad(String nacionalidad) {
		this.pais = pais;
	}

	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}
}
