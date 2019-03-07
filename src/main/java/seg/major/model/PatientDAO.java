package seg.major.model;

import java.util.Map;

import seg.major.structure.Patient;

public class PatientDAO implements DAOInterface<Patient> {

    public PatientDAO() {

    }

    /** ---------- Inherited / Implemented ---------- */

    /**
     * Lookup a record by ID
     * 
     * @param toGet the ID to lookup and fetch the record for
     */
    public Patient getById(int toGet) {
        return null;
    }

    /**
     * Remove a record by ID
     * 
     * @param toGet the ID to remove
     */
    public void removeById(int toRemove) {
    }

    /**
     * @param toCreate the patient to create as a record
     */
    public void create(Patient toCreate) {
    }

    /**
     * @param toGet the ID of the record
     * @return the patient corresponding to the record
     */
    public Patient get(int toGet) {
        return null;
    }

    /**
     * @param toUpdate the patient to update
     */
    public void update(Patient toUpdate) {
    }

    /**
     * @param toRemove the patient to remove
     */
    public void remove(Patient toRemove) {
    };

    /**
     * @param toGet Map of atttributes and the values to match to a record
     * @return the matched patient
     */
    public Patient get(Map<String, String> toGet) {
        return null;
    }

    /** ---------- Inherited / Implemented ---------- */

}
