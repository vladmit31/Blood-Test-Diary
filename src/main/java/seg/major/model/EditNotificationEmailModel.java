package seg.major.model;

import seg.major.controller.EditNotificationEmailController;

import java.io.*;
import java.net.URI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class EditNotificationEmailModel {

    private static String fileName = "DefaultEmail/Email.txt";

    private static List<String> lines = new ArrayList<>();

    /*public static String getSubject() {
        //read from file

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        File file = new File(classLoader.getResource(fileName).getFile());

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                return line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }*/

    public static void setAll(String subject, List<String> content) {
        //write to file

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        File file = new File(classLoader.getResource(fileName).getFile());
        System.out.println(file.getName());
        try {
            PrintWriter outFile = new PrintWriter("src/main/resources/DefaultEmail/Email.txt");
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
    }

    public static void getFileContents() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

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
        return lines.get(0);
    }

    public static List<String> getBody() {
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
