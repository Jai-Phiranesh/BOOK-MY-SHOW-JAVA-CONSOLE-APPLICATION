import java.util.ArrayList;
import java.util.HashMap;

public class Movies {
    String name;
     private static HashMap<String, ArrayList<Theatre>> locationmovie = new HashMap<>();


    public  Movies(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
