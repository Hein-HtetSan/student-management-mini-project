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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class StudentDisplayController implements Initializable {

    @FXML
    private TableView<Attd> tbl;

    @FXML
    private TableColumn<Attd, String> stimeCol;
    @FXML
    private TableColumn<Attd, String> etimeCol;
    @FXML
    private TableColumn<Attd, String> subjectCol;
    @FXML
    private TableColumn<Attd, String> stdnameCol;
    @FXML
    private TableColumn<Attd, String> attdCol;
    @FXML
    private Label lblId;
    @FXML
    private Label lblName;
    @FXML
    private Label lblPercentage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    String pid = "0";
    String name = "0";
    public void setData(String id, String namee) throws ClassNotFoundException, SQLException{
        pid = id;
        name = namee;
        
        lblId.setText(id);
        lblName.setText(namee);
    }

    @FXML
    private void onDisplay(ActionEvent event) throws ClassNotFoundException, SQLException {
        stimeCol.setCellValueFactory(new PropertyValueFactory("stime"));
        etimeCol.setCellValueFactory(new PropertyValueFactory("etime"));
        subjectCol.setCellValueFactory(new PropertyValueFactory("subject"));
        stdnameCol.setCellValueFactory(new PropertyValueFactory("stdname"));
        attdCol.setCellValueFactory(new PropertyValueFactory("attd"));
        
        int pcount = 0;
        int acount = 0;
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM attendance WHERE pid=?");
        stmt.setString(1,pid);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            String stime = rs.getString("stime");
            String etime = rs.getString("etime");
            String status = rs.getString("status");
            String pid = rs.getString("pid");
            String suid = rs.getString("suid");
            
            System.out.println(stime+etime+status+pid+suid);
            
            Attd attd = new Attd();
            attd.setStime(stime);
            attd.setEtime(etime);
            attd.setAttd(status);
            attd.setStdname(pid);
            attd.setSubject(suid);
                        
            tbl.getItems().add(attd);
            if(status.equals("1")){
                pcount++;
            }else{
                acount++;
            }
        }
        double percentage = pcount * 100 / (pcount + acount);
        lblPercentage.setText(String.valueOf(percentage));
    }

    @FXML
    private void onReport(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AttendanceReport.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    
}
