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

    private List<Persona> lista_personas;
    private List<Presentacion> lista_presentaciones;

    /* Cuenta de las presentaciones importadas */
    private int count = 0;

    public Registro() {
        nombre_presentaciones = new HashMap<String, Presentacion>();
        id_presentaciones = new HashMap<Integer, Presentacion>();
        nombre_personas = new HashMap<String, Persona>();
        lista_personas = new LinkedList<Persona>();
        lista_presentaciones = new LinkedList<Presentacion>();
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
            this.lista_personas.add(p);
        }
    }

    /* Dado un nombre de archivo abre e infla los contenidos del
     * registro. Se asegura de no importar información
     * inconsistente, como por ejemplo errores de síntax o
     * elementos faltantes. 
     * @param p nombre del archivo a abrir */
    public void importar(String nombre_archivo) throws FileNotFoundException, IOException {
        BufferedReader file = null;
        try {
        		file = new BufferedReader (
                new FileReader(nombre_archivo)
                );
        }catch(FileNotFoundException f) {
                System.err.println("Error: el archivo no existe");
                return;
        }

        String line;
        while ( (line = file.readLine()) != null) {
            // TODO No debería ser  una lista enlazada
            LinkedList<String> lineArray = CSVTokener.csvArray(new CSVTokener(line));
            if (lineArray.size() != 9) {
                System.err.println("Error: Se esperaba una linea con " +
                        "9 campos. Recibidos: " +lineArray.size());
                System.out.println(lineArray.getFirst());
                System.exit(1);
            }

// TODO constructor ()
            Presentacion p = null;
            Persona presentador = null;
            int i = 0;
            for (String s: lineArray)  {
                switch (i) {
                    case 0:
                        p = new Presentacion(s);
                        break;
                    case 1:
                        presentador = new Persona(s, 0, 0);
                        break;
                    case 2:
                        presentador.setEdad(Integer.parseInt(s));
                        break;
                    case 3:
                        p.setDia(Integer.parseInt(s));
                        break;
                    case 4:
                        p.setMes(Integer.parseInt(s));
                        break;
                    case 5:
                        p.setAno(Integer.parseInt(s));
                        break;
                    case 6:
                        p.setDuracion(Integer.parseInt(s));
                        break;
                    case 7:
                        p.setDescripcion(s);
                        break;
                    case 8:
                        // TODO : Pasar mapa inmodificable
                        p.setAsistentes(s, Collections.unmodifiableMap(nombre_personas));
                        break;
                }
                i++;
            }
                p.setExpositor(presentador);
                if (!presentador.getNombre().equals("")) {
                    this.insertarPersona(presentador);
                }
                this.insertarPresentacion(p);
        }
        file.close();
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

    // TODO : Diferenciar asistentes y expositores
    public List<Persona> getExpositores() {
        return Collections.unmodifiableList(this.lista_personas);
    }

    // TODO : STUB
    public List<Persona> getAsistentes() {
        return getExpositores();
    }

    public List<Presentacion> getPresentaciones() {
        return Collections.unmodifiableList(this.lista_presentaciones);
    }
}

