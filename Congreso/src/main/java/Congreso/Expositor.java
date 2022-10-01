package Congreso;

import Congreso.excepciones.InvalidCorreoException;
import Congreso.excepciones.InvalidEdadException;
import Congreso.excepciones.InvalidFonoException;
import Congreso.excepciones.InvalidNombreException;

public class Expositor extends Persona {
	private String pais;
	private String ocupacion;
	
    public Expositor(String nombre, int edad, long fono, String correo, String pais, String ocupacion) 
    		throws InvalidNombreException, InvalidEdadException, InvalidFonoException, InvalidCorreoException {
    	super(nombre, fono, correo);
    	setEdad(edad);
    	setPais(pais);
    	setOcupacion(ocupacion);
    }
    
    public void setEdad(int edad) throws InvalidEdadException {
    	if(edad > 100 || edad < 18)
    		throw new InvalidEdadException(edad, 18, 100);
    	
    	super.setEdad(edad);
    }

	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public void setOcupacion(String ocupacion) {	
		this.ocupacion = ocupacion;
	}
	
	public String getPais() {
		return pais;
	}

	public String getOcupacion() {
		return ocupacion;
	}

}
