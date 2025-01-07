import java.util.*;

public class BookMyShow {
    private static ArrayList<Admin> adminlist=new ArrayList<Admin>();
    private static ArrayList<User> useList=new ArrayList<User>();
   
    private static HashMap<String, Theatre> theatreandtheatreobj = new HashMap<>();
    private static HashMap<String, Movies> movieandmovieobj = new HashMap<>();

    public static ArrayList<Admin> getAdminlist() {
        return adminlist;
    }

    public static ArrayList<User> getUseList() {
        return useList;
    }

    public static HashMap<String, Movies> getMovieandmovieobj() {
        return movieandmovieobj;
    }
    public static HashMap<String, Theatre> getTheatreandtheatreobj() {
        return theatreandtheatreobj;
    }
    
}
