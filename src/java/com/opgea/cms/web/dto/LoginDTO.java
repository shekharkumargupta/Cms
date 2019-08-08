/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.web.dto;

/**
 *
 * @author Ramesh
 */
public class LoginDTO {

    private String loginId;
    private String password;
    private Long employeeId;

    public LoginDTO() {
    }

    public LoginDTO(String loginId, String password, Long employeeId) {
        this.loginId = loginId;
        this.password = password;
        this.employeeId = employeeId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
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
        final LoginDTO other = (LoginDTO) obj;
        if ((this.loginId == null) ? (other.loginId != null) : !this.loginId.equals(other.loginId)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + (this.loginId != null ? this.loginId.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "LoginDTO{" + "loginId=" + loginId + ", password=" + password + ", employeeId=" + employeeId + '}';
    }

    
    
    
}
