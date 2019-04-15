package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:postgresql://"+dbHost+":"
                + dbPort + "/" + dbName;

        Class.forName("org.postgresql.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public ResultSet getUser(User user){
        ResultSet resSet = null;

        String select = "SELECT * FROM users" +
                " WHERE " + Const.USERS_PASSWORD + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getPussword());

            resSet = prSt.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return resSet;
    }

    public ObservableList<User> get() throws Exception {
        try{
            PreparedStatement statement = getDbConnection().prepareStatement(
                    "SELECT * FROM users ");

            ResultSet result = statement.executeQuery();

            ObservableList<User> list = FXCollections.observableArrayList();
            while(result.next()){
                User user = new User();
                user.setUserName(result.getString("name"));
                user.setPussword(result.getString("password"));
                user.setAutologout(result.getInt("autologout"));
                list.add(user);
            }
            return list;
        } catch(Exception e) {System.out.println(e);}
        return null;
    }

}
