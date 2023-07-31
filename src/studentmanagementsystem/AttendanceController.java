/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package studentmanagementsystem;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//String stime = txtSTime.getText();
//        String etime = txtETime.getText();
//        String subject = txtSub.getText();
//        String stdName = txtStdName.getText();
//        String attendance = txtAttd.getText();
//        
//        if(stime.isEmpty()){
//            lblStatus.setText("Start time cannot be blank");
//        }else if(etime.isEmpty()){
//            lblStatus.setText("End time cannot be blank");
//        }else if(subject.isEmpty()){
//            lblStatus.setText("Subject cannot be blank");
//        }else if(stdName.isEmpty()){
//            lblStatus.setText("Student Name cannot be blank");
//        }else if(attendance.isEmpty()){
//            lblStatus.setText("Attandance cannot be blank");
//        }else{
//            String stdId = "0";
//            String subId = "0";
//            Connection con = DBConnection.getConnection();
//            PreparedStatement stmt = con.prepareStatement("SELECT * FROM student WHERE name=?");
//            stmt.setString(1, stdName);
//            ResultSet rs = stmt.executeQuery();
//            while(rs.next()){
//                stdId = rs.getString("pid");
////                System.out.println("Student Id " + stdId);
//            }
//            
//            PreparedStatement stmt2 = con.prepareStatement("SELECT * FROM subject WHERE name=?");
//            stmt2.setString(1, subject);
//            ResultSet rs2 = stmt2.executeQuery();
//            while(rs2.next()){
//                subId = rs2.getString("suid");
////                System.out.println("Subject Id " + subId);
//            }
//            
//            String query = "INSERT INTO attendance (stime, etime, status, pid, suid) VALUES (?,?,?,?,?)";
//            PreparedStatement stmt3 = con.prepareStatement(query);
//            stmt3.setString(1, stime);
//            stmt3.setString(2, etime);
//            stmt3.setString(3, attendance);
//            stmt3.setString(4, stdId);
//            stmt3.setString(5, subId);
//            stmt3.executeUpdate();
//            lblStatus.setText("Save Successfully");
//        }

/**
 * FXML Controller class
 *
 * @author acer
 */
public class AttendanceController implements Initializable {

    @FXML
    private TextField txtSTime;
    @FXML
    private TextField txtETime;
    @FXML
    private TextField txtSub;
    @FXML
    private TextField txtStdName;
    @FXML
    private TextField txtAttd;
    @FXML
    private Label lblStatus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addStudent(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StudentAdd.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void addAttendance(ActionEvent event) throws ClassNotFoundException, SQLException {
        String stime = txtSTime.getText();
        String etime = txtETime.getText();
        String subject = txtSub.getText();
        String stdName = txtStdName.getText();
        String attendance = txtAttd.getText();
        
        if(stime.isEmpty()){
            lblStatus.setText("Start time cannot be blank");
        }else if(etime.isEmpty()){
            lblStatus.setText("End time cannot be blank");
        }else if(subject.isEmpty()){
            lblStatus.setText("Subject cannot be blank");
        }else if(stdName.isEmpty()){
            lblStatus.setText("Student Name cannot be blank");
        }else if(attendance.isEmpty()){
            lblStatus.setText("Attandance cannot be blank");
        }else{
            String stdId = "0";
            String subId = "0";
            Connection con = DBConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM student WHERE name=?");
            stmt.setString(1, stdName);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                stdId = rs.getString("pid");
//                System.out.println("Student Id " + stdId);
            }
            
            PreparedStatement stmt2 = con.prepareStatement("SELECT * FROM subject WHERE name=?");
            stmt2.setString(1, subject);
            ResultSet rs2 = stmt2.executeQuery();
            while(rs2.next()){
                subId = rs2.getString("suid");
//                System.out.println("Subject Id " + subId);
            }
            
            String query = "INSERT INTO attendance (stime, etime, status, pid, suid) VALUES (?,?,?,?,?)";
            PreparedStatement stmt3 = con.prepareStatement(query);
            stmt3.setString(1, stime);
            stmt3.setString(2, etime);
            stmt3.setString(3, attendance);
            stmt3.setString(4, stdId);
            stmt3.setString(5, subId);
            stmt3.executeUpdate();
            lblStatus.setText("Save Successfully");
        }
    }

    @FXML
    private void onDisplay(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TeacherDisplay.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    
}
