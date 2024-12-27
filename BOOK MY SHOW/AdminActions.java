import java.util.ArrayList;
import java.util.Scanner;

public class AdminActions {

    public static Admin login(final Scanner scanner) {
        System.out.print("Enter The Username:");
        final String userinput = scanner.nextLine();
        System.out.print("Enter The Password:");
        final String passwordinput = scanner.nextLine();
        for (final Admin temp : BookMyShow.getAdminlist()) {
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

    public static void operations(final Scanner scanner, final Admin currentadmin) {
        while (true) {
            System.out.println("\n1.Add Location \n2.Add Theatre\n3.Add movie\n4Logout");
            System.out.print("Enter the choice...");
            final String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    AdminActions.addLocation(scanner);
                    break;
                case "2":
                    AdminActions.addTheatre(scanner);
                    break;
                case "3":
                    AdminActions.addMovie(scanner);
                case "4":
                    return;
            }
        }
    }

    public static void addLocation(final Scanner scanner) {
        System.out.print("Enter The Location To ADD:");
        final String location = scanner.nextLine();
        if (!BookMyShow.getLocationtheatre().containsKey(location.toLowerCase())) {
            BookMyShow.getLocationtheatre().put(location, new ArrayList<Theatre>());
            System.out.println("Successfully added Location:" + location);
        } else {
            System.out.println("Location already exists");
        }
    }

    public static void addTheatre(final Scanner scanner) {
        System.out.print("Enter The Location:");
        final String location = scanner.nextLine();
        final ArrayList<Screen> theatrescreen = new ArrayList<>();
        if (BookMyShow.getLocationtheatre().containsKey(location)) {
            System.out.print("Enter THE THEATRE NAME:");
            final String Theatername = scanner.nextLine();
            for (final Theatre temp : BookMyShow.getLocationtheatre().get(location)) {
                if (temp.getTheatername().equals(Theatername)) {
                    System.out.println("Theater Already Exists");
                    return;
                }
            }

            System.out.print("Enter The Number Of Screencs:");
            final long numberofscreens = Long.parseLong(scanner.nextLine());
            for (int i = 1; i <= numberofscreens; i++) {
                System.out.print("Enter the Screen Name:");
                final String screenname = scanner.nextLine();
                System.out.print("Enter the Number of Seats:");
                final long numberofseats = Long.parseLong(scanner.nextLine());
                System.out.print("Enter the Screen Grid like (2*5*2)*For space:");
                final String grid = scanner.nextLine();
                final var seatsandgrid = Utilities.generateGrid(numberofseats, grid);
                System.out.println("The Seats Arrangement Of The Theatre is:");
                for (final var entry : seatsandgrid.entrySet()) {
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

    public static void addMovie(Scanner scanner) {
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
        for (var temp : locationandtheatres) {

            System.out.println("->"+temp.getTheatername());
        }

        OUTER: while (true) {
            System.out.println("Enter The Theatre To Add The Movie");
            String theatrechoice = scanner.nextLine();

            for (var temp : locationandtheatres) {

                if (!temp.getTheatername().equals(theatrechoice)) {
                    System.out.println("Error Enter The Correct Theatre Name....");
                    continue OUTER;
                }
                System.out.println("Screens In " + theatrechoice + "are...");
                for (var temp1 : temp.getScreenlist()) {
                    System.out.println("->"+temp1.getName());
                }
                System.out.print("Enter The Screen:");
                String screenchoice = scanner.nextLine();
                for (var temp1 : temp.getScreenlist()) {
                    if (temp1.getName().equals(screenchoice)) {
                        System.out.print("Enter The Movie Name:");
                        String movinename = scanner.nextLine();
                        // System.out.println("Enter The Duration:");
                        // String duration = scanner.nextLine();
                        // System.out.println("Enter The Date");
                        // String date = scanner.nextLine();
                        movie.add(new Movies(movinename));
                        System.out.println("Movie Added SuccessFully.....");
                        break OUTER;

                    } else {
                        System.out.println("Enter The Correct Screen.");
                        continue OUTER;
                    }

                }

            }
        }
        BookMyShow.getLocationmovie().put(location, movie);

    }

}
