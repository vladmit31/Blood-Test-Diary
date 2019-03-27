package seg.major.model.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateReverser {
    public static String reverseDateFormat(LocalDate date) {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String newDate = date.format(formatters);
        return newDate;
    }
}
