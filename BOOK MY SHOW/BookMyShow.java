import java.util.ArrayList;
import java.util.HashMap;

public class BookMyShow {
    private static ArrayList<Admin> adminlist=new ArrayList<Admin>();
    private static ArrayList<User> useList=new ArrayList<User>();
    private static HashMap<String, ArrayList<Theatre>> locationtheatre = new HashMap<>();
    private static HashMap<String, ArrayList<Movies>> locationmovie = new HashMap<>();
    public static Object getLocationmovie;

public static HashMap<String, ArrayList<Movies>> getLocationmovie() {
    return locationmovie;
}

    public static HashMap<String, ArrayList<Theatre>> getLocationtheatre() {
        return locationtheatre;
    }

    public static ArrayList<Admin> getAdminlist() {
        return adminlist;
    }

    public static ArrayList<User> getUseList() {
        return useList;
    }
    
}
