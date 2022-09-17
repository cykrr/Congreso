package Congreso;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;



/* Almacena toda la información correspondiente al
 * runtime: Mapas para rápido acceso a las presentaciones
 * por distintos atributos. */
public class Registro {
    private Map<String, Presentacion> mapaPresentaciones;
    private Map<String, Expositor> mapaExpositores;
    private Map<String, Persona> mapaAsistentes;

    private List<Presentacion> listaPresentaciones;
    private List<Expositor> listaExpositores;
    private List<Persona> listaAsistentes;

    public Registro() {
        mapaPresentaciones = new HashMap<String, Presentacion>();
        mapaExpositores = new HashMap<String, Expositor>();
        mapaAsistentes = new HashMap<String, Persona>();
        
        listaPresentaciones = new LinkedList<Presentacion>();
        listaExpositores = new LinkedList<Expositor>();
        listaAsistentes = new LinkedList<Persona>();
    }

    /** Añade una presentación al registro, se encarga de revisar
     * que la presentación a ser añadida no tenga un nombre
     * repetido y de que no choque temporalmente con otra
     * presentación 
     * @param p presentación a ser añadida
     * */
    public void insertarPresentacion(Presentacion p) {
        Presentacion busqueda = buscarPresentacion(p.getNombre());
        if (busqueda == null) {
            mapaPresentaciones.put(p.getNombre(), p);
            listaPresentaciones.add(p);
        }
    }

    public void insertarExpositor(Expositor e) {
        Expositor busqueda = buscarExpositor(e.getNombre());
        if (busqueda == null) {
            mapaExpositores.put(e.getNombre(), e);
            listaExpositores.add(e);
        }
    }
    
    public void insertarAsistente(Persona a) {
        Persona busqueda = buscarAsistente(a.getNombre());
        if (busqueda == null) {
            mapaAsistentes.put(a.getNombre(), a);
            listaAsistentes.add(a);
        }
    }
   
	public Presentacion buscarPresentacion(String nombre) {
        return mapaPresentaciones.get(nombre);
    }
    
    public Expositor buscarExpositor(String nombre) {
    	return mapaExpositores.get(nombre);
    }
    
    public Persona buscarAsistente(String nombre) {
    	return mapaAsistentes.get(nombre);
    }
    
	public void importar(String csvPresentaciones, String csvExpositores, String csvAsistentes) {
		importarExpositores(csvExpositores);
		importarAsistentes(csvAsistentes);
		importarPresentaciones(csvPresentaciones);
	}
    
    private void importarPresentaciones(String nombreArchivo) {
    	try {
			BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
	    	
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
			    
			    insertarPresentacion(p);
			}
		        
		    br.close();
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    
    private void importarExpositores(String nombreArchivo) {
    	try {
			BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
	    	
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
			    insertarExpositor(expositor);
			}
		        
		    br.close();
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    
    private void importarAsistentes(String nombreArchivo) {	
    	try {
			BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
	    	
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
			    insertarAsistente(asistente);
			}
		        
		    br.close();
    	} catch(IOException e) {
    		e.printStackTrace();
    	}	
    }

    /* Guarda los contenidos del registro */
    public void exportar(String csvPresentaciones, String csvExpositores, String csvAsistentes) {
    	exportarPresentaciones(csvPresentaciones);
    	exportarExpositores(csvExpositores);
    	exportarAsistentes(csvAsistentes);
    }

    private void exportarPresentaciones(String nombreArchivo) {
    	try {
	        BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo));
	        for(int i = 0; i < listaPresentaciones.size(); i++) {
	        	Presentacion p = listaPresentaciones.get(i);
	        	bw.write(p.getNombre() + ";");
	        	bw.write(p.getExpositor().getNombre() + ";");
	        	bw.write(p.getDia() + ";");
	        	bw.write(p.getMes() + ";");
	        	bw.write(p.getAño() + ";");
	        	bw.write(p.getDuracion() + ";");
	        	bw.write(p.getDescripcion() + ";");
	        	bw.write("\"");
	        	
	        	for(int j = 0; j < p.getAsistentes().size(); j++) {
	        		Persona asistente = p.getAsistentes().get(j);		
	        		bw.write(asistente.getNombre());
	        		
	        		if(j < p.getAsistentes().size() - 1)
	        			bw.write(";");
	        	}
	        	
	        	bw.write("\"\n");
	        }
	        
	        bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void exportarExpositores(String nombreArchivo) {
    	try {
    		BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo));
	        for(int i = 0; i < listaExpositores.size(); i++) {
	        	Expositor expositor = listaExpositores.get(i);
	        	bw.write(expositor.getNombre() + ";");
	        	bw.write(expositor.getEdad() + ";");
	        	bw.write(expositor.getFono() + ";");
	        	bw.write(expositor.getCorreo() + ";");
	        	bw.write(expositor.getPais() + ";");
	        	bw.write(expositor.getOcupacion());
	        	bw.write("\n");
	        }
	        
	        bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void exportarAsistentes(String nombreArchivo) {
    	try {
    		BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo));
	        for(int i = 0; i < listaAsistentes.size(); i++) {
	        	Persona asistente = listaAsistentes.get(i);
	        	bw.write(asistente.getNombre() + ";");
	        	bw.write(asistente.getEdad() + ";");
	        	bw.write(asistente.getFono() + ";");
	        	bw.write(asistente.getCorreo());
	        	bw.write("\n");
	        }
	        
	        bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    public void mostrarPresentaciones() throws IOException {
        if (mapaPresentaciones.size() == 0)
        	System.out.println("No se encontraron " + "presentaciones");
        else {
            System.out.println("Mostrando presentaciones:\n---");
            for (Map.Entry<String, Presentacion> p: mapaPresentaciones.entrySet()) {
                p.getValue().mostrar();
            	System.out.print("\n");
            }
        }
        System.out.println("---");
    }

    public List<Expositor> getExpositores() {
        return Collections.unmodifiableList(listaExpositores);
    }

    public List<Persona> getAsistentes() {
        return Collections.unmodifiableList(listaAsistentes);
    }

    public List<Presentacion> getPresentaciones() {
        return Collections.unmodifiableList(listaPresentaciones);
    }
}

