import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

public class Movies {
    String name;
     private static HashMap<String, ArrayList<Theatre>> locationmovie = new HashMap<>();
     private ArrayList<Date>moviedate=new ArrayList<>();
     private String duration;


    public  Movies(String name,String duration,ArrayList<Date>moviedate){
        this.name=name;
        this.duration=duration;
        this.moviedate=moviedate;
    }

    public String getName() {
        return name;
    }
    public String getDuration() {
        return duration;
    }
    public ArrayList<Date> getMoviedate() {
        return moviedate;
    }
}
