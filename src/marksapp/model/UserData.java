/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marksapp.model;

/**
 *
 * @author sangyakoirala
 */
public class UserData {
      // private attributes
//    public methods
    private String name;
    private String email;
    private String password;
//    public constructor
    public UserData(String name, String email, String password){
        this.name=name;
        this.email=email;
        this.password=password;
    }
//    setters
    public void setName(String name){
        this.name= name;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setPassword(String password){
        this.password=password;
    }
//    getters
    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPassword(){
        return this.password;
    }
}
