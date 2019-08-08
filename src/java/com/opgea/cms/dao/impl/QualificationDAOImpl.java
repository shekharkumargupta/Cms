/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao.impl;

import com.opgea.cms.dao.QualificationDAO;
import com.opgea.cms.domain.entities.Qualification;
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
public class QualificationDAOImpl implements QualificationDAO{
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Qualification create(Qualification qualification) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(qualification);
        tx.commit();
        session.close();
        return qualification;
    }

    @Override
    public Qualification update(Qualification qualification) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.update(qualification);
        tx.commit();
        session.close();
        return qualification;
    }

    @Override
    public Qualification delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Qualification find(Long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        Query query = session.getNamedQuery("Qualification.findById");
        query.setParameter("id", id);
        tx.commit();
        Qualification qualification = (Qualification) query.uniqueResult();
        session.close();
        return qualification;
    }

    @Override
    public List<Qualification> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Qualification.findAll");
        return query.list();
    }

    @Override
    public List<Qualification> findAllByCategoryId(Long categoryId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        Query query = session.getNamedQuery("Qualification.findAllByCategoryId");
        query.setParameter("categoryId", categoryId);
        tx.commit();
        List<Qualification> qualificationList =  query.list();
        session.close();
        return qualificationList;
    }
    
}
