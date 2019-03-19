package seg.major.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import seg.major.App;
import seg.major.model.NotificationListModel;
import seg.major.model.database.AppointmentDAO;
import seg.major.model.database.ContactDAO;
import seg.major.model.database.PatientDAO;
import seg.major.structure.Appointment;
import seg.major.structure.Contact;
import seg.major.structure.Patient;
import seg.major.structure.PatientEntry;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class NotificationListController implements Initializable, ControllerInterface {

    public Button notifyButton;
    public TableColumn<PatientEntry, String> forenameColumn;
    public TableColumn<PatientEntry, String> hospitalNumberColumn;
    public TableColumn<PatientEntry, String> diagnosisColumn;
    public TableColumn<PatientEntry, String> surnameColumn;
    public TableColumn<PatientEntry, String> dueDateColumn;
    public Button backButton;
    public TableView notificationTable;
    public Button notifyAllButton;
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
        for(var p : notificationTable.getSelectionModel().getSelectedItems()) {
            PatientEntry pat = (PatientEntry)p;
            System.out.println(((PatientEntry) p).getHospitalNumber());
        }
    }

    public void backButtonClicked(ActionEvent event) {
        primaryController.setPane(App.schema);
    }

    public void notifyAllButtonClicked(ActionEvent event) {

    }
}
