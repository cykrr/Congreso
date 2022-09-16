package Congreso;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;



/* Almacena toda la información correspondiente al
 * runtime: Mapas para rápido acceso a las presentaciones
 * por distintos atributos. */
public class Registro {

    private Map<String, Presentacion> nombre_presentaciones;
    private Map<Integer, Presentacion> id_presentaciones;
    private Map<String, Persona> nombre_personas;

    private List<Presentacion> lista_presentaciones;
    private List<Expositor> lista_expositores;
    private List<Persona> lista_asistentes;

    /* Cuenta de las presentaciones importadas */
    private int count = 0;

    public Registro() {
        nombre_presentaciones = new HashMap<String, Presentacion>();
        id_presentaciones = new HashMap<Integer, Presentacion>();
        nombre_personas = new HashMap<String, Persona>();
        
        lista_presentaciones = new LinkedList<Presentacion>();
        lista_expositores = new LinkedList<Expositor>();
        lista_asistentes = new LinkedList<Persona>();
    }

    /** Añade una presentación al registro, se encarga de revisar
     * que la presentación a ser añadida no tenga un nombre
     * repetido y de que no choque temporalmente con otra
     * presentación 
     * @param p presentación a ser añadida
     * */
    public void insertarPresentacion(Presentacion p) {
        Presentacion busqueda_nombre;
        busqueda_nombre = nombre_presentaciones.get(p.getNombre());
        if (busqueda_nombre != null) {
            System.err.println("ADVERTENCIA: La presentación ya existe");    
            p.mostrar();
        } else {
            this.nombre_presentaciones.put(p.getNombre(),p);
            this.lista_presentaciones.add(p);
        }

    }

    public void insertarPersona(Persona p) {
        // Persona busqueda_id = id_personas.get(p.getId());
        Persona busqueda_nombre = nombre_personas.get(p.getNombre());
        if (busqueda_nombre == null) {
            this.nombre_personas.put(p.getNombre(), p);
            this.lista_asistentes.add(p);
        }
    }
    
    public Expositor buscarExpositor(String nombre) {
    	for(int i = 0; i < lista_expositores.size(); i++) {
    		Expositor expositor = lista_expositores.get(i);
    		if(expositor.getNombre().equals(nombre))
    			return expositor;
    	}
    	return null;
    }
    
    public Persona buscarAsistente(String nombre) {
    	for(int i = 0; i < lista_asistentes.size(); i++) {
    		Persona persona = lista_asistentes.get(i);
    		if(persona.getNombre().equals(nombre))
    			return persona;
    	}
    	return null;
    }
    
	public void importar(String csvPresentaciones, String csvExpositores, String csvAsistentes) {
		importarExpositores(csvExpositores);
		importarAsistentes(csvAsistentes);
		importarPresentaciones(csvPresentaciones);
	}
    
    private void importarPresentaciones(String nombreArchivo) {
    	BufferedReader br;
    	
    	try {
			br = new BufferedReader(new FileReader(nombreArchivo));
	    	
	        String line;
			while((line = br.readLine()) != null) {
			    LinkedList<String> lineArray = CSVTokener.csvArray(new CSVTokener(line));
			    if (lineArray.size() != 8)
			    	continue; // Cantidad de campos por linea no coincide
			            
			    Presentacion p = new Presentacion();
			    p.setNombre(lineArray.get(0));
			    
			    Expositor expositor = buscarExpositor(lineArray.get(1));
			    if(expositor == null)
			    	continue; // Expositor no existe
			    p.setExpositor(expositor);
			    
			    p.setDia(Integer.parseInt(lineArray.get(2)));
			    p.setMes(Integer.parseInt(lineArray.get(3)));
			    p.setAno(Integer.parseInt(lineArray.get(4)));
			    p.setDuracion(Integer.parseInt(lineArray.get(5)));
			    p.setDescripcion(lineArray.get(6));
			    
			    String asistentes = lineArray.get(7);
			    asistentes = asistentes.substring(1, asistentes.length() - 1);
			    LinkedList<String> listaAsistentes = CSVTokener.csvArray(new CSVTokener(asistentes));
			    
			    for(int i = 0; i < listaAsistentes.size(); i++) {
				    Persona asistente = buscarAsistente(listaAsistentes.get(i));
				    if(asistente == null)
				    	continue; // Asistente no existe
			    	p.agregarAsistente(asistente);
			    }
			    
			    lista_presentaciones.add(p);
			}
		        
		    br.close();
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    
    private void importarExpositores(String nombreArchivo) {
    	BufferedReader br;
    	
    	try {
			br = new BufferedReader(new FileReader(nombreArchivo));
	    	
	        String line;
			while((line = br.readLine()) != null) {
			    LinkedList<String> lineArray = CSVTokener.csvArray(new CSVTokener(line));
			    if (lineArray.size() != 6)
			    	break; // Cantidad de campos por linea no coincide
			            
			    String nombre = lineArray.get(0);
			    int edad = Integer.parseInt(lineArray.get(1));
			    int fono = Integer.parseInt(lineArray.get(2));
			    String correo = lineArray.get(3);
			    String pais = lineArray.get(4);
			    String ocupacion = lineArray.get(5);
			    
			    Expositor expositor = new Expositor(nombre, edad, fono, correo, pais, ocupacion);
			    lista_expositores.add(expositor);
			}
		        
		    br.close();
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    
    private void importarAsistentes(String nombreArchivo) {
    	BufferedReader br;
    	
    	try {
			br = new BufferedReader(new FileReader(nombreArchivo));
	    	
	        String line;
			while((line = br.readLine()) != null) {
			    LinkedList<String> lineArray = CSVTokener.csvArray(new CSVTokener(line));
			    if (lineArray.size() != 4)
			    	break; // Cantidad de campos por linea no coincide
			            
			    String nombre = lineArray.get(0);
			    int edad = Integer.parseInt(lineArray.get(1));
			    int fono = Integer.parseInt(lineArray.get(2));
			    String correo = lineArray.get(3);
			    
			    Persona asistente = new Persona(nombre, edad, fono, correo);
			    lista_asistentes.add(asistente);
			}
		        
		    br.close();
    	} catch(IOException e) {
    		e.printStackTrace();
    	}	
    }

    /* Guarda los contenidos del registro */
    public void exportar() throws IOException {
        FileWriter bw = new FileWriter("..//ArchivoExportar.csv");
        for(int i = 0; i < lista_presentaciones.size(); i++) {
        	Presentacion p = lista_presentaciones.get(i);
        	bw.write(p.getNombre() + ";");
        	bw.write(p.getExpositor().getNombre() + ";");
        	bw.write(p.getDia() + ";");
        	bw.write(p.getMes() + ";");
        	bw.write(p.getAño() + ";");
        	bw.write(p.getDuracion() + ";");
        	bw.write(p.getDescripcion() + ";");
        	bw.write("\"");
        	
        	for(int j = 0; j < p.getAsistentes().size(); j++) {
        		Persona a = p.getAsistentes().get(j);		
        		bw.write(a.getNombre());
        		
        		if(j < p.getAsistentes().size() - 1)
        			bw.write(";");
        	}
        	
        	bw.write("\"\n");
        }
        
        bw.close();
    }

    public Presentacion buscarPresentacion(String nombre) {
        return nombre_presentaciones.get(nombre);
    }

    public void mostrarPresentaciones() throws IOException {
        if (nombre_presentaciones.size() == 0)
        	System.out.println("No se encontraron " + "presentaciones");
        else {
            System.out.println("Mostrando presentaciones:\n---");
            for (Map.Entry<String, Presentacion> p: nombre_presentaciones.entrySet()) {
                p.getValue().mostrar();
            	System.out.print("\n");
            }
        }
        System.out.println("---");
    }

    public List<Persona> getExpositores() {
        return Collections.unmodifiableList(this.lista_expositores);
    }

    public List<Persona> getAsistentes() {
        return getExpositores();
    }

    public List<Presentacion> getPresentaciones() {
        return Collections.unmodifiableList(this.lista_presentaciones);
    }
}

