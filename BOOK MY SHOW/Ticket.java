import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Ticket {
    private String theatrename;
    private LocalDate date;
    private String screen;
    private LocalTime time;
    private long numberOfSeats;
    private String moviename;
    private long amountpaid;
   private  ArrayList<String>seats=new ArrayList<>();




    Ticket(String theatre,LocalDate date,String screen,LocalTime time,long numberOfSeats,long amount,ArrayList<String>seats,String moviename){
        this.screen=screen;
        this.theatrename=theatre;
        this.date=date;
        this.time=time;
        this.numberOfSeats=numberOfSeats;
        this.seats=seats;
        this.moviename=moviename;
        this.amountpaid=amount;

    }
    public long getAmountpaid() {
        return amountpaid;
    }
    public String getMoviename() {
        return moviename;
    }

    public String getTheatrename() {
        return theatrename;
    }

   public String getScreen() {
       return screen;
   }


   public LocalTime getTime() {
       return time;
   }

    public long getNumberOfSeats() {
        return numberOfSeats;
    }
   public LocalDate getDate() {
       return date;
   }
   public ArrayList<String> getSeats() {
       return seats;
   }
    
}
