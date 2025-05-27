/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marksapp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import marksapp.dao.UserDao;
import marksapp.model.UserData;
import marksapp.view.LoginView;
import marksapp.view.RegisterView;

/**
 *
 * @author sangyakoirala
 */
public class RegisterController {
    RegisterView view = new RegisterView();
    public RegisterController(RegisterView view){
        this.view=view;
        RegisterUser register= new RegisterUser();
        this.view.registerUser(register);
    }
    public void open(){
        this.view.setVisible(true);
    }
    public void close(){
        this.view.dispose();
    }
    class RegisterUser implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
//            assign required values to variables
            String name= view.getNameTextField().getText();
            String email = view.getEmailTextField().getText();
            String password = String.valueOf(view.getPasswordField().getPassword());
            String confirmPassword = String.valueOf(view.getConfirmPasswordField().getPassword());
            
            if (name.isEmpty()||email.isEmpty()||password.isEmpty()||confirmPassword.isEmpty()){
                JOptionPane.showMessageDialog(view, "Fill in all the fields");
            } else if (!password.equals(confirmPassword)){
                JOptionPane.showMessageDialog(view,"Passwords do not match");
            } else{
                UserData user = new UserData(name,email,password);
                UserDao userDao = new UserDao();
                boolean result = userDao.register(user);
                if (result){
                    JOptionPane.showMessageDialog(view, "Registered Successfully");
                    LoginView loginView = new LoginView();
                    LoginController loginController = new LoginController(loginView);
                    loginController.open();
                    close();
                } else {
                JOptionPane.showMessageDialog(view,"Failed to Register");
                    
                }
            }
                
                
            
        }
        
    }
}
