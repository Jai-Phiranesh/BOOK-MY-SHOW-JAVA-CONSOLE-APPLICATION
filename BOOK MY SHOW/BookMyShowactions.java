import java.util.Scanner;

public class BookMyShowactions {
    public static void start() {
        Scanner scanner = new Scanner(System.in);
        BookMyShow.getAdminlist().add(new Admin("ad123", "123"));

        System.out.println("WELCOME TO BOOK MY SHOW");
        while (true) {

            System.out.println("\n1.Admin\n2.User\n3.Exit");
            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                Admin result = AdminActions.login(scanner);
                if (result == null) {
                    System.out.println("INVALID ADMIN DETAILS:");
                } else if (result.getUserid() == null) {
                    System.out.println("Wrong password:");
                } else {
                    AdminActions.operations(scanner, result);
                }
            } else if (choice.equals("2")) {
                User result = UserActions.login(scanner);
                if (result == null) {
                    System.out.println("NO USER FOUND YOU LIKE TO Register (Y/N):");
                    String yesorno1 = scanner.nextLine();
                    if (yesorno1.equals("y") || yesorno1.equals("Y")) {
                        System.out.println("Enter username:");
                        String useridinput = scanner.nextLine();
                        System.out.println("Enter password:");
                        String password = scanner.nextLine();
                        System.out.println("ARE YOU SURE TO REGISTER Y/N");
                        String yesorno = scanner.nextLine();
                        if (yesorno.equals("y") || yesorno.equals("Y")) {
                            BookMyShow.getUseList().add(new User(useridinput, password));
                        } else if (yesorno.equals("n") || yesorno.equals("N")) {
                            break;
                        }
                    } else if (yesorno1.equals("n") || yesorno1.equals("N")) {
                        break;
                    } else {
                        System.out.println("INVALID INPUT:");
                    }

                } else if (result.getUserid() == null) {
                    System.out.println("Wrong password:");
                } else {
                    UserActions.operations(scanner, result);
                }
            } else if (choice.equals("3")) {
                break;
            } else {
                System.out.println("INVALID DETAILS");
            }

        }
    }
}
