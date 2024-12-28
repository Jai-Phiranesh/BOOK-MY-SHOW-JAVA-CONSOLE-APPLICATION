import java.text.ParseException;
import java.util.Scanner;

public class BookMyShowactions {
    public static void start() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        BookMyShow.getAdminlist().add(new Admin("1", "1"));

        System.out.println("-------------------------");
        System.out.println("        WELCOMME         ");
        System.out.println("           TO            ");
        System.out.println("      BOOK MY SHOW       ");
        System.out.println("-------------------------");
        OUTER:
        while (true) {
            System.out.println();
            System.out.println("Choose The Choice");
            System.out.println("\n1.Admin\n2.User\n3.Exit");
            System.out.print("Enter choice...");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    {
                        Admin result = AdminActions.login(scanner);
                        if (result == null) {
                            System.out.println("INVALID ADMIN DETAILS:");
                        } else if (result.getUserid() == null) {
                            System.out.println("Wrong password:");
                        } else {
                            AdminActions.operations(scanner, result);
                        }       break;
                    }
                case "2":
                    {
                        User result = UserActions.login(scanner);
                        if (result == null) {
                            UserActions.register(scanner);
                            
                        } else if (result.getUserid() == null) {
                            System.out.println("Wrong password:");
                        } else {
                            UserActions.operations(scanner, result);
                        }       break;
                    }
                case "3":
                    break OUTER;
                default:
                    System.out.println("INVALID DETAILS");
                    break;
            }
        }
    }
}