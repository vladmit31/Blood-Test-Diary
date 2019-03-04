package seg.major.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;;

public class Preferences {

  private HashMap<String, String> props = new HashMap<String, String>();

  public void readPreferences(String filepath) {

    Properties prop = new Properties();
    InputStream input = null;

    try {

      input = new FileInputStream(filepath);

      // load a properties file
      prop.load(input);

      // get the property value and print it out
      Iterator it = prop.entrySet().iterator();
      while (it.hasNext()) {
        Map.Entry pair = (Map.Entry) it.next();
        System.out.println(pair.getKey() + " = " + pair.getValue());
        it.remove(); // avoids a ConcurrentModificationException
      }

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
}