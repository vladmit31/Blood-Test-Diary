package seg.major.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateAppointmentController implements Initializable, ViewsController {

    private PrimaryController primaryController;

    private Object data;

    public CheckBox completed;
    public DatePicker appDueDate;
    public Text textInfo;
    public Button updateBtn;
    public Button cancelBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setScreenParent(PrimaryController primaryController) {
        this.primaryController = primaryController;
    }

    public void setData(Object newData) {
        data = newData;
    }
}
