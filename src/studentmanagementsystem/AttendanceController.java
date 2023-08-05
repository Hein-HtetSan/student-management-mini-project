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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.view.JasperViewer;

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

    @FXML
    private void onReport(ActionEvent event) throws ClassNotFoundException, SQLException, JRException {
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM student");
        ResultSet rs = stmt.executeQuery();
        int id = 0;
        ArrayList<Student> students = new ArrayList<Student>();
        while(rs.next()){
            id++;
            String pid = rs.getString("pid");
            String name = rs.getString("name");
            
            Student std = new Student();
            std.setId(id);
            std.setPid(pid);
            std.setName(name);
            students.add(std);
        }
        String path = "C:\\Users\\acer\\Documents\\NetBeansProjects\\StudentManagementSystem\\src\\Report\\StdReport.jrxml";
        JasperReport jr = JasperCompileManager.compileReport(path);
        HashMap map = new HashMap();
        map.put("param1", "Student Report");
        JRBeanArrayDataSource datasource = new JRBeanArrayDataSource(students.toArray());
        JasperPrint jp = JasperFillManager.fillReport(jr, map, datasource);
        JasperViewer.viewReport(jp);
        
        
    }
    
}
