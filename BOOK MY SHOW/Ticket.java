import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Ticket {//Ticket class to store the ticket fields of the user
    private String theatreName;//theatre name where the ticket is booked
    private LocalDate date;//date when the ticket is booked
    private String screen;//Screen name where the ticket is booked
    private LocalTime time;//timing of the show  
    private long numberOfSeats;//number of seats booked
    private String movieName;//movie name
    private long amountPaid;//total amount paid for the ticket
    private ArrayList<String> seats = new ArrayList<>();//seats booked by the user

    Ticket(String theatre, LocalDate date, String screen, LocalTime time, long numberOfSeats, long amount, ArrayList<String> seats, String movieName) {
        this.screen = screen;
        this.theatreName = theatre;
        this.date = date;
        this.time = time;
        this.numberOfSeats = numberOfSeats;
        this.seats = seats;
        this.movieName = movieName;
        this.amountPaid = amount;
    }//constructor to initialize the fields

    public long getAmountPaid() {
        return amountPaid;
    }//getters

    public String getMovieName() {
        return movieName;
    }//getters

    public String getTheatreName() {
        return theatreName;
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
