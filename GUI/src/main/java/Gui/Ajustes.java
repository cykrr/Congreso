package Gui;

import java.io.File;
import java.util.prefs.Preferences;

public class Ajustes {
    enum AJUSTE {
        CARPETA_CACHE,
    };

    Preferences prefs;

    final static String carpetaStringDefault = "./cache/";
    String carpeta = null;

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
