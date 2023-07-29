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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class StudentLoginController implements Initializable {

    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lblStatus;
    @FXML
    private TextField txtPid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onLogin(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        String pid = txtPid.getText();
        String password = txtPassword.getText();
        if(pid.isEmpty()){
            lblStatus.setText("Pid cannot be blank");
        }else if(password.isEmpty()){
            lblStatus.setText("Password cannot be blank");
        }else{
            Connection con = DBConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM student WHERE pid = ? and password = ?");
            stmt.setString(1, pid);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                lblStatus.setText("Login Success!");
                txtPid.setText("");
                txtPassword.setText("");
                
//                Parent root = FXMLLoader.load(getClass().getResource("Attendance.fxml"));
//                Scene scene = new Scene(root);
//                Stage stage = new Stage();
//                stage.setScene(scene);
//                stage.show();
                
            }else {
                lblStatus.setText("Login Failed!");
            }
        }
    }

    @FXML
    private void onRegister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StudentRegister.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    
}
