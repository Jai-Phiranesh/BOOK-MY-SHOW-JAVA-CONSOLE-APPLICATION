import java.time.LocalDate;
import java.util.Scanner;

public class UserActions {
    public static Scanner scanner=new Scanner(System.in);
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

    public static void operations( User currentuser) {
        
        UserActions.displayallMovies( currentuser,Utilities.getToday());

        while (true) {
            System.out.println("---------------------------------------------------------"+"\n");
            System.out.println("Enter The User Choice...");
            System.out.println("------------- 1.Change Location---------------- 2.Change Date 3.        ---------4.Exit ");
            System.out.print("Enter the choice...");
            String choice = scanner.nextLine();
            System.out.println("---------------------------------------------------------"+"\n");
            switch (choice) {

                case "1":
                    UserActions.changeLocation( currentuser);
                    break;
                case "2":
                    UserActions.changeDate(currentuser);
                    break;
                case "3":
                break;
                case "exit":
                    return;
            }
        }
    }

    public static void displayallMovies( User ob,LocalDate today) {
        System.out.println("movies in your location are.");
        String userlocaton=ob.getLocation();
        for(var movies:BookMyShow.getMovieandmovieobj().keySet()){
            var moviesarraylist=BookMyShow.getMovieandmovieobj().get(movies);
            for(var movieobject:moviesarraylist){
            if(movieobject.getLocation().equals(userlocaton)&& (movieobject.getStartdate().isEqual(today))){
            System.out.println(movies);}
            }}
            System.out.println("Enter the movie name to book:");
            String movieChoice=scanner.nextLine();
        

    }

    public static void changeLocation( User currentuserob) {

        System.out.print("Enter The Location To Change:");
        String locationtochange = scanner.nextLine();

        currentuserob.setLocation(locationtochange);
        displayallMovies( currentuserob,Utilities.getToday());
        

    }

    public static void changeDate( User currentuserob) {

         System.out.print("Enter The date To Change:");
        LocalDate dateupdate = LocalDate.parse(scanner.nextLine(),Utilities.getFormatter());
        
        displayallMovies(currentuserob,dateupdate);

    }

}
