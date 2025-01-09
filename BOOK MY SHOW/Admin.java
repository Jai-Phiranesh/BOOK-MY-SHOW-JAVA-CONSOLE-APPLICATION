
public class Admin {//pojo class
    private String userid;//admin id and pass
    private String password;


    public Admin(String id,String pass){//constructor to initialize the id and pass
        this.userid=id;
        this.password=pass;
    }

    public Admin(){//constructor for creating object without parameter
        
    }

    public String getPassword() {//getters
        return password;
    }
    public String getUserid() {//getters
        return userid;
    }
}
