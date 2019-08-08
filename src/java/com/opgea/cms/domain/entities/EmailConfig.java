/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.domain.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Shekhar
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmailConfig.findById", query = "SELECT e FROM EmailConfig e WHERE e.id = :id"),    
    //@NamedQuery(name = "EmailConfig.findAllByCompanyId", query = "SELECT e FROM EmailConfig e WHERE e.branch.company.id =:companyId"),
    @NamedQuery(name = "EmailConfig.findAllByEmployeeId", query = "SELECT e FROM EmailConfig e WHERE e.employee.id =:employeeId"),
    @NamedQuery(name = "EmailConfig.findAll", query = "SELECT e FROM EmailConfig e")
})
public class EmailConfig implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    private String emailId;
    private String password;
    private String smtpHost;
    private Integer port;
    
    @OneToOne
    private Employee employee;
    

    public EmailConfig() {
    }

    

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
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

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    
    
    @Override
    public String toString() {
        return "EmailConfig{" + "id=" + id + ", emailId=" + emailId + ", password=" + password + ", smtpHost=" + smtpHost + ", port=" + port + '}';
    }
    
    
    
    
    
    
}
