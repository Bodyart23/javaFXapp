package sample;

import javafx.scene.control.TextField;

public class User {
    private String userName;
    private String pussword;
    private Integer autologout;

    public User(String userName, String pussword, Integer autologout) {
        this.userName = userName;
        this.pussword = pussword;
        this.autologout = autologout;
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

    public Integer getAutologout() {
        return autologout;
    }

    public void setAutologout(Integer autologout) {
        this.autologout = autologout;
    }

    @Override
    public String toString(){
        return "User:[name]="+userName;
    }
}
