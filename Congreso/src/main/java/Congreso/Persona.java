package Congreso;

import Congreso.excepciones.InvalidCorreoException;
import Congreso.excepciones.InvalidEdadException;
import Congreso.excepciones.InvalidFonoException;
import Congreso.excepciones.InvalidNombreException;

public class Persona {
    private String nombre;
    private int edad;
    private long fono;
    private String correo;
    
    /** Constructor principal, inicializa
     * Persona con todos sus atributos.
     * @param nombre
     * @param edad
     * @param fono
     * @param correo
     * @throws InvalidNombreException
     * @throws InvalidEdadException
     * @throws InvalidFonoException
     * @throws InvalidCorreoException
     */
    public Persona(String nombre, int edad, long fono, String correo) 
    		throws InvalidNombreException, InvalidEdadException, InvalidFonoException, InvalidCorreoException {
    	setNombre(nombre);
    	setEdad(edad);
    	setFono(fono);
    	setCorreo(correo);
    }
    
    /** Constructor especial para subclase Expositor,
     * inicializa Persona con nombre, fono y correo.
     * @param nombre
     * @param fono
     * @param correo
     * @throws InvalidNombreException
     * @throws InvalidFonoException
     * @throws InvalidCorreoException
     */
    protected Persona(String nombre, long fono, String correo) 
    		throws InvalidNombreException, InvalidFonoException, InvalidCorreoException {
    	setNombre(nombre);
    	setFono(fono);
    	setCorreo(correo);
    }
    
    /**
     * Establece el nombre de la persona validando
     * que no contenga caracteres especiales.
     * @param nombre
     * @throws InvalidNombreException
     */
    public void setNombre(String nombre) throws InvalidNombreException {
    	if(!Util.isAlphaOrSpace(nombre))
    		throw new InvalidNombreException(nombre);
    	
    	this.nombre = nombre;
    }
    
    /**
     * Establece la edad de la persona validando
     * que esté en el rango establecido.
     * @param edad
     * @throws InvalidEdadException
     */
    public void setEdad(int edad) throws InvalidEdadException {
    	if(edad > 100 || edad < 1)
    		throw new InvalidEdadException(edad, 1, 100);
    	
    	this.edad = edad;
    }
    
    /**
     * Establece el teléfono de la persona validando
     * que la cantidad de dígitos esté en el rango
     * establecido.
     * @param fono
     * @throws InvalidFonoException
     */
    public void setFono(long fono) throws InvalidFonoException {
    	int digitos = Util.countDigits(fono);
    	if(digitos < 8 || digitos > 12)
    		throw new InvalidFonoException(fono);
    	
    	this.fono = fono;
    }
    
    /**
     * Establece el correo de la persona validando
     * que tenga un formato correcto.
     * @param correo
     * @throws InvalidCorreoException
     */
	public void setCorreo(String correo) throws InvalidCorreoException {
		if(!Util.validateEmail(correo))
			throw new InvalidCorreoException(correo);
		
		this.correo = correo;
	}
	
    
    public String getNombre() {
    	return this.nombre;
    }
    
    public int getEdad() {
    	return this.edad;
    }
    
    public long getFono() {
    	return this.fono;
    }
    
	public String getCorreo() {
		return correo;
	}

    @Override 
    public String toString() {
        return this.nombre;
    }

}
