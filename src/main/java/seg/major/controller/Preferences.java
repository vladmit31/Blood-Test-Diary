package seg.major.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Preferences {

  public Preferences() {
  }

  public void readPreferences(String pathToPrefs) {

    Properties prop = new Properties();
    InputStream input = null;

    try {

      input = new FileInputStream(pathToPrefs);

      // load a properties file
      prop.load(input);

      // retrieve properties with prop.getProperty(<property name>)

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