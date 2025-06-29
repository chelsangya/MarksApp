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
    
//    fetch all marks 
    public ArrayList<MarksData> getMarksForUid(int uid){
        Connection conn= mySql.openConnection();
        String query= "SELECT * FROM marks WHERE uid=?";
        ArrayList<MarksData> marksList = new ArrayList<>();
        try{
            PreparedStatement stmnt= conn.prepareStatement(query);
            stmnt.setInt(1,uid);
            ResultSet result = stmnt.executeQuery();
            while(result.next()){
            String name = result.getString("name");
            int id= result.getInt("id");
            int databaseMarks = result.getInt("databaseMarks");
            int oopMarks = result.getInt("oopMarks");
            int projectMarks = result.getInt("projectMarks");
            int businessMarks = result.getInt("businessMarks");

            MarksData marks = new MarksData(uid,id, name, databaseMarks, oopMarks, projectMarks, businessMarks);
            marksList.add(marks);
            }
            return marksList;
        } catch(SQLException e){
            return null;
        } finally {
            mySql.closeConnection(conn);
        }
        
    }
    public boolean updateMarks(MarksData marks) {
        System.out.println("Id"+marks.getId());
        System.out.println("User id"+marks.getUserId());
        System.out.println("Name"+marks.getName());        
        System.out.println("Db marks"+marks.getDatabaseMarks());
        System.out.println("Business marks"+marks.getBusinessMarks());
        System.out.println("OOP marks"+marks.getOopMarks());
        System.out.println("Database marks"+marks.getDatabaseMarks());
        


        Connection conn = mySql.openConnection();
        String query = "UPDATE marks SET name=?, databaseMarks=?, oopMarks=?, projectMarks=?, businessMarks=? WHERE id=? AND uid=?";
        try {
            
            PreparedStatement stmnt = conn.prepareStatement(query);
            stmnt.setString(1, marks.getName());
            stmnt.setInt(2, marks.getDatabaseMarks());
            stmnt.setInt(3, marks.getOopMarks());
            stmnt.setInt(4, marks.getProjectMarks());
            stmnt.setInt(5, marks.getBusinessMarks());
            stmnt.setInt(6, marks.getId());         
            stmnt.setInt(7, marks.getUserId());     
            int result = stmnt.executeUpdate();
            System.out.println("result:"+result);
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            mySql.closeConnection(conn);
        }
    }

    // Delete marks by id and uid (ownership check)
    public boolean deleteMarks(int markId, int userId) {
        Connection conn = mySql.openConnection();
        String query = "DELETE FROM marks WHERE id=? AND uid=?";
        try {
            PreparedStatement stmnt = conn.prepareStatement(query);
            stmnt.setInt(1, markId);
            stmnt.setInt(2, userId);
            int result = stmnt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            mySql.closeConnection(conn);
        }
    }

}
