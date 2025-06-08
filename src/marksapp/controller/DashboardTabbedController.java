/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marksapp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import marksapp.dao.MarksDao;
import marksapp.model.MarksData;
import marksapp.model.UserData;
import marksapp.view.DashboardTabbedView;
import marksapp.view.DashboardView;
import marksapp.view.LoginView;

/**
 *
 * @author sangyakoirala
 */
public class DashboardTabbedController {
    DashboardTabbedView view;
    UserData user;
    MarksDao marksDao = new MarksDao();
    JTabbedPane tabbedPane;
    public DashboardTabbedController(DashboardTabbedView view,UserData user){
        this.view= view;
        this.user = user;
        String name = user.getName();
        this.view.addMarks( new AddMarks());
        this.view.logout(new LogoutUser());
        tabbedPane=this.view.getTabbedPane();
        this.view.getIndex(new ChangedTab());
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
    class ChangedTab implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            int tab= tabbedPane.getSelectedIndex();
            if (tab==1){
                loadUserMarks();
            }
        }
        private void loadUserMarks() {
//        fetch marks for given user id from marksDao
        ArrayList<MarksData> marksList = marksDao.getMarksForUid(user.getId());
        
//        create non-editable tableModel-- optional
        DefaultTableModel tableModel = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            // All cells are non-editable
            return false;
        }
    };
//        set column identifiers -- headings
        tableModel.setColumnIdentifiers(new String[] {
            "ID", "Name", "Database", "OOP", "Project", "Business"
        });
        
//      populate the data in table rows
        for (MarksData marks : marksList) {
            tableModel.addRow(new Object[]{
                marks.getId(),
                marks.getName(),
                marks.getDatabaseMarks(),
                marks.getOopMarks(),
                marks.getProjectMarks(),
                marks.getBusinessMarks()
            });
        }
        view.getMarksTable().setModel(tableModel);
    }
    
        
    }
    class LogoutUser implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int confirm = JOptionPane.showConfirmDialog(
                    view,
                    "Are you sure you want to log out?",
                    "Logout Confirmation",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {            
            LoginView loginView = new LoginView();
            LoginController loginController = new LoginController(loginView);
            loginController.open();
            close();
            }
        }
        
    }
   
}
