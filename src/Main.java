import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Registro r = new Registro();
        Menu m = new Menu();
        char c = '\0';
        while (c != 's')
        {
            m.mostrar();
            c = m.getChar();
            if(c == 's'){break;}
            switch(c) {
                case 'a':
                    m.crearPresentacion(r);
                    break;
                case 'e':
                    m.editarPresentacion(r);
                    break;
                case 'l':
                    m.mostrarPresentaciones(r);
                    break;
                case 'i':
                    m.importarPresentaciones(r);
                    break;
                default:
                    //m.flush(); Lo comente porque me fallaba
                     // Limpiar entrada estándar
                    continue;
            }
        }
    }
}
