package marksapp.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import marksapp.dao.MarksDao;
import marksapp.model.MarksData;
import marksapp.model.UserData;
import marksapp.view.DashboardView;
import marksapp.view.LoginView;
import marksapp.view.ViewMarks;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sangyakoirala
 */
public class ViewMarksController {
    ViewMarks view;
    UserData user;
    MarksDao marksDao= new MarksDao();
    public ViewMarksController(ViewMarks view, UserData user){
        this.view=view;
        this.user=user;
        String name = user.getName();
        this.view.getWelcomeLabel().setText("Welcome "+ name);
//        loading the marks details
        loadUserMarks();
        this.view.addMarksNavigation(new AddMarksNavigaton());
        this.view.logout(new Logout());
    }
    public void open(){
        view.setVisible(true);
    }
    public void close(){
        view.dispose();
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
    
    
    class AddMarksNavigaton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            DashboardView dashboardView = new DashboardView();
            DashboardController dashboardController= new DashboardController(dashboardView,user);
            dashboardController.open();
            close();            
        }
    }
    
    class Logout implements ActionListener{

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
