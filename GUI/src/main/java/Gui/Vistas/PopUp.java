package Gui.Vistas;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/* Clase para mostrar una vista como PopUp
 * Limitada a la entrada de datos.
 */
public class PopUp {

    /* Funciones que deben implementas
     * las vistas para leer datos */
    public interface PopAble {
        /* Guarda los campos en 
         * la variable de retorno */
        boolean guardar();
        /* Devuelve el retorno */
        Object getValue();

        /* El usuario deberá Castear Object 
         * a su Tipo de dato esperado*/
    }

    private VBox root;
    private Scene dialog;
    private Stage stage;
    private Node child;


    /* Constructor de la Clase PopUp
     *
     * @param s Stage padre
     * @param c Hijo raíz del popup
     *
     * Prepara el stage (ventana) y
     * la escena con un botón 
     * para guardar la información
     * y cerrar la ventana
     */
    public PopUp(Stage s, GridPane c) {
        this.child = c;

        stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(s);
        stage.setResizable(false);

        Button b = new Button("Guardar");

        root = new VBox(c, b);
        root.getChildren().addAll();

        dialog = new Scene(root);

        b.setOnAction(e-> {
            if (child instanceof PopAble) {
                boolean valid = ((PopAble)child).guardar();
                if(valid)
                	stage.close();
            } else {
            	stage.close();
                throw new RuntimeException("child no implementa la interfaz: PopAble");
            }
        });

        VBox.setMargin(b, new Insets(0, 0, 15, 15));

    }


    /* Muestra el díalogo hasta que se 
     * presione el boton de guardar
     * En dicho momento se retornará
     * la referencia al objeto creado
     */
    public Object showDialog()  {
        stage.setScene(dialog);
        stage.sizeToScene();
        stage.showAndWait();
        Object retorno;
        if (child instanceof PopAble) {
            retorno = ((PopAble)child).getValue();
        return retorno;
        } else 
            throw new RuntimeException("child no implementa la interfaz: PopAble");
    }
    
    public void setTitle(String title) {
    	stage.setTitle(title);
    }
}
