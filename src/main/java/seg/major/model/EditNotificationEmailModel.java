package seg.major.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
/**
 * Model class for EditNotificationEmailController class.
 * Provides communication between controller and DAOs if needed.
 * @author Team Pacane
 * @version 1.0
 */
public class EditNotificationEmailModel {

    public enum EmailType{
        REMINDER,
        MISSED
    }

    private static String reminderFileName = "DefaultEmail/ReminderEmail.txt";
    private static String missedAppointmentFileName = "DefaultEmail/MissedAppointmentEmail.txt";
    private static List<String> lines = new ArrayList<>();

    /**
     * Set the subject, content, and type of a email notificatoin
     *
     * @param subject of the email
     * @param content of the email
     * @param type of the notification
     */
    public static void setAll(String subject, List<String> content, EmailType type) {
        //write to file

        String fileName = "";

        if(type == EmailType.REMINDER){
            fileName = reminderFileName;
        }else if(type == EmailType.MISSED){
            fileName = missedAppointmentFileName;
        }

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        File file = new File(classLoader.getResource(fileName).getFile());
        try {
            PrintWriter outFile = new PrintWriter("src/main/resources/" + fileName);
            outFile.println(subject);
            for(String line : content) {
                outFile.println(line);
            }
            outFile.flush();
            outFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        clearContents();
    }

    /**
     * Read a file from disk
     *
     * @param type
     */
    public static void getFileContents(EmailType type) {
        clearContents();

        String fileName = "";

        if(type == EmailType.REMINDER){
            fileName = reminderFileName;
        }else if(type == EmailType.MISSED){
            fileName = missedAppointmentFileName;
        }

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            fr.close();
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the subject of the email
     *
     * @return the subject of the email
     */
    public static String getSubject() {
        if(lines.size() == 0){
            return "No Subject found";
        }
        return lines.get(0);
    }

    /**
     * Get the body of the email
     *
     * @return the body of the email
     */
    public static List<String> getBody() {
        if(lines.size() <= 1){
            ArrayList<String> list = new ArrayList<>();
            list.add("No Body found");
            return list;
        }
        return lines.subList(1,lines.size());
    }

    /**
     * Clear the contents of the lines array
     */
    public static void clearContents() {
        lines.clear();
    }

    /**
     * Reads the body of the email and returns it as a string
     *
     * @return the body of the email
     */
    public static String getBodyAsString() {
        StringBuilder sb = new StringBuilder();

        // while there is still body to be read...
        for (int i = 1 ; i < lines.size() ; ++i) {
            sb.append(lines.get(i));
            sb.append("\n");
        }
        return sb.toString();
    }
}
