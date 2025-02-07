import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class UserActions {
    public static Scanner scanner = new Scanner(System.in);

    public static Account login() {// perform the login action of the user

        System.out.print("Enter The Username:");
        String userIdInput = scanner.nextLine();// get user name
        System.out.print("Enter The Password:");
        String userPasswordInput = scanner.nextLine();// get password


        for (Account temp : BookMyShow.getAccountList()) {// get user arraylist
            if(temp instanceof User){
            if (temp.getId().equals(userIdInput)) {// check every object id and pass
                if (temp.getPass().equals(userPasswordInput)) {
                    return temp;// if matches return the current user object
                } else {
                    return new User(null, null, null);// for wrong pass return object with null values
                }
            }

        }}


        return null;// no account pass null
    }

    public static void register() {// if no account the function is called


        System.out.print("NO USER FOUND YOU LIKE TO Register (Y/N):");// conformation to get into register
        String userChoice = scanner.nextLine();


        if (userChoice.toLowerCase().equals("y")) {// check input
            System.out.print("Enter username:");
            String useridinput = scanner.nextLine();// get user name
            System.out.print("Enter password:");
            String password = scanner.nextLine();// get password
            System.out.print("Enter Your Location:");
            String userlocation = scanner.nextLine();// get locatiom
            System.out.print("ARE YOU SURE TO REGISTER (Y/N):");// ask conformation to register with this detatils
            String userConformation = scanner.nextLine();
            if (userConformation.toLowerCase().equals("y")) {
                BookMyShow.getAccountList().add(new User(useridinput, password, userlocation));
                               // if yes add in user array list with the input fields 
                                                                                       
            } else if (userConformation.toLowerCase().equals("n")) {
                return;// else return
            }
        } 
        
        else if (userChoice.equals("n") || userChoice.equals("N")) {
            return;// if n return to previous features
        }


    }

    public static void operations(User currentUser) {


        UserActions.displayAllMovies(currentUser, Utilities.getToday());// very bigning of the login show the movies

        while (true) {


            System.out.println("---------------------------------------------------------" + "\n");
            System.out.println("Enter The User Choice...");
            System.out.println(
                    "------------- 1.Change Location---------------- 2.Change Date ---------------3.view thickets---------------4.Display Movies--------------5.exit------------------ ");
            System.out.print("Enter the choice:");
            String choice = scanner.nextLine();
            System.out.println("---------------------------------------------------------" + "\n");


            switch (choice) {

                case "1":// based on the choice call the function (1)
                    UserActions.changeLocation(currentUser);
                    UserActions.displayAllMovies(currentUser, Utilities.getToday());// after the change in the location
                                                                                    // show the movies
                    break;
                case "2":// based on the choice call the function (2)
                    LocalDate dateUpdate = UserActions.changeDate(currentUser);
                    if(dateUpdate==null){
                        System.out.println("Cant go the past date....");
                        continue;
                    }
                    UserActions.displayAllMovies(currentUser, dateUpdate);// after the change in the date show the  movies
                                                                         
                    break;
                case "3":// based on the choice call the function (3)
                    UserActions.viewtickets(currentUser);
                    break;
                case "4":// based on the choice call the function (4)
                    UserActions.displayAllMovies(currentUser, Utilities.getToday());
                    break;
                case "5":// exit the function and go to previous function
                    return;
            }
        }
    }

    public static void displayAllMovies(User currentuser, LocalDate today) {// display movies based on users location and date

        HashSet<String> moviesinthatlocation = new HashSet<>();// to store the movies in that location
        ArrayList<Movies> moviesavailable = new ArrayList<>();// to store the every object of the particulat movie of   the user entered
        

        if (BookMyShow.getMovieandmovieobj().keySet().isEmpty()) {
            System.out.println("\n" + "No movies in the app currently wait coming soon.." + "\n");
            return;
        } // to check for no movies in the entire app

        System.out.println("\n"+"Movies Available In Your Preferences");


        for (var movies : BookMyShow.getMovieandmovieobj().keySet()) {
            boolean check=false;
            var moviesarraylist = BookMyShow.getMovieandmovieobj().get(movies);
            for (var movieobject : moviesarraylist) {
                // Check and display the movies based on the location and date
                if (movieobject.getLocation().equals(currentuser.getLocation()) && (movieobject.getStartdate().isEqual(today))) {
                    check=true;
                moviesinthatlocation.add(movies);
                break;

                }

            }
            if(check){
                System.out.println("->" + movies);
            }
        }
        if(moviesinthatlocation.isEmpty()){
            System.out.println("No movies in your location or date");
            return;
        }

        
        moviename:while(true){
        
        System.out.print("Enter the movie name to book or press (n) to exit the option:");
        String movieChoice = scanner.nextLine();// get the movie name to book


        if (moviesinthatlocation.contains(movieChoice)) {// if movie entered bye the user exists in that location
            for (var movieobject : BookMyShow.getMovieandmovieobj().get(movieChoice)) {
                if (movieobject.getLocation().equals(currentuser.getLocation()) && (movieobject.getStartdate().isEqual(today))) {
                    moviesavailable.add(movieobject);// add all the movie object in arraylist
                }

            }

        } else if (movieChoice.equals("n") || movieChoice.equals("N")) {
            System.out.println("Thank you");
            return;// if n return
        }

        else {
            System.out.print("Incorrect theatre name Enter again (y) or press (N) to exit:");
            String choice = scanner.nextLine();
            if (choice.toLowerCase().equals("n")) {
                return;
            }
            continue moviename;

        }

        show_movie_details(moviesavailable, movieChoice, currentuser);// pass the movie object,current user, finally movie name

    break;}}

    public static void show_movie_details(ArrayList<Movies> movieavailable, String moviechoice, User currenUser) {
        System.out.println("\n" +
                "---------------------------------------------------------");
        System.out.println("Movie:" + moviechoice);
        System.out.println("\n" + // print movie name
                "---------------------------------------------------------");
        HashMap<String, HashSet<Show>> theatreAgainstShow = TheatreActions.theatreAgainstShow(movieavailable);
             // to reduce the time consuming process to prirnt the theatre name and shows in the theatre alone
       

        for (String keytheatrename : theatreAgainstShow.keySet()) {
            System.out.println("Theatre:" + keytheatrename);// print all theatre name were the movies are running
            System.out.println("\n" +
                    "---------------------------------------------------------");
            System.out.println("shows are:" + theatreAgainstShow.get(keytheatrename).toString());// print show in the theatre
            System.out.println("\n" +
                    "---------------------------------------------------------" + "\n");
        }


        String theatrename;
        theatre:while(true){
        System.out.print("Enter the Theatre name:");
         theatrename = scanner.nextLine();// get the theatre name
        for (String keytheatrename : theatreAgainstShow.keySet()) {
            if (theatrename.equals(keytheatrename)) {
                break theatre;//check the user entered theatre name is valid or not
                
            }}
            System.out.print("Incorrect theatre name Enter again (y) or press (N) to exit:");
                String choice = scanner.nextLine();
                if (choice.toLowerCase().equals("n")) {
                    return;
                }
                continue theatre;
           
        }


        Show currentshow = null;// to get the show object of the user entered show
        LocalTime showtime;


       show: while(true){
        System.out.print("Enter the Show Start Time example:(09:00):");
         showtime = LocalTime.parse(scanner.nextLine(),
                Utilities.getTimeformatter());// get the show start time
        var showtocheck = theatreAgainstShow.get(theatrename);// get the show object hashset
       
        for (var shows : showtocheck) {//to get the particular show
            if (shows.getStarttime().equals(showtime)) {// check the user entered show time is valid or not
                currentshow = shows;// assagin the current show
                break show;

            }}
           
                System.out.print("Incorrect show time Enter again (y) or press (N) to exit:");//if not ask another try
                String choice = scanner.nextLine();
                if (choice.toLowerCase().equals("n")) {
                    return;
                }
                continue show;
    
            
        
   

}

        if (currentshow == null) {
            System.out.println("Enter the correct details:");
            return;
        } // if the show time is wrong

        bookTickets(currentshow,currenUser,theatrename,moviechoice,showtime);
    }



        public static void bookTickets(Show currentshow,User currenUser,String theatrename,String moviechoice,LocalTime showtime){// to book the ticket

        System.out.println("--------Screen Name-------" + currentshow.getScreen().getName());// print the screen name of   the show
                                                                                            
        System.out.println("--------Screen Available Seats Count-------" + currentshow.getScreen().getAvailableseats());// show  available in the show
        System.out.println("-------------Price" + currentshow.getPrice() + "------------");// price for the ticket
        for (var seats : currentshow.getSeatarrangement().entrySet()) {
            System.out.println(seats.getKey() + " " + seats.getValue());
        } // print the seat to book

        System.out.print("Enter the number seats to book:");
        Long seatcount = Long.parseLong(scanner.nextLine());// get the number of seats to book


        long finalseatcount = seatcount;// for printing price at last

        ArrayList<String> userseats = new ArrayList<>();// for storing which seats does the user books

        HashMap<Character, ArrayList<String>> duplicate = new HashMap<>();// for avoiding the change if the user gives no  while paying
                                                                          
        for (var duplicateclone : currentshow.getSeatarrangement().entrySet()) {
            duplicate.put(duplicateclone.getKey(), new ArrayList<String>());// store the all the elements from the old  object
                                                                          
            duplicate.get(duplicateclone.getKey()).addAll(duplicateclone.getValue());
        }


        if (currentshow.getScreen().getAvailableseats() < seatcount) {
            System.out.println("Seats in the screen are not sufficient");
            System.out.println("The available seats are" + currentshow.getScreen().getAvailableseats());
            return;
        } // check entered seats are availabe in the show

        int printingPurpose = 1;
        while (seatcount > 0) {// continue untill the seatcount is 0


            String grid = currentshow.getScreen().getGrid();// get the grid
            var starremoved = grid.split("\\*");// seperate the grid using regex and stored in array list
            long sum = 0;// sum initialized to 0

            for (String a : starremoved) {// to calculate sum
                long temp = Long.parseLong(a);
                sum += temp;
            }

            System.out.print("Enter the row for " + printingPurpose + " st seat to book example(A1,B1):");// get the ticket row and seat number
                                                                                           
            String choiceseat = scanner.nextLine();
            char row = choiceseat.charAt(0);// get the row from the string

            String bookseat = null;// to check the seat  is already booked or not

            int seatchoice = Integer.parseInt(choiceseat.substring(1));// seat number
            if(seatchoice>sum){
                System.out.println("enter correct seat number");
                continue ;
            }

            if (seatchoice <= Integer.parseInt(starremoved[0])) {// if seatnumber is less or equal to first grid
                bookseat = duplicate.get(row).get(seatchoice - 1);// get the -1 (because no seat)
                if (bookseat.equals("X")) {// check the bookseat is X or not
                    System.out.println(duplicate.get(row));// if x
                    System.out.println("Seat already booked Try any other seats");// say the user to book another seat
                    continue;
                }
                duplicate.get(row).set(seatchoice - 1, "X");// set the -1 (because no seat)
                userseats.add(choiceseat);// add the user seats
                  System.out.println(duplicate.get(row));// to print the selected seat
            
            
            }
            
             else if (seatchoice >= ((sum + 1) - Integer.parseInt(starremoved[2]))) {// If seatNumber is greater than or equal to sum + 1 and minus the last grid size to calculate the seat after the second seat
                bookseat = duplicate.get(row).get(seatchoice + 1);
                if (bookseat.equals("X")) {// check the bookseat is X or not
                    System.out.println(duplicate.get(row));// if x
                    System.out.println("Seat already booked Try any other seats");// say the user to book another seat
                    continue;
                }
                duplicate.get(row).set(seatchoice + 1, "X");
                userseats.add(choiceseat);// add the user seats
                System.out.println(duplicate.get(row));// to print the selected seat
            } else if (seatchoice > Integer.parseInt(starremoved[0])) {// for second grid make the seatnumber as it is
                bookseat = duplicate.get(row).get(seatchoice);
                if (bookseat.equals("X")) {// check the bookseat is X or not
                    System.out.println(duplicate.get(row));// if x
                    System.out.println("Seat already booked Try any other seats");// say the user to book another seat
                    continue;
                }
                duplicate.get(row).set(seatchoice, "X");
                userseats.add(choiceseat);// add the user seats
                System.out.println(duplicate.get(row));// to print the selected seat
              
            }

            printingPurpose++;// increment for printing
            seatcount--;// to exit while or condition
        }

        long totalamount = finalseatcount * currentshow.getPrice();

        System.out.println("The total amount for the ticket is:" + totalamount);// show the total ticket price

        System.out.print("Enter The y to Confirm The Ticket Booking or n to exit:");

        String userchoice = scanner.nextLine();// ask for conformation


        if (userchoice.toLowerCase().equals("y")) {// if yes
            currentshow.getScreen().setAvailableseats(currentshow.getScreen().getAvailableseats() - seatcount);// Reduce the seat count

            currenUser.getTickets().add(new Ticket(theatrename, currentshow.getShowDate(),
                    currentshow.getScreen().getName(), showtime, finalseatcount, totalamount, userseats, moviechoice));// create the ticket object and pass essential fields

            currentshow.setSeatarrangement(duplicate);// mark the original seats to be the clone

        } else if (userchoice.equals("n") || userchoice.equals("N")) {
            System.out.println("Thank you");
            return;// else return to the function
        }

    }

    public static void changeLocation(User currentuserob) {// to change location

        System.out.print("Enter The Location To Change:");
        String locationtochange = scanner.nextLine();// get the input to change location
        currentuserob.setLocation(locationtochange);// set to user
        return;

    }

    public static LocalDate changeDate(User currentuserob) {// tp change date

        System.out.print("Enter The date To Change:");
        LocalDate dateupdate = LocalDate.parse(scanner.nextLine(), Utilities.getFormatter());
        if(dateupdate.isBefore(Utilities.getToday())){
           
            return null;
        }//to avoid the case of adding movie in the past
        

        return dateupdate;// return the date which is choosen by the user

    }

    public static void viewtickets(User currentuser) {// to dispaly the ticket
        for (var ticket : currentuser.getTickets()) {// get the all tickets object from the currnt user ticket array
                                                     // list
            System.out.println("- - - - - - - - - - - - - - - - - - - - - ");
            System.out.println("|  Movie name:" + ticket.getMovieName() + "  |");// print the details of the ticket like
            System.out.println("|  Theare name:" + ticket.getTheatreName() + "  |");
            System.out.println("|  Date:" + ticket.getDate() + "  |");
            System.out.println("|  Screen name:" + ticket.getScreen() + "  |");
            System.out.println("|  Timing:" + ticket.getTime() + "  |");
            System.out.println("|  Number of seats:" + ticket.getNumberOfSeats() + "  |");
            System.out.println("Amount Paid:" + ticket.getAmountPaid());
            System.out.println("|  seats are:" + ticket.getSeats() + "  |");
            System.out.println("|  QR CODE" +

                    "\n  ---____*****" +
                    "\n  ****----___*" +
                    "\n  ---____*****" +
                    "\n  ****----___*");
            System.out.println("- - - - - - - - - - - - - - - - - - - - - ");

        }
    }

}
