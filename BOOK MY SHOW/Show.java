import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Show {// show pojo class
    private LocalTime startTime;// start time of show
    private LocalTime endTime;// endtime of show
    private long price;
    private LocalDate showDate;// show date
    private String movieName;// name of the movie in that time
    private Screen screen;
    private HashMap<Character, ArrayList<String>> seatArrangement = new HashMap<>();// seat pattern hash map, row name and seats

    public Show(LocalTime startTime, LocalTime endTime, LocalDate showDate, String movieName, Screen screen,long price, HashMap<Character, ArrayList<String>> seatArrangement) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.showDate = showDate;
        this.movieName = movieName;
        this.screen = screen;
        this.price = price;
        this.seatArrangement = seatArrangement;
    }

    public HashMap<Character, ArrayList<String>> getSeatarrangement() {
        return seatArrangement;
    }

    public long getPrice() {
        return price;
    }

    public String getMoviename() {
        return movieName;
    }

    public LocalTime getEndtime() {
        return endTime;
    }

    public LocalTime getStarttime() {
        return startTime;
    }

    public Screen getScreen() {
        return screen;
    }

    public LocalDate getShowDate() {
        return showDate;
    }

    public void setShowDate(LocalDate showDate) {
        this.showDate = showDate;
    }

    public void setStarttime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndtime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setSeatarrangement(HashMap<Character, ArrayList<String>> seatArrangement) {
        this.seatArrangement = seatArrangement;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;   
        }
        Show shows = (Show) obj;
        return Objects.equals(this.startTime, shows.startTime) 
            && Objects.equals(this.endTime, shows.endTime)
            && Objects.equals(this.showDate, shows.showDate);// to for same timing of the show
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime, showDate);
    }

    @Override
    public String toString() {
        return startTime.toString() + "-" + endTime.toString();
    }
}