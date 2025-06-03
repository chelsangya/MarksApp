/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package marksapp;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
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
          try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Nimbus look and feel not available. Using default.");
        }
        LoginView view = new LoginView();
        LoginController controller = new LoginController(view);
        controller.open();
    }
    
}
