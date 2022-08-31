import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Menu m = new Menu();
        char c = '\0';
        while (c != 'q')
        {
            m.mostrar();
            c = m.getChar();
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
            }
        }
    }
}
