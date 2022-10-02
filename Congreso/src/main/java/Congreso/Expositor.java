package Congreso;

import Congreso.excepciones.InvalidCorreoException;
import Congreso.excepciones.InvalidEdadException;
import Congreso.excepciones.InvalidFonoException;
import Congreso.excepciones.InvalidNombreException;

public class Expositor extends Persona {
	private String pais;
	private String ocupacion;
	
	/**
	 * Constructor principal, crea un Expositor
	 * con todos sus atributos.
	 * @param nombre
	 * @param edad
	 * @param fono
	 * @param correo
	 * @param pais
	 * @param ocupacion
	 * @throws InvalidNombreException
	 * @throws InvalidEdadException
	 * @throws InvalidFonoException
	 * @throws InvalidCorreoException
	 */
    public Expositor(String nombre, int edad, long fono, String correo, String pais, String ocupacion) 
    		throws InvalidNombreException, InvalidEdadException, InvalidFonoException, InvalidCorreoException {
    	super(nombre, fono, correo);
    	setEdad(edad);
    	setPais(pais);
    	setOcupacion(ocupacion);
    }
    
    /**
     * Establece la edad del Expositor validando
     * que se encuentre en el rango establecido.
     * @param edad
     * @throws InvalidEdadException
     */
    public void setEdad(int edad) throws InvalidEdadException {
    	if(edad > 100 || edad < 18)
    		throw new InvalidEdadException(edad, 18, 100);
    	
    	super.setEdad(edad);
    }

    /**
     * Establece el país del expositor.
     * @param pais
     */
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	/**
	 * Establece la ocupación del expositor.
	 * @param ocupacion
	 */
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
