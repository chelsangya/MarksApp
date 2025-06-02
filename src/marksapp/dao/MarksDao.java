package marksapp.dao;


import java.sql.*;
import marksapp.database.MySqlConnection;
import marksapp.model.MarksData;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sangyakoirala
 */
public class MarksDao {
    MySqlConnection mySql = new MySqlConnection();
    public boolean addMarks(MarksData marks){
        String query= "INSERT INTO marks (name,uid,databaseMarks,oopMarks,projectMarks,businessMarks) VALUES(?,?,?,?,?,?)";
        Connection conn= mySql.openConnection();
        try{
            PreparedStatement stmnt = conn.prepareStatement(query);
            stmnt.setString(1,marks.getName());
            stmnt.setString(2,marks.getUserId());
            stmnt.setInt(3,marks.getDatabaseMarks());
            stmnt.setInt(4,marks.getOopMarks());
            stmnt.setInt(5,marks.getProjectMarks());
            stmnt.setInt(6,marks.getBusinessMarks());
            int result=stmnt.executeUpdate();
            return result>0;
        } catch(SQLException e){
            return false;
        } finally{
            mySql.closeConnection(conn);
        }
        
    }
}
