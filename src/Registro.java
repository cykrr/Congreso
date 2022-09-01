import java.util.HashMap;

/* Almacena toda la información correspondiente al
 * runtime: Mapas para rápido acceso a las presentaciones
 * por distintos atributos. */
public class Registro {

  private HashMap<String, Presentacion> nombre_presentaciones;
  private HashMap<Integer, Presentacion> id_presentaciones;

  /* Cuenta de las presentaciones importadas */
  private int count = 0; 

  Registro() {
    nombre_presentaciones = new HashMap<String, Presentacion>();
    id_presentaciones = new HashMap<Integer, Presentacion>();
  }

  /** Añade una presentación al registro, se encarga de revisar
   * que la presentación a ser añadida no tenga un nombre
   * repetido y de que no choque temporalmente con otra
   * presentación 
   * @param p presentación a ser añadida
   * */
  public void insertarPresentacion(Presentacion p) {
    Presentacion busqueda_nombre, busqueda_id;
    busqueda_nombre = nombre_presentaciones.get(p.getNombre());
    busqueda_id = id_presentaciones.get(p.getId());
    if (busqueda_id != null || busqueda_id != null) {
      
    }

  }

  /* Dado un nombre de archivo abre e infla los contenidos del
   * registro. Se asegura de no importar información
   * inconsistente, como por ejemplo errores de síntax o
   * elementos faltantes. 
   * @param p nombre del archivo a abrir */
  public void importar(String nombre_archivo) {

  }


  /* Dado un nombre de archivo abre y guarda los contenidos del
   * registro. 
   * @param p nombre del archivo a guardar */
  public void exportar(String nombre_archivo) {

  }

}
