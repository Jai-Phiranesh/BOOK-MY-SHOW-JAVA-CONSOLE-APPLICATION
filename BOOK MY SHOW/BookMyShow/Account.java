package BookMyShow;
public class Account {

    private String id;//id 

    private String pass;//pass

    public String getId() {
        return id;
    }//getters

    public String getPass() {
        return pass;
    } //setters

    Account(String id, String pass){
        this.id=id;
        this.pass=pass;
    }//constructor

}
