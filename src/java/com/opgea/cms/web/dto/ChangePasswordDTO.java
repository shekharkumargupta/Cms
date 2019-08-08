/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.web.dto;

/**
 *
 * @author Ramesh
 */
public class ChangePasswordDTO {
    
    private String loginId;
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ChangePasswordDTO other = (ChangePasswordDTO) obj;
        if ((this.loginId == null) ? (other.loginId != null) : !this.loginId.equals(other.loginId)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.loginId != null ? this.loginId.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "ChangePasswordDTO{" + "loginId=" + loginId + ", currentPassword=" + currentPassword + ", newPassword=" + newPassword + ", confirmPassword=" + confirmPassword + '}';
    }
    
    
}
