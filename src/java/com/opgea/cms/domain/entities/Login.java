/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.domain.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ramesh
 */
@Entity
@Table(name = "login")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Login.findAll", query = "SELECT l FROM Login l"),
    @NamedQuery(name = "Login.findByLoginId", query = "SELECT l FROM Login l WHERE l.loginId = :loginId"),
    @NamedQuery(name = "Login.findByEmployeeId", query = "SELECT l FROM Login l WHERE l.employee.id = :employeeId"),
    @NamedQuery(name = "Login.findByLoginIdAndPassword", query = "SELECT l FROM Login l WHERE l.loginId = :loginId and l.password = :password")})
public class Login implements Serializable {
    
    @Id
    private String loginId;
    private String password;
    @OneToOne
    private Employee employee;
    private Boolean isActive;

    public Login() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Login other = (Login) obj;
        if ((this.loginId == null) ? (other.loginId != null) : !this.loginId.equals(other.loginId)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + (this.loginId != null ? this.loginId.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Login{" + "loginId=" + loginId + ", password=" + password + ", employee=" + employee + ", isActive=" + isActive + '}';
    }
    
    
}
