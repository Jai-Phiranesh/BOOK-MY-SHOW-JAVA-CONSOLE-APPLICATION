import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Ticket {//Ticket class to store the ticket fields of the user
    private String theatrename;//theatre name were the ticket booked
    private LocalDate date;//date  when the ticket booked
    private String screen;//Screen name were the ticket booked
    private LocalTime time;//timing of the show  
    private long numberOfSeats;//number of geats booked
    private String moviename;//movie name
    private long amountpaid;//total amount paid for the ticket
   private  ArrayList<String>seats=new ArrayList<>();//seats booked by the user




    Ticket(String theatre,LocalDate date,String screen,LocalTime time,long numberOfSeats,long amount,ArrayList<String>seats,String moviename){
        this.screen=screen;
        this.theatrename=theatre;
        this.date=date;
        this.time=time;
        this.numberOfSeats=numberOfSeats;
        this.seats=seats;
        this.moviename=moviename;
        this.amountpaid=amount;

    }//constructor to initialize the fields
    public long getAmountpaid() {
        return amountpaid;
    }//getters
    public String getMoviename() {
        return moviename;
    }//getters

    public String getTheatrename() {
        return theatrename;
    }//getters

   public String getScreen() {
       return screen;
   }//getters


   public LocalTime getTime() {
       return time;
   }//getters

    public long getNumberOfSeats() {
        return numberOfSeats;
    }//getters
   public LocalDate getDate() {
       return date;
   }//getters
   public ArrayList<String> getSeats() {
       return seats;
   }//getters
    
}
