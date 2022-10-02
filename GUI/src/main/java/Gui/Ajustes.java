package Gui;

import java.io.File;
import java.util.prefs.Preferences;

/* Clase que carga los ajustes del programa
 * por medio de la API de Java. Actualmente
 * se usa para obtener la carpeta donde
 * se almacenará la base de datos. 
 */
public class Ajustes {
    enum AJUSTE {
        CARPETA_CACHE,
    };

    Preferences prefs;

    final static String carpetaStringDefault = "../data";
    String carpeta = null;
    
    /** @brief Constructo de la clase Ajustes.
     * 	
     * 	Comprueba que el path dirija a una carpeta y lo guarda.
     * */
    public Ajustes() {
         prefs = Preferences.userNodeForPackage(getClass());
        carpeta = prefs.get("carpeta_cache", carpetaStringDefault);

        File f = new File(carpeta);

        // Si no hay opción elegida
        if (carpeta.equals(carpetaStringDefault)) {
            // Creamos una carpeta aqui
            f.mkdirs();
        } else if (!f.isDirectory()) {
            System.err.println("Error: La ruta leída corresponde a un archivo");
        } 
    }

    /* Guarda el AJUSTE por medio de la API de Java.
     * El ajuste persiste en ejecuciones.
     */
    public void guardarAjuste(AJUSTE ajuste, String valor) {
        switch (ajuste) {
            case CARPETA_CACHE:
                this.prefs.put("carpeta_cache", valor);
                carpeta = valor;
                break;
            default:
        }
    }
}
