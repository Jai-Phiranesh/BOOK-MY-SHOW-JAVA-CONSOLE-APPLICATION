import java.util.Scanner;

public class UserActions {
    
   public static User  login(Scanner scanner){
        System.out.println("Enter The Username:");
        String userinput=scanner.nextLine();
        System.out.println("Enter The Password:");
        String passwordinput=scanner.nextLine();
        for(User temp:BookMyShow.getUseList()){
            if(temp.getUserid().equals(userinput)){
                if(temp.getPassword().equals(passwordinput)){
                    return temp;
                }
                else{
                   return new User(null,null);
                }
            }
            
        }
        return null;
    }

    public static void operations(Scanner scanner,User currentuser){

        
    }


}
