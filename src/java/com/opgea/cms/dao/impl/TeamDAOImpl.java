/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao.impl;

import com.opgea.cms.dao.TeamDAO;
import com.opgea.cms.domain.entities.Employee;
import com.opgea.cms.domain.entities.Opening;
import com.opgea.cms.domain.entities.Team;
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
public class TeamDAOImpl implements TeamDAO{
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Team create(Team team) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(team);
        tx.commit();
        session.close();
        return team;
    }

    @Override
    public Team update(Team team) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.merge(team);
        tx.commit();
        session.close();
        return team;
    }

    @Override
    public Team remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Team find(Long id) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Team.findById");
        query.setParameter("id", id);
        Team team = (Team) query.uniqueResult();
        session.close();
        return team;
    }

    @Override
    public List<Team> findAllByBranchId(Long branchId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Team.findAllByBranchId");
        query.setParameter("branchId", branchId);
        List<Team> teams = query.list();
        return teams;
    }

    @Override
    public List<Team> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Team.findAll");
        List<Team> teams = query.list();
        return teams;
    }

    @Override
    public void addEmployeeToTeam(Long teamId, Long employeeId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Team.findById");
        query.setParameter("id", teamId);
        Team team = (Team) query.uniqueResult();
        
        Query query2 = session.getNamedQuery("Employee.findById");
        query2.setParameter("id", employeeId);
        Employee employee = (Employee) query2.uniqueResult();
        
        employee.setTeam(team);
        
        Transaction tx = session.getTransaction();
        tx.begin();
        session.merge(employee);
        /*
        team.addEmployee(employee);
        session.merge(team);
         * 
         */
        tx.commit();
        session.close();
    }

    @Override
    public List<Team> findAllByOpeningId(Long openingId) {
        Session session = sessionFactory.openSession();
        //Query query = session.getNamedQuery("Opening.findById");
        //query.setParameter("id", openingId);
        Query query = session.createQuery("FROM Opening as o LEFT JOIN FETCH  o.teams WHERE o.id="+openingId);
        Opening opening = (Opening) query.uniqueResult();
        List<Team> teams = opening.getTeams();
        session.close();
        return teams;
    }
}
