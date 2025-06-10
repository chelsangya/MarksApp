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
import marksapp.view.DashboardTabbedView;
import marksapp.view.UpdateMarksView;

/**
 *
 * @author sangyakoirala
 */
public class UpdateController {
    MarksData initMarks;
    UserData user;
    UpdateMarksView view;
    MarksDao marksDao;

    public UpdateController(UpdateMarksView view, UserData user, MarksData initMarks) {
        marksDao= new MarksDao();
        this.view = view;
        this.initMarks = initMarks;
        this.user = user;
        String name=initMarks.getName();
        String oop = Integer.toString(initMarks.getOopMarks());
        String business = Integer.toString(initMarks.getBusinessMarks());
        String project= Integer.toString(initMarks.getProjectMarks());
        String db= Integer.toString(initMarks.getDatabaseMarks());
        this.view.getOopMarksTextField().setText(oop);        
        this.view.getBusinessMarksTextField().setText(business);
        this.view.getProjectMarksTextField().setText(project);
        this.view.getDatabaseMarksTextField().setText(db);
        this.view.getStudentNameTextField().setText(name);

        this.view.backNavigation(new ReturnBack());
        this.view.updateMarks(new UpdateMarks());
        this.view.deleteMark(new DeleteMark());
    }

    public void open() {
        this.view.setVisible(true);
    }

    public void close() {
        this.view.dispose();
    }
    
    class ReturnBack implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            DashboardTabbedView tabView= new DashboardTabbedView();
            DashboardTabbedController cont= new DashboardTabbedController(tabView,user);
            cont.open();
            close();
        }
        
    }

    class UpdateMarks implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name= view.getStudentNameTextField().getText();
            String databaseMarksStr= view.getDatabaseMarksTextField().getText();
            String oopMarksStr= view.getOopMarksTextField().getText();
            String projectMarksStr = view.getProjectMarksTextField().getText();
            String businessMarksStr= view.getBusinessMarksTextField().getText();
            int id = initMarks.getId();
            int userid=user.getId();
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

                MarksData marks= new MarksData(uid,id,name,databaseMarks,oopMarks,projectMarks,businessMarks);
                boolean marksAdded= marksDao.updateMarks(marks);
                if(!marksAdded){
                    JOptionPane.showMessageDialog(view,"Failed to update marks");                    
                } else{
                    JOptionPane.showMessageDialog(view,"Marks updated");
                    DashboardTabbedView tabView= new DashboardTabbedView();
                    DashboardTabbedController cont= new DashboardTabbedController(tabView,user);
                    cont.open();
                    close();
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
    
    class DeleteMark implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
             int confirm = JOptionPane.showConfirmDialog(
                    view,
                    "Are you sure you want to log out?",
                    "Logout Confirmation",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {  
            boolean deleted= marksDao.deleteMarks(initMarks.getId(), initMarks.getUserId());
            if (deleted){
            JOptionPane.showMessageDialog(view,"Deleted");
            DashboardTabbedView tabView= new DashboardTabbedView();
            DashboardTabbedController cont= new DashboardTabbedController(tabView,user);
            cont.open();
            close();
            } else{
                JOptionPane.showMessageDialog(view,"Failed to delete");
            }
            }
        }
        
    }

}

