
import java.util.HashMap;

public class Theatre {//theatre pojo class
    private String theatername;//theatre name
    private String location;//theatre location
    private  HashMap<String, Screen> screennameandobj = new HashMap<>();//screen name and screen object of theatre
    public  Theatre(String location,String name) {
        this.theatername=name;
       
        this.location=location;


    }//constructor to initialize location and name
    public String getTheatername() {
        return theatername;
    }//getters
    public  HashMap<String, Screen> getScreennameandobj() {
        return screennameandobj;
    }//getters

    public String getLocation() {
        return location;

    }//getters
}