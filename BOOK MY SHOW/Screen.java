import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Screen {
     private String name;//screen name
    private long numberofseats;//number of seats in that screen
    private long availableseats=numberofseats;
    private HashSet<Show> shows = new HashSet<Show>();//show arraylist stores all the show object of that screen


    public void setAvailableseats(long availableseats) {
        this.availableseats = availableseats;
    }

    public long getAvailableseats() {
        return availableseats;
    }
    private  HashMap<Character, ArrayList<String>> seatarrangement = new HashMap<>();//seat pattern hash map ,row name and seats
   
    public HashSet<Show> getShows() {
        return shows;
    }//getters
    public  HashMap<Character, ArrayList<String>> getSeatarrangement() {
        return seatarrangement;
    }//getters

    public Screen(String name, long numberofseats, HashMap<Character, ArrayList<String>> seats) {
        this.name = name;
        this.numberofseats = numberofseats;
        this.seatarrangement = seats;
        this.availableseats=numberofseats;
    }//constructors Screen to initialize fields of the class

    public long getNumberofseats() {
        return numberofseats;
    }//getters

   
    public String getName() {
        return name;
    }//getters
    public void setSeatarrangement(HashMap<Character, ArrayList<String>> seatarrangement) {
        this.seatarrangement = seatarrangement;
    }
}
