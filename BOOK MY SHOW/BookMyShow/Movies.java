
package BookMyShow;
import java.time.LocalDate;

public class Movies {
    private  String name; // name of the movie
    // private  Long duration; // duration of the movie
    private  String location; // location of the movie
    private  LocalDate startDate;// date of the movie
    private  Theatre theatre; // theatre object where the movie is added
    private  Screen screen; // Screen object where the movie is added
    private  Show show; // Show object where the movie is added

    public Movies(String name,String location, LocalDate startDate,long duration, Theatre theatre,Screen screen, Show show) {
        this.name = name;
        // this.duration = duration;
        this.location = location;
        this.startDate = startDate;
        this.theatre = theatre;
        this.screen = screen;
        this.show = show;
    }

    // Getters
    public String getName() {
        return name;
    }

    // public Long getDuration() {
    // return duration;
    // }

    public LocalDate getStartdate() {
        return startDate;
    }

    public String getLocation() {
        return location;
    }

    public Screen getScreenob() {
        return screen;
    }

    public Show getShowob() {
        return show;
    }

    public Theatre getTheatreob() {
        return theatre;
    }
}