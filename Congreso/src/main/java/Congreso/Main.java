package Congreso;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        Registro r = new Registro();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Menu m = new Menu(r,br);
        
        char c = '\0';
        m.importarPresentaciones(r, "Presentaciones.csv");
        while (c != 's')
        {
            m.mostrar();
            c = m.getChar();
            if(c == 's'){break;}
            switch(c) {
                case 'a':
                    m.crearPresentacion();
                    break;
                case 'e':
                    m.editarPresentacion();
                    break;
                case 'l':
                    m.mostrarPresentaciones();
                    break;
                case 'i':
                    m.importarPresentaciones();
                    break;
                case 'm':
                    m.administrarAsistentes();
                    break;
            }
        }
    }
}
