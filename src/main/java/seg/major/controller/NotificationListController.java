package seg.major.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import seg.major.App;
import seg.major.controller.util.EmailSender;
import seg.major.model.EditNotificationEmailModel;
import seg.major.model.NotificationListModel;
import seg.major.model.database.AppointmentDAO;
import seg.major.model.database.ContactDAO;
import seg.major.model.database.PatientDAO;
import seg.major.model.util.EmailBuilder;
import seg.major.structure.Appointment;
import seg.major.structure.Contact;
import seg.major.structure.Patient;
import seg.major.structure.PatientEntry;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import java.net.URL;
import java.time.LocalDate;
import java.util.stream.Collectors;
/**
 * AddPatientController acts as the controller for the notifyList.fxml file
 * @author Team Pacane
 * @version 1.0
 */
public class NotificationListController implements Initializable, ControllerInterface {

    private PrimaryController primaryController;
    private Map<String, Object> data = new HashMap<>();
    private NotificationListModel notificationListModel;
    private List<PatientEntry> toBeNotified = null;

    @FXML
    public Button notifyButton;
    @FXML
    public TableColumn<PatientEntry, String> forenameColumn;
    @FXML
    public TableColumn<PatientEntry, String> hospitalNumberColumn;
    @FXML
    public TableColumn<PatientEntry, String> diagnosisColumn;
    @FXML
    public TableColumn<PatientEntry, String> surnameColumn;
    @FXML
    public TableColumn<PatientEntry, String> dueDateColumn;
    @FXML
    public TableColumn<PatientEntry, String> lastNotifiedColumn;
    @FXML
    public Button backButton;
    @FXML
    public TableView<PatientEntry> notificationTable;
    @FXML
    public Button notifyAllButton;
    @FXML
    public Button editDefaultEmail;

    /**
     * Allow javafx to initalise the controller with the view
     */
    public void initialize(URL url, ResourceBundle rb) {
        this.notificationListModel = new NotificationListModel();

        setupTable();
    }

    private void setupTable() {
        notificationTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        setupColumns();
        setupRows();
        fillTable(this.notificationListModel.getCarriedOverAppointmentEntries());
        notificationTable.setPlaceholder(new Label("No patients found"));
    }

    private void setupRows() {
        notificationTable.setRowFactory(t -> {
            TableRow<PatientEntry> row = new TableRow<PatientEntry>();
            row.setOnMouseClicked(click -> {
                if (!row.isEmpty() && click.getButton() == MouseButton.PRIMARY && click.getClickCount() == 2) {
                    Patient patient = NotificationListModel.getPatientByID(row.getItem().getPatientID());
                    List<Contact> listOfContacts = NotificationListModel.getAllContacts().stream()
                            .filter(contact -> contact.getPatientID() == patient.getID()).collect(Collectors.toList());
                    primaryController.sendTo(App.customEmail, "contacts_list", listOfContacts);
                    primaryController.setPane(App.customEmail);
                }
            });
            return row;
        });
    }

    private void setupColumns() {
        forenameColumn.setCellValueFactory(new PropertyValueFactory<>("forename"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        hospitalNumberColumn.setCellValueFactory(new PropertyValueFactory<>("hospitalNumber"));
        diagnosisColumn.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("nextAppointment"));
        lastNotifiedColumn.setCellValueFactory(new PropertyValueFactory<>("lastNotified"));

    }

    private void fillTable(List<PatientEntry> patientEntries) {
        notificationTable.getItems().clear();
        for (PatientEntry patientEntry : patientEntries) {
            notificationTable.getItems().add(patientEntry);
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
     * @param toAddKey the key of the data
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
        fillTable(notificationListModel.getCarriedOverAppointmentEntries());
    }

    public void notifyButtonClicked(ActionEvent event) {
        List<Contact> patientContacts = new ArrayList<>();
        HashMap<Contact, String> toNotifiy = new HashMap<>();
        for(var p : notificationTable.getSelectionModel().getSelectedItems()) {
            PatientEntry patientEntry = (PatientEntry)p;

            Patient patient = PatientDAO.getByID(patientEntry.getPatientID());

            patient.setLastTimeNotified(LocalDate.now());

            PatientDAO.update(patient);

            Appointment app = AppointmentDAO.getById(patientEntry.getAppointmentID());

            List<Contact> contacts = ContactDAO.getByPatientId(patientEntry.getPatientID());

            patientContacts.addAll(ContactDAO.getByPatientId(patientEntry.getPatientID()));

            for(Contact cont : contacts){
                toNotifiy.put(cont, EmailBuilder.generate(cont, patient, app, EditNotificationEmailModel.EmailType.MISSED));
            }
        }
        if(toNotifiy.size() != 0){

            Iterator it = toNotifiy.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();

                //it.remove(); // avoids a ConcurrentModificationException
            }

            StringBuilder sb = new StringBuilder();

            sb.append("Your email has been sent to: \n");

            Iterator it1 = toNotifiy.entrySet().iterator();
            while (it1.hasNext()) {
                Map.Entry pair = (Map.Entry)it1.next();
                Contact contact = (Contact)pair.getKey();
                String generatedContent = (String)pair.getValue();
                sb.append(contact.getForename() + " " + contact.getSurname() + "\n");
                //it1.remove(); // avoids a ConcurrentModificationException
            }
            EmailSender sender = new EmailSender(toNotifiy, "Missed appointment!");
            sender.start();
        }


        update();
    }

    public void backButtonClicked(ActionEvent event) {
        primaryController.setPane(App.schema);
    }

    public void notifyAllButtonClicked(ActionEvent event) {
        List<Contact> patientContacts = new ArrayList<>();
        HashMap<Contact, String> toNotifiy = new HashMap<>();
        for (var p : notificationTable.getItems()){
            PatientEntry patientEntry = (PatientEntry)p;

            Patient patient = PatientDAO.getByID(patientEntry.getPatientID());

            patient.setLastTimeNotified(LocalDate.now());

            PatientDAO.update(patient);


            Appointment app = AppointmentDAO.getById(patientEntry.getAppointmentID());

            List<Contact> contacts = ContactDAO.getByPatientId(patientEntry.getPatientID());

            patientContacts.addAll(ContactDAO.getByPatientId(patientEntry.getPatientID()));

            for(Contact cont : contacts){
                toNotifiy.put(cont, EmailBuilder.generate(cont, patient, app, EditNotificationEmailModel.EmailType.MISSED));
            }
        }
        if(toNotifiy.size() != 0){

            Iterator it = toNotifiy.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();

            }

            StringBuilder sb = new StringBuilder();

            sb.append("Your email has been sent to: \n");

            Iterator it1 = toNotifiy.entrySet().iterator();
            while (it1.hasNext()) {
                Map.Entry pair = (Map.Entry)it1.next();
                Contact contact = (Contact)pair.getKey();
                String generatedContent = (String)pair.getValue();
                sb.append(contact.getForename() + " " + contact.getSurname() + "\n");
            }

            EmailSender sender = new EmailSender(toNotifiy, "Missed appointment!");
            sender.start();
        }

        update();

    }

    public void editDefaultEmailButtonClicked(ActionEvent event) {
        primaryController.setPane(App.editDefaultEmail);
    }
}
