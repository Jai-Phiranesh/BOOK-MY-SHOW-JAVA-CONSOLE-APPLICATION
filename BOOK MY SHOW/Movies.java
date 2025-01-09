import java.time.LocalDate;


public class Movies {
    String name;
    //  private ArrayList<Date>moviedate=new ArrayList<>();
     private Long duration;//duration of the movie
     private String location;//location of the movie
     private LocalDate startdate;//date of the movie
     private Theatre theatreob;//theatre object were the movie added
     private Screen screenob;//Screen object were the movie added
     private Show showob;//Show object were the movie added

     


    public  Movies(String name,String location,LocalDate startdate,long duration,Theatre ob,Screen screenob,Show showob){
        this.name=name;
        this.duration=duration;
        this.startdate=startdate;
        this.theatreob=ob;
        this.screenob=screenob;
        this.showob=showob;
        this.location=location;
    }//to initialize fields of the class

    public String getName() {
        return name;
    }//getters
    public long getDuration() {
        return duration;
    }//getters
    // public ArrayList<Date> getMoviedate() {
    //     return moviedate;
    // }
  
   public LocalDate getStartdate() {
       return startdate;
   }//getters

   public String getLocation() {
       return location;
   }//getters
   public Screen getScreenob() {
       return screenob;
   }//getters
   public Show getShowob() {
       return showob;
   }//getters
   public Theatre getTheatreob() {
       return theatreob;
   }//getters

   
}