import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminAction {
    private static  Scanner scanner = new Scanner(System.in);

    public static Admin login() {
        System.out.print("Enter The Username:");// get usernmae
        String userId = scanner.nextLine();
        System.out.print("Enter The Password:");// get password
        String passwordInput = scanner.nextLine();
        for (Admin admin : BookMyShow.getAdminlist()) {// check the id and pass
            if (admin.getUserid().equals(userId)) {
                if (admin.getPassword().equals(passwordInput)) {
                    return admin;// current admin object for successfull login
                }
            }
        }
        return null;
    }

    public static void operations(Admin currentAdmin) {
        while (true) {
            System.out.println("\n" + "---------------------------------------------------------" + "\n");
            System.out.println("Enter The Admin Choice...");
            System.out.println(
                    "--------------1.Add Movie --------------2.view all Movies --------------3.Add Theatre ----------4.view all theatre------------5.Logout\n");
            System.out.print("Enter the choice...");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    AdminAction.addMovie();// based on the choice call the function (1)
                    break;
                case "2":
                    AdminAction.viewallmovies();// based on the choice call the function (2)
                    break;
                case "3":
                    AdminAction.addTheatre();// based on the choice call the function (3)
                    break;
                case "4":
                    AdminAction.viewalltheatre();// based on the choice call the function (4)
                    break;
                case "5":
                    return;// exit
                default:
                    System.out.println("Enter The Valid Choice");// for invalid choice
            }
        }
    }

    public static void addMovie() {
        System.out.println("\n" + "---------------------------------------------------------" + "\n");
        System.out.print("Enter Movie Name:");// get movie name and location
        String movieName = scanner.nextLine();
        System.out.print("Enter Location:");//get location
        String movieLocation = scanner.nextLine();
        System.out.print("Enter  Date:");// get the date as input

        

        LocalDate dateStart = LocalDate.parse(scanner.nextLine(), Utilities.getFormatter());// LocalDate class used and formatted the date as in utilities
        boolean checkTheatreinThatlocation = false;// number of theatre in that locations

        if(dateStart.isBefore(Utilities.getToday())){
            System.out.println("Cant Add the Movie for the Past");
            return;
        }//to avoid the case of adding movie in the past
        
        ArrayList<Theatre> theatreInThatLocation=new ArrayList<>();
        for (String theatreName : BookMyShow.getTheatreandtheatreobj().keySet()) {
            Theatre theatreInLocation = BookMyShow.getTheatreandtheatreobj().get(theatreName);// get theatre object 
            System.out.println("Theatres in the location you entered are..");
            if (theatreInLocation.getLocation().equals(movieLocation)) {//check the theatres in that location
                
               
                System.out.println("->" + theatreInLocation.getTheatrename());
                theatreInThatLocation.add(theatreInLocation);
                // check location already exists
                checkTheatreinThatlocation = true;
            }
        }
        
        if (!checkTheatreinThatlocation) {// if no theatre in that location
            System.out.println("No theatre in that location add theatre in that location");
            return;
        }

        Theatre currentTheatre = null;// to get the current looping theatre

        theatre: while (true) {
            System.out.print("Enter The Theatre Name:");
            String choiceTheatre = scanner.nextLine();

            for (var theatreObjects :theatreInThatLocation) {// check the input theatre name is correct or already exists in that location
                if (theatreObjects.getTheatrename().equals(choiceTheatre)) {
                    currentTheatre=theatreObjects;
                    break theatre;
                }} 
                    System.out.print("Incorrect theatre name Enter again by pressing any key or press (N) to exit:");
                    String choice = scanner.nextLine();
                    if (choice.toLowerCase().equals("n")) {
                        return;
                    }
                    continue theatre;
                
        }

        Screen currentScreen = null;// store current screen
        screen: while (true) {
            System.out.println("Screens In The Theatre are...");// displaying screens in the theatre
            for (String screenName : currentTheatre.getScreenNameandScreenObject().keySet()) {
                System.out.println("*-> " + screenName);
            }

            System.out.print("Enter Screen Name:");
            String choiceScreen = scanner.nextLine();//get the screen name

            for (String screen : currentTheatre.getScreenNameandScreenObject().keySet()) {// check the entered screen is in theatre
                if (screen.equals(choiceScreen)) {
                    currentScreen = currentTheatre.getScreenNameandScreenObject().get(screen);
                    break screen ;
                }
            }
            
                System.out.print("Incorrect Screen name Enter by pressing any key or press (N) to exit:");//to reenter the choice
                String choice = scanner.nextLine();
                if (choice.toLowerCase().equals("n")) {
                    return;
                }
                continue screen;
            
            

           
        }

        show: while(true) {
            System.out.print("Enter The Show start time(HH:mm): ");// get the show start time
            LocalTime choiceStartTime = LocalTime.parse(scanner.nextLine(), Utilities.getTimeformatter());// Using localTime store the time and format is (HH:mm)
            System.out.print("Enter The Duration of movie:");
            long choiceDuration = Long.parseLong(scanner.nextLine());// get the duration of the movie
            LocalTime endTime = choiceStartTime.plusMinutes(choiceDuration + 30);// end time is starttime +duration and 30 minutes for the interval

            System.out.print("Enter the price for the Show:");
            long price = Long.parseLong(scanner.nextLine());

            Show currentShow = null;// store current show

            if (currentScreen.getShows().isEmpty()) {// if show class is empty add directly
                currentShow = new Show(choiceStartTime, choiceStartTime.plusMinutes(choiceDuration + 30),
                        dateStart, currentScreen, price, currentScreen.getSeatarrangement());//create new show object
                currentScreen.getShows().add(currentShow);//add to the arraylist
                if (!BookMyShow.getMovieandmovieobj().containsKey(movieName)) {
                    BookMyShow.getMovieandmovieobj().put(movieName, new ArrayList<Movies>());
                }//if new movie name add the arraylist of movie object
                BookMyShow.getMovieandmovieobj().get(movieName).add(new Movies(movieName, movieLocation, dateStart,
                        choiceDuration, currentTheatre, currentScreen, currentShow));//add to the movie arraylist
                System.out.println("added successfully");
                return;
            }

            for (Show show : currentScreen.getShows()) {// if show is already there
               

                //if there is a show already in that particular date check the timing
                if (show.getShowDate().isEqual(dateStart)) {
                if ((choiceStartTime.isBefore(show.getStarttime()) || choiceStartTime.isAfter(show.getEndtime()))
                        && (endTime.isBefore(show.getStarttime()) || endTime.isAfter(show.getEndtime()))) {//check the timing of the show already exists or not

                    currentShow = new Show(choiceStartTime, choiceStartTime.plusMinutes(choiceDuration + 30),
                            dateStart, currentScreen, price, currentScreen.getSeatarrangement());// Store in the show object
                    if (currentScreen.getShows().contains(currentShow)) {//check for same show already exists or not
                        System.out.print("show already exists Enter again(y) or press (N) to exit:");//to reenter
                        String choice = scanner.nextLine();
                        if (choice.toLowerCase().equals("n")) {
                            return;
                        }
                        continue show;
                    }
                } else {
                    System.out.print("show already exists Enter again(y) or press (N) to exit:");//to reenter the choice
                    String choice = scanner.nextLine();
                    if (choice.toLowerCase().equals("n")) {
                        return;
                    }
                    continue show;}}
                    if (!BookMyShow.getMovieandmovieobj().containsKey(movieName)) {//if new movie name add the arraylist of movie object
                        BookMyShow.getMovieandmovieobj().put(movieName, new ArrayList<Movies>());
                    }
                    currentScreen.getShows().add(currentShow);//add to the arraylist

                    BookMyShow.getMovieandmovieobj().get(movieName)
                            .add(new Movies(movieName, movieLocation, dateStart,
                                    choiceDuration, currentTheatre, currentScreen, currentShow));//add to the movie arraylist
                    System.out.println("added successfully");
                    return;
                
                
            }
            break;
        }
    }

    public static void viewallmovies() {//to print all movies that are added
        if (BookMyShow.getMovieandmovieobj().isEmpty()) {
            System.out.println("No movies in the App..");
            return;
        }

        for (String movieName : BookMyShow.getMovieandmovieobj().keySet()) {
            ArrayList<Movies> movies = BookMyShow.getMovieandmovieobj().get(movieName);
            for (Movies currentMovie : movies) {
                System.out.println("\n" + "---------------------------------------------------------" + "\n");
                System.out.println("Movie Name:" + currentMovie.getName());
                System.out.println("Movie Date:" + currentMovie.getShowob().getShowDate());
                System.out.println("Theatre Name:" + currentMovie.getTheatreob().getTheatrename());
                System.out.println("Theatre Location:" + currentMovie.getTheatreob().getLocation());
                System.out.println("Theatre Screen:" + currentMovie.getScreenob().getName());
                System.out.println("Seat arrangement :" + currentMovie.getScreenob().getSeatarrangement());
                System.out.println("Show start Time:" + currentMovie.getShowob().getStarttime());
                System.out.println("Show end Time:" + currentMovie.getShowob().getEndtime());
                System.out.println("\n" + "---------------------------------------------------------" + "\n");
            }
        }
    }

    public static void addTheatre() {
        System.out.println("\n" + "---------------------------------------------------------" + "\n");
        System.out.print("Enter The Theatre Name:");
        String theatreName = scanner.nextLine();// get theatre name

        System.out.print("Enter The Location of Theatre :");
        String theatreLocation = scanner.nextLine();// get location of theatre
        
        //check the theatre name in that locaton is already exists
            
            
            if (BookMyShow.getTheatreandtheatreobj().containsKey(theatreLocation)) {
                System.out.println("Theatre Already exists in that Location....");
                return;
            }
        

        Theatre currentTheatreAdding = new Theatre(theatreLocation, theatreName);

        System.out.print("Enter The Number Of Screens:");
        long numberOfScreens = Long.parseLong(scanner.nextLine());//get the number of screens
        int screenCount = 1;

        screen: while (screenCount <= numberOfScreens) {
            System.out.print("Enter the Screen Name:");
            String screenName = scanner.nextLine();

            for (String existingScreenName : currentTheatreAdding.getScreenNameandScreenObject().keySet()) {
                if (existingScreenName.equals(screenName)) {//check the screen name already exists
                    System.out.println("Screen name already exists:");
                    continue screen;
                }
            }

            System.out.print("Enter the Number of Seats:");
            long numberOfSeats = Long.parseLong(scanner.nextLine());//get seats
            System.out.print("Enter the Screen Grid like (2*5*2)*For space:");
            String grid = scanner.nextLine();//get grid
            var seatsAndGrid = Utilities.generateGrid(numberOfSeats, grid);//get pattern from utilities

            currentTheatreAdding.getScreenNameandScreenObject().put(screenName,
                    new Screen(screenName, numberOfSeats, seatsAndGrid, grid));

            System.out.println("Screen Added Successfully....");
            screenCount += 1;
        }
        
        BookMyShow.getTheatreandtheatreobj().put(theatreName, currentTheatreAdding);//add in the hash map
    }

    public static void viewalltheatre() {//print all the theatres that are added
        if(BookMyShow.getTheatreandtheatreobj().isEmpty()){
            System.out.println("No theatre...");
            return;
        }
        for (String theatreName : BookMyShow.getTheatreandtheatreobj().keySet()) {
            System.out.println("---------------------------------------------------------\n");
            Theatre theatre = BookMyShow.getTheatreandtheatreobj().get(theatreName);

            System.out.println("Theatre Name is.." + theatre.getTheatrename());
            System.out.println("Theatre Location is.." + theatre.getLocation());

            System.out.println("screens are ");
            for (var screenEntry : theatre.getScreenNameandScreenObject().entrySet()) {
                System.out.println("---------------------------------------------------------\n");
                System.out.println("Screen Name:" + screenEntry.getKey());
                System.out.println("Number of seats:" + screenEntry.getValue().getNumberofseats());
                System.out.println("Screen pattern:" + screenEntry.getValue().getSeatarrangement());
            }
        }
    }
}