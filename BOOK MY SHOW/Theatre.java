
import java.util.HashMap;

public class Theatre {
    private String theatername;
    private String location;
    private  HashMap<String, Screen> screennameandobj = new HashMap<>();
    public  Theatre(String location,String name) {
        this.theatername=name;
       
        this.location=location;


    }
    public String getTheatername() {
        return theatername;
    }
    public  HashMap<String, Screen> getScreennameandobj() {
        return screennameandobj;
    }

    public String getLocation() {
        return location;

    }
}
