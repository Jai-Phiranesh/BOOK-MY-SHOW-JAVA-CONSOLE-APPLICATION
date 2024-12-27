import java.util.Scanner;

public class UserActions {
    
   public static User  login(Scanner scanner){
        System.out.print("Enter The Username:");
        String userinput=scanner.nextLine();
        System.out.print("Enter The Password:");
        String passwordinput=scanner.nextLine();
        for(User temp:BookMyShow.getUseList()){
            if(temp.getUserid().equals(userinput)){
                if(temp.getPassword().equals(passwordinput)){
                    return temp;
                }
                else{
                   return new User(null,null,null);
                }
            }
            
        }
        return null;
    }


    public static void register(Scanner scanner){
        System.out.println("NO USER FOUND YOU LIKE TO Register (Y/N):");
                    String yesorno1 = scanner.nextLine();
                    if (yesorno1.toLowerCase().equals("y")) {
                        System.out.print("Enter username:");
                        String useridinput = scanner.nextLine();
                        System.out.print("Enter password:");
                        String password = scanner.nextLine();
                        System.out.print("Enter Your Location:");
                        String userlocation = scanner.nextLine();
                        System.out.print("ARE YOU SURE TO REGISTER Y/N");
                        String yesorno = scanner.nextLine();
                        if (yesorno.toLowerCase().equals("y")) {
                            BookMyShow.getUseList().add(new User(useridinput, password,userlocation));
                        } else if (yesorno.toLowerCase().equals("n")) {
                            return;
                        }
                    } else if (yesorno1.equals("n") || yesorno1.equals("N")) {
                        return;}
    }

    public static void operations(Scanner scanner,User currentuser){

        while (true) {
            System.out.println("\n1.Add Location \n2.Add Theatre");
            System.out.print("Enter the choice...");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    AdminActions.addLocation(scanner);
                    break;
                case "2":
                    AdminActions.addTheatre(scanner);                }
        }
    }



    public static void displayallMovies(Scanner scanner,User ob){
        System.out.println("Movies In Your Locations are");
        for(var temp:BookMyShow.getLocationmovie().get(ob.getLocation())){
            
            System.out.println(temp.getName());

        }
    }





}
