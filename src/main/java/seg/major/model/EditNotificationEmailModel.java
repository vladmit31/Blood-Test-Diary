package seg.major.model;

import seg.major.controller.EditNotificationEmailController;

import java.io.*;
import java.net.URI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
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
        System.out.println(file.getName());
        try {
            PrintWriter outFile = new PrintWriter("src/main/resources/" + fileName);
            outFile.println(subject);
            for(String line : content) {
                System.out.println(line);
                outFile.println(line);
            }
            outFile.flush();
            outFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        clearContents();
    }

    public static void getFileContents(EmailType type) {
        clearContents();

        String fileName = "";

        if(type == EmailType.REMINDER){
            fileName = reminderFileName;
        }else if(type == EmailType.MISSED){
            fileName = missedAppointmentFileName;
        }

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        System.out.println("{+{{{{" + fileName);

        File file = new File(classLoader.getResource(fileName).getFile());

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                lines.add(line);
            }
            fr.close();
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getSubject() {
        if(lines.size() == 0){
            return "No Subject found";
        }
        return lines.get(0);
    }

    public static List<String> getBody() {
        if(lines.size() <= 1){
            ArrayList<String> list = new ArrayList<>();
            list.add("No Body found");
            return list;
        }
        return lines.subList(1,lines.size());
    }

    public static void clearContents() {
        lines.clear();
    }

    public static String getBodyAsString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1 ; i < lines.size() ; ++i) {
            sb.append(lines.get(i));
            sb.append("\n");
        }
        return sb.toString();
    }
}
