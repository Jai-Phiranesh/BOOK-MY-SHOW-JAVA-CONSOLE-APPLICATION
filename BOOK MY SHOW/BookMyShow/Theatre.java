

package BookMyShow;
import java.util.HashMap;

public class Theatre {//theatre pojo class
    private String theatreName;//theatre name
    private String location;//theatre location
    private  HashMap<String, Screen> screenNameAndObject = new HashMap<>();//screen name and screen object of theatre

    public  Theatre(String location,String name) {
         this.theatreName=name;         
         this.location=location;       
    }//constructor to initialize location and name

    public String getTheatrename() {
         return theatreName;
    }//getters

    public  HashMap<String, Screen> getScreenNameandScreenObject() {
         return screenNameAndObject;
    }//getters
    
    public String getLocation() {
         return location;
    }//getters
}