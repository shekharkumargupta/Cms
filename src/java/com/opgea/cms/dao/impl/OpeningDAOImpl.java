/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao.impl;

import com.opgea.cms.dao.OpeningDAO;
import com.opgea.cms.dao.TeamAndOpeningDAO;
import com.opgea.cms.dao.TeamDAO;
import com.opgea.cms.domain.entities.Employee;
import com.opgea.cms.domain.entities.EmployeeAndOpening;
import com.opgea.cms.domain.entities.Opening;
import com.opgea.cms.domain.entities.Team;
import com.opgea.cms.domain.entities.TeamAndOpening;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ramesh
 */
@Repository
public class OpeningDAOImpl implements OpeningDAO{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private TeamDAO teamDAO;
    
    @Autowired
    private TeamAndOpeningDAO teamAndOpeningDAO;
    
    @Override
    public Opening create(Opening opening) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(opening);
        tx.commit();
        session.close();
        return opening;
    }

    @Override
    public Opening update(Opening opening) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.merge(opening);
        tx.commit();
        session.close();
        return opening;
    }

    @Override
    public Opening remove(Long openingId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Opening find(Long openingId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Opening.findById");
        query.setParameter("id", openingId);
        Opening opening = (Opening) query.uniqueResult();
        //session.close();
        return opening;
    }

    @Override
    public List<Opening> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Opening.findAll");
        List<Opening> openings = query.list();
        return openings;
    }

    @Override
    public List<Opening> findAllByBranchId(Long branchId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Opening.findAllByBranchId");
        query.setParameter("branchId", branchId);
        List<Opening> openings = query.list();
        return openings;
    }

    @Override
    public List<Opening> findAllByCompanyId(Long companyId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Opening.findAllByCompanyId");
        query.setParameter("companyId", companyId);
        List<Opening> openings = query.list();
        return openings;
    }

    @Override
    public List<Opening> findAllByClientId(Long clientId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Opening.findAllByClientId");
        query.setParameter("clientId", clientId);
        List<Opening> openings = query.list();
        return openings;
    }

    @Override
    public List<Opening> findAllByTeamId(Long teamId) {
        Team team = teamDAO.find(teamId);
        List<Opening> openings = team.getOpenings();
        return openings;
    }
    
    @Override
    public List<Opening> findAllByEmployeeId(Long employeeId) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Employee as e LEFT JOIN FETCH  e.openings WHERE e.id="+employeeId);
        Employee employee = (Employee) query.uniqueResult();
        List<Opening> openings = employee.getOpenings();
        return openings;
    }

    @Override
    public void assignOpeningToTeam(Long teamId, Long openingId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Team.findById");
        query.setParameter("id", teamId);
        Team team = (Team) query.uniqueResult();
        
        Query query2 = session.getNamedQuery("Opening.findById");
        query2.setParameter("id", openingId);
        Opening opening = (Opening) query2.uniqueResult();
        
        if(team.getOpenings().contains(opening) == false){
            Transaction tx = session.getTransaction();
            tx.begin(); 
            team.addOpening(opening);
            session.update(team);

            TeamAndOpening teamAndOpening = new TeamAndOpening();
            teamAndOpening.setTeamId(teamId);
            teamAndOpening.setOpeningId(openingId);
            teamAndOpening.setAllotedDate(Calendar.getInstance().getTime());
            //saving TeamAndOpening
            session.save(teamAndOpening);

            opening.getTeams().add(team);
            opening.getTeamAndOpenings().add(teamAndOpening);
            session.update(team);
            tx.commit();
        }
        session.close();
    }

    @Override
    public void assignOpeningToEmployee(Long employeeId, Long openingId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Employee.findById");
        query.setParameter("id", employeeId);
        Employee employee = (Employee) query.uniqueResult();
        
        Query query2 = session.getNamedQuery("Opening.findById");
        query2.setParameter("id", openingId);
        Opening opening = (Opening) query2.uniqueResult();
        
         
        /*
         * Checking whether opening is already assigned to
         * employee or not
         */
        if(employee.getOpenings().contains(opening) == false){
            Transaction tx = session.getTransaction();
            tx.begin();     
            employee.addOpening(opening);
            session.update(employee);
            
            EmployeeAndOpening employeeAndOpening = new EmployeeAndOpening();
            employeeAndOpening.setEmployeeId(employeeId);
            employeeAndOpening.setOpeningId(openingId);
            employeeAndOpening.setAllotedDate(Calendar.getInstance().getTime());
            //saving TeamAndOpening
            session.save(employeeAndOpening);
            
            opening.getEmployees().add(employee);
            opening.getEmployeeAndOpenings().add(employeeAndOpening);
            //Saving opening
            session.update(opening);
            tx.commit();
        }
        session.close();
    }

    @Override
    public Boolean isAreadyAssignedToTeam(Long teamId, Long openingId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Team.findById");
        query.setParameter("id", teamId);
        Team team = (Team) query.uniqueResult();
        List<Opening> openings = team.getOpenings();
        
        Query query2 = session.getNamedQuery("Opening.findById");
        query2.setParameter("id", openingId);
        Opening opening = (Opening) query2.uniqueResult();
        session.close();
        
        return openings.contains(opening);
    }

    @Override
    public Boolean isAreadyAssignedToEmployee(Long employeeId, Long openingId) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Employee as e LEFT JOIN FETCH  e.openings WHERE e.id="+employeeId);
        Employee employee = (Employee) query.uniqueResult();
        List<Opening> openings = employee.getOpenings();
        
        Query query2 = session.getNamedQuery("Opening.findById");
        query2.setParameter("id", openingId);
        Opening opening = (Opening) query2.uniqueResult();
        session.close();
        
        return openings.contains(opening);
    }

    

    
    
}
