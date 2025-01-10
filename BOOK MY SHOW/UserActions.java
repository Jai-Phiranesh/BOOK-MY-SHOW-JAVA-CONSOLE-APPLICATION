import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class UserActions {
    public static Scanner scanner = new Scanner(System.in);

    public static User login(Scanner scanner) {
        System.out.print("Enter The Username:");
        String userinput = scanner.nextLine();
        System.out.print("Enter The Password:");
        String passwordinput = scanner.nextLine();
        for (User temp : BookMyShow.getUseList()) {
            if (temp.getUserid().equals(userinput)) {
                if (temp.getPassword().equals(passwordinput)) {
                    return temp;
                } else {
                    return new User(null, null, null);
                }
            }

        }
        return null;
    }

    public static void register(Scanner scanner) {
        System.out.print("NO USER FOUND YOU LIKE TO Register (Y/N):");
        String yesorno1 = scanner.nextLine();
        if (yesorno1.toLowerCase().equals("y")) {
            System.out.print("Enter username:");
            String useridinput = scanner.nextLine();
            System.out.print("Enter password:");
            String password = scanner.nextLine();
            System.out.print("Enter Your Location:");
            String userlocation = scanner.nextLine();
            System.out.print("ARE YOU SURE TO REGISTER (Y/N):");
            String yesorno = scanner.nextLine();
            if (yesorno.toLowerCase().equals("y")) {
                BookMyShow.getUseList().add(new User(useridinput, password, userlocation));
            } else if (yesorno.toLowerCase().equals("n")) {
                return;
            }
        } else if (yesorno1.equals("n") || yesorno1.equals("N")) {
            return;
        }
    }

    public static void operations(User currentuser) {

        UserActions.displayallMovies(currentuser, Utilities.getToday());

        while (true) {
            System.out.println("---------------------------------------------------------" + "\n");
            System.out.println("Enter The User Choice...");
            System.out.println(
                    "------------- 1.Change Location---------------- 2.Change Date 3.        ---------4.Exit ");
            System.out.print("Enter the choice...");
            String choice = scanner.nextLine();
            System.out.println("---------------------------------------------------------" + "\n");
            switch (choice) {

                case "1":
                    UserActions.changeLocation(currentuser);
                    break;
                case "2":
                    UserActions.changeDate(currentuser);
                    break;
                case "3":
                    break;
                case "4":
                    return;
            }
        }
    }

    public static void displayallMovies(User ob, LocalDate today) {
        System.out.println("movies in your location are.");
        String userlocaton = ob.getLocation();
        HashSet<String> moviesinthatlocation = new HashSet<>();
        ArrayList<Movies> moviesavailable = new ArrayList<>();

        for (var movies : BookMyShow.getMovieandmovieobj().keySet()) {
            boolean check=false;
            var moviesarraylist = BookMyShow.getMovieandmovieobj().get(movies);
            for (var movieobject : moviesarraylist) {
                if (movieobject.getLocation().equals(userlocaton) && (movieobject.getStartdate().isEqual(today))) {
                    check=true;
                    
                }

            }
            if(check){
                System.out.println("->"+movies);
                moviesinthatlocation.add(movies);
            }

        }
        System.out.println("Enter the movie name to book");
        String movieChoice = scanner.nextLine();
        if (moviesinthatlocation.contains(movieChoice)) {
            for (var movieobject : BookMyShow.getMovieandmovieobj().get(movieChoice)) {
                if (movieobject.getLocation().equals(userlocaton) && (movieobject.getStartdate().isEqual(today))) {
                    moviesavailable.add(movieobject);
                }

            }

        }

        else {
            System.out.println("Enter the corrent movie name...");
            return;
        }

        showmovies(moviesavailable, movieChoice);

    }

    public static void showmovies(ArrayList<Movies> movieavailable, String moviechoice) {
        System.out.println("\n" +
                "---------------------------------------------------------" + "\n");
        System.out.println("Movie" + moviechoice);
        System.out.println("\n" +
                "---------------------------------------------------------" + "\n");
        HashMap<String,HashSet<Show>>theatreAgainstShow=new HashMap<>();
        for (var currentMovie : movieavailable) {

           
            if(theatreAgainstShow.containsKey(currentMovie.getTheatreob().getTheatername())){
                theatreAgainstShow.get(currentMovie.getTheatreob().getTheatername()).add(currentMovie.getShowob());
            }
            else{
                HashSet<Show> show= new HashSet<>();
                show.add(currentMovie.getShowob());
                theatreAgainstShow.put(currentMovie.getTheatreob().getTheatername(),show);
            }
        }

        for(String keytheatrename:theatreAgainstShow.keySet()){
           System.out.println("Theatre:"+keytheatrename);
            System.out.println("shows are:"+theatreAgainstShow.get(keytheatrename).toString());
        }

        System.out.println("Enter the Theatre name:");
        String theatrename=scanner.nextLine();
        System.out.println("Enter the Show Start Time(09:00):");
       LocalTime showtime = LocalTime.parse(scanner.nextLine(),
                    Utilities.getTimeformatter());
        var showtocheck=theatreAgainstShow.get(theatrename);
        Show currentshow=null;
        for(var shows:showtocheck){
            if(shows.getStarttime().equals(showtime)){
                currentshow=shows;

            }
        }

        if(currentshow==null){
            System.out.println("Enter the correct details:");
            return;
        }

        System.out.println("--------Screen Name-------"+currentshow.getScreen().getName());
        System.out.println("--------Screen Seats Count-------"+currentshow.getScreen().getNumberofseats());
        for(var seats:currentshow.getScreen().getSeatarrangement().entrySet()){
            System.out.println(seats.getKey()+" "+seats.getValue());
        }

        System.out.println("Enter seats to book");
        String seat=scanner.nextLine();
        return;


    }

    public static void changeLocation(User currentuserob) {

        System.out.print("Enter The Location To Change:");
        String locationtochange = scanner.nextLine();
        currentuserob.setLocation(locationtochange);
        displayallMovies(currentuserob, Utilities.getToday());

    }

    public static void changeDate(User currentuserob) {

        System.out.print("Enter The date To Change:");
        LocalDate dateupdate = LocalDate.parse(scanner.nextLine(), Utilities.getFormatter());

        displayallMovies(currentuserob, dateupdate);

    }

}
        