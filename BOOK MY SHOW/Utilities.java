import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class Utilities {
    private static DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static LocalDate today = LocalDate.now();
    private static DateTimeFormatter timeformatter=DateTimeFormatter.ofPattern("HH:mm");

    public static DateTimeFormatter getTimeformatter() {
        return timeformatter;
    }
    public static LocalDate getToday() {
        return today;
    }
    
    public static DateTimeFormatter getFormatter() {
        return formatter;
    }

public static HashMap<Character, ArrayList<String>> generateGrid(long numberofseats, String grid) {
        var starremoved = grid.split("\\*");
        long sum = 0;

       
        for (String a : starremoved) {
            long temp = Long.parseLong(a);
            sum += temp;
        }

        
        if (numberofseats % sum == 0) {
            var hashmap = new HashMap<Character, ArrayList<String>>();
            char chara = 'A';

            
            while (numberofseats > 0) {
                ArrayList<String> row = new ArrayList<>();

                for (int i = 0; i < starremoved.length; i++) {
                    long groupSize = Long.parseLong(starremoved[i]);

                    
                    for (int j = 0; j < groupSize; j++) {
                        row.add("_");
                    }

                    
                    if (i < starremoved.length - 1) {
                        row.add("<SPACE>");
                    }
                }

                
                
                hashmap.put(chara, row);
                chara++;

                
                numberofseats -= sum;
            }

            
            

            return hashmap;
        }

        
        System.out.println("Enter the correct number of seats:");
        return null;
    }
}
