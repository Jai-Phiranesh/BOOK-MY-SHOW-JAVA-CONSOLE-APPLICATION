import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Show  {//show pojo class
    private LocalTime starttime;//start time of show
    private LocalTime endtime;//endtime of show
   


    private LocalDate showDate;//show date
    private String moviename;//name of the movie in that time


    public Show(LocalTime starttime,LocalTime endttime,LocalDate showDate,String moviename){

        this.starttime=starttime;
        this.endtime=endttime;
        this.showDate=showDate;
        this.moviename=moviename;

    }

    public String getMoviename() {
        return moviename;
    }//getters

    public LocalTime getEndtime() {
        return endtime;
    }//getters
    public LocalTime getStarttime() {
        return starttime;
    }//getters
   

    public LocalDate getShowDate() {
        return showDate;
    }//getters
    public void setShowDate(LocalDate showDate) {
        this.showDate = showDate;
    }//setters

    public void setStarttime(LocalTime starttime) {
        this.starttime = starttime;
    }//setters
    public void setEndtime(LocalTime endtime) {
        this.endtime = endtime;
    }//setters

    @Override
    public boolean equals(Object obj) {
        if(obj==null||getClass()!=obj.getClass());
        Show shows =(Show)obj;
        return Objects.equals(this.starttime,shows.starttime)&&Objects.equals(this.endtime, shows.endtime)&&Objects.equals(this.showDate, shows.showDate);//for same timing of the show 
    }

    @Override
    public int hashCode() {//Object method overrided
        
        return Objects.hash(starttime,endtime,showDate);
    }//generate hashcode for the fiven object



}
