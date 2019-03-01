package seg.major.database;


import javafx.scene.control.DatePicker;

import javax.xml.crypto.Data;
import javax.xml.transform.Result;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public static void insertPatient(String fname, String sname,
                                     String vnumber, Date dob,
                                     String local_clinic, Date next_appointment,
                                     Double refreshRate) {
        String command = "INSERT INTO patient(vnumber, fname, sname, dob, local_clinic, next_appointment, refresh_rate) " +
                         "VALUES(\'" + vnumber + "\', \'" + fname + "\', \'" + sname + "\', \'" + dob + "\', \'" + local_clinic +
                         "\', \'" + next_appointment + "\', \'" + refreshRate + "\');";


        execute(command);

        int patientID = getLastInsertedPatientID();
        
        addAppointment(patientID, next_appointment);
        

    }

    private static void addAppointment(int patientID, Date next_appointment) {
        String command = "INSERT INTO appointment(due_date, patient_id) " +
                         "VALUES(\'" + next_appointment + "\', \'" + patientID + "\');";

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

    public static List getPatients() {
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
            resultSet = stt.executeQuery("SELECT * FROM patient");

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String vnumber = resultSet.getString("vnumber");
                String fname = resultSet.getString("fname");
                String sname = resultSet.getString("sname");
                Date dob = resultSet.getDate("dob");
                String local_clinic = resultSet.getString("local_clinic");
                Date next_appointment = resultSet.getDate("next_appointment");
                Double refresh_rate = resultSet.getDouble("refresh_rate");

                System.out.println(id);
                System.out.println(vnumber);
                System.out.println(fname);
                System.out.println(sname);
                System.out.println(dob);
                System.out.println(local_clinic);
                System.out.println(next_appointment);
                System.out.println(refresh_rate);
                System.out.println();
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

    public static void main(String[] args) {
        Date date1 = getMySqlDate("1998-01-01");
        Date date2 = getMySqlDate("2019-03-02");
        insertPatient("Horia", "Pavel", "v1234", date1, "cluj", date2, 2.0);
        List obj = getPatients();
        System.out.println();
    }
}
