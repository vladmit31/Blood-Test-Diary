package seg.major.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.time.LocalDate;

import seg.major.structure.Patient;

public class PatientDAO implements DAOInterface<Patient> {

    private static final String TABLE_NAME = "patient";
    private static final String ID = "id";
    private static final String FORENAME = "fname";
    private static final String SURNAME = "sname";
    private static final String DOB = "dob";
    private static final String HOSPITAL_NUMBER = "local_clinic";
    private static final String LOCAL_CLINIC = "next_appointment";
    private static final String NEXT_APPOINTMENT = "refresh_rate";
    private static final String REFRESH_RATE = "id";
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
    public Patient getById(int toGet) {
        return get(toGet);
    }

    /**
     * Remove a record by ID
     * 
     * @param toRemove the ID to remove
     */
    public void removeById(int toRemove) {
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
    public void create(Patient toCreate) {
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
    public Patient get(int toGet) {
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
            toReturn = resultSetToUser(rs);
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
    public void update(Patient toUpdate) {
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
            ps.execute("USE db");
            rs = ps.executeQuery();
            toReturn = resultSetToUser(rs);
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
    public void remove(Patient toRemove) {
        removeById(toRemove.getID());
    }

    /**
     * @param toGet Map of atttributes and the values to match to a record
     * @return the matched patient
     */
    public Patient get(Map<String, String> toGet) {
        return null;
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
    private static Patient resultSetToUser(ResultSet toConvert) throws SQLException {
        if (toConvert.next()) {
            int id = toConvert.getInt(ID);
            String hospitalNumber = toConvert.getString(V_NUMBER);
            String forename = toConvert.getString(FORENAME);
            String surname = toConvert.getString(SURNAME);
            LocalDate dob = toConvert.getDate(DOB).toLocalDate();
            String localClinic = toConvert.getString(LOCAL_CLINIC);
            LocalDate nextAppointment = toConvert.getDate(NEXT_APPOINTMENT).toLocalDate();
            Double refreshRate = toConvert.getDouble(REFRESH_RATE);
            return new Patient(forename, surname, dob, hospitalNumber, localClinic, nextAppointment, refreshRate);

        } else {
            return null;
        }
    }

    /**
     * Convert a ResultSet to an array containing all records contained
     * 
     * @param toConvert the ResultSet to be read
     * @return the users
     */
    private Patient[] resultSetToUserArray(ResultSet toConvert) throws SQLException {
        Patient[] toReturn = new Patient[toConvert.getFetchSize()];
        int i = 0;
        while (toConvert.next()) {
            toReturn[i] = resultSetToUser(toConvert);
            i++;
        }

        return toReturn;
    }

}
