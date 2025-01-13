
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AdminAction {
    public static Scanner scanner = new Scanner(System.in);

    public static Admin login() {
        System.out.print("Enter The Username:");//get usernmae 
        String userinput = scanner.nextLine();
        System.out.print("Enter The Password:");//get password
        String passwordinput = scanner.nextLine();
        for (Admin temp : BookMyShow.getAdminlist()) {//check the id and pass  
            if (temp.getUserid().equals(userinput)) {
                if (temp.getPassword().equals(passwordinput)) {
                    return temp;//current admin object for successfull login 
                } else {
                    return new Admin(null, null);// for incorrect password return null object
                }
            }

        }
        return null;//if no account return null 

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
                    AdminAction.addMovie();//based on the choice call the function (1)
                    break;

                  
                case "2":
                    AdminAction.viewallmovies();//based on the choice call the function (2)
                    break;

                case "3":
                    AdminAction.addTheatre();//based on the choice call the function (3)
                    break;
                case "4":
                    AdminAction.viewalltheatre();//based on the choice call the function (4)
                    break;
                case "5":
                    return;//exit
                default:
                    System.out.println("Enter The Valid Choice");//for invalid choice
            }
        }
    }

    public static void addMovie() {
        System.out.println("\n" + "---------------------------------------------------------" + "\n");
        System.out.print("Enter Movie Name:");//get movie name and location
        String moviename = scanner.nextLine();
        System.out.print("Enter Location:");
        String movielocation = scanner.nextLine();
        int result = 0;//number of theatre in that locations 
        for (var temp : BookMyShow.getTheatreandtheatreobj().keySet()) {
            var checktheatreLocation = BookMyShow.getTheatreandtheatreobj().get(temp);//get theatre name locations

            if (checktheatreLocation.getLocation().equals(movielocation)) {//check location already exists
                result += 1;
            }

        }
        if (result == 0) {//if no theatre in that location
            System.out.println("No  theatre in that location add thetre in that location");
            return;
        }
        System.out.print("Enter  Date:");//get the date as input

        LocalDate datestart = LocalDate.parse(scanner.nextLine(), Utilities.getFormatter());//LocalDate class used and formatted the date as in utilities

        System.out.println("Theatres in the location you entered are..");
        for (var temp : BookMyShow.getTheatreandtheatreobj().keySet()) {//printing theatres in that location
            var printtheatre = BookMyShow.getTheatreandtheatreobj().get(temp);

            System.out.println("->" + printtheatre.getTheatername());
        }

        System.out.print("Enter The Theatre Name:");
        String choicetheatre = scanner.nextLine();
        Theatre currenttheatre = null;//to get the current looping theatre

        for (var temp : BookMyShow.getTheatreandtheatreobj().keySet()) {//check the input theatre name is correct or already exists in that location
            currenttheatre = BookMyShow.getTheatreandtheatreobj().get(temp);

            if (currenttheatre.getTheatername().equals(choicetheatre)) {
                break;
            } else {
                System.out.println("Incorrect theatre name");
                return;
            }
        }

        while (true) {

            System.out.println("Screens In The Theatre are...");//displaying screens in the theatre
            for (String scnmae : currenttheatre.getScreennameandobj().keySet()) {
                System.out.println("*-> " + scnmae);
            }

            System.out.print("Enter Screen Name:");
            String choicescreen = scanner.nextLine();
            Screen currentscreen = null;//store current screen
            for (var screen : currenttheatre.getScreennameandobj().keySet()) {//check the entered screen is in theatrte
                if (screen.equals(choicescreen)) {
                    currentscreen = currenttheatre.getScreennameandobj().get(screen);
                    break;
                } 
            }

            System.out.print("Enter The Show start time(HH:mm): ");//get the show start time
            LocalTime choicestarttime = LocalTime.parse(scanner.nextLine(),
                    Utilities.getTimeformatter());//Using localTime store the time and format is (HH:mm)
            System.out.print("Enter The Duration of movie:");
            long choiceduration = Long.parseLong(scanner.nextLine());//get the duration of the movie
            var endTime = choicestarttime.plusMinutes(choiceduration + 30);//end time is starttime +duration and 30 minutes for the intervel
            System.out.print("Enter the price for the Show:");
            long price=Long.parseLong(scanner.nextLine());

            Show currentshow = null;//store current show


            if (currentscreen.getShows().isEmpty()) {//if show class is empty add directly
                currentshow = new Show(choicestarttime, choicestarttime.plusMinutes(choiceduration + 30),
                        datestart, moviename,currentscreen,price,currentscreen.getSeatarrangement());
                currentscreen.getShows().add(currentshow);
                if(!BookMyShow.getMovieandmovieobj().containsKey(moviename)){
                BookMyShow.getMovieandmovieobj().put(moviename, new ArrayList<Movies>());}
                BookMyShow.getMovieandmovieobj().get(moviename).add(new Movies(moviename, movielocation, datestart,
                        choiceduration, currenttheatre, currentscreen, currentshow));
                System.out.println("added successfully");
                return;
            }
            for (var shows : currentscreen.getShows()) {//if show is already there
                currentshow = shows;
               
                if (shows.getShowDate().isEqual(datestart)) {//check date to see that particular date shows
                    if ((choicestarttime.isBefore(shows.getStarttime()) || choicestarttime.isAfter(shows.getEndtime()))
                            && (endTime.isBefore(shows.getStarttime()) || endTime.isAfter(shows.getEndtime()))) {//check the show given as input is valid or not by comparing to old show timings
                        currentshow = new Show(choicestarttime, choicestarttime.plusMinutes(choiceduration + 30),
                                datestart, moviename,currentscreen,price,currentscreen.getSeatarrangement());//store in the show object
                                if (currentscreen.getShows().contains(currentshow)) {
                                    System.out.println("Show already Exists");
                                    return;
                
                                }//check the same show (start time and end time  and date already exists )
                        currentscreen.getShows().add(currentshow);
                        
                        BookMyShow.getMovieandmovieobj().get(moviename)//if all the condition is true add the movie and show object
                                .add(new Movies(moviename, movielocation, datestart,
                                        choiceduration, currenttheatre, currentscreen, currentshow));
                        System.out.println("added successfully");
                        return;

                    } 
                    
                    else {
                        System.out.println("Show already Exists");//else say the user that show already exists
                        return;
                    }
                }
            }

        }

    }

    public static void viewallmovies() {

        if (BookMyShow.getMovieandmovieobj().isEmpty()) {
            System.out.println("No movies in the App..");
            return;

        }//if movie and object hashmap is empty print no movie in the app
        for (var movie : BookMyShow.getMovieandmovieobj().keySet()) {//print the movies and their details like theatre name ,location etc..
            var movies = BookMyShow.getMovieandmovieobj().get(movie);//get arraylist using key
            for (var currentMovie : movies) {//loop array list
                System.out.println("\n" +
                        "---------------------------------------------------------" + "\n");
                System.out.println("Movie Name:" + currentMovie.getName());
                System.out.println("Movie Duration:" + currentMovie.getDuration());
                System.out.println("Movie Date:" + currentMovie.getShowob().getShowDate());
                System.out.println("Theatre Name:" + currentMovie.getTheatreob().getTheatername());
                System.out.println("Theatre Location:" + currentMovie.getTheatreob().getLocation());
                System.out.println("Theatre Screen:" + currentMovie.getScreenob().getName());
                System.out.println("Seat arrangement :" + currentMovie.getScreenob().getSeatarrangement());
                System.out.println("Show start Time:" + currentMovie.getShowob().getStarttime());
                System.out.println("Show end Time:" + currentMovie.getShowob().getEndtime());

                System.out.println("\n" +
                        "---------------------------------------------------------" + "\n");
            }
        }

    }

    public static void addTheatre() {
        System.out.println("\n" + "---------------------------------------------------------" + "\n");
        System.out.print("Enter The Theatre Name:");
        String theatername = scanner.nextLine();//get theatre name

        System.out.print("Enter The Location of Theatre :");
        String theatreLocation = scanner.nextLine();//get location of theatre
        for (var temp : BookMyShow.getTheatreandtheatreobj().keySet()) {//check it already exists or not
            var currenttheatre = BookMyShow.getTheatreandtheatreobj().get(temp);//get every theatre object and check name and location
            if (temp.equals(theatername) && currenttheatre.getLocation().equals(theatreLocation)) {
                System.out.println("Theatre Already exists in that Location....");//if already exists return
                return;
            }

        }
        Theatre curenTheatreadding = new Theatre(theatreLocation, theatername);//pass the name and location and create object
       
        System.out.print("Enter The Number Of Screencs:");
        long numberofscreens = Long.parseLong(scanner.nextLine());//get number of seats
        for (int i = 1; i <= numberofscreens; i++) {//loop based on number of screens

            System.out.print("Enter the Screen Name:");
            String screenname = scanner.nextLine();//get screen name
            System.out.print("Enter the Number of Seats:");
            long numberofseats = Long.parseLong(scanner.nextLine());//get number of screens 
            System.out.print("Enter the Screen Grid like (2*5*2)*For space:");
            String grid = scanner.nextLine();
            var seatsandgrid = Utilities.generateGrid(numberofseats, grid);//call the utility function and add to screen
            System.out.println("The Seats Arrangement Of The Theatre is:");
            for (var entry : seatsandgrid.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }//print seats pattern
            HashMap<Character,ArrayList<String>>duplicate=new HashMap<>();
        for(var duplicateclone:seatsandgrid.entrySet()){
            duplicate.put(duplicateclone.getKey(), new ArrayList<String>());
            duplicate.get(duplicateclone.getKey()).addAll(duplicateclone.getValue());
        }

            curenTheatreadding.getScreennameandobj().put(screenname,
                    new Screen(screenname, numberofseats, duplicate,grid));//add to the hashmap in theatre object

            System.out.println("Screen Added Successfully....");

        }
        BookMyShow.getTheatreandtheatreobj().put(theatername, curenTheatreadding);//add the object and theatre name in hashmap in book my show

    }

    public static void viewalltheatre() {//display all the theatres

        for (var temp : BookMyShow.getTheatreandtheatreobj().keySet()) {//get key set
            System.out.println("---------------------------------------------------------\n");
            var theatre = BookMyShow.getTheatreandtheatreobj().get(temp);//get theatre object

            System.out.println("Theatre Name is.." + theatre.getTheatername());//print all deatails of theatre
            System.out.println("Theatre Location is.." + theatre.getLocation());//print location
            
            System.out.println("screens are ");
            for (var temp1 : theatre.getScreennameandobj().entrySet()) {//get hashmap of screen
                System.out.println("---------------------------------------------------------\n");
                System.out.println("Screen Name:" + temp1.getKey());
                System.out.println("Number of seats:" + temp1.getValue().getNumberofseats());//print number of seats
                System.out.println("Screen pattern:" + temp1.getValue().getSeatarrangement());//print the seat grid of the screen

               

            }
        }

    }

}
