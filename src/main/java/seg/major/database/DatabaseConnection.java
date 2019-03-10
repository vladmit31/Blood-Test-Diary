package seg.major.database;


import com.ja.security.PasswordHash;
import javafx.scene.control.DatePicker;
import seg.major.App;
import seg.major.structure.Appointment;
import seg.major.structure.Contact;
import seg.major.structure.Patient;
import seg.major.structure.User;

import javax.xml.crypto.Data;
import javax.xml.transform.Result;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.DateFormat.*;

public class DatabaseConnection {

    // Credentials
    private static final String url = "jdbc:mysql://localhost:3306/";
    private static final String user = "root";
    private static final String password = "";

    private static Connection con;

    public static void insertPatient(Patient patient, LocalDate appDate) {
        String command = "INSERT INTO patient(vnumber, fname, sname, dob, local_clinic, refresh_rate) " +
                         "VALUES(\'" + patient.getHospitalNumber() + "\', \'" + patient.getForename() + "\', \'" + patient.getSurname() + "\', \'" + patient.getDob() + "\', \'" + patient.getLocalClinic() +
                         "\', \'" + Patient.DEFAULT_REFRESH_RATE + "\');";


        execute(command);

        int patientID = getLastInsertedPatientID();
        
        addAppointment(patientID, appDate);
        

    }

    private static void addAppointment(int patientID, LocalDate appDate) {
        String command = "INSERT INTO appointment(due_date, patient_id) " +
                         "VALUES(\'" + appDate + "\', \'" + patientID + "\');";

        execute(command);
    }


    private static void execute(String command) {

        try {
            con = DriverManager.getConnection(url, user, password);
            Statement stt = con.createStatement();
            stt.execute("USE db");
            try {
                stt.execute(command);
            } finally {
                stt.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private static int getLastInsertedPatientID(){
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List resList = new ArrayList();
        Statement stt = null;
        ResultSet resultSet = null;

        try {
            stt = con.createStatement();
            stt.execute("USE db");
            resultSet = stt.executeQuery("SELECT MAX(id) FROM patient;");

            if(resultSet.next()){
                System.out.println((int)(resultSet.getInt(1)) + "asdfasdf");
                return (int)(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                stt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static List<Patient> getPatients() {
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Patient> resList = new ArrayList<Patient>();
        Statement stt = null;
        ResultSet resultSet = null;

        try {
            stt = con.createStatement();
            stt.execute("USE db");
            resultSet = stt.executeQuery("SELECT * FROM patient");

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String vnumber = resultSet.getString("vnumber");
                String fname = resultSet.getString("fname");
                String sname = resultSet.getString("sname");
                Date dob = resultSet.getDate("dob");
                String local_clinic = resultSet.getString("local_clinic");
                Double refresh_rate = resultSet.getDouble("refresh_rate");

                Patient newPatient = new Patient(fname,sname,((java.sql.Date) dob).toLocalDate(),vnumber,local_clinic);

                newPatient.setId(id);

                resList.add(newPatient);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                stt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resList;
    }

    private static Date getMySqlDate(String date) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = null;
        try {
            myDate = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        return sqlDate;
    }

    public static User getUserByUsername(String username) {
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Statement stt = null;
        ResultSet resultSet = null;

        try {
            stt = con.createStatement();
            stt.execute("USE db");
            resultSet = stt.executeQuery("SELECT * FROM user WHERE username LIKE" + "'" + username + "'");

            while (resultSet.next()) {

                String userName = resultSet.getString("username");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");

                return new User(userName, email, password);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                stt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static List<User> getUsers() {
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<User> resList = new ArrayList<User>();
        Statement stt = null;
        ResultSet resultSet = null;

        try {
            stt = con.createStatement();
            stt.execute("USE db");
            resultSet = stt.executeQuery("SELECT * FROM user");

            while (resultSet.next()) {

                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");

                resList.add(new User(username,email,password));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                stt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resList;
    }

    public static void inputNewUser(User user) {
        String command = null;
        try {
            command = "INSERT INTO user " +
                    "VALUES(\'" + user.getUsername() + "\', \'" + user.getEmail() + "\', \'" + (new PasswordHash()).createHash(user.getPassword()) + "\' , \'" + 0 + "\' );";

            System.out.println(command);

            execute(command);


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }
    public static void updatePatientData(Patient toBeUpdated, LocalDate appDate){
        String command = null;

            command = "UPDATE patient SET vnumber=\'"+toBeUpdated.getHospitalNumber()+"\' ,"+
                                              "fname=\'"+toBeUpdated.getForename()+"\' ,"+
                                                "sname=\'"+toBeUpdated.getSurname()+"\' ,"+
                                                 "dob=\'"+toBeUpdated.getDob()+"\' ,"+
                                                    "local_clinic=\'"+toBeUpdated.getLocalClinic() +"\' WHERE id=" + toBeUpdated.getId();




            execute(command);

            String updateAppCommand = null;

            updateAppCommand = "UPDATE appointment SET due_date=\'" + appDate + "\' WHERE patient_id=" + toBeUpdated.getId();

            execute(updateAppCommand);
    }

    public static List<Appointment> getAppointments() {
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Appointment> resList = new ArrayList<Appointment>();
        Statement stt = null;
        ResultSet resultSet = null;

        try {
            stt = con.createStatement();
            stt.execute("USE db");
            resultSet = stt.executeQuery("SELECT * FROM appointment");

            while (resultSet.next()) {

                int appId = resultSet.getInt("app_id");
                int status = resultSet.getInt("status");
                Date dueDate = resultSet.getDate("due_date");
                int patientId = resultSet.getInt("patient_id");

                LocalDate date = ((java.sql.Date) dueDate).toLocalDate();

                Appointment a = new Appointment(status,date,patientId);
                a.setAppId(appId);

                resList.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                stt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resList;
    }

    public static List<Appointment> getAppointmentsForTheWeek(String mondayDate, String fridayDate) {
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Appointment> resList = new ArrayList<Appointment>();
        Statement stt = null;
        ResultSet resultSet = null;

        try {
            stt = con.createStatement();
            stt.execute("USE db");

            String sqlQuery = "SELECT * FROM appointment WHERE due_date between \'" + mondayDate +
                                            "\' and \'" + fridayDate + "\';";

            resultSet = stt.executeQuery(sqlQuery);

            while (resultSet.next()) {

                int appId = resultSet.getInt("app_id");
                int status = resultSet.getInt("status");
                Date dueDate = resultSet.getDate("due_date");
                int patientId = resultSet.getInt("patient_id");

                LocalDate date = ((java.sql.Date) dueDate).toLocalDate();

                Appointment a = new Appointment(status,date,patientId);
                a.setAppId(appId);

                resList.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                stt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resList;
    }

    public static Patient getPatientById(int id) {
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Statement stt = null;
        ResultSet resultSet = null;

        try {
            stt = con.createStatement();
            stt.execute("USE db");
            resultSet = stt.executeQuery("SELECT * FROM patient WHERE id LIKE" + "'" + id + "'");

            while (resultSet.next()) {

                int patientId = resultSet.getInt("id");
                String vnumber = resultSet.getString("vnumber");
                String fname = resultSet.getString("fname");
                String sname = resultSet.getString("sname");
                Date dob = resultSet.getDate("dob");
                String local_clinic = resultSet.getString("local_clinic");
                Double refresh_rate = resultSet.getDouble("refresh_rate");

                Patient patient = new Patient(fname,sname,((java.sql.Date) dob).toLocalDate(),vnumber,local_clinic);

                patient.setId(id);

                return patient;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                stt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Appointment getAppointmentById(int id) {
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Statement stt = null;
        ResultSet resultSet = null;

        try {
            stt = con.createStatement();
            stt.execute("USE db");
            resultSet = stt.executeQuery("SELECT * FROM appointment WHERE app_id LIKE" + "'" + id + "'");

            while (resultSet.next()) {

                int app_id = resultSet.getInt("app_id");
                int status = resultSet.getInt("status");
                Date dueDate = resultSet.getDate("due_date");
                int patient_id = resultSet.getInt("patient_id");

                Appointment appointment = new Appointment(status,((java.sql.Date) dueDate).toLocalDate(),patient_id);

                appointment.setAppId(app_id);

                return appointment;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                stt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static List<Contact> getContactsByPatientId(int patientID){
        List<Contact> toReturn = new ArrayList<>();
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Statement stt = null;
        ResultSet resultSet = null;

        try {
            stt = con.createStatement();
            stt.execute("USE db");
            resultSet = stt.executeQuery("SELECT * FROM contact WHERE patient_id LIKE"
                                                    + "'" + patientID + "'");

            while (resultSet.next()) {

                int contact_id = resultSet.getInt("id");
                String forname = resultSet.getString("forename");
                String surname = resultSet.getString("surname");
                String relationship = resultSet.getString("relationship");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");

                Contact newContact = new Contact(patientID, forname, surname, relationship, phone, email);

                newContact.setID(contact_id);

                toReturn.add(newContact);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                stt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return toReturn;
    }

    public static void addContactToPatient(int patient_id, Contact newContact) {
        String command = "INSERT INTO contact(forename, surname, relationship, phone, email, patient_id)" +
                         "VALUES(\'" + newContact.getForename() + "\', \'" + newContact.getSurname() +
                         "\', \'" + newContact.getRelationship() + "\', \'" + newContact.getPhone() +
                         "\', \'" + newContact.getEmail() + "\', \'" + patient_id + "\');";

        execute(command);
    }

    public static void deleteContact(int id, Contact toDelete) {
        String command = "DELETE FROM contact WHERE id=" + toDelete.getID();

        execute(command);
    }
}
