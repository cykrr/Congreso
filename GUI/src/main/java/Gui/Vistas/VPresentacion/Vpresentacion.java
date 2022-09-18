package Gui.Vistas.VPresentacion;

import Congreso.Presentacion;
import javafx.geometry.Insets;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Vpresentacion extends VBox {
    public Vpresentacion(Presentacion p) {
        super();
        Text title = new Text(p.getNombre());
        this.getChildren().add(title);
        title.getStyleClass().add("h1");
        VBox.setMargin(title, new Insets(10));
        this.setWidth(Integer.MAX_VALUE);
        this.setBorder(new Border (
                    new BorderStroke(
                        Color.TRANSPARENT, // UP
                        Color.TRANSPARENT, // RIGHT

                        new Color(         // BOTTOM
                            0x29/0xff,
                            0x29/0xff,
                            0x29/0xff,
                            0xff/0xff),  

                        Color.TRANSPARENT, // LEFT
                        BorderStrokeStyle.NONE, // UP
                        BorderStrokeStyle.NONE, // RIGHT
                        BorderStrokeStyle.SOLID, // BOTTOM
                        BorderStrokeStyle.NONE, // LEFT
                        CornerRadii.EMPTY, // no sé
                        new BorderWidths(1), // Tamaño del borde
                        Insets.EMPTY) // no sé
                    )
                );
        

    }
}
