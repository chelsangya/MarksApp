/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marksapp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import marksapp.model.MarksData;
import marksapp.model.UserData;
import marksapp.view.UpdateMarksView;

/**
 *
 * @author sangyakoirala
 */
public class UpdateController {
    MarksData initMarks;
    UserData user;
    UpdateMarksView view;

    public UpdateController(UpdateMarksView view, UserData user, MarksData initMarks) {
        this.view = view;
        this.initMarks = initMarks;
        this.user = user;
    }

    public void open() {
        this.view.setVisible(true);
    }

    public void close() {
        this.view.dispose();
    }

    class UpdateMarks implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name= view.getStudentNameTextField().getText();
            String dbMarks= view.getDatabaseMarksTextField().getText();
            String oopMarks= view.getOopMarksTextField().getText();
            String projectMarks = view.getProjectMarksTextField().getText();
            String businessMArks= view.getBusinessMarksTextField().getText();
        }

    }
}
