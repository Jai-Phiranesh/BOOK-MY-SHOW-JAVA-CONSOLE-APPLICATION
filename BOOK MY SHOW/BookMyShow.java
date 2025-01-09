import java.util.*;

public class BookMyShow {//Book my show pojo class which represents the database
    private static ArrayList<Admin> adminlist=new ArrayList<Admin>();//admin list store admin object
    private static ArrayList<User> useList=new ArrayList<User>();//user list store user object
   
    private static HashMap<String, Theatre> theatreandtheatreobj = new HashMap<>();//theatre name and theatre object hashmap
    private static HashMap<String, ArrayList<Movies>> movieandmovieobj = new HashMap<>();//movie name and movie arraylist of objects to store the same name movie objects in the arraylist

    public static ArrayList<Admin> getAdminlist() {//getters
        return adminlist;
    }

    public static ArrayList<User> getUseList() {//getters
        return useList;
    }

   public static HashMap<String, ArrayList<Movies>> getMovieandmovieobj() {//getters
       return movieandmovieobj;
   }
    public static HashMap<String, Theatre> getTheatreandtheatreobj() {//getters
        return theatreandtheatreobj;
    }
    
}
