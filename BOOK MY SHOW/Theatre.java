import java.util.ArrayList;

public class Theatre {
    private String Theatername;
    ArrayList<Screen> screenlist=new ArrayList<>();
    public  Theatre(String name,ArrayList<Screen> screens) {
        this.Theatername=name;
        this.screenlist=screens;
    }
    public String getTheatername() {
        return Theatername;
    }
    public ArrayList<Screen> getScreenlist() {
        return screenlist;
    }
}
