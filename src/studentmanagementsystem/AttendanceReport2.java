/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author acer
 */
public class AttendanceReport2 {

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the stdname
     */
    public String getStdname() {
        return stdname;
    }

    /**
     * @param stdname the stdname to set
     */
    public void setStdname(String stdname) {
        this.stdname = stdname;
    }

    /**
     * @return the stime
     */
    public String getStime() {
        return stime;
    }

    /**
     * @param stime the stime to set
     */
    public void setStime(String stime) {
        this.stime = stime;
    }

    /**
     * @return the etime
     */
    public String getEtime() {
        return etime;
    }

    /**
     * @param etime the etime to set
     */
    public void setEtime(String etime) {
        this.etime = etime;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the subjectname
     */
    public String getSubjectname() {
        return subjectname;
    }

    /**
     * @param subjectname the subjectname to set
     */
    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    private int id;
    private String stdname;
    private String stime;
    private String etime;
    private String status;
    private String subjectname;
    
//        
    
    
}
