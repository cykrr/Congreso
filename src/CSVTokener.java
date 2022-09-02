import java.util.LinkedList;

public class CSVTokener {
    private String csv; // String del csv a procesar
    private int posicion; // Posición del caracter actual
    private char anterior; // Caracter anterior
    private char sep; // Separador
    private int sepCount;

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

    public char getPrev() {
        return anterior;
    }

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
            }
            else if (c == '\"') {
					out += c;
					openSub = !openSub;
            }
            else
					out += c;
        } while (true);
    }


    public static LinkedList<String> csvArray(CSVTokener t) {
        LinkedList<String> ret = new LinkedList<String>(); 
        String field;
        while (!(field = t.getField()).equals("")) {
            ret.add(field);
        }
        return ret;
    }
}
