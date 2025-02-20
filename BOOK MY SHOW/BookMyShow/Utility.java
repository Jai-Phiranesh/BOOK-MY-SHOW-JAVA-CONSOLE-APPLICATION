package BookMyShow;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public interface Utility {
    public  HashMap<Character, ArrayList<String>> generateGrid(long numberofseats, String grid);
    public  DateTimeFormatter getTimeformatter();
    public  LocalDate getToday();
    public  DateTimeFormatter getFormatter();
}
