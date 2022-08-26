public class Presentacion {
    private int id = 0;
    private String nombre = "";
    private String descripcion = "";

    public void mostrar() {
        System.out.println("Nombre: " + nombre);
        System.out.println("ID: " + id);
        System.out.println("Descripción: " + descripcion);
    }
}
