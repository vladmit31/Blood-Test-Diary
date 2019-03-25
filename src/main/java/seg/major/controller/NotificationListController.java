package seg.major.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import seg.major.App;
import seg.major.controller.util.EmailSender;
import seg.major.model.CustomEmailModel;
import seg.major.model.EditNotificationEmailModel;
import seg.major.model.NotificationListModel;
import seg.major.model.database.AppointmentDAO;
import seg.major.model.database.ContactDAO;
import seg.major.model.database.PatientDAO;
import seg.major.structure.Appointment;
import seg.major.structure.Contact;
import seg.major.structure.Patient;
import seg.major.structure.PatientEntry;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class NotificationListController implements Initializable, ControllerInterface {

    public Button notifyButton;
    public TableColumn<PatientEntry, String> forenameColumn;
    public TableColumn<PatientEntry, String> hospitalNumberColumn;
    public TableColumn<PatientEntry, String> diagnosisColumn;
    public TableColumn<PatientEntry, String> surnameColumn;
    public TableColumn<PatientEntry, String> dueDateColumn;
    public TableColumn <PatientEntry, String> lastNotifiedColumn;
    public Button backButton;
    public TableView notificationTable;
    public Button notifyAllButton;
    public Button editDefaultEmail;
    private PrimaryController primaryController;
    private Map<String, Object> data = new HashMap<>();

    private NotificationListModel notificationListModel;

    private List<PatientEntry> toBeNotified = null;

    public void initialize(URL url, ResourceBundle rb) {
        this.notificationListModel = new NotificationListModel();

        setupTable();
    }

    private void setupTable() {
        notificationTable.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );
        setupColumns();
        setupRows();
        fillTable(this.notificationListModel.getCarriedOverAppointmentEntries());
        notificationTable.setPlaceholder(new Label("No patients found"));
    }

    private void setupRows() {
        notificationTable.setRowFactory(t -> {
            TableRow<PatientEntry> row = new TableRow<>();
            row.setOnMouseClicked(click -> {
                if (!row.isEmpty() && click.getButton() == MouseButton.PRIMARY && click.getClickCount() == 2) {
                        Patient patient = PatientDAO.get(row.getItem().getPatientID());
                        List<Contact> listOfContacts = ContactDAO.getAll().stream().filter(contact -> contact.getPatientID() == patient.getID()).collect(Collectors.toList());
                        primaryController.sendTo(App.customEmail,"contacts_list", listOfContacts);
                        primaryController.setPane(App.customEmail);
                }
            });
            return row;
        });
    }

    private void viewPatient(PatientEntry patientEntry){
        System.out.println(patientEntry.getPatientID() + " " + patientEntry.getForename() + " " + patientEntry.getSurname() + " | " + patientEntry.getDiagnosis());
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
        for(PatientEntry patientEntry : patientEntries) {
            notificationTable.getItems().add(patientEntry);
        }
    }



    public void setScreenParent(PrimaryController primaryController) {
        this.primaryController = primaryController;
    }

    public void setData(Map<String, Object> toInject) {

    }

    public void addData(String toAddKey, Object toAddVal) {

    }

    public void update() {
        fillTable(notificationListModel.getCarriedOverAppointmentEntries());
    }

    public void notifyButtonClicked(ActionEvent event) {
        List<Contact> patientContacts = new ArrayList<>();
        for(var p : notificationTable.getSelectionModel().getSelectedItems()) {
            PatientEntry patientEntry = (PatientEntry)p;

            Patient patient = PatientDAO.getByID(patientEntry.getPatientID());

            patient.setLastTimeNotified(LocalDate.now());

            PatientDAO.update(patient);

            patientContacts.addAll(ContactDAO.getByPatientId(patientEntry.getPatientID()));
        }
        EmailSender emailSender = new EmailSender(patientContacts, EditNotificationEmailModel.getSubject(),
                EditNotificationEmailModel.getBodyAsString());
        emailSender.start();

        update();
    }

    public void backButtonClicked(ActionEvent event) {
        primaryController.setPane(App.schema);
    }

    public void notifyAllButtonClicked(ActionEvent event) {
        List<Contact> patientContacts = new ArrayList<>();
        for (var p : notificationTable.getItems()){
            PatientEntry patientEntry = (PatientEntry)p;

            Patient patient = PatientDAO.getByID(patientEntry.getPatientID());

            patient.setLastTimeNotified(LocalDate.now());

            PatientDAO.update(patient);

            patientContacts.addAll(ContactDAO.getByPatientId(patientEntry.getPatientID()));
        }
        EmailSender emailSender = new EmailSender(patientContacts, EditNotificationEmailModel.getSubject(),
                EditNotificationEmailModel.getBodyAsString());
        emailSender.start();

        update();
    }

    public void editDefaultEmailButtonClicked(ActionEvent event) {
        primaryController.setPane(App.editDefaultEmail);
    }
}
