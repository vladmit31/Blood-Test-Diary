package seg.major.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import seg.major.model.ContactsModel;

import java.net.URL;
import java.util.ResourceBundle;

public class ContactsController implements Initializable, ViewsController{

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

    /** ---------- Inherited / Implemented ---------- */
    /**
     * Allow javafx to initalise the controller with the view
     */
    public void initialize(URL url, ResourceBundle rb) {
        //model = new AddPatientModel();
    }

    /**
     * Set the primaryController
     *
     * @param primaryController the PrimaryController to set
     */
    public void setScreenParent(PrimaryController primaryController) {
        this.primaryController = primaryController;
    }
    /** ---------- Inherited / Implemented ---------- */

}

