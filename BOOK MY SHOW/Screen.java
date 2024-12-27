
import java.util.ArrayList;
import java.util.HashMap;

public class Screen {
    private String name;
    private long numberofseats;

    private  HashMap<Character, ArrayList<String>> seatarrangement = new HashMap<>();

    public  HashMap<Character, ArrayList<String>> getSeatarrangement() {
        return seatarrangement;
    }

    public Screen(String name, long numberofseats, HashMap<Character, ArrayList<String>> seats) {
        this.name = name;
        this.numberofseats = numberofseats;
        this.seatarrangement = seats;
    }

    public long getNumberofseats() {
        return numberofseats;
    }

   
    public String getName() {
        return name;
    }

}
