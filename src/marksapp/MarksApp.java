/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package marksapp;
import marksapp.controller.LoginController;
import marksapp.controller.RegisterController;
import marksapp.view.LoginView;
import marksapp.view.RegisterView;

/**
 *
 * @author sangyakoirala
 */
public class MarksApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LoginView view = new LoginView();
        LoginController controller = new LoginController(view);
        controller.open();
    }
    
}
