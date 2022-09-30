package Congreso;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Congreso.excepciones.NullExpositorException;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;



/* Almacena toda la información correspondiente al
 * runtime. Mapas para rápido acceso a las presentaciones
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

	/** Inserta un expositor en la base de datos
	 *  @param e Expositor a insertar
	 */
    public void insertarExpositor(Expositor e) {
        Expositor busqueda = buscarExpositor(e.getNombre());
        if (busqueda == null) {
            mapaExpositores.put(e.getNombre(), e);
            listaExpositores.add(e);
        }
    }
    
	/** Inserta un asistente en la base de datos
	 *  @param a Asistente a insertar
	 */
    public void insertarAsistente(Persona a) {
        Persona busqueda = buscarAsistente(a.getNombre());
        if (busqueda == null) {
            mapaAsistentes.put(a.getNombre(), a);
            listaAsistentes.add(a);
        }
    }
    
	/** Edita una presentación en la base de datos.
	 *  Elimina la presentación anterior y añade la nueva, transfiriendo los
	 *  asistentes de la anterior a la nueva.
	 * 
	 *  @param p1 Presentación pre-modificación
	 *  @param p2 Presentación post-modificación
	 */
	public void editarPresentacion(Presentacion p1, Presentacion p2) {
		LinkedList<Persona> asistentes = p1.getAsistentes();

		if(p2.getAsistentes().size() > 0) {
			LinkedList<Persona> asistentesAgregar = p2.getAsistentes();
			
			asistentes.addAll(asistentesAgregar);
		}
		
		// TODO : Podemos pasar colecciones
		p2.setAsistentes(asistentes);
		
		eliminarPresentacion(p1);
		insertarPresentacion(p2);
	}
	
	/** Edita un asistente en la base de datos.
	 *  Elimina el asistente anterior y añade el nuevo.
	 * 
	 *  Se encarga de actualizar todas las presentaciones afectadas por la
	 *  modificación
	 *  @param a1 Asistente pre-modificación
	 *  @param a2 Asistente post-modificación
	 */
	public void editarAsistente(Persona a1, Persona a2) {
		mapaAsistentes.remove(a1.getNombre());
		listaAsistentes.remove(a1);
		insertarAsistente(a2);
		
		for(int i = 0; i < listaPresentaciones.size(); i++) {
			LinkedList<Persona> asistentes = listaPresentaciones.get(i).getAsistentes();
			int index = asistentes.indexOf(a1);
			if(index != -1)
				asistentes.set(index, a2);
		}
	}
	
	/** Edita un Expositor en la base de datos.
	 *  Elimina el expositor anterior y añade el nuevo.
	 * 
	 *  Se encarga de actualizar todas las presentaciones afectadas por la
	 *  modificación
	 *  @param a1 Asistente pre-modificación
	 *  @param a2 Asistente post-modificación
	 * @throws NullExpositorException 
	 */
	public void editarExpositor(Expositor e1, Expositor e2) throws NullExpositorException {
		for(int i = 0; i < listaPresentaciones.size(); i++) {
			if(e1 == listaPresentaciones.get(i).getExpositor()) {
				listaPresentaciones.get(i).setExpositor(e2);
				break;
			}
		}
		
		mapaExpositores.remove(e1.getNombre());
		listaExpositores.remove(e1);
		insertarExpositor(e2);
	}
	
	/** Elimina una presentación de la base de datos.
	 * 
	 * @param p Presentación a eliminar.
	 */
	public void eliminarPresentacion(Presentacion p) {
		mapaPresentaciones.remove(p.getNombre());
		listaPresentaciones.remove(p);
	}

	/** Elimina una asistente de la base de datos.
	 *  Se encarga de eliminar el asistente de todas las presentaciones
	 *  involucradas.
	 * 
	 * @param p Asistente a eliminar.
	 */
	public void eliminarAsistente(Persona a) {
		mapaAsistentes.remove(a.getNombre());
		listaAsistentes.remove(a);
		
		for(int i = 0; i < listaPresentaciones.size(); i++)
			listaPresentaciones.get(i).getAsistentes().remove(a);
	}
	
	/** Elimina un expositor de la base de datos.
	 * @param e Expositor a ser eliminado. */
	public void eliminarExpositor(Expositor e) {
		mapaExpositores.remove(e.getNombre());
		listaExpositores.remove(e);
	}
   
	/** Busca una presentación en la base de datos dado su nombre. */
	public Presentacion buscarPresentacion(String nombre) {
        return mapaPresentaciones.get(nombre);
    }
    
	/** Busca un expositor en la base de datos dado su nombre. */
    public Expositor buscarExpositor(String nombre) {
    	return mapaExpositores.get(nombre);
    }
    
	/** Busca un asistente en la base de datos dado su nombre. */
    public Persona buscarAsistente(String nombre) {
    	return mapaAsistentes.get(nombre);
    }
    
    /** Busca si el expositor se encuentra en alguna presentación */
    public boolean expositorEnPresentacion(Expositor e) {
    	for(int i = 0; i < listaPresentaciones.size(); i++) {
    		if(e == listaPresentaciones.get(i).getExpositor())
    			return true;
    	}	
    	return false;
    }
    
	/** Importa archivos dado nombres de los archivos a cargar 
	 * @param csvPresentaciones Archivo del que cargar las presentaciones.
	 * @param csvExpositores Archivo del que cargar los expositores.
	 * @param csvAsistentes Archivo del que cargar los asistentes.
	*/
	public boolean importar(String csvPresentaciones, String csvExpositores, String csvAsistentes) {
		try {
			importarExpositores(csvExpositores);
			importarAsistentes(csvAsistentes);
			importarPresentaciones(csvPresentaciones);
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return true;
	}
    
	/** Importa presentaciones dado un nombre de archivo
	 * @param nombreArchivo nombre del archivo a cargar.
	 */
    private void importarPresentaciones(String nombreArchivo) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
    	
        String line;
		while((line = br.readLine()) != null) {
			line = new String(line.getBytes("ISO-8859-1"), "UTF-8");
		    LinkedList<String> lineArray = CSVTokener.csvArray(new CSVTokener(line));
		    
		    String nombre = lineArray.get(0);
		    Expositor expositor = buscarExpositor(lineArray.get(1));
		    LocalDate fecha = Util.parseDate(lineArray.get(2));
		    LocalTime hora = Util.parseTime(lineArray.get(3));
		    int duracion = Integer.parseInt(lineArray.get(4));
		    String descripcion = lineArray.get(5);
		    
		    Presentacion p = new Presentacion(nombre, expositor, fecha, hora, duracion, descripcion);
		    insertarPresentacion(p);
		    
		    String asistentes = lineArray.get(6);
		    asistentes = asistentes.substring(1, asistentes.length() - 1);
		    LinkedList<String> listaAsistentes = CSVTokener.csvArray(new CSVTokener(asistentes));
		    
		    for(int i = 0; i < listaAsistentes.size(); i++) {
			    Persona asistente = buscarAsistente(listaAsistentes.get(i));
		    	p.agregarAsistente(asistente);
		    }
		}
	        
	    br.close();
    }
    
	/** Importa expositores dado un nombre de archivo
	 * @param nombreArchivo nombre del archivo a cargar.
	 */
    private void importarExpositores(String nombreArchivo) throws Exception {
    	BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
	    	
        String line;
		while((line = br.readLine()) != null) {
			line = new String(line.getBytes("ISO-8859-1"), "UTF-8");
		    LinkedList<String> lineArray = CSVTokener.csvArray(new CSVTokener(line));
		            
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
    }
    
	/** Importa asistentes dado un nombre de archivo
	 * @param nombreArchivo nombre del archivo a cargar.
	 */
    private void importarAsistentes(String nombreArchivo) throws Exception {	
		BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
    	
        String line;
		while((line = br.readLine()) != null) {
			line = new String(line.getBytes("ISO-8859-1"), "UTF-8");
		    LinkedList<String> lineArray = CSVTokener.csvArray(new CSVTokener(line));
		            
		    String nombre = lineArray.get(0);
		    int edad = Integer.parseInt(lineArray.get(1));
		    int fono = Integer.parseInt(lineArray.get(2));
		    String correo = lineArray.get(3);
		    
		    Persona asistente = new Persona(nombre, edad, fono, correo);
		    insertarAsistente(asistente);
		}
	        
	    br.close();
    }

    /* Guarda los contenidos del registro */
    public void exportar(String csvPresentaciones, String csvExpositores, String csvAsistentes) {
		try {
			exportarPresentaciones(csvPresentaciones);
			exportarExpositores(csvExpositores);
			exportarAsistentes(csvAsistentes);
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
    }

	/** Exporta presentaciones dado un nombre de archivo.
	 * @param nombreArchivo nombre del archivo a exportar.
	 */
    private void exportarPresentaciones(String nombreArchivo) throws Exception {
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nombreArchivo), StandardCharsets.UTF_8));
        for(int i = 0; i < listaPresentaciones.size(); i++) {
        	Presentacion p = listaPresentaciones.get(i);
        	bw.write(p.getNombre() + ";");
        	bw.write(p.getExpositor().getNombre() + ";");
        	bw.write(p.getStringFecha() + ";");
        	bw.write(p.getStringHora() + ";");
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
	}

	/** Exporta expositores dado un nombre de archivo.
	 * @param nombreArchivo nombre del archivo a exportar.
	 */
	private void exportarExpositores(String nombreArchivo) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nombreArchivo), StandardCharsets.UTF_8));
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
	}

	/** Exporta asistentes dado un nombre de archivo.
	 * @param nombreArchivo nombre del archivo a exportar.
	 */
	private void exportarAsistentes(String nombreArchivo) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nombreArchivo), StandardCharsets.UTF_8));
        for(int i = 0; i < listaAsistentes.size(); i++) {
        	Persona asistente = listaAsistentes.get(i);
        	bw.write(asistente.getNombre() + ";");
        	bw.write(asistente.getEdad() + ";");
        	bw.write(asistente.getFono() + ";");
        	bw.write(asistente.getCorreo());
        	bw.write("\n");
        }
        
        bw.close();
	}

	// Setters - Getters

    public Iterator<Presentacion> getPresentaciones() {
        return listaPresentaciones.iterator();
    }
    
    public Iterator<Expositor> getExpositores() {
        return listaExpositores.iterator();
    }

    public Iterator<Persona> getAsistentes() {
        return listaAsistentes.iterator();
    }
    
    public Integer getCantidadPresentaciones() {
    	return listaPresentaciones.size();
    }
    
    public Integer getCantidadExpositores() {
    	return listaExpositores.size();
    }
    
    public Integer getCantidadAsistentes() {
    	return listaAsistentes.size();
    }
}

