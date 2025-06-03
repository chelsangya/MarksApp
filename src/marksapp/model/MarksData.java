/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marksapp.model;

/**
 *
 * @author sangyakoirala
 */
public class MarksData {
    private int id;
    private int userId;
    private String name;
    private int databaseMarks;
    private int oopMarks;
    private int businessMarks;
    private int projectMarks;
     public MarksData( int userId,String name,int databaseMarks,int oopMarks,int projectMarks,int businessMarks){
        this.userId=userId;
        this.name=name;
        this.businessMarks=businessMarks;
        this.databaseMarks=databaseMarks;
        this.oopMarks=oopMarks;
        this.projectMarks=projectMarks;
    }
    
    public MarksData(int userId,int id, String name,int databaseMarks,int oopMarks,int projectMarks,int businessMarks){
        this.userId=userId;
        this.id=id;
        this.name=name;
        this.businessMarks=businessMarks;
        this.databaseMarks=databaseMarks;
        this.oopMarks=oopMarks;
        this.projectMarks=projectMarks;
    }

    public int getId() {
        return id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDatabaseMarks() {
        return databaseMarks;
    }

    public void setDatabaseMarks(int databaseMarks) {
        this.databaseMarks = databaseMarks;
    }

    public int getOopMarks() {
        return oopMarks;
    }

    public void setOopMarks(int oopMarks) {
        this.oopMarks = oopMarks;
    }

    public int getBusinessMarks() {
        return businessMarks;
    }

    public void setBusinessMarks(int businessMarks) {
        this.businessMarks = businessMarks;
    }

    public int getProjectMarks() {
        return projectMarks;
    }

    public void setProjectMarks(int projectMarks) {
        this.projectMarks = projectMarks;
    }
    
}
