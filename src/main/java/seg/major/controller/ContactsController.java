package seg.major.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import seg.major.App;
import seg.major.model.ContactsModel;
import seg.major.structure.Contact;
import seg.major.structure.Patient;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ResourceBundle;

public class ContactsController implements Initializable, ControllerInterface {

    private Contact toBeDeleted = null;

    @FXML
    public TextField forenameField;

    @FXML
    public TextField surnameField;

    @FXML
    public TextField relationshipField;

    @FXML
    public TextField phoneField;

    @FXML
    public TextField emailField;

    @FXML
    public Button addButton;

    @FXML
    public Button deleteButton;

    @FXML
    public Button backButton;

    @FXML
    public TableView<Contact> contactsTable;

    @FXML
    private TableColumn<Contact, String> forenameColumn;

    @FXML
    private TableColumn<Contact, String> surnameColumn;

    @FXML
    private TableColumn<Contact, String> relationshipColumn;

    @FXML
    private TableColumn<Contact, String> phoneColumn;

    @FXML
    private TableColumn<Contact, String> emailColumn;

    private PrimaryController primaryController;
    private Map<String, Object> data = new HashMap<>();
    private Patient curPatient;

    /** ---------- Inherited / Implemented ---------- */
    /**
     * Allow javafx to initalise the controller with the view
     */
    public void initialize(URL url, ResourceBundle rb) {

    }

    private void setupTable() {
        setupColumns();
        setUpRows();
        System.out.println("!!!!" + ((Patient) data.get("patient")).getID());
        Patient p = (Patient) data.get("patient");
        if (p != null) {
            fillTable(ContactsModel.getContactList(p.getID()));
            this.curPatient = p;
        }
    }

    private void setupColumns() {
        contactsTable.getItems().clear();
        forenameColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("forename"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("surname"));
        relationshipColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("relationship"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("email"));
    }

    private void setUpRows() {
        contactsTable.setRowFactory(t -> {
            TableRow<Contact> row = new TableRow<Contact>();
            row.setOnMouseClicked(click -> {
                if (!row.isEmpty() && click.getButton() == MouseButton.PRIMARY) {
                    toBeDeleted = row.getItem();
                }
            });
            return row;
        });
    }

    private void fillTable(List<Contact> contactList) {
        System.out.println(contactList.size() + "adsddsadsa");
        contactsTable.getItems().clear();
        for (Contact contact : contactList) {
            contactsTable.getItems().add(contact);
            // System.out.println(contact.toString());
        }
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
        setupTable();
    }

    /** ---------- Inherited / Implemented ---------- */

    private void emptyAddFields() {
        this.forenameField.setText("");
        this.surnameField.setText("");
        this.relationshipField.setText("");
        this.phoneField.setText("");
        this.emailField.setText("");
    }

    @FXML
    public void addButtonClicked(ActionEvent event) {
        if (checkUserInput()) {

            ContactsModel.addContact(curPatient.getID(), forenameField.getText(), surnameField.getText(),
                    relationshipField.getText(), phoneField.getText(), emailField.getText());
            fillTable(ContactsModel.getContactList(curPatient.getID()));
            emptyAddFields();

        }
    }

    private boolean checkUserInput() {
        return !(forenameField.getText().equals("") || surnameField.getText().equals("")
                || relationshipField.getText().equals("") || phoneField.getText().equals("")
                || emailField.getText().equals(""));
    }

    @FXML
    public void deleteButtonClicked(ActionEvent event) {
        if (toBeDeleted != null) {
            ContactsModel.deleteContact(toBeDeleted);
            fillTable(ContactsModel.getContactList(curPatient.getID()));
        }
    }

    @FXML
    public void backButtonClicked(ActionEvent event) {
        primaryController.setPane(App.updatePatient);
    }
}
