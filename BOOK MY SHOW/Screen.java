import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Screen {
    private  String name;            // screen name
    private  long numberOfSeats;     // number of seats in that screen
    private  long availableSeats;   //available seats while booking
    private  HashSet<Show> shows = new HashSet<>();    // show arraylist stores all the show object of that screen
    private  String grid;
    
    private HashMap<Character, ArrayList<String>> seatArrangement = new HashMap<>();    // seat pattern hash map, row name and seats

    public Screen(String name, long numberOfSeats, HashMap<Character, ArrayList<String>> seats, String grid) {
        this.name = name;
        this.numberOfSeats = numberOfSeats;
        this.seatArrangement = seats;
        this.availableSeats = numberOfSeats;
        this.grid = grid;
    }

    public void setAvailableseats(long availableSeats) {//getters
        this.availableSeats = availableSeats;
    }

    public long getAvailableseats() {//getters
        return availableSeats;
    }

    public HashSet<Show> getShows() {//getters
        return shows;
    }

    public HashMap<Character, ArrayList<String>> getSeatarrangement() {//getters
        return seatArrangement;
    }

    public long getNumberofseats() {//getters
        return numberOfSeats;
    }

    public String getGrid() {//getters
        return grid;
    }

    public String getName() {//getters
        return name;
    }

    public void setSeatarrangement(HashMap<Character, ArrayList<String>> seatArrangement) {//setters
        this.seatArrangement = seatArrangement;
    }
}