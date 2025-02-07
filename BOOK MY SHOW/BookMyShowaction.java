
import java.util.Scanner;

public class BookMyShowaction {
    public static Scanner scanner = new Scanner(System.in);
    public static void start() {
     
        BookMyShow.getAccountList().add(new Admin("admin", "123"));//added the default admin pass and id to admin list

        System.out.println("-------------------------");
        System.out.println("        WELCOMME         ");
        System.out.println("           TO            ");
        System.out.println("      BOOK MY SHOW       ");
        System.out.println("-------------------------");
       
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
                        Account result = AdminAction.login();
                        if (result == null) {//no account
                            System.out.println("INVALID ADMIN DETAILS OR WRONG PASSWORD:");
                        } else {
                            AdminAction.operations( (Admin)result);//success of login
                        }       break;
                    }
                case "2"://user login
                    {
                        Account currentUser = UserActions.login();
                        if (currentUser == null) {//no account
                            UserActions.register();
                            
                        } else if (currentUser.getId() == null) {//password attempts reached
                            System.out.println("Wrong password:");
                        } else {
                            UserActions.operations( (User)currentUser);//success of login
                        }       break;
                    }
                case "3":
                    return;
                default:
                    System.out.println("INVALID DETAILS");//except the valid input
                    break;
            }
        }
    }
}
