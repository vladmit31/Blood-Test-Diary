package seg.major.model.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Props allows access to a properties file, giving access to external variables
 * such as usernames, passwords, and settings.
 */
public class Props extends Properties {

    private static final long serialVersionUID = 1L;
    private URL location;

    public Props(URL location) {
        this.location = location;
        readProperties();
    }

    /**
     * Read the properties from the location and put the properties in the props
     * HashMap
     */
    public void readProperties() {

        InputStream input = null;

        try {

            input = location.openStream();
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
     * @param toSetProp property to set
     * @param toSetVal  value to set proprty p to
     */
    public void writeProperty(String toSetProp, String toSetVal) {

        OutputStream output = null;

        try {

            // output = new FileOutputStream(location);
            output = new FileOutputStream(location.getFile());

            // set the properties value and save the file
            this.setProperty(toSetProp, toSetVal);
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

        Map<String, String> props = new HashMap<String, String>();
        for (String key : this.stringPropertyNames()) {
            String value = this.getProperty(key);
            props.put(key, value);
        }
        return props;
    }
}
