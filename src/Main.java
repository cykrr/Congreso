import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Menu m = new Menu();
        char c = '\0';
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
                default:
                    m.flush();
                     // Limpiar entrada estándar
                    continue;
            }
        }
    }
}
