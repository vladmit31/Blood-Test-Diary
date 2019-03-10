package seg.major.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import seg.major.model.ContactsModel;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ContactsController implements Initializable, ControllerInterface {

    @FXML
    private TableColumn forenameColumn;

    @FXML
    private TableColumn surnameColumn;

    @FXML
    private TableColumn relationshipColumn;

    @FXML
    private TableColumn phoneColumn;

    @FXML
    private TableColumn emailColumn;

    private ContactsModel model;
    private PrimaryController primaryController;
    private Map<String, Object> data = new HashMap<>();

    /** ---------- Inherited / Implemented ---------- */
    /**
     * Allow javafx to initalise the controller with the view
     */
    public void initialize(URL url, ResourceBundle rb) {
        // model = new AddPatientModel();
    }

    /**
     * Set the primaryController
     *
     * @param primaryController the PrimaryController to set
     */
    public void setScreenParent(PrimaryController primaryController) {
        this.primaryController = primaryController;
    }

    /**
     * Set the data
     * 
     * @param data the data to set
     */
    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    /**
     * Add data to the given fx-item and update the scene
     * 
     * @param tpAddKey the key of the data
     * @param toAddVal the value of the data
     */
    public void addData(String toAddKey, Object toAddVal) {
        data.put(toAddKey, toAddVal);
        update();
    }

    /**
     * Update the scene with changes from the data HashMap
     */
    public void update() {
    }
    /** ---------- Inherited / Implemented ---------- */

}
