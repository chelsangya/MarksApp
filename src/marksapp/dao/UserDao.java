/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marksapp.dao;

import marksapp.database.MySqlConnection;
import marksapp.model.UserData;
import java.sql.*;
import marksapp.model.LoginRequest;
/**
 *
 * @author sangyakoirala
 */
public class UserDao {
    MySqlConnection mySql = new MySqlConnection();
    public boolean register(UserData user){
      String query="INSERT INTO users(fname,email,fpassword) VALUES(?,?,?)";  
      Connection conn = mySql.openConnection();
      try{
          PreparedStatement stmnt = conn.prepareStatement(query);
          stmnt.setString(1, user.getName());
          stmnt.setString(2,user.getEmail());
          stmnt.setString(3,user.getPassword());
          int result = stmnt.executeUpdate();
          boolean value= result>0;
          return value;
      } catch(Exception e){
          return false;
      } finally{
          mySql.closeConnection(conn);
      }
    }
    
    public UserData login(LoginRequest loginReq){
        String query="SELECT * FROM users WHERE email=? and fpassword=?";
        Connection conn = mySql.openConnection();
        try{
            PreparedStatement stmnt = conn.prepareStatement(query);
            stmnt.setString(1,loginReq.getEmail());
            stmnt.setString(2,loginReq.getPassword());
            ResultSet result= stmnt.executeQuery();
            if (result.next()){
                String email= result.getString("email");
                String name = result.getString("fname");
                String password = result.getString("fpassword");
                String id = result.getString("id");
                UserData user = new UserData(id,name,email,password);
                return user;
            } else {
                return null;
            }
        } catch (Exception e){
            return null;
        } finally{
            mySql.closeConnection(conn);
        }
    }
    
    
    
}
