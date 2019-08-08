/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao.impl;

import com.opgea.cms.dao.TeamAndOpeningDAO;
import com.opgea.cms.domain.entities.TeamAndOpening;
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
public class TeamAndOpeningDAOImpl implements TeamAndOpeningDAO{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public TeamAndOpening create(TeamAndOpening teamAndOpening) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(teamAndOpening);
        tx.commit();
        session.close();
        return teamAndOpening;
    }

    @Override
    public TeamAndOpening update(TeamAndOpening teamAndOpening) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.merge(teamAndOpening);
        tx.commit();
        session.close();
        return teamAndOpening;
    }

    @Override
    public TeamAndOpening remove(Long teaAndOpeningId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TeamAndOpening findById(Long teamAndOpeningId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("TeamAndOpening.findById");
        query.setParameter("id", teamAndOpeningId);
        TeamAndOpening opening = (TeamAndOpening) query.uniqueResult();
        //session.close();
        return opening;
    }

    @Override
    public List<TeamAndOpening> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("TeamAndOpening.findAll");
        List<TeamAndOpening> openings = query.list();
        return openings;
    }

    @Override
    public List<TeamAndOpening> findAllByOPeningId(Long openingId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("TeamAndOpening.findAllByOpeningId");
        query.setParameter("openingId", openingId);
        List<TeamAndOpening> openings = query.list();
        return openings;
    }

    @Override
    public List<TeamAndOpening> findAllByTeamId(Long teamId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("TeamAndOpening.findAllByTeamId");
        query.setParameter("teamId", teamId);
        List<TeamAndOpening> openings = query.list();
        return openings;
    }
    
}
