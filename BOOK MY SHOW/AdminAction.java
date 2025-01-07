
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class AdminAction {
    public static Scanner scanner = new Scanner(System.in);

    public static Admin login() {
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

    public static void operations(Admin currentadmin) {
        while (true) {
            System.out.println("\n" + "---------------------------------------------------------" + "\n");
            System.out.println("Enter The Admin Choice...");
            System.out.println(
                    "--------------1.Add Movie --------------2.view all Movies --------------3.Add Theatre ----------4.view all theatre------------5.Logout\n");
            System.out.print("Enter the choice...");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    AdminAction.addMovie();

                    break;
                case "2":
                     AdminAction.viewallmovies();

                case "3":
                    AdminAction.addTheatre();
                    break;
                case "4":
                    AdminAction.viewalltheatre();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Enter The Valid Choice");
            }
        }
    }

    public static void addMovie()  {
        System.out.println("\n" + "---------------------------------------------------------" + "\n");
        System.out.print("Enter Movie Name:");
        String moviename = scanner.nextLine();
        System.out.print("Enter Location:");
        String movielocation = scanner.nextLine();
        int result = 0;
        for (var temp : BookMyShow.getTheatreandtheatreobj().keySet()) {
            var checktheatreLocation = BookMyShow.getTheatreandtheatreobj().get(temp);

            if (checktheatreLocation.getLocation().equals(movielocation)) {
                result += 1;
            }

        }
        if (result == 0) {
            System.out.println("No  theatre in that location add thetre in that location");
            return;
        }

        System.out.println("Theatres in the location you entered are..");
        for (var temp : BookMyShow.getTheatreandtheatreobj().keySet()) {
            var printtheatre = BookMyShow.getTheatreandtheatreobj().get(temp);

            System.out.println("->" + printtheatre.getTheatername());
        }
        


        System.out.print("Enter The Theatre Name:");
        String choicetheatre = scanner.nextLine();
        Theatre currenttheatre = null;
        System.out.print("Enter  Date:");

        LocalDate datestart = LocalDate.parse(scanner.nextLine(), Utilities.getFormatter());
      
        for (var temp : BookMyShow.getTheatreandtheatreobj().keySet()) {
            currenttheatre = BookMyShow.getTheatreandtheatreobj().get(temp);
            if (currenttheatre.getTheatername().equals(choicetheatre)) {
                break;
            } else {
                System.out.println("Incorrect theatre name");
                return;
            }
        }

        while (true) {

            System.out.println("Screens In The Theatre are...");
            for (String scnmae : currenttheatre.getScreennameandobj().keySet()) {
                System.out.println("*-> " + scnmae);
            }

            System.out.print("Enter Screen Name:");
            String choicescreen = scanner.nextLine();
            for (var screen : currenttheatre.getScreennameandobj().keySet()) {
                if (screen.equals(choicescreen)) {
                    var currentscreen = currenttheatre.getScreennameandobj().get(screen);
                    System.out.print("Enter The Show start time: ");
                    LocalTime choicestarttime = LocalTime.parse(scanner.nextLine(),
                            Utilities.getTimeformatter());
                    System.out.print("Enter The Duration of movie:");
                    long choiceduration = Long.parseLong(scanner.nextLine());
                    if (currentscreen.getShows().isEmpty()) {
                        var currentshow = new Show(choicestarttime, choicestarttime.plusMinutes(choiceduration + 30),
                                datestart, moviename);
                        currentscreen.getShows().add(currentshow);
                        BookMyShow.getMovieandmovieobj().put(moviename, new Movies(moviename, movielocation, datestart,
                                choiceduration, currenttheatre, currentscreen, currentshow));
                        System.out.println("added successfully");
                        return;

                    }
                    for (var show : currentscreen.getShows()) {
                        if (show.getShowDate().isEqual(datestart)) {
                            if (choicestarttime.isAfter(show.getEndtime())
                                    && choicestarttime.isBefore(show.getStarttime())) {
                                var currentshow = new Show(choicestarttime,
                                        choicestarttime.plusMinutes(choiceduration + 30), datestart, moviename);
                                currentscreen.getShows().add(currentshow);
                                BookMyShow.getMovieandmovieobj().put(moviename, new Movies(moviename, movielocation,
                                        datestart, choiceduration, currenttheatre, currentscreen, currentshow));
                                System.out.println("added successfully");
                                return;

                            }
                            else{
                                System.out.println("Show Already Filled");
                                return;
                            }
                        }
                    }

                }

            }
            

        }
    }

    public static void viewallmovies() {

    if (BookMyShow.getMovieandmovieobj().isEmpty()) {
        System.out.println("No movies in the App..");
    return ;

    }
    for (var movie : BookMyShow.getMovieandmovieobj().keySet()) {
        var currentMovie=BookMyShow.getMovieandmovieobj().get(movie);
    System.out.println("\n" +
    "---------------------------------------------------------" + "\n");
   System.out.println("Movie Name:"+currentMovie.getName());
   System.out.println("Movie Duration:"+currentMovie.getDuration());
   System.out.println("Movie Date:"+currentMovie.getShowob().getShowDate());
   System.out.println("Theatre Name:"+currentMovie.getTheatreob().getTheatername());
   System.out.println("Theatre Location:"+currentMovie.getTheatreob().getLocation());
   System.out.println("Theatre Screen:"+currentMovie.getScreenob().getName());
   System.out.println("Seat arrangement :"+currentMovie.getScreenob().getSeatarrangement());
   System.out.println("Show start Time:"+currentMovie.getShowob().getStarttime());
   System.out.println("Show end Time:"+currentMovie.getShowob().getEndtime());

    System.out.println("\n" +
    "---------------------------------------------------------" + "\n");
    }

    }

    public static void addTheatre() {
        System.out.println("\n" + "---------------------------------------------------------" + "\n");
        System.out.print("Enter The Theatre Name:");
        String theatername = scanner.nextLine();

        System.out.print("Enter The Location of Theatre :");
        String theatreLocation = scanner.nextLine();
        for (var temp : BookMyShow.getTheatreandtheatreobj().keySet()) {
            var currenttheatre = BookMyShow.getTheatreandtheatreobj().get(temp);
            if (temp.equals(theatername) && currenttheatre.getLocation().equals(theatreLocation)) {
                System.out.println("Theatre Already exists in that Location....");
                return;
            }

        }
        Theatre curenTheatreadding = new Theatre(theatreLocation, theatername);
        BookMyShow.getTheatreandtheatreobj().put(theatername, curenTheatreadding);
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

            curenTheatreadding.getScreennameandobj().put(screenname,
                    new Screen(screenname, numberofseats, seatsandgrid));

            System.out.println("Screen Added Successfully....");

        }

    }

    public static void viewalltheatre() {

        for (var temp : BookMyShow.getTheatreandtheatreobj().keySet()) {
            System.out.println("---------------------------------------------------------\n");
            var theatre = BookMyShow.getTheatreandtheatreobj().get(temp);

            System.out.println("Theatre Name is.." + theatre.getTheatername());
            System.out.println("Theatre Location is.." + theatre.getLocation());
            System.out.println("screens are ");
            for (var temp1 : theatre.getScreennameandobj().entrySet()) {
                System.out.println("---------------------------------------------------------\n");
                System.out.println("Screen Name:" + temp1.getKey());
                System.out.println("Number of seats:" + temp1.getValue().getNumberofseats());
                System.out.println("Screen pattern:" + temp1.getValue().getSeatarrangement());

            }
        }

    }

}
