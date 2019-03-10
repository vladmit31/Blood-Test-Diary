package seg.major.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Week {
    private LocalDate mondayDate;
    private LocalDate fridayDate;

    LocalDate now;

    public Week(LocalDate now){
        this.now = now;
        update();
    }

    private void update(){
        this.mondayDate = now.with(DayOfWeek.MONDAY);
        this.fridayDate = now.with(DayOfWeek.FRIDAY);
    }

    public void increment(){
        now = now.plus(1, ChronoUnit.WEEKS);
        update();
    }

    public void decrement(){
        now = now.minus(1, ChronoUnit.WEEKS);
        update();
    }

    public String getMondayDate(){
        return this.mondayDate.toString();
    }

    public String getFridayDate(){
        return this.fridayDate.toString();
    }

    public String toString(){
        return formatDate(this.mondayDate) + " - " + formatDate(this.fridayDate);
    }

    private String formatDate(LocalDate date){
        return date.format(
                DateTimeFormatter.ofLocalizedDate( FormatStyle.FULL )
                        .withLocale( Locale.UK ));
    }

}
