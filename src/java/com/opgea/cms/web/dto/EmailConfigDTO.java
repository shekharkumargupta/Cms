/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.web.dto;

/**
 *
 * @author Shekhar
 */
public class EmailConfigDTO {
    
    private Long id;
    private String emailId;
    private String password;
    private String smtpHost;
    private Integer portNo;
    private Long employeeId;
    private String employeeName;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPortNo() {
        return portNo;
    }

    public void setPortNo(Integer portNo) {
        this.portNo = portNo;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    @Override
    public String toString() {
        return "EmailConfigDTO{" + "id=" + id + ", emailId=" + emailId + ", password=" + password + ", smtpHost=" + smtpHost + ", portNo=" + portNo + ", employeeId=" + employeeId + ", employeeName=" + employeeName + '}';
    }
    
    
    
}
