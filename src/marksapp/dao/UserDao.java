/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marksapp.dao;

import marksapp.database.MySqlConnection;
import marksapp.model.UserData;
import java.sql.*;
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
}
