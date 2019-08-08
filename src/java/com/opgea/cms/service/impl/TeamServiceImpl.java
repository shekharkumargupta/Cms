/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service.impl;

import com.opgea.cms.dao.BranchDAO;
import com.opgea.cms.dao.EmployeeDAO;
import com.opgea.cms.dao.OpeningDAO;
import com.opgea.cms.dao.TeamDAO;
import com.opgea.cms.domain.entities.Branchh;
import com.opgea.cms.domain.entities.Employee;
import com.opgea.cms.domain.entities.Opening;
import com.opgea.cms.domain.entities.Team;
import com.opgea.cms.domain.modal.ExtJSTreeModel;
import com.opgea.cms.domain.qualifiers.EmployeeType;
import com.opgea.cms.service.TeamService;
import com.opgea.cms.web.dto.TeamDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ramesh
 */
@Service
public class TeamServiceImpl implements TeamService {

    
    @Autowired
    private TeamDAO teamDAO;
    
    @Autowired
    private BranchDAO branchDAO;
    
    @Autowired
    private EmployeeDAO employeeDAO;
    
    @Autowired
    private OpeningDAO openingDAO;
    
    
    @Override
    public TeamDTO create(TeamDTO teamDTO) {
        Team team = new Team();
        team.setName(teamDTO.getName());
        if(teamDTO.getId() > 0){
            teamDAO.update(team);
        }else{
            Branchh branch = branchDAO.find(teamDTO.getBranchId());
            team.setBranch(branch);
            teamDAO.create(team);
        }
        return teamDTO;
    }

    @Override
    public TeamDTO update(TeamDTO teamDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TeamDTO remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TeamDTO find(Long id) {
        Team team = teamDAO.find(id);
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(team.getId());
        teamDTO.setName(team.getName());
        return teamDTO;
    }

    @Override
    public List<TeamDTO> findAll() {
        List<TeamDTO> teamDTOList = new ArrayList<TeamDTO>();
        List<Team> teamList = teamDAO.findAll();
        for(Team team: teamList){
            TeamDTO teamDTO = new TeamDTO();
            teamDTO.setId(team.getId());
            teamDTO.setName(team.getName());
            teamDTOList.add(teamDTO);
        }
        return teamDTOList;
    }

    @Override
    public List<TeamDTO> findAllByBranchId(Long branchId) {
        List<TeamDTO> teamDTOList = new ArrayList<TeamDTO>();
        List<Team> teamList = teamDAO.findAllByBranchId(branchId);
        for(Team team: teamList){
            TeamDTO teamDTO = new TeamDTO();
            teamDTO.setId(team.getId());
            teamDTO.setName(team.getName());
            teamDTOList.add(teamDTO);
        }
        return teamDTOList;
    }

    @Override
    public ExtJSTreeModel findAllTeamAndMemberByBranchId(Long branchId) {
        ExtJSTreeModel rootMenu = new ExtJSTreeModel();
        rootMenu.setId("0");
        rootMenu.setText("Team");
        rootMenu.setDescription("Setting Basics of Application");
        rootMenu.setLeaf(false);
        rootMenu.setExpanded("true");
        rootMenu.setIconCls("teamIcon");
        List<ExtJSTreeModel> rootList = new ArrayList<ExtJSTreeModel>();

        
        List<Employee> allEmployees = employeeDAO.findAllByBranchId(branchId);
        for(Employee employee : allEmployees){
            StringBuilder name = new StringBuilder();
            name.append(employee.getFirstName());
            name.append(" ");
            name.append(employee.getMiddleInitial());
            name.append(" ");
            name.append(employee.getLastName());
            if(employee.getEmployeeType().ordinal() == EmployeeType.TeamLeader.ordinal()){
                name.append(" <b>TL</b>");
            }
            ExtJSTreeModel extEmpModel = new ExtJSTreeModel("0_"+String.valueOf(employee.getId()), name.toString(), "userIcon", "", Boolean.TRUE, "FALSE", null, null);
            rootList.add(extEmpModel);
        }
        

        List<Team> teamList = teamDAO.findAllByBranchId(branchId);
        for(Team team: teamList){
            List<ExtJSTreeModel> memberList = new ArrayList<ExtJSTreeModel>();
            //List<Employee> employees = team.getEmployees();
            List<Employee> employees = employeeDAO.findAllByTeamId(team.getId());
            for(Employee employee : employees){
                StringBuilder name = new StringBuilder();
                name.append(employee.getFirstName());
                name.append(" ");
                name.append(employee.getMiddleInitial());
                name.append(" ");
                name.append(employee.getLastName());
                if(employee.getEmployeeType().ordinal() == EmployeeType.TeamLeader.ordinal()){
                    name.append(" <b>TL</b>");
                }
                ExtJSTreeModel memberModel = new ExtJSTreeModel("0_"+String.valueOf(employee.getId()), name.toString(), "activeUserIcon", "", Boolean.TRUE, "FALSE", null, null);
                if(rootList.contains(memberModel) == true){
                    rootList.remove(memberModel);
                }
                memberList.add(memberModel);
            }
            ExtJSTreeModel teamModel = new ExtJSTreeModel(String.valueOf(team.getId()), team.getName(), "teamIcon", "", Boolean.FALSE, "FALSE", null, memberList);
            rootList.add(teamModel);
        }
        rootMenu.setChildren(rootList);
        return rootMenu;
    }
    
    @Override
    public ExtJSTreeModel findAllTeamAndOpeningByBranchId(Long branchId) {
        ExtJSTreeModel rootMenu = new ExtJSTreeModel();
        rootMenu.setId("0");
        rootMenu.setText("Team");
        rootMenu.setDescription("Setting Basics of Application");
        rootMenu.setLeaf(false);
        rootMenu.setExpanded("true");
        rootMenu.setIconCls("teamIcon");
        List<ExtJSTreeModel> rootList = new ArrayList<ExtJSTreeModel>();

        /*
        List<Opening> allOpenings = openingDAO.findAllByBranchId(branchId);
        for(Opening opening : allOpenings){
            ExtJSTreeModel extEmpModel = new ExtJSTreeModel(String.valueOf(opening.getId()), opening.getPositionName()+" "+opening.getId(), "activeUserIcon", "", Boolean.TRUE, "FALSE", null);
            rootList.add(extEmpModel);
        }
        */
        
        System.out.println("TeamService: >> findAllWithOpeningByBranchId >>");
        List<Team> teamList = teamDAO.findAllByBranchId(branchId);
        for(Team team: teamList){
            List<ExtJSTreeModel> openingList = new ArrayList<ExtJSTreeModel>();
            List<Opening> openings = team.getOpenings();
            for(Opening opening : openings){
                ExtJSTreeModel openingModel = new ExtJSTreeModel(String.valueOf(team.getId()+"_"+opening.getId()), String.valueOf(opening.getId()), "openingIcon", opening.getPositionName(), Boolean.TRUE, "FALSE", opening.getPositionName(), null);
                if(rootList.contains(openingModel) == true){
                    rootList.remove(openingModel);
                }
                openingList.add(openingModel);
            }
            ExtJSTreeModel teamModel = new ExtJSTreeModel(String.valueOf(team.getId()), team.getName()+" <b>"+openingList.size()+"</b> ", "userIcon", "", Boolean.FALSE, "FALSE", "", openingList);
            rootList.add(teamModel);
        }
        rootMenu.setChildren(rootList);
        return rootMenu;
    }

    @Override
    public void addEmployee(Long teamId, Long employeeId) {
        teamDAO.addEmployeeToTeam(teamId, employeeId);
    }

    @Override
    public void addOpening(Long teamId, Long openingId) {
        openingDAO.assignOpeningToTeam(teamId, openingId);
    }

    @Override
    public List<TeamDTO> findAllByOpeningId(Long openingId) {
        List<TeamDTO> teamDTOList = new ArrayList<TeamDTO>();
        List<Team> teamList = teamDAO.findAllByOpeningId(openingId);
        for(Team team: teamList){
            TeamDTO teamDTO = new TeamDTO();
            teamDTO.setId(team.getId());
            teamDTO.setName(team.getName());
            teamDTOList.add(teamDTO);
        }
        return teamDTOList;
    }
}
