import java.time.LocalDate;


public class Movies {
    String name;
    //  private ArrayList<Date>moviedate=new ArrayList<>();
     private Long duration;
     private String location;
     private LocalDate startdate;
     private Theatre theatreob;
     private Screen screenob;
     private Show showob;

     


    public  Movies(String name,String location,LocalDate startdate,long duration,Theatre ob,Screen screenob,Show showob){
        this.name=name;
        this.duration=duration;
        this.startdate=startdate;
        this.theatreob=ob;
        this.screenob=screenob;
        this.showob=showob;
        this.location=location;
    }

    public String getName() {
        return name;
    }
    public long getDuration() {
        return duration;
    }
    // public ArrayList<Date> getMoviedate() {
    //     return moviedate;
    // }
  
   public LocalDate getStartdate() {
       return startdate;
   }

   public String getLocation() {
       return location;
   }
   public Screen getScreenob() {
       return screenob;
   }
   public Show getShowob() {
       return showob;
   }
   public Theatre getTheatreob() {
       return theatreob;
   }

   
}