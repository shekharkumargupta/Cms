/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service.impl;

import com.opgea.cms.dao.CompanyDAO;
import com.opgea.cms.dao.DesignationDAO;
import com.opgea.cms.domain.entities.Company;
import com.opgea.cms.domain.entities.Designation;
import com.opgea.cms.service.DesignationService;
import com.opgea.cms.web.dto.DesignationDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ramesh
 */
@Service
public class DesignationServiceImpl implements DesignationService{

    @Autowired
    private DesignationDAO designationDAO;
    
    @Autowired
    private CompanyDAO companyDAO;
    
    @Override
    public DesignationDTO create(DesignationDTO designationDTO) {
        Designation designation = new Designation();
        System.out.println("DesignationDTO >> Service : "+designationDTO);
        if(designationDTO.getId() > 0){
            designation = designationDAO.find(designationDTO.getId());
        }
        
        designation.setName(designationDTO.getName());
        
        if(designationDTO.getId() > 0){
            designationDAO.update(designation);
        }else{
            Company company = companyDAO.find(designationDTO.getCompanyId());
            designation.setCompany(company);
            designationDAO.create(designation);
        }
        
        return designationDTO;
    }

    @Override
    public DesignationDTO update(DesignationDTO designationDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DesignationDTO remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DesignationDTO find(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<DesignationDTO> findAll() {
        List<Designation> designations = designationDAO.findAll();
        List<DesignationDTO> designationList = new ArrayList<DesignationDTO>();
        for(Designation designation : designations){
            DesignationDTO designationDTO = new DesignationDTO();
            designationDTO.setId(designation.getId());
            designationDTO.setName(designation.getName());
            designationDTO.setCompanyId(designation.getCompany().getId());
            designationList.add(designationDTO);
        }
        return designationList;
    }

    @Override
    public List<DesignationDTO> findAllByCompanyId(Long companyId) {
        List<Designation> designations = designationDAO.findAllByCompanyId(companyId);
        List<DesignationDTO> designationList = new ArrayList<DesignationDTO>();
        for(Designation designation : designations){
            DesignationDTO designationDTO = new DesignationDTO();
            designationDTO.setId(designation.getId());
            designationDTO.setName(designation.getName());
            designationDTO.setCompanyId(designation.getCompany().getId());
            designationList.add(designationDTO);
        }
        return designationList;
    }
    
}
