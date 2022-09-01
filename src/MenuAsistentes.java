import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.io.InputStreamReader;

public class MenuAsistentes {
    private BufferedReader br;
    private Presentacion p;

    public MenuAsistentes(Presentacion p) {
    	br = new BufferedReader(new InputStreamReader(System.in));
    	this.p = p;
    }

	public void mostrar() {
        System.out.println("Administrando asistentes de la presentación: " + p.getNombre());
        System.out.println("1: Agregar asistente");
        System.out.println("2: Eliminar asistente");
        System.out.println("3: Buscar asistente");
        System.out.println("4: Mostrar asistentes");
        System.out.println("5: Volver al menú principal");
        System.out.println("---");
	}

	public void agregarAsistente() throws IOException {
    	System.out.println("Ingrese nombre del asistente:");
    	String nombre = br.readLine();
    	
    	System.out.println("Ingrese edad del asistente:");
    	int edad = Integer.parseInt(br.readLine());
    	
    	System.out.println("Ingrese teléfono del asistente:");
    	int fono = Integer.parseInt(br.readLine());
    	
    	Persona persona = new Persona(nombre, edad, fono);
    	p.agregarAsistente(persona);
	}

	public void eliminarAsistente() throws IOException {
    	System.out.println("Ingrese nombre del asistente:");
    	String nombre = br.readLine();
    	
    	Persona persona = buscarAsistente(nombre);
    	if(persona == null)
    		System.out.println("Error: no se encontraron asistentes con ese nombre");
    	else
    		p.eliminarAsistente(persona);
	}

	public void buscarAsistente() throws IOException {
    	System.out.println("Ingrese nombre del asistente:");
    	String nombre = br.readLine();
    	
    	Persona persona = buscarAsistente(nombre);
    	if(persona == null)
    		System.out.println("Error: no se encontraron asistentes con ese nombre");
    	else
    		persona.mostrarDatos();
	}
	
	private Persona buscarAsistente(String nombre) {
		LinkedList<Persona> asistentes = p.getAsistentes();
		for(int i = 0; i < asistentes.size(); i++) {
			Persona persona = asistentes.get(i);
			if(nombre.equals(persona.getNombre()))
				return persona;
		}
		return null;
	}

	public void mostrarAsistentes() {
		p.mostrarAsistentes();
        System.out.println("5: Salir");
        System.out.println("---");
	}

}
