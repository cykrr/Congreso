import java.io.BufferedReader;
import java.io.IOException;

public class Menu {
	private Registro r;
    private BufferedReader br;

    public Menu(Registro r, BufferedReader br) {
        this.r = r;
        this.br = br;
    }

    public String getLine() throws IOException {
        return br.readLine();
    }

    public char getChar() throws IOException {
        String in = br.readLine();
        if (in.length() != 1) {
            return '\0';
        }
        return in.charAt(0);
    }

    public void mostrar() {
        System.out.println("Presentaciones del congreso internacional:");
        System.out.println("a: Crear presentación");
        System.out.println("e: Editar presentación");
        // System.out.println("x: eliminar presentación");
        System.out.println("l: Listar presentaciones");
        // System.out.println("b: buscar presentación");
        System.out.println("i: Importar presentaciones");
        System.out.println("m: Administrar asistentes");
        System.out.println("s: Salir");
        System.out.print("--- ");
    }

    public void crearPresentacion() throws IOException {
    	String nombre = "";
        System.out.println("Ingrese el nombre de la presentación:");
        
        while(nombre.equals("")) 
        	nombre = getLine();
        
        crearPresentacion(nombre);
        System.out.println("---");
    }
    
    public void crearPresentacion(String nombre) {
        Presentacion p  = new Presentacion(nombre);
        r.insertarPresentacion(p);
    }
    
    public void editarPresentacion() throws IOException {
    	String nombre = "";
        System.out.println("Ingrese el nombre de la presentación a editar:");
        
        while(nombre.equals("")) 
        	nombre = getLine();
        
        Presentacion p = r.buscarPresentacion(nombre);
        if(p == null) {
        	System.out.println("Error: Presentación no encontrada");
          char c;
          do 
            System.out.println("Desea ver las presentaciones disponibles? (S/n)");
          while ( !((c = getChar()) != 0 ||
                     c != '\n' ||
                     c != 's'  ||
                     c != 'S'  ||
                     c != 'n'  ||
                     c != 'N')
              );

          switch(c) { 
            case 'S':
            case 's':
            case '\n':
              mostrarPresentaciones();
              editarPresentacion();
          }
        	return;
        }
        
        MenuEditar submenu = new MenuEditar(p);
        
        char c = '\0';
        
        while (c != '6') {
        	submenu.mostrar();
            c = getChar();
            
            switch(c) {
            case '1':
                submenu.editarExpositor();
                break;
            case '2':
                submenu.editarDescripcion();
                break;
            case '3':
                submenu.editarFecha();
                break;
            case '4':
                submenu.editarHora();
                break;
            case '5':
                submenu.editarDuracion();
                break;
            }
        }
    }

    public void administrarAsistentes() throws IOException {
    	String nombre = "";
        System.out.println("Ingrese el nombre de la presentación:");

        while(nombre.equals("")) 
        	nombre = getLine();

        Presentacion p = r.buscarPresentacion(nombre);
        
        if(p == null) {
        	System.out.println("Error: Presentación no encontrada");
          char c;
          do 
            System.out.println("Desea ver las presentaciones disponibles? (S/n)");
          while ( !((c = getChar()) != 0 ||
                     c != '\n' ||
                     c != 's'  ||
                     c != 'S'  ||
                     c != 'n'  ||
                     c != 'N')
              );

          switch(c) { 
            case 'S':
            case 's':
            case '\n':
              mostrarPresentaciones();
              administrarAsistentes();
          }
        	return;
        }

        MenuAsistentes submenu = new MenuAsistentes(p);

        char c = '\0';
        
        while (c != '5') {
        	submenu.mostrar();
            c = getChar();

            switch(c) {
            case '1':
                submenu.agregarAsistente();
                break;
            case '2':
                submenu.eliminarAsistente();
                break;
            case '3':
                submenu.buscarAsistente();
                break;
            case '4':
                submenu.mostrarAsistentes();
                break;
            }
        }
    }

    public void mostrarPresentaciones() throws IOException {
        r.mostrarPresentaciones();
    }
    
    public void buscarPorNombre(Registro r) throws IOException {
        String in = "";
        while (in.equals("")) {
            System.out.println("Ingrese el nombre a buscar:");
            in = getLine();
        }
        // Presentacion busqueda_presentacion = r.buscarPresentacion(in);
        System.out.println("---");
    }

    public void buscarPorId(Registro r) throws IOException {
        String in = "";
        while (in.equals("")) {
            System.out.println("Ingrese el ID a buscar:");
            in = getLine();
        }
        System.out.println("---");
    }

    public void buscarPorFecha (Registro r) throws IOException {
        String in = "";
        while (in.equals("")) {
            System.out.println("Ingrese la fecha a buscar:");
            in = getLine();
        }
        System.out.println("---");
    }

    public void buscarPresentacion(Registro r) throws IOException {
        char n = '\0';
        while (n != 'n' && n != 'i' && n != 'f') {
            System.out.println("n: Buscar por nombre");
            System.out.println("i: Buscar por ID");
            System.out.println("f: Buscar por fecha");
            System.out.println("Seleccione una opción:");
            n = getChar();
        }
        switch (n) {
            case 'n':
                buscarPorNombre(r);
                break;
            case 'i':
                buscarPorId(r);
                break;
            case 'f':
                buscarPorFecha(r);
                break;
        }
        
    }

	public void importarPresentaciones() throws IOException{
        System.out.println("Ingrese el nombre del archivo a cargar:");	
        String nombrePresentacion = "";
        while ((nombrePresentacion = getLine()).equals(""));
        r.importar(nombrePresentacion);
	}
	
	public void importarPresentaciones(Registro r, String nombrePresentacion) throws IOException{
        r.importar(nombrePresentacion);
	}
}
