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
    private String id;
    private String userId;
    private String name;
    private int databaseMarks;
    private int oopMarks;
    private int businessMarks;
    private int projectMarks;
     public MarksData( String userId,String name,int databaseMarks,int oopMarks,int projectMarks,int businessMarks){
        this.businessMarks=businessMarks;
        this.databaseMarks=databaseMarks;
        this.oopMarks=oopMarks;
        this.projectMarks=projectMarks;
    }
    
    public MarksData(String userId,String id, String name,int databaseMarks,int oopMarks,int projectMarks,int businessMarks){
        this.userId=userId;
        this.id=id;
        this.businessMarks=businessMarks;
        this.databaseMarks=databaseMarks;
        this.oopMarks=oopMarks;
        this.projectMarks=projectMarks;
    }

    public String getId() {
        return id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserId() {
        return userId;
    }

    public void setId(String id) {
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
