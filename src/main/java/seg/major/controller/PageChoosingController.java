package seg.major.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class PageChoosingController {
    public Button SchemaButton;
    public Button PatientButton;
    //private PageChoosingModel model;

    @FXML
    private void handleSchemaButton() throws Exception {
        getPane("views/schema.fxml");
    }

    @FXML
    private void handlePatientButton() throws Exception {
        getPane("views/patient.fxml");
    }

    private AnchorPane getPane(String url) throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource(url));
        return pane;
    }
}
