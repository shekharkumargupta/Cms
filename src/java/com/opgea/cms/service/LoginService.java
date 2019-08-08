/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service;

import com.opgea.cms.web.dto.ChangePasswordDTO;
import com.opgea.cms.web.dto.LoginDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface LoginService {
 
    public LoginDTO create(LoginDTO loginDTO);
    public LoginDTO update(LoginDTO loginDTO);
    public LoginDTO remove(String loginId);
    public LoginDTO find(String loginId);
    public LoginDTO findByEmployeeId(Long employeeId);
    public Boolean isAuthenticUser(String loginId, String password);
    public List<LoginDTO> findAll();
    public Boolean updatePassword(ChangePasswordDTO changePasswordDTO);
}
