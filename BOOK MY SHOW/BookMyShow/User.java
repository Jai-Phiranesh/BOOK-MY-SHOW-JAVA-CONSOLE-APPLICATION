
package BookMyShow;
import java.util.ArrayList;

public class User extends Account{//User pojo class
   
    
    private String location;//user location
    private ArrayList<Ticket>tickets=new ArrayList<>();//store the user tickets

   

    public ArrayList<Ticket> getTickets() {//getters
        return tickets;
    }
    public String getLocation() {//getters
        return location;
    }
    public void setLocation(String location) {//setters
        this.location = location;
    }


    public User(String id,String pass,String location){//constructor to initialize the id and pass and location
       super(id, pass);
        this.location=location;
        
    }

    
}