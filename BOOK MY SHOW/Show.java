import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Show {// show pojo class
    private LocalTime starttime;// start time of show
    private LocalTime endtime;// endtime of show

    private long price;
    private LocalDate showDate;// show date
    private String moviename;// name of the movie in that time
    private Screen screen;
    private HashMap<Character, ArrayList<String>> seatarrangement = new HashMap<>();// seat pattern hash map ,row name
                                                                                    // and seats

    public HashMap<Character, ArrayList<String>> getSeatarrangement() {
        return seatarrangement;
    }//getters

    public Show(LocalTime starttime, LocalTime endttime, LocalDate showDate, String moviename, Screen screenob,
            long price, HashMap<Character, ArrayList<String>> seatarrangement) {

        this.starttime = starttime;
        this.endtime = endttime;
        this.showDate = showDate;
        this.moviename = moviename;
        this.screen = screenob;
        this.price = price;
        this.seatarrangement=seatarrangement;

    }//constructor to initialize the fields

    public long getPrice() {
        return price;
    }//getters

    public String getMoviename() {
        return moviename;
    }// getters

    public LocalTime getEndtime() {
        return endtime;
    }// getters

    public LocalTime getStarttime() {
        return starttime;
    }// getters

    public Screen getScreen() {
        return screen;
    }// getters

    public LocalDate getShowDate() {
        return showDate;
    }// getters

    public void setShowDate(LocalDate showDate) {
        this.showDate = showDate;
    }// setters

    public void setStarttime(LocalTime starttime) {
        this.starttime = starttime;
    }// setters

    public void setEndtime(LocalTime endtime) {
        this.endtime = endtime;
    }// setters


    public void setSeatarrangement(HashMap<Character, ArrayList<String>> seatarrangement) {
        this.seatarrangement = seatarrangement;
    }//setteres

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            ;
        Show shows = (Show) obj;
        return Objects.equals(this.starttime, shows.starttime) && Objects.equals(this.endtime, shows.endtime)
                && Objects.equals(this.showDate, shows.showDate);// to for same timing of the show
    }

    @Override
    public int hashCode() {// Object method overrided

        return Objects.hash(starttime, endtime, showDate);
    }// generate hashcode for the given object

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return starttime.toString() + "-" + endtime.toString();
    }//to print the start and end time of show

}
