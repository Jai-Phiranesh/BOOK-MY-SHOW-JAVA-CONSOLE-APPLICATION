import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class AdminActions {

    public static Admin login(Scanner scanner) {
        System.out.print("Enter The Username:");
        String userinput = scanner.nextLine();
        System.out.print("Enter The Password:");
        String passwordinput = scanner.nextLine();
        for (Admin temp : BookMyShow.getAdminlist()) {
            if (temp.getUserid().equals(userinput)) {
                if (temp.getPassword().equals(passwordinput)) {
                    return temp;
                } else {
                    return new Admin(null, null);
                }
            }

        }
        return null;

    }

    public static void operations(Scanner scanner, Admin currentadmin) throws ParseException {
        while (true) {
            System.out.println("Enter The Admin Choice...");
            System.out.println("\n1.Add Location \n2.Add Theatre\n3.Add movie\n4.Logout");
            System.out.print("Enter the choice...");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    AdminActions.addLocation(scanner);
                    break;
                case "2":
                    AdminActions.addTheatre(scanner);
                    break;
                case "3":
                    AdminActions.addMovie(scanner);
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Enter The Valid Choice");
            }
        }
    }

    public static void addLocation(Scanner scanner) {
        System.out.print("Enter The Location To ADD:");
        String location = scanner.nextLine();
        if (!BookMyShow.getLocationtheatre().containsKey(location.toLowerCase())) {
            BookMyShow.getLocationtheatre().put(location, new ArrayList<Theatre>());
            System.out.println("Successfully added Location:" + location);
        } else {
            System.out.println("Location already exists");
        }
    }

    public static void addTheatre(Scanner scanner) {
        System.out.print("Enter The Location:");
        String location = scanner.nextLine();
        ArrayList<Screen> theatrescreen = new ArrayList<>();
        if (BookMyShow.getLocationtheatre().containsKey(location)) {
            System.out.print("Enter The Theatre Name:");
            String Theatername = scanner.nextLine();
            for (Theatre temp : BookMyShow.getLocationtheatre().get(location)) {
                if (temp.getTheatername().equals(Theatername)) {
                    System.out.println("Theater Already Exists");
                    return;
                }
            }

            System.out.print("Enter The Number Of Screencs:");
            long numberofscreens = Long.parseLong(scanner.nextLine());
            for (int i = 1; i <= numberofscreens; i++) {
                System.out.print("Enter the Screen Name:");
                String screenname = scanner.nextLine();
                System.out.print("Enter the Number of Seats:");
                long numberofseats = Long.parseLong(scanner.nextLine());
                System.out.print("Enter the Screen Grid like (2*5*2)*For space:");
                String grid = scanner.nextLine();
                var seatsandgrid = Utilities.generateGrid(numberofseats, grid);
                System.out.println("The Seats Arrangement Of The Theatre is:");
                for (var entry : seatsandgrid.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
                theatrescreen.add(new Screen(screenname, numberofseats, seatsandgrid));
                System.out.println("Screen Added Successfully....");

            }
            BookMyShow.getLocationtheatre().get(location).add(new Theatre(Theatername, theatrescreen));

        } else {
            System.out.println("NO LOCATIONS FOUND");
        }

    }

    public static void addMovie(Scanner scanner) throws ParseException {
        ArrayList<Date> dateList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print("Enter The Locations Were Movie TO Be Add: ");
        String location = scanner.nextLine();
        ArrayList<Movies> movie = BookMyShow.getLocationmovie().get(location);
        if (movie == null) {
            movie = new ArrayList<>();
        }
        if (!BookMyShow.getLocationtheatre().containsKey(location)) {
            System.out.println("Location Does Not Exists");
            return;
        }
        var locationandtheatres = BookMyShow.getLocationtheatre().get(location);
        OUTER: while (true) {
            System.out.println("Theatres In The Location are..");
            for (var temp : locationandtheatres) {
                System.out.println("->" + temp.getTheatername() + "  Theatre");
            }
            System.out.print("Enter The Theatre To Add The Movie (1.TO Exit The Add Movie):");
            String theatrechoice = scanner.nextLine();
            if (theatrechoice.equals("1")) {
                return;
            }
            Theatre currenttheatre = null;
            int result = 0;
            for (var temp : locationandtheatres) {

                if (temp.getTheatername().equals(theatrechoice)) {
                    currenttheatre = temp;
                    result = 1;
                    break;
                }

            }
            if (result != 1) {
                System.out.println("Enter the correct theatre name..");
                continue OUTER;
            }
            System.out.println("Screens In " + theatrechoice + " Theatre are...");
            for (var temp1 : currenttheatre.getScreenlist()) {
                System.out.println("->" + temp1.getName());
            }
            Screen currentScreen = null;
            int result1 = 0;
            System.out.print("Enter The Screen:");
            String screenchoice = scanner.nextLine();
            for (var temp1 : currenttheatre.getScreenlist()) {
                if (temp1.getName().equals(screenchoice)) {
                    currentScreen = temp1;
                    result1 = 1;
                    break;

                }
            }

            if (result != 1) {
                System.out.println("Enter the correct screen name..");
                continue OUTER;
            }
            System.out.print("Enter The Movie Name:");
            String movinename = scanner.nextLine();
            System.out.println("Enter The Duration:");
            String duration = scanner.nextLine();
            System.out.println("Enter The Date");
            String date = scanner.nextLine();
            Date dateob = dateFormat.parse(date); // Try to parse the date
            dateList.add(dateob);
            movie.add(new Movies(movinename, duration, dateList));
            System.out.println("Movie Added SuccessFully.....");
            BookMyShow.getLocationmovie().put(location, movie);
            break OUTER;

        }
    }

}
