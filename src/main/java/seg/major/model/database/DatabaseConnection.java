package seg.major.model.database;

import com.ja.security.PasswordHash;
import seg.major.structure.Patient;
import seg.major.structure.User;
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

public class DatabaseConnection {

    // Credentials
    private static final String url = "jdbc:mysql://localhost:3306/";
    private static final String user = "root";
    private static final String password = "";

    private static Connection con;

    public static void insertPatient(Patient patient) {
        String command = "INSERT INTO patient(vnumber, fname, sname, dob, local_clinic, next_appointment, refresh_rate) "
                + "VALUES(\'" + patient.getHospitalNumber() + "\', \'" + patient.getForename() + "\', \'"
                + patient.getSurname() + "\', \'" + patient.getDob() + "\', \'" + patient.getLocalClinic() + "\', \'"
                + patient.getNextAppointment() + "\', \'" + Patient.DEFAULT_REFRESH_RATE + "\');";

        execute(command);

        int patientID = getLastInsertedPatientID();

        addAppointment(patientID, patient.getNextAppointment());

    }

    private static void addAppointment(int patientID, LocalDate next_appointment) {
        String command = "INSERT INTO appointment(due_date, patient_id) " + "VALUES(\'" + next_appointment + "\', \'"
                + patientID + "\');";

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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private static int getLastInsertedPatientID() {
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
            resultSet = stt.executeQuery("SELECT MAX(id) FROM patient;");

            if (resultSet.next()) {
                System.out.println((int) (resultSet.getInt(1)) + "asdfasdf");
                return (int) (resultSet.getInt(1));
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
                Date next_appointment = resultSet.getDate("next_appointment");
                Double refresh_rate = resultSet.getDouble("refresh_rate");

                resList.add(new Patient(fname, sname, ((java.sql.Date) dob).toLocalDate(), vnumber, local_clinic,
                        ((java.sql.Date) next_appointment).toLocalDate()));
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

                // return new User(userName, email, password);
                return null;
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

                resList.add(null);
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
            command = "INSERT INTO user " + "VALUES(\'" + user.getUsername() + "\', \'" + user.getEmail() + "\', \'"
                    + (new PasswordHash()).createHash(user.getPassword()) + "\' , \'" + 0 + "\' );";

            System.out.println(command);

            execute(command);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }
}
