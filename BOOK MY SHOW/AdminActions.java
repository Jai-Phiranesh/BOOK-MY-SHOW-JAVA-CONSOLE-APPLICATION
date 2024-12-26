import java.util.Scanner;

public class AdminActions {
    public static Admin  login(Scanner scanner){
        System.out.println("Enter The Username:");
        String userinput=scanner.nextLine();
        System.out.println("Enter The Password:");
        String passwordinput=scanner.nextLine();
        for(Admin temp:BookMyShow.getAdminlist()){
            if(temp.getUserid().equals(userinput)){
                if(temp.getPassword().equals(passwordinput)){
                    return temp;
                }
                else{
                   return new Admin(null,null);
                }
            }
            
        }
        return null;

    }

    public static void operations(Scanner scanner,Admin currentadmin){
        
    }
}
