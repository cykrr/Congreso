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

/* @param <T> Tipo de retorno
 */
public class PopUp {

    public interface PopAble {
        void guardar();
        Object getValue();
    }

    private VBox root;
    private Scene dialog;
    private Stage stage;
    private Node child;


    /* @param s Stage padre
     * @param c Hijo raíz del popup
     *
     */
    public PopUp(Stage s, GridPane c) {
        this.child = c;

        stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(s);

        Button b = new Button("Guardar");

        root = new VBox(c, b);
        root.getChildren().addAll();

        dialog = new Scene(root);

        b.setOnAction(e-> {
            if (child instanceof PopAble)
                ((PopAble)child).guardar();
            else 
                throw new RuntimeException("child no implementa la interfaz: Guardar");
            stage.close();
        });

        VBox.setMargin(b, new Insets(0, 0, 15, 15));

    }


    public Object showDialog()  {
        stage.setScene(dialog);
        stage.showAndWait();
        Object retorno;
        if (child instanceof PopAble) {
            retorno = ((PopAble)child).getValue();
        return retorno;
        } else 
            throw new RuntimeException("child no implementa la interfaz: Guardar");
    }
}
