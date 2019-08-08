/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service.impl;

import com.opgea.cms.dao.BranchDAO;
import com.opgea.cms.dao.ClientDAO;
import com.opgea.cms.dao.EmployeeDAO;
import com.opgea.cms.dao.OpeningDAO;
import com.opgea.cms.domain.entities.Branchh;
import com.opgea.cms.domain.entities.Client;
import com.opgea.cms.domain.entities.Employee;
import com.opgea.cms.domain.entities.Opening;
import com.opgea.cms.domain.entities.OpeningDetails;
import com.opgea.cms.domain.qualifiers.OpeningStatus;
import com.opgea.cms.service.OpeningService;
import com.opgea.cms.web.dto.OpeningDTO;
import com.opgea.util.DateUtil;
import com.opgea.util.NumberUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ramesh
 */
@Service
public class OpeningServiceImpl implements OpeningService{
    
    @Autowired
    private OpeningDAO openingDAO;
    @Autowired
    private BranchDAO branchDAO;
    @Autowired
    private ClientDAO clientDAO;
    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    public OpeningDTO create(OpeningDTO openingDTO) {
        Branchh branch = branchDAO.find(openingDTO.getBranchId());
        Client client = clientDAO.find(openingDTO.getClientId());
        Employee employee = employeeDAO.find(openingDTO.getCreatedById());
        Opening opening = null;
        if(openingDTO.getOpeningId() > 0){
            opening = openingDAO.find(openingDTO.getOpeningId());
        }else{
            opening = new Opening();
        }

        opening.setPositionName(openingDTO.getPositionName());
        opening.setQualification(openingDTO.getQualification());
        opening.setLocation(openingDTO.getLocation());
        opening.setMaximumSalary(openingDTO.getMaximumSalary());
        opening.setMinimumSalary(openingDTO.getMinimumSalary());
        opening.setMaximumExperience(openingDTO.getMaximumExp());
        opening.setMinimumExperience(openingDTO.getMinimumExp());
        opening.setKeySkills(openingDTO.getKeySkills());
        opening.setBranch(branch);
        opening.setClient(client);
        opening.setOpeningStatus(OpeningStatus.values()[openingDTO.getOpeningStatusId()]);
        opening.setCreatedDate(Calendar.getInstance().getTime());
        opening.setCreatedBy(employee);        
        if(openingDTO.getOpeningId() > 0){
            openingDAO.update(opening);
        }else{
            openingDAO.create(opening);
        }
        
        openingDTO.setBranchName(branch.getBranchDetails().getBranchName());
        openingDTO.setClientName(client.getClientName());
        openingDTO.setOpeningStatusString(OpeningStatus.values()[openingDTO.getOpeningStatusId()].name());
       return openingDTO;
    }

    @Override
    public OpeningDTO update(OpeningDTO openingDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public OpeningDTO remove(Long openingId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public OpeningDTO find(Long openingId) {
        Opening opening = openingDAO.find(openingId);
        Client client = opening.getClient();
        Branchh branch = opening.getBranch();
        //OpeningDetails details = opening.getOpeningDetails();
        Employee createdBy = opening.getCreatedBy();
        OpeningDTO openingDTO = new OpeningDTO();
        openingDTO.setOpeningId(opening.getId());
        
        if(branch != null){
            openingDTO.setBranchId(branch.getId());
            openingDTO.setBranchName(branch.getBranchDetails().getBranchName());
        }
        if(client != null){
            openingDTO.setClientId(client.getId());
            openingDTO.setClientName(client.getClientName());
        }
        /*
        if(details != null){
            openingDTO.setOpeningDetails(details.getJobDetails());
        }
         * 
         */
        openingDTO.setQualification(opening.getQualification());
        openingDTO.setPositionName(opening.getPositionName());
        openingDTO.setLocation(opening.getLocation());
        openingDTO.setKeySkills(opening.getKeySkills());

        if(opening.getMaximumSalary() != null){
            openingDTO.setMaximumSalary(opening.getMaximumSalary());
            openingDTO.setMaxLacs(NumberUtils.getLacs(opening.getMaximumSalary()));
            openingDTO.setMaxThousands(NumberUtils.getThousands(opening.getMaximumSalary()));
        }
        if(opening.getMinimumSalary() != null){
            openingDTO.setMinimumSalary(opening.getMinimumSalary());
            openingDTO.setMinLacs(NumberUtils.getLacs(opening.getMinimumSalary()));
            openingDTO.setMinThousands(NumberUtils.getThousands(opening.getMinimumSalary()));
        }
        if(opening.getMaximumExperience() != null){
            openingDTO.setMaximumExp(opening.getMaximumExperience());
            openingDTO.setMaxYrs(NumberUtils.getYears(opening.getMaximumExperience()));
            openingDTO.setMaxMonths(NumberUtils.getMonths(opening.getMaximumExperience()));
        }
        if(opening.getMinimumExperience() != null){
            openingDTO.setMinimumExp(opening.getMinimumExperience());
            openingDTO.setMinYrs(NumberUtils.getYears(opening.getMinimumExperience()));
            openingDTO.setMinMonths(NumberUtils.getMonths(opening.getMinimumExperience()));
        }
        if(createdBy != null){
            openingDTO.setCreatedById(createdBy.getId());
            openingDTO.setCreatedByName(createdBy.getFirstName()+" "+createdBy.getMiddleInitial()+" "+createdBy.getLastName());
        }

        openingDTO.setOpeningStatusId(opening.getOpeningStatus().ordinal());
        openingDTO.setOpeningStatusString(opening.getOpeningStatus().name());
        openingDTO.setCreatedDate(DateUtil.getDateTimeString(opening.getCreatedDate().getTime()));
        return openingDTO;
    }

    @Override
    public List<OpeningDTO> findAll() {
        List<Opening> openingList = openingDAO.findAll();
        List<OpeningDTO> openingDTOList = new ArrayList<OpeningDTO>();
        for(Opening opening : openingList){
            Client client = opening.getClient();
            Branchh branch = opening.getBranch();
            //OpeningDetails details = opening.getOpeningDetails();
            Employee createdBy = opening.getCreatedBy();
            OpeningDTO openingDTO = new OpeningDTO();
            openingDTO.setOpeningId(opening.getId());

            if(branch != null){
                openingDTO.setBranchId(branch.getId());
                openingDTO.setBranchName(branch.getBranchDetails().getBranchName());
            }
            if(client != null){
                openingDTO.setClientId(client.getId());
                openingDTO.setClientName(client.getClientName());
            }
            /*
            if(details != null){
                openingDTO.setOpeningDetails(details.getJobDetails());
            }
             * 
             */
            openingDTO.setQualification(opening.getQualification());
            openingDTO.setPositionName(opening.getPositionName());
            openingDTO.setLocation(opening.getLocation());
            openingDTO.setKeySkills(opening.getKeySkills());
            if(opening.getMaximumSalary() != null){
                openingDTO.setMaximumSalary(opening.getMaximumSalary());
                openingDTO.setMaxLacs(NumberUtils.getLacs(opening.getMaximumSalary()));
                openingDTO.setMaxThousands(NumberUtils.getThousands(opening.getMaximumSalary()));
            }
            if(opening.getMinimumSalary() != null){
                openingDTO.setMinimumSalary(opening.getMinimumSalary());
                openingDTO.setMinLacs(NumberUtils.getLacs(opening.getMinimumSalary()));
                openingDTO.setMinThousands(NumberUtils.getThousands(opening.getMinimumSalary()));
            }
            if(opening.getMaximumExperience() != null){
                openingDTO.setMaximumExp(opening.getMaximumExperience());
                openingDTO.setMaxYrs(NumberUtils.getYears(opening.getMaximumExperience()));
                openingDTO.setMaxMonths(NumberUtils.getMonths(opening.getMaximumExperience()));
            }
            if(opening.getMinimumExperience() != null){
                openingDTO.setMinimumExp(opening.getMinimumExperience());
                openingDTO.setMinYrs(NumberUtils.getYears(opening.getMinimumExperience()));
                openingDTO.setMinMonths(NumberUtils.getMonths(opening.getMinimumExperience()));
            }
            if(createdBy != null){
                openingDTO.setCreatedById(createdBy.getId());
                openingDTO.setCreatedByName(createdBy.getFirstName()+" "+createdBy.getMiddleInitial()+" "+createdBy.getLastName());
            }
            openingDTO.setOpeningStatusId(opening.getOpeningStatus().ordinal());
            openingDTO.setOpeningStatusString(opening.getOpeningStatus().name());
            openingDTO.setCreatedDate(DateUtil.getDateTimeString(opening.getCreatedDate().getTime()));
            openingDTOList.add(openingDTO);
        }
        return openingDTOList;
    }

    @Override
    public List<OpeningDTO> findAllByBranchId(Long branchId) {
        List<Opening> openingList = openingDAO.findAllByBranchId(branchId);
        List<OpeningDTO> openingDTOList = new ArrayList<OpeningDTO>();
        for(Opening opening : openingList){
            Client client = opening.getClient();
            Branchh branch = opening.getBranch();
            //OpeningDetails details = opening.getOpeningDetails();
            Employee createdBy = opening.getCreatedBy();
            OpeningDTO openingDTO = new OpeningDTO();
            openingDTO.setOpeningId(opening.getId());

            if(branch != null){
                openingDTO.setBranchId(branch.getId());
                openingDTO.setBranchName(branch.getBranchDetails().getBranchName());
            }
            if(client != null){
                openingDTO.setClientId(client.getId());
                openingDTO.setClientName(client.getClientName());
            }
            /*
            if(details != null){
                openingDTO.setOpeningDetails(details.getJobDetails());
            }
             * 
             */
            openingDTO.setQualification(opening.getQualification());
            openingDTO.setPositionName(opening.getPositionName());
            openingDTO.setLocation(opening.getLocation());
            openingDTO.setKeySkills(opening.getKeySkills());
            if(opening.getMaximumSalary() != null){
                openingDTO.setMaximumSalary(opening.getMaximumSalary());
                openingDTO.setMaxLacs(NumberUtils.getLacs(opening.getMaximumSalary()));
                openingDTO.setMaxThousands(NumberUtils.getThousands(opening.getMaximumSalary()));
            }
            if(opening.getMinimumSalary() != null){
                openingDTO.setMinimumSalary(opening.getMinimumSalary());
                openingDTO.setMinLacs(NumberUtils.getLacs(opening.getMinimumSalary()));
                openingDTO.setMinThousands(NumberUtils.getThousands(opening.getMinimumSalary()));
            }
            if(opening.getMaximumExperience() != null){
                openingDTO.setMaximumExp(opening.getMaximumExperience());
                openingDTO.setMaxYrs(NumberUtils.getYears(opening.getMaximumExperience()));
                openingDTO.setMaxMonths(NumberUtils.getMonths(opening.getMaximumExperience()));
            }
            if(opening.getMinimumExperience() != null){
                openingDTO.setMinimumExp(opening.getMinimumExperience());
                openingDTO.setMinYrs(NumberUtils.getYears(opening.getMinimumExperience()));
                openingDTO.setMinMonths(NumberUtils.getMonths(opening.getMinimumExperience()));
            }
            if(createdBy != null){
                openingDTO.setCreatedById(createdBy.getId());
                openingDTO.setCreatedByName(createdBy.getFirstName()+" "+createdBy.getMiddleInitial()+" "+createdBy.getLastName());
            }
            openingDTO.setOpeningStatusId(opening.getOpeningStatus().ordinal());
            openingDTO.setOpeningStatusString(opening.getOpeningStatus().name());
            openingDTO.setCreatedDate(DateUtil.getDateTimeString(opening.getCreatedDate().getTime()));
            openingDTOList.add(openingDTO);
        }
        return openingDTOList;
    }

    @Override
    public List<OpeningDTO> findAllByCompanyId(Long companyId) {
        List<Opening> openingList = openingDAO.findAllByCompanyId(companyId);
        List<OpeningDTO> openingDTOList = new ArrayList<OpeningDTO>();
        for(Opening opening : openingList){
            Client client = opening.getClient();
            Branchh branch = opening.getBranch();
            //OpeningDetails details = opening.getOpeningDetails();
            Employee createdBy = opening.getCreatedBy();
            OpeningDTO openingDTO = new OpeningDTO();
            openingDTO.setOpeningId(opening.getId());

            if(branch != null){
                openingDTO.setBranchId(branch.getId());
                openingDTO.setBranchName(branch.getBranchDetails().getBranchName());
            }
            if(client != null){
                openingDTO.setClientId(client.getId());
                openingDTO.setClientName(client.getClientName());
            }
            /*
            if(details != null){
                openingDTO.setOpeningDetails(details.getJobDetails());
            }
             * 
             */
            openingDTO.setQualification(opening.getQualification());
            openingDTO.setPositionName(opening.getPositionName());
            openingDTO.setLocation(opening.getLocation());
            openingDTO.setKeySkills(opening.getKeySkills());
            if(opening.getMaximumSalary() != null){
                openingDTO.setMaximumSalary(opening.getMaximumSalary());
                openingDTO.setMaxLacs(NumberUtils.getLacs(opening.getMaximumSalary()));
                openingDTO.setMaxThousands(NumberUtils.getThousands(opening.getMaximumSalary()));
            }
            if(opening.getMinimumSalary() != null){
                openingDTO.setMinimumSalary(opening.getMinimumSalary());
                openingDTO.setMinLacs(NumberUtils.getLacs(opening.getMinimumSalary()));
                openingDTO.setMinThousands(NumberUtils.getThousands(opening.getMinimumSalary()));
            }
            if(opening.getMaximumExperience() != null){
                openingDTO.setMaximumExp(opening.getMaximumExperience());
                openingDTO.setMaxYrs(NumberUtils.getYears(opening.getMaximumExperience()));
                openingDTO.setMaxMonths(NumberUtils.getMonths(opening.getMaximumExperience()));
            }
            if(opening.getMinimumExperience() != null){
                openingDTO.setMinimumExp(opening.getMinimumExperience());
                openingDTO.setMinYrs(NumberUtils.getYears(opening.getMinimumExperience()));
                openingDTO.setMinMonths(NumberUtils.getMonths(opening.getMinimumExperience()));
            }
            if(createdBy != null){
                openingDTO.setCreatedById(createdBy.getId());
                openingDTO.setCreatedByName(createdBy.getFirstName()+" "+createdBy.getMiddleInitial()+" "+createdBy.getLastName());
            }
            openingDTO.setOpeningStatusId(opening.getOpeningStatus().ordinal());
            openingDTO.setOpeningStatusString(opening.getOpeningStatus().name());
            openingDTO.setCreatedDate(DateUtil.getDateTimeString(opening.getCreatedDate().getTime()));
            openingDTOList.add(openingDTO);
        }
        return openingDTOList;
    }

    @Override
    public List<OpeningDTO> findAllByClientId(Long clientId) {
        List<Opening> openingList = openingDAO.findAllByClientId(clientId);
        List<OpeningDTO> openingDTOList = new ArrayList<OpeningDTO>();
        for(Opening opening : openingList){
            Client client = opening.getClient();
            Branchh branch = opening.getBranch();
            //OpeningDetails details = opening.getOpeningDetails();
            Employee createdBy = opening.getCreatedBy();
            OpeningDTO openingDTO = new OpeningDTO();
            openingDTO.setOpeningId(opening.getId());

            if(branch != null){
                openingDTO.setBranchId(branch.getId());
                openingDTO.setBranchName(branch.getBranchDetails().getBranchName());
            }
            if(client != null){
                openingDTO.setClientId(client.getId());
                openingDTO.setClientName(client.getClientName());
            }
            /*
            if(details != null){
                openingDTO.setOpeningDetails(details.getJobDetails());
                System.out.println("Details: "+details.getJobDetails());
            }
             * 
             */
            openingDTO.setQualification(opening.getQualification());
            openingDTO.setPositionName(opening.getPositionName());
            openingDTO.setLocation(opening.getLocation());
            openingDTO.setKeySkills(opening.getKeySkills());
            if(opening.getMaximumSalary() != null){
                openingDTO.setMaximumSalary(opening.getMaximumSalary());
                openingDTO.setMaxLacs(NumberUtils.getLacs(opening.getMaximumSalary()));
                openingDTO.setMaxThousands(NumberUtils.getThousands(opening.getMaximumSalary()));
            }
            if(opening.getMinimumSalary() != null){
                openingDTO.setMinimumSalary(opening.getMinimumSalary());
                openingDTO.setMinLacs(NumberUtils.getLacs(opening.getMinimumSalary()));
                openingDTO.setMinThousands(NumberUtils.getThousands(opening.getMinimumSalary()));
            }
            if(opening.getMaximumExperience() != null){
                openingDTO.setMaximumExp(opening.getMaximumExperience());
                openingDTO.setMaxYrs(NumberUtils.getYears(opening.getMaximumExperience()));
                openingDTO.setMaxMonths(NumberUtils.getMonths(opening.getMaximumExperience()));
            }
            if(opening.getMinimumExperience() != null){
                openingDTO.setMinimumExp(opening.getMinimumExperience());
                openingDTO.setMinYrs(NumberUtils.getYears(opening.getMinimumExperience()));
                openingDTO.setMinMonths(NumberUtils.getMonths(opening.getMinimumExperience()));
            }           
            if(createdBy != null){
                openingDTO.setCreatedById(createdBy.getId());
                openingDTO.setCreatedByName(createdBy.getFirstName()+" "+createdBy.getMiddleInitial()+" "+createdBy.getLastName());
            }
            openingDTO.setOpeningStatusId(opening.getOpeningStatus().ordinal());
            openingDTO.setOpeningStatusString(opening.getOpeningStatus().name());
            openingDTO.setCreatedDate(DateUtil.getDateTimeString(opening.getCreatedDate().getTime()));
            openingDTOList.add(openingDTO);
        }
        return openingDTOList;
    }

    @Override
    public List<OpeningDTO> findAllByTeamId(Long teamId) {
        List<Opening> openingList = openingDAO.findAllByTeamId(teamId);
        List<OpeningDTO> openingDTOList = new ArrayList<OpeningDTO>();
        for(Opening opening : openingList){
            Client client = opening.getClient();
            Branchh branch = opening.getBranch();
            //OpeningDetails details = opening.getOpeningDetails();
            Employee createdBy = opening.getCreatedBy();
            OpeningDTO openingDTO = new OpeningDTO();
            openingDTO.setOpeningId(opening.getId());

            if(branch != null){
                openingDTO.setBranchId(branch.getId());
                openingDTO.setBranchName(branch.getBranchDetails().getBranchName());
            }
            if(client != null){
                openingDTO.setClientId(client.getId());
                openingDTO.setClientName(client.getClientName());
            }
            /*
            if(details != null){
                openingDTO.setOpeningDetails(details.getJobDetails());
            }
             * 
             */
            openingDTO.setQualification(opening.getQualification());
            openingDTO.setPositionName(opening.getPositionName());
            openingDTO.setLocation(opening.getLocation());
            openingDTO.setKeySkills(opening.getKeySkills());
            if(opening.getMaximumSalary() != null){
                openingDTO.setMaximumSalary(opening.getMaximumSalary());
                openingDTO.setMaxLacs(NumberUtils.getLacs(opening.getMaximumSalary()));
                openingDTO.setMaxThousands(NumberUtils.getThousands(opening.getMaximumSalary()));
            }
            if(opening.getMinimumSalary() != null){
                openingDTO.setMinimumSalary(opening.getMinimumSalary());
                openingDTO.setMinLacs(NumberUtils.getLacs(opening.getMinimumSalary()));
                openingDTO.setMinThousands(NumberUtils.getThousands(opening.getMinimumSalary()));
            }
            if(opening.getMaximumExperience() != null){
                openingDTO.setMaximumExp(opening.getMaximumExperience());
                openingDTO.setMaxYrs(NumberUtils.getYears(opening.getMaximumExperience()));
                openingDTO.setMaxMonths(NumberUtils.getMonths(opening.getMaximumExperience()));
            }
            if(opening.getMinimumExperience() != null){
                openingDTO.setMinimumExp(opening.getMinimumExperience());
                openingDTO.setMinYrs(NumberUtils.getYears(opening.getMinimumExperience()));
                openingDTO.setMinMonths(NumberUtils.getMonths(opening.getMinimumExperience()));
            }
            if(createdBy != null){
                openingDTO.setCreatedById(createdBy.getId());
                openingDTO.setCreatedByName(createdBy.getFirstName()+" "+createdBy.getMiddleInitial()+" "+createdBy.getLastName());
            }
            openingDTO.setOpeningStatusId(opening.getOpeningStatus().ordinal());
            openingDTO.setOpeningStatusString(opening.getOpeningStatus().name());
            openingDTO.setCreatedDate(DateUtil.getDateTimeString(opening.getCreatedDate().getTime()));
            openingDTOList.add(openingDTO);
        }
        return openingDTOList;
    }
    
    @Override
    public List<OpeningDTO> findAllByEmployeeId(Long employeeId) {
         List<Opening> openingList = openingDAO.findAllByEmployeeId(employeeId);
        List<OpeningDTO> openingDTOList = new ArrayList<OpeningDTO>();
        for(Opening opening : openingList){
            Client client = opening.getClient();
            Branchh branch = opening.getBranch();
            //OpeningDetails details = opening.getOpeningDetails();
            Employee createdBy = opening.getCreatedBy();
            OpeningDTO openingDTO = new OpeningDTO();
            openingDTO.setOpeningId(opening.getId());

            if(branch != null){
                openingDTO.setBranchId(branch.getId());
                openingDTO.setBranchName(branch.getBranchDetails().getBranchName());
            }
            if(client != null){
                openingDTO.setClientId(client.getId());
                openingDTO.setClientName(client.getClientName());
            }
            /*
            if(details != null){
                openingDTO.setOpeningDetails(details.getJobDetails());
            }
             * 
             */
            openingDTO.setQualification(opening.getQualification());
            openingDTO.setPositionName(opening.getPositionName());
            openingDTO.setLocation(opening.getLocation());
            openingDTO.setKeySkills(opening.getKeySkills());
            if(opening.getMaximumSalary() != null){
                openingDTO.setMaximumSalary(opening.getMaximumSalary());
                openingDTO.setMaxLacs(NumberUtils.getLacs(opening.getMaximumSalary()));
                openingDTO.setMaxThousands(NumberUtils.getThousands(opening.getMaximumSalary()));
            }
            if(opening.getMinimumSalary() != null){
                openingDTO.setMinimumSalary(opening.getMinimumSalary());
                openingDTO.setMinLacs(NumberUtils.getLacs(opening.getMinimumSalary()));
                openingDTO.setMinThousands(NumberUtils.getThousands(opening.getMinimumSalary()));
            }
            if(opening.getMaximumExperience() != null){
                openingDTO.setMaximumExp(opening.getMaximumExperience());
                openingDTO.setMaxYrs(NumberUtils.getYears(opening.getMaximumExperience()));
                openingDTO.setMaxMonths(NumberUtils.getMonths(opening.getMaximumExperience()));
            }
            if(opening.getMinimumExperience() != null){
                openingDTO.setMinimumExp(opening.getMinimumExperience());
                openingDTO.setMinYrs(NumberUtils.getYears(opening.getMinimumExperience()));
                openingDTO.setMinMonths(NumberUtils.getMonths(opening.getMinimumExperience()));
            }
            if(createdBy != null){
                openingDTO.setCreatedById(createdBy.getId());
                openingDTO.setCreatedByName(createdBy.getFirstName()+" "+createdBy.getMiddleInitial()+" "+createdBy.getLastName());
            }
            openingDTO.setOpeningStatusId(opening.getOpeningStatus().ordinal());
            openingDTO.setOpeningStatusString(opening.getOpeningStatus().name());
            openingDTO.setCreatedDate(DateUtil.getDateTimeString(opening.getCreatedDate().getTime()));
            openingDTOList.add(openingDTO);
        }
        return openingDTOList;
    }

    @Override
    public Boolean isAreadyAssignedToTeam(Long teamId, Long openingId) {
        return openingDAO.isAreadyAssignedToTeam(teamId, openingId);
    }

    @Override
    public Boolean isAreadyAssignedToEmployee(Long employeeId, Long openingId) {
        return openingDAO.isAreadyAssignedToEmployee(employeeId, openingId);
    }

    @Override
    public String getOpeningDetails(Long openingId) {
        Opening opening = openingDAO.find(openingId);
        OpeningDetails details = opening.getOpeningDetails();
        if(details != null){
            return details.getJobDetails();
        }else{
            return "Details not available";
        }
    }

    
    
}
