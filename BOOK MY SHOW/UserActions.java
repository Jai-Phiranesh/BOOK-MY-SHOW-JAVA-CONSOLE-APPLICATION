import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class UserActions {
    public static Scanner scanner = new Scanner(System.in);

    public static User login() {//perform the login action of the user
        System.out.print("Enter The Username:");
        String userinput = scanner.nextLine();//get user name
        System.out.print("Enter The Password:");
        String passwordinput = scanner.nextLine();//grt password
        for (User temp : BookMyShow.getUseList()) {//get user arraylist
            if (temp.getUserid().equals(userinput)) {//check every object id and pass
                if (temp.getPassword().equals(passwordinput)) {
                    return temp;//if matches return the current user object
                } else {
                    return new User(null, null, null);//for wrong pass return object with null values
                }
            }

        }
        return null;//no account pass null
    }

    public static void register() {//if no account the function is called
        System.out.print("NO USER FOUND YOU LIKE TO Register (Y/N):");//conformation to get into register
        String yesorno1 = scanner.nextLine();
        if (yesorno1.toLowerCase().equals("y")) {//check input
            System.out.print("Enter username:");
            String useridinput = scanner.nextLine();//get user name
            System.out.print("Enter password:");
            String password = scanner.nextLine();//get password
            System.out.print("Enter Your Location:");
            String userlocation = scanner.nextLine();//get locatiom
            System.out.print("ARE YOU SURE TO REGISTER (Y/N):");//ask conformation to register with this detatils
            String yesorno = scanner.nextLine();
            if (yesorno.toLowerCase().equals("y")) {
                BookMyShow.getUseList().add(new User(useridinput, password, userlocation));//if yes add in array list with new object with this input fields
            } else if (yesorno.toLowerCase().equals("n")) {
                return;//else return
            }
        } else if (yesorno1.equals("n") || yesorno1.equals("N")) {
            return;//if n return to previous features
        }
    }

    public static void operations(User currentuser) {

        UserActions.displayallMovies(currentuser, Utilities.getToday());//very bigning of the login show the movies

        while (true) {
            System.out.println("---------------------------------------------------------" + "\n");
            System.out.println("Enter The User Choice...");
            System.out.println(
                    "------------- 1.Change Location---------------- 2.Change Date ---------------3.view thickets---------------4.Exit ");
            System.out.print("Enter the choice...");
            String choice = scanner.nextLine();
            System.out.println("---------------------------------------------------------" + "\n");
            switch (choice) {

                case "1"://based on the choice call the function (1)
                    UserActions.changeLocation(currentuser);
                    UserActions.displayallMovies(currentuser, Utilities.getToday());//after the change in the location show the movies 
                    break;
                case "2"://based on the choice call the function (2)
                    LocalDate dateupdate=UserActions.changeDate(currentuser);
                    UserActions.displayallMovies(currentuser, dateupdate);//after the change in the date show the movies 
                    break;
                case "3"://based on the choice call the function (3)
                    UserActions.viewtickets(currentuser);
                    break;
                case "4"://exit the function and go to previous fumvtion
                    return;
            }
        }
    }

    public static void displayallMovies(User ob, LocalDate today) {//display movies based on users location and date
       
        HashSet<String> moviesinthatlocation = new HashSet<>();//to store the movies in that location
        ArrayList<Movies> moviesavailable = new ArrayList<>();//to store the every object of the particulat movie of the user entered
        if(BookMyShow.getMovieandmovieobj().keySet().isEmpty()){
            System.out.println("\n"+"No movies in your location or date try some other date or location"+"\n");
                return;
        }//to check for no movies in entire app

        for (var movies : BookMyShow.getMovieandmovieobj().keySet()) {
            boolean check = false;
            var moviesarraylist = BookMyShow.getMovieandmovieobj().get(movies);
            for (var movieobject : moviesarraylist) {
                if (movieobject.getLocation().equals(ob.getLocation()) && (movieobject.getStartdate().isEqual(today))) {//check and display the movies based on the location and date
                    check = true;

                }

            }
            if (check) {//to check the movies and print the movie only availabe in that location and date
                System.out.println("->" + movies);
                moviesinthatlocation.add(movies);
            }
            else{//to print no movies in that location or date
                System.out.println("\n"+"No movies in your location or date try some other date or location"+"\n");
                return;
            }

        }
        System.out.print("Enter the movie name to book or press (n) to exit the option:");
        String movieChoice = scanner.nextLine();//get the movie name to book
        if (moviesinthatlocation.contains(movieChoice)) {//if movie entered bye the user exists
            for (var movieobject : BookMyShow.getMovieandmovieobj().get(movieChoice)) {
                if (movieobject.getLocation().equals(ob.getLocation()) && (movieobject.getStartdate().isEqual(today))) {
                    moviesavailable.add(movieobject);//add all the movie object in arraylist
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

        showmovies(moviesavailable, movieChoice,ob);//pass the movie object,current user, finally movie name

    }

    public static void showmovies(ArrayList<Movies> movieavailable, String moviechoice,User currenUser) {
        System.out.println("\n" +
                "---------------------------------------------------------") ;
        System.out.println("Movie:" + moviechoice);
        System.out.println("\n" +//print movie name
                "---------------------------------------------------------" );
        HashMap<String, HashSet<Show>> theatreAgainstShow = new HashMap<>();//to reduce the time consuming process to prirnt the teatre name show alone
        for (var currentMovie : movieavailable) {//print all the movie object fields which is enterd by the user(only the usesr location and date)

            if (theatreAgainstShow.containsKey(currentMovie.getTheatreob().getTheatername())) {//if theatre name already exists
                theatreAgainstShow.get(currentMovie.getTheatreob().getTheatername()).add(currentMovie.getShowob());//add the show object
            } else {
                HashSet<Show> show = new HashSet<>();//creat new hash set
                show.add(currentMovie.getShowob());//add to hash set
                theatreAgainstShow.put(currentMovie.getTheatreob().getTheatername(), show);//add to hashmap
            }
        }

        for (String keytheatrename : theatreAgainstShow.keySet()) {
            System.out.println("Theatre:" + keytheatrename);//print theatre name
            System.out.println("\n" +
                "---------------------------------------------------------") ;
            System.out.println("shows are:" + theatreAgainstShow.get(keytheatrename).toString());//print show
            System.out.println("\n" +
                "---------------------------------------------------------"+"\n") ;
        }

        System.out.print("Enter the Theatre name:");
        String theatrename = scanner.nextLine();//get the theatre name
        for (String keytheatrename : theatreAgainstShow.keySet()) {
            if(!theatrename.equals(keytheatrename)){
                System.out.println("Enter correct theatre name:");
                return;
            }
        }

        System.out.print("Enter the Show Start Time example:(09:00):");
        LocalTime showtime = LocalTime.parse(scanner.nextLine(),
                Utilities.getTimeformatter());//get the show start time 
        var showtocheck = theatreAgainstShow.get(theatrename);//get the show object
        Show currentshow = null;//to get the show object of the user entered show
        for (var shows : showtocheck) {
            if (shows.getStarttime().equals(showtime)) {//check both the start time are same
                currentshow = shows;//assagin the current show

            }
        }

        if (currentshow == null) {
            System.out.println("Enter the correct details:");
            return;
        }//if the show time is wrong

        System.out.println("--------Screen Name-------" + currentshow.getScreen().getName());//print the screen name of the show
        System.out.println("--------Screen Available Seats Count-------" + currentshow.getScreen().getAvailableseats());//show available seats in the show
        System.out.println("-------------Price"+currentshow.getPrice()+"------------");//price for the ticket
        for (var seats : currentshow.getSeatarrangement().entrySet()) {
            System.out.println(seats.getKey() + " " + seats.getValue());
        }//print the seat to book

        System.out.print("Enter the number seats to book:");
        Long seatcount = Long.parseLong(scanner.nextLine());//get the number of seats to book
        long finalseatcount=seatcount;//for printing price
        ArrayList<String>userseats=new ArrayList<>();//for storing which seats does the user books
        HashMap<Character,ArrayList<String>>duplicate=new HashMap<>();//for avoing the change if the user gives no while paying
        for(var duplicateclone:currentshow.getSeatarrangement().entrySet()){
            duplicate.put(duplicateclone.getKey(), new ArrayList<String>());//store the all the elements from the old object
            duplicate.get(duplicateclone.getKey()).addAll(duplicateclone.getValue());
        }
        if (currentshow.getScreen().getAvailableseats() < seatcount) {
            System.out.println("Seats in the screen are not sufficient");
            System.out.println("The available seats are" + currentshow.getScreen().getAvailableseats());
            return;
        }//check entered seats are availabe in the show
        
       
        int j = 1;
        while (seatcount > 0) {//continue untill the seatcount is 0
            String grid=currentshow.getScreen().getGrid();
            var starremoved = grid.split("\\*");
            long sum = 0;

       
        for (String a : starremoved) {
            long temp = Long.parseLong(a);
            sum += temp;
        }

           
           

            System.out.print("Enter the row for " + j + " st seat to book example(A1,B1):");
            String choiceseat=scanner.nextLine();
            char row = choiceseat.charAt(0);
           String bookseat=null;
           
            int seatchoice =Integer.parseInt(choiceseat.substring(1)) ;
            
            
           if(seatchoice<=Integer.parseInt(starremoved[0])){
            bookseat=currentshow.getSeatarrangement().get(row).get(seatchoice-1);
            if (bookseat.equals("X")) {
                System.out.println(duplicate.get(row));
                System.out.println("Seat already booked Try any other seats");
                continue;
            }
            duplicate.get(row).set(seatchoice - 1, "X");
            userseats.add(choiceseat);
            System.out.println(duplicate.get(row));
           }
           else if(seatchoice>=((sum+1)-Integer.parseInt(starremoved[2]))){
            bookseat=currentshow.getSeatarrangement().get(row).get(seatchoice+1);
            if (bookseat.equals("X")) {
                System.out.println(duplicate.get(row));
                System.out.println("Seat already booked Try any other seats");
                continue;
            }
            duplicate.get(row).set(seatchoice + 1, "X");
            userseats.add(choiceseat);
            System.out.println(duplicate.get(row));
           }
           else if(seatchoice>Integer.parseInt(starremoved[0])){
            bookseat=currentshow.getSeatarrangement().get(row).get(seatchoice);
            if (bookseat.equals("X")) {
                System.out.println(duplicate.get(row));
                System.out.println("Seat already booked Try any other seats");
                continue;
            }
            duplicate.get(row).set(seatchoice, "X");
            userseats.add(choiceseat);
            System.out.println(duplicate.get(row));
           }
            
            
           
           
          
           
           

            j++;
            seatcount--;

        }
        long totalamount=finalseatcount*currentshow.getPrice();

        System.out.println("The total amount for the ticket is:"+totalamount);//show the total ticket price
        System.out.print("enter the y to confirm the ticket booking or n to exit:");
        String userchoice=scanner.nextLine();//ask for conformation
        if (userchoice.toLowerCase().equals("y")) {//yes
            currentshow.getScreen().setAvailableseats(currentshow.getScreen().getAvailableseats()-seatcount);//reduce the seat count
            currenUser.getTickets().add(new Ticket(theatrename, currentshow.getShowDate(),currentshow.getScreen().getName() , showtime, finalseatcount,totalamount, userseats,moviechoice));//make the ticket object and pass essential fields
            currentshow.setSeatarrangement(duplicate);//mark the clone seats to be the original

            

        }
        else if (userchoice.equals("n") || userchoice.equals("N")) {
            System.out.println("Thank you");
            return;//else return to the function
        }
        


    }

    public static void changeLocation(User currentuserob) {//to change location

        System.out.print("Enter The Location To Change:");
        String locationtochange = scanner.nextLine();//get the input to change location
        currentuserob.setLocation(locationtochange);//set to user
        return;

    }

    public static LocalDate changeDate(User currentuserob) {//tp change date

        System.out.print("Enter The date To Change:");
        LocalDate dateupdate = LocalDate.parse(scanner.nextLine(), Utilities.getFormatter());

        return dateupdate;//return the date which is choosen by the user

    }


    public static void viewtickets(User currentuser){//to dispaly the ticket
        for(var ticket:currentuser.getTickets()){//get the all tickets object from the currnt user ticket array list
            System.out.println("- - - - - - - - - - - - - - - - - - - - - ");
            System.out.println("|  Movie name:"+ticket.getMoviename()+"  |");//print the details of the ticket like
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
