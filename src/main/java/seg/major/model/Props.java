package seg.major.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.HashMap;
import java.util.Map;
import java.io.OutputStream;
import java.io.FileOutputStream;

/**
 * Props allows access to a properties file, giving access to external variables such as usernames,
 * passwords, and settings.
 */
public class Props extends Properties {

    private static final long serialVersionUID = 1L;
    private String filepath;

    public Props(String filepath) {
        this.filepath = filepath;
        readProperties();
    }

    /**
     * Read the properties from the filepath and put the properties in the props HashMap
     */
    public void readProperties() {

        InputStream input = null;

        try {

            input = new FileInputStream(filepath);
            this.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * Write a given property p and value val to the properties file
     * 
     * @param p   property to set
     * @param val value to set proprty p to
     */
    public void writeProperty(String p, String val) {

        OutputStream output = null;

        try {

            output = new FileOutputStream(filepath);

            // set the properties value and save the file
            this.setProperty(p, val);
            this.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * @return the current Map of properties
     */
    public Map<String, String> getAllProperties() {

        Map<String, String> props= new HashMap<String, String>();
        for (String key : this.stringPropertyNames()) {
            String value = this.getProperty(key);
            props.put(key, value);
        }
        return props;
    }
}
