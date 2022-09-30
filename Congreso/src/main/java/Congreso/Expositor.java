package Congreso;

import Congreso.excepciones.InvalidCorreoException;
import Congreso.excepciones.InvalidEdadException;
import Congreso.excepciones.InvalidFonoException;
import Congreso.excepciones.InvalidNombreException;
import Congreso.excepciones.InvalidOcupacionException;
import Congreso.excepciones.InvalidPaisException;

public class Expositor extends Persona {
	private String pais;
	private String ocupacion;
	
    public Expositor(String nombre, int edad, long fono, String correo, String pais, String ocupacion) 
    		throws InvalidNombreException, InvalidEdadException, InvalidFonoException, InvalidCorreoException, InvalidPaisException, InvalidOcupacionException {
    	super(nombre, edad, fono, correo);
    	setPais(pais);
    	setOcupacion(ocupacion);
    }

	public void setPais(String pais) throws InvalidPaisException {
		if(!Util.isAlphaOrSpace(pais))
			throw new InvalidPaisException(pais);
		
		this.pais = pais;
	}
	
	public void setOcupacion(String ocupacion) throws InvalidOcupacionException {
		if(!Util.isAlphaOrSpace(ocupacion))
			throw new InvalidOcupacionException(ocupacion);
		
		this.ocupacion = ocupacion;
	}
	
	public String getPais() {
		return pais;
	}

	public String getOcupacion() {
		return ocupacion;
	}

}
