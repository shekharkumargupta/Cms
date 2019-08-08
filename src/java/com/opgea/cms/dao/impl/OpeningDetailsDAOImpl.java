/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao.impl;

import com.opgea.cms.dao.OpeningDetailsDAO;
import com.opgea.cms.domain.entities.OpeningDetails;
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
public class OpeningDetailsDAOImpl implements OpeningDetailsDAO{
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public OpeningDetails create(OpeningDetails details) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(details);
        tx.commit();
        session.close();
        return details;
    }

    @Override
    public OpeningDetails update(OpeningDetails details) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.merge(details);
        tx.commit();
        session.close();
        return details;
    }

    @Override
    public OpeningDetails remove(Long openingDetailsId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public OpeningDetails find(Long openingDetailsId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("OpeningDetails.findById");
        query.setParameter("id", openingDetailsId);
        OpeningDetails openingDetails = (OpeningDetails) query.uniqueResult();
        session.close();
        return openingDetails;
    }

    @Override
    public List<OpeningDetails> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("OpeningDetails.findAll");
        List<OpeningDetails> openingDetails = query.list();
        return openingDetails;
    }

    @Override
    public OpeningDetails findByOpeningId(Long openingId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("OpeningDetails.findByOpeningId");
        query.setParameter("openingId", openingId);
        OpeningDetails openingDetails = (OpeningDetails) query.uniqueResult();
        session.close();
        return openingDetails;
    }
    
}
