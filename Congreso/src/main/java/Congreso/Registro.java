package Congreso;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;



/* Almacena toda la información correspondiente al
 * runtime: Mapas para rápido acceso a las presentaciones
 * por distintos atributos. */
public class Registro {

    private HashMap<String, Presentacion> nombre_presentaciones;
    private HashMap<Integer, Presentacion> id_presentaciones;
    private HashMap<String, Persona> personas;

    /* Cuenta de las presentaciones importadas */
    private int count = 0;

    public Registro() {
        nombre_presentaciones = new HashMap<String, Presentacion>();
        id_presentaciones = new HashMap<Integer, Presentacion>();
        personas = new HashMap<String, Persona>();
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
        }

    }

    public void insertarPersona(Persona p) {
        // Persona busqueda_id = id_personas.get(p.getId());
        Persona busqueda_nombre = personas.get(p.getNombre());
        if (busqueda_nombre == null) {
            this.personas.put(p.getNombre(), p);
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
                        p.setAsistentes(s, personas);
                        break;
                }
                i++;
            }
                p.setExpositor(presentador);
                this.insertarPresentacion(p);
        }
        file.close();
    }

    /* Dado un nombre de archivo abre y guarda los contenidos del
     * registro. 
     * @param p nombre del archivo a guardar */
    public void exportar(String nombre_archivo) {
        //TODO interfaz toCSV()
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
}

