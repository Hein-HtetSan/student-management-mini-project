/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package studentmanagementsystem;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class TeacherRegisterController implements Initializable {

    @FXML
    private TextField txtPid;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtConfirmPassword;
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
    private void onRegister(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        String pid = txtPid.getText();
        String name = txtName.getText();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();
        
        if(pid.isEmpty()){
            lblStatus.setText("Pid cannot be blank.");
        }else if(name.isEmpty()){
            lblStatus.setText("Name cannot be blank.");
        }else if(password.isEmpty()){
            lblStatus.setText("Password cannot be blank.");
        }else if(!password.equals(confirmPassword)){
            lblStatus.setText("Passwords don't match");
        }else{
            Connection con = DBConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO teacher VALUES (?,?,?)");
            stmt.setString(1, pid);
            stmt.setString(2, name);
            stmt.setString(3, password);
            stmt.executeUpdate();
            lblStatus.setText("Saved Successfully");
            
        }
    }
    
}
