import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Menu {
    static private BufferedReader br;
    static private LinkedList<Presentacion> presentaciones;

    Menu() {
        br = new BufferedReader(new InputStreamReader(System.in));
        presentaciones = new LinkedList<Presentacion>();
    }

    public String getLine() throws IOException {
        return br.readLine();
    }
    public int getInt() throws IOException {
        return Integer.parseInt(getLine());
    }
    public char getChar() throws IOException {
        return getLine().charAt(0);
    }

    public void mostrar() {
        System.out.println("Presentaciones del congreso internacional:");
        System.out.println("a: crear presentación");
        System.out.println("x: eliminar presentación");
        System.out.println("l: listar presentaciones");
        System.out.println("---");
    }

    public void crearPresentacion() throws IOException {
        System.out.println("Ingrese el nombre de la presentación:");
        String nombre = "";
        while (nombre.equals("")) nombre = getLine();
    }

    public void mostrarPresentaciones() throws IOException {
        for (Presentacion p: presentaciones)
            p.mostrar();
    }
}
