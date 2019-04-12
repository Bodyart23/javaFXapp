package sample;

public class User {
    private String userName;
    private String pussword;

    public User(String userName, String pussword) {
        this.userName = userName;
        this.pussword = pussword;
    }

    public User(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPussword() {
        return pussword;
    }

    public void setPussword(String pussword) {
        this.pussword = pussword;
    }

    @Override
    public String toString(){
        return "User:[name]="+userName;
    }
}
