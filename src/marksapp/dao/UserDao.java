/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marksapp.dao;

import marksapp.database.MySqlConnection;
import marksapp.model.UserData;
import java.sql.*;
import marksapp.model.LoginRequest;
import marksapp.model.ResetPasswordRequest;
/**
 *
 * @author sangyakoirala
 */
public class UserDao {
    MySqlConnection mySql = new MySqlConnection();
    
    public boolean register(UserData user){
        Connection conn = mySql.openConnection();
           String createTableSQL = "CREATE TABLE IF NOT EXISTS demoUsers ("
            + "id INT AUTO_INCREMENT PRIMARY KEY, "               
            + "name VARCHAR(50) NOT NULL, "
            + "email VARCHAR(100) UNIQUE NOT NULL, "
            + "password VARCHAR(255) NOT NULL "
            + ")";
        try {
            PreparedStatement createtbl= conn.prepareStatement(createTableSQL);
            createtbl.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(UserDao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
      String query="INSERT INTO demoUsers(name,email,password) VALUES(?,?,?)";  
      
      try{
          PreparedStatement stmnt = conn.prepareStatement(query);
          stmnt.setString(1, user.getName());
          stmnt.setString(2,user.getEmail());
          stmnt.setString(3,user.getPassword());
          int result = stmnt.executeUpdate();
          boolean value= result>0;
          return value;
      } catch(SQLException e){
          return false;
      } finally{
          mySql.closeConnection(conn);
      }
    }
    
    public UserData login(LoginRequest loginReq){
        String query="SELECT * FROM demoUsers WHERE email=? and password=?";
        Connection conn = mySql.openConnection();
        try{
            PreparedStatement stmnt = conn.prepareStatement(query);
            stmnt.setString(1,loginReq.getEmail());
            stmnt.setString(2,loginReq.getPassword());
            ResultSet result= stmnt.executeQuery();
            if (result.next()){
                String email= result.getString("email");
                String name = result.getString("name");
                String password = result.getString("password");
                int id = result.getInt("id");
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
    
    public boolean checkEmail(String email){
        String query = "SELECT * FROM demoUsers WHERE email=?";
        Connection conn= mySql.openConnection();
        try{
            PreparedStatement stmnt = conn.prepareStatement(query);
            stmnt.setString(1,email);
            ResultSet result = stmnt.executeQuery();
//            return result.next();
            if (result.next()){
                return true;
            } else{
                return false;
            }
        } catch (Exception e){
            return false;
        } finally{
            mySql.closeConnection(conn);
        }
    }
    
    public boolean resetPassword(ResetPasswordRequest reset){
        String query = "UPDATE demoUsers SET password=? WHERE email=?";
        Connection conn= mySql.openConnection();
        try{
            PreparedStatement stmnt =conn.prepareStatement(query);
            stmnt.setString(1,reset.getPassword());
            stmnt.setString(2,reset.getEmail());
            int result = stmnt.executeUpdate();
            return result>0;
        } catch (Exception e){
            return false;
        } finally{
            mySql.closeConnection(conn);
        }
    }
    
    
}
