package Congreso;
import java.util.LinkedList;

public class CSVTokener {
    private String csv; // String del csv a procesar
    private int posicion; // Posición del caracter actual
    private char anterior; // Caracter anterior
    private char sep; // Separador
    private int sepCount;
    
    /*
     * Constructores que inicializan los
     * atributos necesario para empezar
     * a manipular un String con los metodos
     * locales de la clase. 
     * */
    CSVTokener(String csv) {
        this.csv = csv;
        this.posicion = -1;
        this.sep = ';';
    }
    CSVTokener(String csv, char sep) {
        this.csv = csv;
        this.posicion = -1;
        this.sep = sep;
    } 
    
    /*
     * Retorna un char que corresponde al
     * caracter anterior de la poscion
     * donde nos encontramos ubicados en
     * un string.
     * */
    public char getPrev() {
        return anterior;
    }
    
    /*
     * Retorna un char que corresponde al
     * caracter siguiente de la poscion
     * donde nos encontramos ubicados en
     * un string. Tambien modifica el 
     * atributo posicion para ubicarnos
     * y el atributo anterior
     * con el char que corresponde al 
     * anterior a la nueva posicion
     * */
    public char getNext() {

        if (posicion < csv.length() - 1) {
            if (posicion != -1) {
                anterior = csv.charAt(posicion);
            }
            posicion++;
        return csv.charAt(posicion);
        } else {
            return '\0';
        } 
    }
    
    /*
     * Se emcarga de regorer el atributo String
     * de la clase caracter por caracte, guardando
     * cada caracter en una String a retonrar, deja de 
     * guardar caracter y retorna la String al encontrarse
     * con un separado o con un salto de linea.
     * Ademas de guardar todo lo que este dentro de corchetes
     * en el String antes de retornarlo en caso de identificar
     * corchetes en el atributo String csv. 
     * */
    public String getField() {
        char c; String out = "";
        boolean openSub = false;
        do {
        	c = getNext();
//            System.out.print(c);

            if (c == '\0' || c == '\n'){
						return out;

            }
            else if (c == sep) {

                sepCount++;
                if (!openSub && sepCount != 0)
                    return out;
                else {
                    out += sep;
                }
            }
            else if (c == '\"') {
					out += c;
					openSub = !openSub;
            }
            else
					out += c;
        } while (true);
    }

    /* *
     * Retorna una lista enlazada de String, que corresponden
     * a cada String del atributo csv separados en posiciones
     * de acuerdo a un separador proporcionado.
     * */
    public static LinkedList<String> csvArray(CSVTokener t) {
        LinkedList<String> ret = new LinkedList<String>(); 
        String field;
        while (!(field = t.getField()).equals("")) {
            ret.add(field);
        }
        return ret;
    }
}
