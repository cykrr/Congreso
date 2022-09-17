package CLI;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import Congreso.Expositor;
import Congreso.Persona;
import Congreso.Presentacion;
import Congreso.Util;

import java.io.IOException;

public class MenuEditar {
    private BufferedReader br;
    private Presentacion p;

    public MenuEditar(Presentacion p) {
    	br = new BufferedReader(new InputStreamReader(System.in));
    	this.p = p;
    }

    public void mostrar() {
        System.out.println("Editando presentación: " + p.getNombre());
        System.out.println("1: Establecer expositor");
        System.out.println("2: Establecer descripcion");
        System.out.println("3: Establecer fecha");
        System.out.println("4: Establecer hora de inicio");
        System.out.println("5: Establecer duración");
        System.out.println("6: Volver al menú principal");
        System.out.println("---");
    }

    public void editarExpositor() throws IOException {
    	System.out.println("Ingrese nombre del expositor:");
    	String nombre = br.readLine();
    	
    	System.out.println("Ingrese edad del expositor:");
    	int edad = Integer.parseInt(br.readLine());
    	
    	System.out.println("Ingrese teléfono del expositor:");
    	int fono = Integer.parseInt(br.readLine());

    	System.out.println("Ingrese correo del expositor:");
    	String correo = br.readLine();
    	
    	Persona persona = new Persona(nombre, edad, fono, correo);

        // TODO : Vamos a seguir usando CLI? Limpiar
    	p.setExpositor((Expositor)persona);
    }
    
    public void editarDescripcion() throws IOException {
    	System.out.println("Ingrese descripción de la presentación:");
    	String descripcion = br.readLine();
    	p.setDescripcion(descripcion);
    }
    
    public void editarFecha() throws IOException {
    	System.out.println("Ingrese fecha de la presentación en formato dd-MM-yyyy:");
    	String fecha = br.readLine();
    	
    	if(Util.validateDate(fecha))
    		p.setFecha(fecha);
    	else
    		System.out.println("La fecha ingresada no es válida");
    }
    
    public void editarHora() throws IOException {
    	System.out.println("Ingrese hora de la presentación en formato HH:mm");
    	String hora = br.readLine();
	
    	if(Util.validateTime(hora))
    		p.setHora(hora);
    	else
    		System.out.println("La hora ingresada no es válida");
    }
    
    public void editarDuracion() throws IOException {
    	System.out.println("Ingrese duración en minutos de la presentación:");
    	int duracion = Integer.parseInt(br.readLine());
    	p.setDuracion(duracion);
    }
}
