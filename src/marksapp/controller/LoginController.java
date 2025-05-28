/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marksapp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import marksapp.controller.mail.SMTPSMailSender;
import marksapp.dao.UserDao;
import marksapp.model.LoginRequest;
import marksapp.model.ResetPasswordRequest;
import marksapp.model.UserData;
import marksapp.view.DashboardView;
import marksapp.view.LoginView;

/**
 *
 * @author sangyakoirala
 */
public class LoginController {
    private LoginView view;
    public LoginController(LoginView view){
        this.view=view;
        LoginUser loginUser = new LoginUser();
        this.view.loginUser(loginUser);
        ResetPassword resetPass= new ResetPassword();
        this.view.forgotPassword(resetPass);
    }
    public void open(){
        view.setVisible(true);
    }
    public void close(){
        view.dispose();
    }
    class LoginUser implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String email=view.getEmailTextField().getText();
            String password = String.valueOf(view.getPasswordField().getPassword());
            if (email.isEmpty()||password.isEmpty()){
                JOptionPane.showMessageDialog(view, "Fill in all the fields");
            } else {
                LoginRequest loginReq = new LoginRequest(email,password);
                UserDao userDao = new UserDao();
                UserData user = userDao.login(loginReq);
                if(user==null){
                    JOptionPane.showMessageDialog(view,"Login failed");
                } else{
                    DashboardView dashboardView = new DashboardView();
                    DashboardController dashboardController = new DashboardController(dashboardView,user);
                    dashboardController.open();
                    close();
                    
                }
            }
        }
        
    }
    
    class ResetPassword implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            String email = JOptionPane.showInputDialog(view,"Enter your email");
            if(email.isEmpty()){
                JOptionPane.showMessageDialog(view, "Email cannot be empty");
            } else {
                UserDao userDao = new UserDao();
                boolean emailExists = userDao.checkEmail(email);
                if (!emailExists){
                    JOptionPane.showMessageDialog(view, "Email does not exist");
                } else {
                    String otp = "987586";
//                    SMTPSMailSender smtpSender= new SMTPSMailSender();
                    String title="Reset Password Verification";
                    String body="The otp to reset your password is "+otp;
                    boolean mailSent= SMTPSMailSender.sendMail(email,title,body);
                    if (!mailSent){
                        JOptionPane.showMessageDialog(view, "Failed to send OTP. Try again later.");
                    } else {
                        String otpReceived = JOptionPane.showInputDialog(view,"Enter your otp code");
                        if(!otp.equals(otpReceived)){
                            JOptionPane.showMessageDialog(view, "Otp did not match");
                            
                        } else {
                            String password = JOptionPane.showInputDialog(view,"Enter your new password");
                            if (password.trim().isEmpty()){
                                JOptionPane.showMessageDialog(view, "Password cannot be empty");
                            } else {
                                ResetPasswordRequest resetReq = new ResetPasswordRequest(email,password);
                                boolean updateResult = userDao.resetPassword(resetReq);
                                if(!updateResult){
                                 JOptionPane.showMessageDialog(view,"Failed to reset password");
                                } else {
                                    JOptionPane.showMessageDialog(view, "Password has been changed");
                                }
                                
                            }
                        }
                        
                    }
                    
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
        
    }
}
