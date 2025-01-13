import java.text.ParseException;
import java.util.Scanner;

public class BookMyShowaction {
    public static Scanner scanner = new Scanner(System.in);
    public static void start() throws ParseException {
     
        BookMyShow.getAdminlist().add(new Admin("1", "1"));//added the default admin pass and id to admin list

        System.out.println("-------------------------");
        System.out.println("        WELCOMME         ");
        System.out.println("           TO            ");
        System.out.println("      BOOK MY SHOW       ");
        System.out.println("-------------------------");
        OUTER:
        while (true) {//untill the user give the exit option loop continue
            System.out.println();
            System.out.println("---------------------------------------------------------"+"\n");
            System.out.println("Choose The Choice");
            System.out.println("\n1.Admin\n2.User\n3.Exit");
            System.out.print("Enter choice...");
            String choice = scanner.nextLine();
            System.out.println("---------------------------------------------------------"+"\n");
            switch (choice) {
                case "1"://for admin login
                    {
                        Admin result = AdminAction.login();
                        if (result == null) {//no account
                            System.out.println("INVALID ADMIN DETAILS:");
                        } else if (result.getUserid() == null) {//password attempts reached
                            System.out.println("Wrong password:");
                        } else {
                            AdminAction.operations( result);//success of login
                        }       break;
                    }
                case "2"://user login
                    {
                        User result = UserActions.login();
                        if (result == null) {//no account
                            UserActions.register();
                            
                        } else if (result.getUserid() == null) {//password attempts reached
                            System.out.println("Wrong password:");
                        } else {
                            UserActions.operations( result);//success of login
                        }       break;
                    }
                case "3":
                    break OUTER;//to exit the app
                default:
                    System.out.println("INVALID DETAILS");//except the valid input
                    break;
            }
        }
    }
}
