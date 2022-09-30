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
    
    public Persona(String nombre, int edad, long fono, String correo) 
    		throws InvalidNombreException, InvalidEdadException, InvalidFonoException, InvalidCorreoException {
    	setNombre(nombre);
    	setEdad(edad);
    	setFono(fono);
    	setCorreo(correo);
    }
    
    public void setNombre(String nombre) throws InvalidNombreException {
    	if(!Util.isAlphaOrSpace(nombre))
    		throw new InvalidNombreException(nombre);
    	
    	this.nombre = nombre;
    }
    
    public void setEdad(int edad) throws InvalidEdadException {
    	if(edad > 100 || edad < 1)
    		throw new InvalidEdadException(edad);
    	
    	this.edad = edad;
    }
    
    public void setFono(long fono) throws InvalidFonoException {
    	int digitos = Util.countDigits(fono);
    	if(digitos < 8 || digitos > 12)
    		throw new InvalidFonoException(fono);
    	
    	this.fono = fono;
    }
    
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
