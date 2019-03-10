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
import java.util.List;
import java.util.ResourceBundle;

public class ContactsController implements Initializable, ViewsController{


    private Object data;

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
    public TableView contactsTable;

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

    private ContactsModel contactsModel;
    private PrimaryController primaryController;

    /** ---------- Inherited / Implemented ---------- */
    /**
     * Allow javafx to initalise the controller with the view
     */
    public void initialize(URL url, ResourceBundle rb) {

    }

    private void setupTable() {
        setupColumns();
        setUpRows();
        fillTable(contactsModel.getContactList());
    }

    private void setupColumns() {
        forenameColumn.setCellValueFactory(new PropertyValueFactory("forename"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory("surname"));
        relationshipColumn.setCellValueFactory(new PropertyValueFactory("relationship"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory("email"));
    }

    private void setUpRows(){
        contactsTable.setRowFactory(t -> {
            TableRow<Contact> row = new TableRow<>();
            row.setOnMouseClicked(click -> {
                if (!row.isEmpty() && click.getButton() == MouseButton.PRIMARY) {
                    toBeDeleted = row.getItem();
                }
            });
            return row;
        });
    }

    private void fillTable(List<Contact> contactList){
        contactsTable.getItems().clear();
        for(Contact contact : contactList){
            contactsTable.getItems().add(contact);
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
    /** ---------- Inherited / Implemented ---------- */

    public void setData(Object o) {
        data = o;
        Patient patient = (Patient) data;
        System.out.println("!!!!" + patient.getForename() + " " + patient.getSurname());
        contactsModel = new ContactsModel(patient);

        setupTable();

        fillTable(contactsModel.getContactList());
    }

    private void emptyAddFields(){
        this.forenameField.setText("");
        this.surnameField.setText("");
        this.relationshipField.setText("");
        this.phoneField.setText("");
        this.emailField.setText("");
    }

    public void addButtonClicked(ActionEvent event) {
        if(checkUserInput()){
            Patient patient = (Patient) data;
            contactsModel.addContact(new Contact(patient.getId(),forenameField.getText(),
                    surnameField.getText(), relationshipField.getText(), phoneField.getText(),
                    emailField.getText()));
            fillTable(this.contactsModel.getContactList());
            emptyAddFields();
        }
    }

    private boolean checkUserInput() {
        return !(forenameField.getText().equals("") || surnameField.getText().equals("") ||
                relationshipField.getText().equals("") || phoneField.getText().equals("") ||
                emailField.getText().equals(""));
    }

    public void deleteButtonClicked(ActionEvent event) {
        if(toBeDeleted != null){
            contactsModel.deleteContact(toBeDeleted);
            fillTable(contactsModel.getContactList());
        }
    }

    public void backButtonClicked(ActionEvent event) {
        primaryController.setPane(App.updatePatient);
    }
}

