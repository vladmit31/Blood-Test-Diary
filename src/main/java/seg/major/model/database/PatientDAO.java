package seg.major.model.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;

import seg.major.structure.Patient;

public class PatientDAO {

    private static final String TABLE_NAME = "patient";
    private static final String ID = "id";
    private static final String FORENAME = "fname";
    private static final String SURNAME = "sname";
    private static final String DOB = "dob";
    private static final String HOSPITAL_NUMBER = "local_clinic";
    private static final String LOCAL_CLINIC = "next_appointment";
    private static final String NEXT_APPOINTMENT = "next_appointment";
    private static final String REFRESH_RATE = "refresh_rate";
    private static final String V_NUMBER = "vnumber";

    public PatientDAO() {
    }

    /** ---------- Inherited / Implemented ---------- */

    /**
     * Lookup a record by ID
     * 
     * @param toGet the ID of the record
     * @return the patient corresponding to the record
     */
    public static Patient getByID(int toGet) {
        return get(toGet);
    }

    /**
     * Remove a record by ID
     * 
     * @param toRemove the ID to remove
     */
    public static void removeByID(int toRemove) {
        String query = "DELETE FROM patient WHERE id = ? LIMIT 1;";
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = DAOConnection.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, toRemove);
            ps.execute("USE db");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (Exception e) {
            }
            try {
                conn.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * @param toCreate the patient to create as a record
     */
    public static void create(Patient toCreate) {
        String query = "INSERT INTO patient (vnumber, fname, sname, dob, local_clinic, next_appointment, refresh_rate) VALUES (?, ?, ?,?, ?,?,?)";
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = DAOConnection.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, toCreate.getHospitalNumber());
            ps.setString(2, toCreate.getForename());
            ps.setString(3, toCreate.getSurname());
            ps.setString(4, toCreate.getLocalClinic());
            ps.setDate(5, Date.valueOf(toCreate.getDob()));
            ps.setDate(6, Date.valueOf(toCreate.getDob()));
            ps.setDouble(7, toCreate.getRefreshRate());
            ps.execute("USE db");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                ps.close();
            } catch (Exception e) {
            }
            try {
                conn.close();
            } catch (Exception e) {
            }
        }

    }

    /**
     * @param toGet the ID of the record
     * @return the patient corresponding to the record
     */
    public static Patient get(int toGet) {
        String query = "SELECT * FROM patient WHERE id = ? LIMIT 1;";
        Patient toReturn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = DAOConnection.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, toGet);
            ps.execute("USE db");
            rs = ps.executeQuery();
            toReturn = resultSetToPatient(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }
            try {
                ps.close();
            } catch (Exception e) {
            }
            try {
                conn.close();
            } catch (Exception e) {
            }
        }

        return toReturn;
    }

    /**
     * @param toUpdate the patient to update
     */
    public static void update(Patient toUpdate) {
        String query = "UPDATE patient SET vnumber = ?, fname = ?, sname = ?, dob = ?,  local_clinic = ?, next_appointment = ?, refresh_rate = ? WHERE id = ?";

        Patient toReturn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = DAOConnection.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, toUpdate.getHospitalNumber());
            ps.setString(2, toUpdate.getForename());
            ps.setString(3, toUpdate.getSurname());
            ps.setString(4, toUpdate.getLocalClinic());
            ps.setDate(5, Date.valueOf(toUpdate.getDob()));
            ps.setDate(6, Date.valueOf(toUpdate.getDob()));
            ps.setDouble(7, toUpdate.getRefreshRate());
            ps.setInt(8, toUpdate.getID());
            ps.execute("USE db");
            rs = ps.executeQuery();
            toReturn = resultSetToPatient(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }
            try {
                ps.close();
            } catch (Exception e) {
            }
            try {
                conn.close();
            } catch (Exception e) {
            }
        }

    }

    /**
     * @param toRemove the patient to remove
     */
    public static void remove(Patient toRemove) {
        removeByID(toRemove.getID());
    }

    /**
     * @param toGet Map of attributes and the values to match to a record
     * @return the matched patient
     */
    public static Patient get(Map<String, String> toGet) {

        String query = mapToSQLQuery(toGet);
        Patient toReturn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = DAOConnection.getConnection();
            ps = conn.prepareStatement(query);
            ps.execute("USE db");
            rs = ps.executeQuery();
            toReturn = resultSetToPatient(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }
            try {
                ps.close();
            } catch (Exception e) {
            }
            try {
                conn.close();
            } catch (Exception e) {
            }
        }

        return toReturn;
    }

    /**
     * @param toGet Map of attributes and the values to match to a record
     * @return the matched patient
     */
    public static List<Patient> getAll(Map<String, String> toGet) {

        String query = mapToSQLQuery(toGet);
        List<Patient> toReturn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = DAOConnection.getConnection();
            ps = conn.prepareStatement(query);
            ps.execute("USE db");
            rs = ps.executeQuery();
            toReturn = resultSetToPatientArray(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }
            try {
                ps.close();
            } catch (Exception e) {
            }
            try {
                conn.close();
            } catch (Exception e) {
            }
        }

        return toReturn;
    }

    /**
     * Get all the current Patients
     * 
     * @return a Patient array that contains every patient in the table
     */
    public static List<Patient> getAll() {
        String query = "SELECT * FROM patient";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        List<Patient> toReturn = null;
        try {
            conn = DAOConnection.getConnection();
            ps = conn.prepareStatement(query);
            ps.execute("USE db");
            rs = ps.executeQuery();
            toReturn = resultSetToPatientArray(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }
            try {
                ps.close();
            } catch (Exception e) {
            }
            try {
                conn.close();
            } catch (Exception e) {
            }
        }

        return toReturn;
    }

    /** ---------- Inherited / Implemented ---------- */

    private static int getLastInsertedPatientID() {

        String query = "SELECT MAX(id) FROM patient";

        Patient toReturn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = DAOConnection.getConnection();
            ps = conn.prepareStatement(query);
            ps.execute("USE db");
            rs = ps.executeQuery(query);

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }
            try {
                ps.close();
            } catch (Exception e) {
            }
            try {
                conn.close();
            } catch (Exception e) {
            }
        }
        return -1;
    }

    /**
     * Get the patient the ResultSet pointer currently points to
     * 
     * @param toConvert the ResultSet to be read
     * @return the patient
     */
    private static Patient resultSetToPatient(ResultSet toConvert) throws SQLException {

        int id = toConvert.getInt(ID);
        String hospitalNumber = toConvert.getString(V_NUMBER);
        String forename = toConvert.getString(FORENAME);
        String surname = toConvert.getString(SURNAME);
        LocalDate dob = toConvert.getDate(DOB).toLocalDate();
        String localClinic = toConvert.getString(LOCAL_CLINIC);
        LocalDate nextAppointment = toConvert.getDate(NEXT_APPOINTMENT).toLocalDate();
        Double refreshRate = toConvert.getDouble(REFRESH_RATE);
        return new Patient(id, forename, surname, dob, hospitalNumber, localClinic, nextAppointment, refreshRate);

    }

    /**
     * Convert a ResultSet to an array containing all records contained
     * 
     * @param toConvert the ResultSet to be read
     * @return the patients
     */
    private static List<Patient> resultSetToPatientArray(ResultSet toConvert) throws SQLException {
        List<Patient> toReturn = new ArrayList<Patient>();
        while (toConvert.next()) {
            toReturn.add(resultSetToPatient(toConvert));
        }

        return toReturn;
    }

    /**
     * Build a statment of the form: "SELECT * FROM patient WHERE (col1 = val1 AND
     * col2 = val2 AND ... colN = valN);"
     * 
     * from the provided map.
     * 
     * @param toQuery the map to convery to a query
     * @return the constructed statement
     */
    private static String mapToSQLQuery(Map<String, String> toQuery) {

        // build the statement
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM patient WHERE ( ");
        for (Map.Entry<String, String> entry : toQuery.entrySet()) {
            sb.append(entry.getKey());
            sb.append("='");
            sb.append(entry.getValue());
            sb.append("' AND ");
        }
        // remove the last AND then close the brackets
        sb.delete(sb.length() - 4, sb.length());
        sb.append(");");

        return sb.toString();
    }

}
