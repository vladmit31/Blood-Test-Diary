package seg.major.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;
import seg.major.App;
import seg.major.model.SchemaModel;
import seg.major.structure.Appointment;
import seg.major.structure.AppointmentEntry;
import seg.major.structure.Patient;
import seg.major.structure.User;

/**
 * SchemaController acts as the controller for the schema.fxml file
 */
public class SchemaController implements Initializable, ViewsController {

    private PrimaryController primaryController;

    public TableColumn carriedOverName;
    public TableColumn carriedOverHospital;
    public TableColumn carriedOverComplete;
    public TableColumn carriedOverDueDate;
    public TableColumn thisWeekName;
    public TableColumn thisWeekHospital;
    public TableColumn thisWeekComplete;
    public TableColumn thisWeekDueDate;
    public TableColumn mondayName;
    public TableColumn mondayHospital;
    public TableColumn mondayComplete;
    public TableColumn mondayDueDate;
    public TableColumn tuesdayName;
    public TableColumn tuesdayHospital;
    public TableColumn tuesdayComplete;
    public TableColumn tuesdayDueDate;
    public TableColumn wednesdayName;
    public TableColumn wednesdayHospital;
    public TableColumn wednesdayComplete;
    public TableColumn wednesdayDueDate;
    public TableColumn thursdayName;
    public TableColumn thursdayHospital;
    public TableColumn thursdayComplete;
    public TableColumn thursdayDueDate;
    public TableColumn fridayName;
    public TableColumn fridayHospital;
    public TableColumn fridayComplete;
    public TableColumn fridayDueDate;

    public Button under12Btn;
    public Button over12Btn;
    public boolean isUnder12 = true;

    public Text bottomInfo;

    private SchemaModel schemaModel;

    private Object data;

    public Tab carriedOverTab;
    public TableView carriedOverTable;
    public Tab thisWeekTab;
    public TableView thisWeekTable;
    public Tab mondayTab;
    public TableView mondayTable;
    public Tab tuesdayTab;
    public TableView tuesdayTable;
    public Tab wednesdayTab;
    public TableView wednesdayTable;
    public Tab thursdayTab;
    public TableView thursdayTable;
    public Tab fridayTab;
    public TableView fridayTable;

  /** ---------- FXML ---------- */
  public Button prevWeekBtn;
  public Button nextWeekBtn;
  public Text weekText;
  public Text userInfo;
  public Button logoutBtn;
  public MenuItem menuPatientsInfo;
  public MenuItem menuExit;
  /** ---------- FXML ---------- */

  /** ---------- Inherited / Implemented ---------- */
  /**
   * Allow javafx to initalise the controller with the view
   */
  public void initialize(URL url, ResourceBundle rb) {
      this.schemaModel = new SchemaModel();
      setUpTable();
      setUpButtons();
      setCurrentDate();
      this.weekText.setText(this.schemaModel.getWeek());
  }

    /**
     * Get date for calculating the current week
     */
  private void setCurrentDate() {
      LocalDate localDate = LocalDate.now();
      // TODO: get the current date and compute the week that we are in
      //System.out.println(DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate));
  }

    private void setUpButtons() {
        under12Btn.setOnAction(e -> {
            under12Btn.setStyle("-fx-background-color: blue;" + "-fx-text-fill: white");
            over12Btn.setStyle(null);
            isUnder12 = true;
            refresh();
        });
        over12Btn.setOnAction(e -> {
            over12Btn.setStyle("-fx-background-color: blue;" + "-fx-text-fill: white");
            under12Btn.setStyle(null);
            isUnder12 = false;
            refresh();
        });
    }

    private void setUpTable() {
      setUpColumns();
      setUpRows();
      fillTablesForUnder12();
      under12Btn.setStyle("-fx-background-color: blue;" + "-fx-text-fill: white");
      over12Btn.setStyle(null);
    }

    private void setUpRows() {
        mondayTable.setRowFactory(t -> {
            TableRow<Appointment> row = new TableRow<>();
            row.setOnMouseClicked(click -> {
                if (!row.isEmpty() && click.getButton() == MouseButton.PRIMARY && click.getClickCount() == 2) {
                    Appointment appointment = row.getItem();
                    //viewPatient(patient);


                    UpdateAppointmentController uac = (UpdateAppointmentController) primaryController.getControllerByName(App.updateAppointment);
                    uac.setData(appointment);
                    primaryController.setPane(App.updateAppointment);


                }
            });
            return row;
        });
    }

/*    private void fillTable(){
        patientTable.getItems().clear();
        for (Patient patient : patientModel.getPatientList()) {
            patientTable.getItems().add(patient);
        }
    }*/

    public void fillTable(TableView tb, List<AppointmentEntry> appEntryList) {
        tb.getItems().clear();
        for (AppointmentEntry appEntry : appEntryList) {
            tb.getItems().add(appEntry);
        }
    }

    public void fillTablesForUnder12() {
        fillTable(mondayTable,schemaModel.getAppointmentsAndPatientsForDayUnder12(DayOfWeek.MONDAY));
        fillTable(tuesdayTable,schemaModel.getAppointmentsAndPatientsForDayUnder12(DayOfWeek.TUESDAY));
        fillTable(wednesdayTable,schemaModel.getAppointmentsAndPatientsForDayUnder12(DayOfWeek.WEDNESDAY));
        fillTable(thursdayTable,schemaModel.getAppointmentsAndPatientsForDayUnder12(DayOfWeek.THURSDAY));
        fillTable(fridayTable,schemaModel.getAppointmentsAndPatientsForDayUnder12(DayOfWeek.FRIDAY));
    }

    public void fillTablesForOver12() {
        fillTable(mondayTable,schemaModel.getAppointmentsAndPatientsForDayOver12(DayOfWeek.MONDAY));
        fillTable(tuesdayTable,schemaModel.getAppointmentsAndPatientsForDayOver12(DayOfWeek.TUESDAY));
        fillTable(wednesdayTable,schemaModel.getAppointmentsAndPatientsForDayOver12(DayOfWeek.WEDNESDAY));
        fillTable(thursdayTable,schemaModel.getAppointmentsAndPatientsForDayOver12(DayOfWeek.THURSDAY));
        fillTable(fridayTable,schemaModel.getAppointmentsAndPatientsForDayOver12(DayOfWeek.FRIDAY));
    }

    private void setUpColumns(){
        carriedOverName.setCellValueFactory(new PropertyValueFactory("name"));
        carriedOverHospital.setCellValueFactory(new PropertyValueFactory("vnumber"));
        carriedOverComplete.setCellValueFactory(new PropertyValueFactory("complete"));
        carriedOverDueDate.setCellFactory(new PropertyValueFactory("dueDate"));

        thisWeekName.setCellValueFactory(new PropertyValueFactory("name"));
        thisWeekHospital.setCellValueFactory(new PropertyValueFactory("vnumber"));
        thisWeekComplete.setCellValueFactory(new PropertyValueFactory("complete"));
        thisWeekDueDate.setCellValueFactory(new PropertyValueFactory("dueDate"));

        mondayName.setCellValueFactory(new PropertyValueFactory("name"));
        mondayHospital.setCellValueFactory(new PropertyValueFactory("vnumber"));
        mondayComplete.setCellValueFactory(new PropertyValueFactory("complete"));
        mondayDueDate.setCellValueFactory(new PropertyValueFactory("dueDate"));

        tuesdayName.setCellValueFactory(new PropertyValueFactory("name"));
        tuesdayHospital.setCellValueFactory(new PropertyValueFactory("vnumber"));
        tuesdayComplete.setCellValueFactory(new PropertyValueFactory("complete"));
        tuesdayDueDate.setCellValueFactory(new PropertyValueFactory("dueDate"));

        wednesdayName.setCellValueFactory(new PropertyValueFactory("name"));
        wednesdayHospital.setCellValueFactory(new PropertyValueFactory("vnumber"));
        wednesdayComplete.setCellValueFactory(new PropertyValueFactory("complete"));
        wednesdayDueDate.setCellValueFactory(new PropertyValueFactory("dueDate"));

        thursdayName.setCellValueFactory(new PropertyValueFactory("name"));
        thursdayHospital.setCellValueFactory(new PropertyValueFactory("vnumber"));
        thursdayComplete.setCellValueFactory(new PropertyValueFactory("complete"));
        thursdayDueDate.setCellValueFactory(new PropertyValueFactory("dueDate"));

        fridayName.setCellValueFactory(new PropertyValueFactory("name"));
        fridayHospital.setCellValueFactory(new PropertyValueFactory("vnumber"));
        fridayComplete.setCellValueFactory(new PropertyValueFactory("complete"));
        fridayDueDate.setCellValueFactory(new PropertyValueFactory("dueDate"));
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


  public void setData(Object newData) {
    data = newData;
  }

  public void setAuthenticatedUser() {
    userInfo.setText("You logged in as " + ((User)data).getUsername());
  }

    public void logout(ActionEvent event) {
      primaryController.setPane(App.login);
    }

    public void switchToPatients(ActionEvent event) {
      primaryController.setPane(App.patients);
    }

    public void previousWeekButtonClicked(ActionEvent event) {
      this.schemaModel.decrementWeek();

      this.weekText.setText(schemaModel.getWeek());
      refresh();
    }

    public void nextWeekButtonClicked(ActionEvent event) {
      this.schemaModel.incrementWeek();

      this.weekText.setText(schemaModel.getWeek());
      refresh();
    }

    public void refresh() {
      // TODO: check for which button is selected for update.
      if(isUnder12) {
          fillTablesForUnder12();
      }else {
          fillTablesForOver12();
      }
    }
}
