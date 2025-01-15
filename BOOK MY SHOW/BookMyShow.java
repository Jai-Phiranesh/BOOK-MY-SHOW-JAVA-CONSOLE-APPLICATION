import java.util.*;

public class BookMyShow {
    // Book my show pojo class which represents the database
    private static final ArrayList<Admin> ADMIN_LIST = new ArrayList<>();  // admin list store admin object
    private static final ArrayList<User> USER_LIST = new ArrayList<>();    // user list store user object
    
    // theatre name and theatre object hashmap
    private static final HashMap<String, Theatre> THEATRE_MAP = new HashMap<>();
    
    // movie name and movie arraylist of objects to store the same name movie objects in the arraylist
    private static final HashMap<String, ArrayList<Movies>> MOVIE_MAP = new HashMap<>();

    // getters
    public static ArrayList<Admin> getAdminlist() {
        return ADMIN_LIST;
    }

    // getters
    public static ArrayList<User> getUseList() {
        return USER_LIST;
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