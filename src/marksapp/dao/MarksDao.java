package marksapp.dao;


import java.sql.*;
import java.util.ArrayList;
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
        Connection conn = mySql.openConnection();
        String createMarksTableSQL = "CREATE TABLE IF NOT EXISTS marks ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(50) NOT NULL, "
                + "uid int NOT NULL, "
                + "databaseMarks INT NOT NULL, "
                + "oopMarks INT NOT NULL, "
                + "projectMarks INT NOT NULL, "
                + "businessMarks INT NOT NULL, "
                + "FOREIGN KEY (uid) REFERENCES demoUsers(id)"
                + ")";

         
        try {
            PreparedStatement createtbl= conn.prepareStatement(createMarksTableSQL);
            createtbl.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        String query= "INSERT INTO marks (name,uid,databaseMarks,oopMarks,projectMarks,businessMarks) VALUES(?,?,?,?,?,?)";
//        MarksData(user.getId(),name,databaseMarks,oopMarks,projectMarks,businessMarks);
        try{
            PreparedStatement stmnt = conn.prepareStatement(query);
            stmnt.setString(1,marks.getName());
            stmnt.setInt(2,marks.getUserId());
            stmnt.setInt(3,marks.getDatabaseMarks());
            stmnt.setInt(4,marks.getOopMarks());
            stmnt.setInt(5,marks.getProjectMarks());
            stmnt.setInt(6,marks.getBusinessMarks());
            int result=stmnt.executeUpdate();
            return result>0;
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        } finally{
            mySql.closeConnection(conn);
        }
        
    }
    


}
