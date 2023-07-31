/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package studentmanagementsystem;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class TeacherDisplayController implements Initializable {

    @FXML
    private TableView<Attd> tbl;
    @FXML
    private TableColumn<Attd, String> sTimeCol;
    @FXML
    private TableColumn<Attd, String> eTimeCol;
    @FXML
    private TableColumn<Attd, String> subCol;
    @FXML
    private TableColumn<Attd, String> stdNameCol;
    @FXML
    private TableColumn<Attd, String> attdCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void callDisplay(ActionEvent event) throws ClassNotFoundException, SQLException {
        // mapping
        sTimeCol.setCellValueFactory(new PropertyValueFactory("stime"));
        eTimeCol.setCellValueFactory(new PropertyValueFactory("etime"));
        subCol.setCellValueFactory(new PropertyValueFactory("subject"));
        stdNameCol.setCellValueFactory(new PropertyValueFactory("stdname"));
        attdCol.setCellValueFactory(new PropertyValueFactory("attd"));
        
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM attendance");
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            String stime = rs.getString("stime");
            String etime = rs.getString("etime");
            String status = rs.getString("status");
            String pid = rs.getString("pid");
            String suid = rs.getString("suid");
            
            
            Attd attd = new Attd();
            attd.setStime(stime);
            attd.setEtime(etime);
            attd.setAttd(status);
            attd.setStdname(pid);
            attd.setSubject(suid);
                        
            tbl.getItems().add(attd);
        }
    }
    
}




