package Gui;

import java.util.prefs.Preferences;

public class Ajustes {
    enum AJUSTE {
        CARPETA_CACHE,
    };

    Preferences prefs;

    final static String carpetaString = "carpeta_cache";
    String carpeta = null;

    public Ajustes() {
         prefs = Preferences.userNodeForPackage(getClass());
        carpeta = prefs.get(carpetaString, "./cache/");
    }

    public void guardarAjuste(AJUSTE ajuste, String valor) {
        switch (ajuste) {
            case CARPETA_CACHE:
                this.prefs.put(carpetaString, valor);
                carpeta = valor;
                break;
            default:
        }
    }
}
