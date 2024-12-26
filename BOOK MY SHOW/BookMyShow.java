import java.util.ArrayList;

public class BookMyShow {
    private static ArrayList<Admin> adminlist=new ArrayList<Admin>();
    private static ArrayList<User> useList=new ArrayList<User>();


    public static ArrayList<Admin> getAdminlist() {
        return adminlist;
    }

    public static ArrayList<User> getUseList() {
        return useList;
    }
    
}
