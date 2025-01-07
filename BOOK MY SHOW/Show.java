import java.time.LocalDate;
import java.time.LocalTime;

public class Show {
    private LocalTime starttime;
    private LocalTime endtime;
   


    private LocalDate showDate;
    private String moviename;


    public Show(LocalTime starttime,LocalTime endttime,LocalDate showDate,String moviename){

        this.starttime=starttime;
        this.endtime=endttime;
        this.showDate=showDate;
        this.moviename=moviename;

    }

    public String getMoviename() {
        return moviename;
    }

    public LocalTime getEndtime() {
        return endtime;
    }
    public LocalTime getStarttime() {
        return starttime;
    }
   

    public LocalDate getShowDate() {
        return showDate;
    }
    public void setShowDate(LocalDate showDate) {
        this.showDate = showDate;
    }

    public void setStarttime(LocalTime starttime) {
        this.starttime = starttime;
    }
    public void setEndtime(LocalTime endtime) {
        this.endtime = endtime;
    }
    



}
