package seg.major.model.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Reverses LocalDate and returns strings for a
 * more accurate display.
 * @author Team Pacane
 * @version 1.0
 */
public class DateReverser {
    public static String reverseDateFormat(LocalDate date) {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String newDate = date.format(formatters);
        return newDate;
    }
}
