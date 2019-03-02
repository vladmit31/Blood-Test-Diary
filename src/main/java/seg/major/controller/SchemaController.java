package seg.major.controller;

import java.util.ResourceBundle;
import java.net.URL;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class SchemaController implements Initializable {

  public void initialize(URL url, ResourceBundle rb) {
  }

  public AnchorPane getPane() throws Exception {
    AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("views/schema.fxml"));
    return pane;
  }
}
