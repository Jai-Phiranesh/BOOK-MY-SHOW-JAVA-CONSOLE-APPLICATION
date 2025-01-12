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
                    "------------- 1.Change Location---------------- 2.Change Date ---------------3.view thickets---------------4.Exit ");
            System.out.print("Enter the choice...");
            String choice = scanner.nextLine();
            System.out.println("---------------------------------------------------------" + "\n");
            switch (choice) {

                case "1":
                    UserActions.changeLocation(currentuser);
                    UserActions.displayallMovies(currentuser, Utilities.getToday());
                    break;
                case "2":
                    LocalDate dateupdate=UserActions.changeDate(currentuser);
                    UserActions.displayallMovies(currentuser, dateupdate);
                    break;
                case "3":
                    UserActions.viewtickets(currentuser);
                    break;
                case "4":
                    return;
            }
        }
    }

    public static void displayallMovies(User ob, LocalDate today) {
       
        HashSet<String> moviesinthatlocation = new HashSet<>();
        ArrayList<Movies> moviesavailable = new ArrayList<>();
        if(BookMyShow.getMovieandmovieobj().keySet().isEmpty()){
            System.out.println("\n"+"No movies in your location or date try some other date or location"+"\n");
                return;
        }

        for (var movies : BookMyShow.getMovieandmovieobj().keySet()) {
            boolean check = false;
            var moviesarraylist = BookMyShow.getMovieandmovieobj().get(movies);
            for (var movieobject : moviesarraylist) {
                if (movieobject.getLocation().equals(ob.getLocation()) && (movieobject.getStartdate().isEqual(today))) {
                    check = true;

                }

            }
            if (check) {
                System.out.println("->" + movies);
                moviesinthatlocation.add(movies);
            }
            else{
                System.out.println("\n"+"No movies in your location or date try some other date or location"+"\n");
                return;
            }

        }
        System.out.print("Enter the movie name to book or press (n) to exit the option:");
        String movieChoice = scanner.nextLine();
        if (moviesinthatlocation.contains(movieChoice)) {
            for (var movieobject : BookMyShow.getMovieandmovieobj().get(movieChoice)) {
                if (movieobject.getLocation().equals(ob.getLocation()) && (movieobject.getStartdate().isEqual(today))) {
                    moviesavailable.add(movieobject);
                }

            }

        }
        else if (movieChoice.equals("n") || movieChoice.equals("N")) {
            System.out.println("Thank you");
            return;
        }

        else {
            System.out.println("Enter the correct movie name...");
            return;
        }

        showmovies(moviesavailable, movieChoice,ob);

    }

    public static void showmovies(ArrayList<Movies> movieavailable, String moviechoice,User currenUser) {
        System.out.println("\n" +
                "---------------------------------------------------------") ;
        System.out.println("Movie:" + moviechoice);
        System.out.println("\n" +
                "---------------------------------------------------------" );
        HashMap<String, HashSet<Show>> theatreAgainstShow = new HashMap<>();
        for (var currentMovie : movieavailable) {

            if (theatreAgainstShow.containsKey(currentMovie.getTheatreob().getTheatername())) {
                theatreAgainstShow.get(currentMovie.getTheatreob().getTheatername()).add(currentMovie.getShowob());
            } else {
                HashSet<Show> show = new HashSet<>();
                show.add(currentMovie.getShowob());
                theatreAgainstShow.put(currentMovie.getTheatreob().getTheatername(), show);
            }
        }

        for (String keytheatrename : theatreAgainstShow.keySet()) {
            System.out.println("Theatre:" + keytheatrename);
            System.out.println("\n" +
                "---------------------------------------------------------") ;
            System.out.println("shows are:" + theatreAgainstShow.get(keytheatrename).toString());
            System.out.println("\n" +
                "---------------------------------------------------------"+"\n") ;
        }

        System.out.print("Enter the Theatre name:");
        String theatrename = scanner.nextLine();
        System.out.print("Enter the Show Start Time example:(09:00):");
        LocalTime showtime = LocalTime.parse(scanner.nextLine(),
                Utilities.getTimeformatter());
        var showtocheck = theatreAgainstShow.get(theatrename);
        Show currentshow = null;
        for (var shows : showtocheck) {
            if (shows.getStarttime().equals(showtime)) {
                currentshow = shows;

            }
        }

        if (currentshow == null) {
            System.out.println("Enter the correct details:");
            return;
        }

        System.out.println("--------Screen Name-------" + currentshow.getScreen().getName());
        System.out.println("--------Screen Seats Count-------" + currentshow.getScreen().getNumberofseats());
        System.out.println("-------------Price"+currentshow.getPrice()+"------------");
        for (var seats : currentshow.getScreen().getSeatarrangement().entrySet()) {
            System.out.println(seats.getKey() + " " + seats.getValue());
        }

        System.out.print("Enter the number seats to book:");
        Long seatcount = Long.parseLong(scanner.nextLine());
        long finalseatcount=seatcount;
        ArrayList<String>userseats=new ArrayList<>();
        HashMap<Character,ArrayList<String>>duplicate=new HashMap<>();
        for(var clone:currentshow.getScreen().getSeatarrangement().entrySet()){
            duplicate.put(clone.getKey(),new ArrayList<String>());
            for(var cloneseats:clone.getValue()){
                duplicate.get(clone.getKey()).add(cloneseats);

            }
        }
        while (seatcount > 0) {
            int j = 1;

            if (currentshow.getScreen().getAvailableseats() < seatcount) {
                System.out.println("Seats in the screen are not sufficient");
                System.out.println("The available seats are" + currentshow.getScreen().getAvailableseats());
                return;
            }

            System.out.print("Enter the row for " + j + " st seat to book example(A1,B1):");
            String choiceseat=scanner.nextLine();
            char row = choiceseat.charAt(0);
           
           
            int seatchoice =Integer.parseInt(choiceseat.substring(1)) ;
            if (duplicate.get(row).get(seatchoice - 1).equals("<SPACE>")) {
                seatchoice += 1;
            }
            if (duplicate.get(row).get(seatchoice - 1).equals("X")) {
                System.out.println(duplicate.get(row));
                System.out.println("Seat already booked Try any other seats");
                continue;
            }
           duplicate.get(row).set(seatchoice - 1, "X");
            userseats.add(choiceseat);
            System.out.println(duplicate.get(row));
           
           

            j++;
            seatcount--;

        }
        long totalamount=finalseatcount*currentshow.getPrice();

        System.out.println("The total amount for the ticket is:"+totalamount);
        System.out.print("enter the y to confirm the ticket booking or n to exit:");
        String userchoice=scanner.nextLine();
        if (userchoice.toLowerCase().equals("y")) {
            currentshow.getScreen().setAvailableseats(currentshow.getScreen().getAvailableseats()-seatcount);
            currenUser.getTickets().add(new Ticket(theatrename, currentshow.getShowDate(),currentshow.getScreen().getName() , showtime, finalseatcount,totalamount, userseats,moviechoice));
            currentshow.getScreen().setSeatarrangement(duplicate);

            

        }
        else if (userchoice.equals("n") || userchoice.equals("N")) {
            System.out.println("Thank you");
            return;
        }
        


    }

    public static void changeLocation(User currentuserob) {

        System.out.print("Enter The Location To Change:");
        String locationtochange = scanner.nextLine();
        currentuserob.setLocation(locationtochange);
        return;

    }

    public static LocalDate changeDate(User currentuserob) {

        System.out.print("Enter The date To Change:");
        LocalDate dateupdate = LocalDate.parse(scanner.nextLine(), Utilities.getFormatter());

        return dateupdate;

    }


    public static void viewtickets(User currentuser){
        for(var ticket:currentuser.getTickets()){
            System.out.println("- - - - - - - - - - - - - - - - - - - - - ");
            System.out.println("|  Movie name:"+ticket.getMoviename()+"  |");
            System.out.println("|  Theare name:"+ticket.getTheatrename()+"  |");
            System.out.println("|  Date:"+ticket.getDate()+"  |");
            System.out.println("|  Screen name:"+ticket.getScreen()+"  |");
            System.out.println("|  Timing:"+ticket.getTime()+"  |");
            System.out.println("|  Number of seats:"+ticket.getNumberOfSeats()+"  |");
            System.out.println("Amount Paid:"+ticket.getAmountpaid());
            System.out.println("|  seats are:"+ticket.getSeats()+"  |");
            System.out.println("|  QR CODE"+
            
            "\n  ---____*****"+
            "\n  ****----___*"+
            "\n  ---____*****"+
            "\n  ****----___*");
            System.out.println("- - - - - - - - - - - - - - - - - - - - - ");
            
        }
    }

}
