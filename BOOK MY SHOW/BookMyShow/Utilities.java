
package BookMyShow;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class Utilities implements Utility{
    private static DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy");//date formatter
    private static LocalDate today = LocalDate.now();//to get current date
    private static DateTimeFormatter timeformatter=DateTimeFormatter.ofPattern("HH:mm");//time formatter
    

    public  DateTimeFormatter getTimeformatter() {//getters
        return timeformatter;
    }
    public  LocalDate getToday() {//getters
        return today;
    }
    
    public  DateTimeFormatter getFormatter() {
        return formatter;//getters
    }

public  HashMap<Character, ArrayList<String>> generateGrid(long numberofseats, String grid) {//method to generate the seat pattern
        var starremoved = grid.split("\\*");//seperate the *(were the space comes )
        long sum = 0;

        for (String a : starremoved) {
            long temp = Long.parseLong(a);
            sum += temp;
        }//get the sum of the input

        
        if (numberofseats % sum == 0) {//if it is divisible and no seats left
            var hashmap = new HashMap<Character, ArrayList<String>>();
            char chara = 'A';//row name

            
            while (numberofseats > 0) {
                ArrayList<String> row = new ArrayList<>();//to store the every row

                for (int i = 0; i < starremoved.length; i++) {//number of columns
                    long groupSize = Long.parseLong(starremoved[i]);

                    
                    for (int j = 0; j < groupSize; j++) {
                        row.add("_");//seats added to the row
                    }

                    
                    if (i < starremoved.length - 1) {
                        row.add("<SPACE>");//space were the * comes
                    }
                }

                
                
                hashmap.put(chara, row);//add to hash map
                chara++;//next row

                
                numberofseats -= sum;//untill the seats is 0 the loop continue and rows added
            }

            
            

            return hashmap;//return the seat grid
        }

        
        System.out.println("Enter the correct number of seats:");
        return null;//for wrong number like 152
    }



}
