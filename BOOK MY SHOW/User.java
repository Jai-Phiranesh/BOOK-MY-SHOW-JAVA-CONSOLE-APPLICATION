

public class User {//USer pojo class
    private String userid;//user id
    private String password;//user pass
    private String location;//user location

    public String getLocation() {//getters
        return location;
    }
    public void setLocation(String location) {//setters
        this.location = location;
    }


    public User(String id,String pass,String location){//constructor to initialize the id and pass and location
        this.userid=id;
        this.password=pass;
        this.location=location;
    }

    public User(){//constructor for creating object without parameter

    }

    public String getPassword() {//getters
        return password;
    }

    public String getUserid() {//getters
        return userid;
}}
