
package Dialog;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class dialog {
    public static void display(String title, String message){
    Stage window = new Stage();
    window.setTitle(title);
    window.setMaxWidth(300);
    window.setMaxHeight(150);
    window.setMinWidth(300);
    window.setMinHeight(150);
    
    Label label = new Label();
    label.setText(message);
    Button buttonOK = new Button("Ok");
    buttonOK.setOnAction(e -> window.close());
    
    VBox layout = new VBox(5);
    layout.getChildren().addAll(label, buttonOK);
    layout.setAlignment(Pos.CENTER);
    
    Scene scene = new Scene(layout);
    window.setScene(scene);
    window.showAndWait();
    
    }
}
