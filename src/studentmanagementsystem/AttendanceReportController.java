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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class AttendanceReportController implements Initializable {

    @FXML
    private DatePicker Sdate;
    @FXML
    private DatePicker Edate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onPrint(ActionEvent event) throws ClassNotFoundException, SQLException, JRException {
        LocalDate fdate = Sdate.getValue();
        LocalDate tdate = Edate.getValue();
        String fdate_Str = fdate.toString();
        String tdate_Str = tdate.toString();
        
        System.out.println("F ; " + fdate_Str);
        System.out.println("L ; " + tdate_Str);
        
        Connection con = DBConnection.getConnection();
        String query = "SELECT std.name as studentName, attd.stime, attd.etime, attd.status, "
                + " sub.name as subjectName FROM attendance attd INNER JOIN student std "
                + " ON attd.pid = std.pid "
                + " INNER JOIN subject sub "
                + " ON sub.suid = attd.suid "
                + " WHERE Date(attd.stime) >= '" + fdate_Str +
                "' and Date(attd.etime) <= '" + tdate_Str + "' ";
        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        int id = 0;
        ArrayList<AttendanceReport2> reports = new ArrayList<AttendanceReport2>();
        while(rs.next()){
            id++;
            String name = rs.getString("studentName");
            String status = String.valueOf(rs.getInt("status"));
            String stime = rs.getString("stime");
            String etime = rs.getString("etime");
            String subject = rs.getString("subjectName");
            
            AttendanceReport2 report = new AttendanceReport2();
            report.setId(id);
            report.setStdname(name);
            report.setStime(stime);
            report.setEtime(etime);
            if(status.equals("1")){
                report.setStatus("Persent");
            }else{
                report.setStatus("Absent");
            }
            report.setSubjectname(subject);
            reports.add(report);
        }
        String path = "C:\\Users\\acer\\Documents\\NetBeansProjects\\StudentManagementSystem\\src\\Report\\Attendance.jrxml";
        JasperReport jr = JasperCompileManager.compileReport(path);
        HashMap map = new HashMap();
        map.put("title", "Attendance Report");
        JRBeanArrayDataSource datasource = new JRBeanArrayDataSource(reports.toArray());
        JasperPrint jp = JasperFillManager.fillReport(jr, map, datasource);
        JasperViewer.viewReport(jp);
    }
    
}
