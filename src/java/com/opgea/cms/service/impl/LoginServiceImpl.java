/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service.impl;

import com.opgea.cms.dao.EmployeeDAO;
import com.opgea.cms.dao.LoginDAO;
import com.opgea.cms.domain.entities.Employee;
import com.opgea.cms.domain.entities.Login;
import com.opgea.cms.service.LoginService;
import com.opgea.cms.web.dto.ChangePasswordDTO;
import com.opgea.cms.web.dto.LoginDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ramesh
 */
@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private LoginDAO loginDAO;
    
    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    public LoginDTO create(LoginDTO loginDTO) {
        Employee employee = employeeDAO.find(loginDTO.getEmployeeId());
        Login login = new Login();
        login.setLoginId(loginDTO.getLoginId());
        login.setPassword(loginDTO.getPassword());
        login.setEmployee(employee);
        loginDAO.create(login);
        return loginDTO;
    }

    @Override
    public LoginDTO update(LoginDTO loginDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LoginDTO remove(String loginId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LoginDTO find(String loginId) {
        Login login = loginDAO.find(loginId);
        LoginDTO loginDTO = new LoginDTO();
        if(login.getEmployee() != null){
            loginDTO.setEmployeeId(login.getEmployee().getId());
            loginDTO.setLoginId(login.getLoginId());
            loginDTO.setPassword(login.getPassword());
        }
        return loginDTO;
    }

    @Override
    public List<LoginDTO> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    @Override
    public Boolean isAuthenticUser(String loginId, String password) {
        Boolean authentic = false;
        Login login = loginDAO.find(loginId);
        if(login != null){
            if(login.getPassword().equals(password) && login.getIsActive() == true){
                authentic = true;
            }
        }
        return authentic;
    }

    @Override
    public LoginDTO findByEmployeeId(Long employeeId) {
        Login login = loginDAO.findByEmployeeId(employeeId);
        LoginDTO loginDTO = new LoginDTO();
        if(login.getEmployee() != null){
            loginDTO.setEmployeeId(login.getEmployee().getId());
            loginDTO.setLoginId(login.getLoginId());
            loginDTO.setPassword(login.getPassword());
        }
        return loginDTO;
    }

    @Override
    public Boolean updatePassword(ChangePasswordDTO changePasswordDTO) {
        Boolean status = false;
        Login login = loginDAO.find(changePasswordDTO.getLoginId());
        if(login != null){
            if(login.getPassword().equals(changePasswordDTO.getCurrentPassword())){
                login.setPassword(changePasswordDTO.getConfirmPassword());
                loginDAO.update(login);
                status = true;
            }
        }else{
            status = false;
        }
        return status;
    }
    
}
