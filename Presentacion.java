import java.time.LocalTime;

public class Presentacion {
    private int id = 0;
    private String nombre = "";
    private String descripcion = "";
    private LocalTime hora = null;

    public Presentacion() {
        hora = LocalTime.now();
    }

    public Presentacion(String nombre) {
        if (nombre.equals("")) 
            System.out.println("ADVERTENCIA: Creando presentación sin nombre");

        this.nombre = nombre;
    }

    public Presentacion(String nombre, int id) {
        if (nombre.equals("")) 
            System.out.println("ADVERTENCIA: Creando presentación sin nombre");

        this.nombre = nombre;
        this.id = id;
    }

    public void mostrar() {
        System.out.println("Nombre: " + nombre);
        System.out.println("ID: " + id);
        System.out.println("Descripción: " +((descripcion.length() > 0) ?  descripcion : "No hay descripcion"));
    }
}
