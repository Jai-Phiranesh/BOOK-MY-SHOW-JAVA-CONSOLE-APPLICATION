public class Admin {
    private String userid;
    private String password;


    public Admin(String id,String pass){
        this.userid=id;
        this.password=pass;
    }

    public Admin(){
        
    }

    public String getPassword() {
        return password;
    }
    public String getUserid() {
        return userid;
    }
}
