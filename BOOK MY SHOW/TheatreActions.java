import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class TheatreActions {
    public static HashMap<String, HashSet<Show>> theatreAgainstShow(ArrayList<Movies> movieAvailable) {
         HashMap<String, HashSet<Show>> theatreAgainstShow = new HashMap<>();
         for (var currentMovie : movieAvailable) {// get all the movie object fields which is entered by the user (only the user location and date)
        //     theatreAgainstShow.computeIfAbsent(currentMovie.getTheatreob().getTheatrename(), k->{ new HashSet<Show>();
        //     theatreAgainstShow.get(currentMovie.getTheatreob().getTheatrename()).add(currentMovie.getShowob());
        // return theatreAgainstShow.get(currentMovie.getTheatreob().getTheatrename());});
        
            if (theatreAgainstShow.containsKey(currentMovie.getTheatreob().getTheatrename())) {// if theatre name already exists
                theatreAgainstShow.get(currentMovie.getTheatreob().getTheatrename()).add(currentMovie.getShowob());// add the show object
            } else {
                HashSet<Show> show = new HashSet<>();// create new hash set
                show.add(currentMovie.getShowob());// add to hash set
                theatreAgainstShow.put(currentMovie.getTheatreob().getTheatrename(), show);// add to hashmap
            }
        }
        return theatreAgainstShow;
    }
}
