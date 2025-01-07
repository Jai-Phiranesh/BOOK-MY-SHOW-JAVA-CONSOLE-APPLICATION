

public class User {
    private String userid;
    private String password;
    private String location;

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }


    public User(String id,String pass,String location){
        this.userid=id;
        this.password=pass;
        this.location=location;
    }

    public User(){

    }

    public String getPassword() {
        return password;
    }

    public String getUserid() {
        return userid;
}}
