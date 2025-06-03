/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marksapp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import marksapp.dao.MarksDao;
import marksapp.model.MarksData;
import marksapp.model.UserData;
import marksapp.view.DashboardView;

/**
 *
 * @author sangyakoirala
 */
public class DashboardController {
    DashboardView view;
    UserData user;
    MarksDao marksDao = new MarksDao();
    public DashboardController(DashboardView view,UserData user){
        this.view= view;
        this.user = user;
        String name = user.getName();
        this.view.getWelcomeLabel().setText("Welcome "+ name);
        this.view.addMarks(new AddMarks());
    }
    public void open(){
        view.setVisible(true);
    }
    public void close(){
        view.dispose();
    }
    class AddMarks implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            String name= view.getStudentNameTextField().getText();
            String databaseMarksStr=view.getDatabaseMarksTextField().getText();
            String oopMarksStr=view.getOopMarksTextField().getText();
            String projectMarksStr=view.getProjectMarksTextField().getText();
            String businessMarksStr=view.getBusinessMarksTextField().getText();
            if(name.trim().isEmpty()){
                JOptionPane.showMessageDialog(view, "Enter the student name");
            } else if (databaseMarksStr.trim().isEmpty()||
                    oopMarksStr.trim().isEmpty()||
                    projectMarksStr.trim().isEmpty()||
                    businessMarksStr.trim().isEmpty()){
                JOptionPane.showMessageDialog(view, "Enter the marks");
            } else if (!isValidMark(databaseMarksStr) ||
                    !isValidMark(oopMarksStr) ||
                    !isValidMark(projectMarksStr) ||
                    !isValidMark(businessMarksStr)) {
                JOptionPane.showMessageDialog(view, "Marks must be numbers between 0 and 100");
            } else {
                int databaseMarks = Integer.parseInt(databaseMarksStr.trim());
                int projectMarks = Integer.parseInt(projectMarksStr.trim());
                int businessMarks = Integer.parseInt(businessMarksStr.trim());                
                int oopMarks = Integer.parseInt(oopMarksStr.trim());
                int uid=user.getId();

                MarksData marks= new MarksData(uid,name,databaseMarks,oopMarks,projectMarks,businessMarks);
                boolean marksAdded= marksDao.addMarks(marks);
                if(!marksAdded){
                    JOptionPane.showMessageDialog(view,"Failed to insert marks");
                    
                    
                } else{
                    JOptionPane.showMessageDialog(view,"Marks added");
                    view.getStudentNameTextField().setText("");
                    view.getProjectMarksTextField().setText("");
                    view.getDatabaseMarksTextField().setText("");
                    view.getOopMarksTextField().setText("");
                    view.getBusinessMarksTextField().setText("");
                }
            }
            
        }
        private boolean isValidMark(String markStr) {
            try {
                int mark = Integer.parseInt(markStr.trim());
                return mark >= 0 && mark <= 100;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        
    }
}
