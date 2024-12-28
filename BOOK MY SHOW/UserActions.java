import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;
import java.util.Scanner;

public class UserActions {

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
        System.out.println("NO USER FOUND YOU LIKE TO Register (Y/N):");
        String yesorno1 = scanner.nextLine();
        if (yesorno1.toLowerCase().equals("y")) {
            System.out.print("Enter username:");
            String useridinput = scanner.nextLine();
            System.out.print("Enter password:");
            String password = scanner.nextLine();
            System.out.print("Enter Your Location:");
            String userlocation = scanner.nextLine();
            System.out.print("ARE YOU SURE TO REGISTER (Y/N).....");
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

    public static void operations(Scanner scanner, User currentuser) {

        while (true) {
            UserActions.displayallMovies(scanner, currentuser);
            System.out.println("Enter The User Choice...");
            System.out.println("\n1.Change Location \n2.Add\nExit ");
            System.out.print("Enter the choice...");
            String choice = scanner.nextLine();
            switch (choice) {

                case "1":
                    UserActions.changeLocation(scanner, currentuser);
                    break;
                case "2":
                    AdminActions.addTheatre(scanner);
                case "exit":
                    return;
            }
        }
    }

    public static void changeLocation(Scanner scanner, User currentuserob) {
        System.out.println("Available Locations Are:");
        for (var location : BookMyShow.getLocationtheatre().entrySet()) {
            System.out.println(location.getKey());
        }
        System.out.println("Enter The Location To Change:");
        String locationtochange = scanner.nextLine();
        if (BookMyShow.getLocationtheatre().containsKey(locationtochange)) {
            currentuserob.setLocation(locationtochange);
            displayallMovies(scanner, currentuserob);
        } else {
            System.out.println("Enter The Correct Location Your Loaction Is Still" + currentuserob.getLocation());
        }
    }

    public static void displayallMovies(Scanner scanner, User ob) {
        System.out.println("Movies In Your Locations are");
        var locationMovies = BookMyShow.getLocationmovie().get(ob.getLocation());
        if (locationMovies != null) {
            for (var temp : locationMovies) {

                LocalDate today = LocalDate.now();
                for (Date temp1 : temp.getMoviedate()) {
                    LocalDate movieDate = temp1.toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();// java utill date to java time
                    if (today.isEqual(movieDate)) {
                        System.out.println(temp.getName());
                    }

                }

            }
        } else {
            System.out.println("No Movies In Your Location....");
        }

    }
}
