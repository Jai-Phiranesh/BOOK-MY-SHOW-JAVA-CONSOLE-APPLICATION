import java.util.*;

public class BookMyShow {
    // Book my show pojo class which represents the database
     // admin list store admin object
    private  static ArrayList<Account> accountList = new ArrayList<>();    // user list store user object
    
    // theatre name and theatre object hashmap
    private static  HashMap<String, Theatre> THEATRE_MAP = new HashMap<>();
    
    // movie name and movie arraylist of objects to store the same name movie objects in the arraylist
    private static  HashMap<String, ArrayList<Movies>> MOVIE_MAP = new HashMap<>();
public static ArrayList<Account> getAccountList() {
    return accountList;
}
    // getters
    public static HashMap<String, ArrayList<Movies>> getMovieandmovieobj() {
        return MOVIE_MAP;
    }

    // getters
    public static HashMap<String, Theatre> getTheatreandtheatreobj() {
        return THEATRE_MAP;
    }
}