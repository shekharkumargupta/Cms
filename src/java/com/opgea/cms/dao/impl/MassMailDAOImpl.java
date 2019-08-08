/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao.impl;

import com.opgea.cms.dao.MassMailDAO;
import com.opgea.cms.domain.entities.MassMail;
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
public class MassMailDAOImpl implements MassMailDAO{
    
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(List<MassMail> massMailList) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        for(MassMail massMail: massMailList){
            session.save(massMail);
        }
        tx.commit();
        session.close();
      
    }
    
    @Override
    public MassMail findById(Long massMailId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("MassMail.findById");
        query.setParameter("id", massMailId);
        MassMail massMail = (MassMail) query.uniqueResult();
        session.close();
        return massMail;
    }

    @Override
    public List<MassMail> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("MassMail.findAll");
        List<MassMail> massMails = query.list();
        return massMails;
    }

    @Override
    public List<MassMail> findAllByOpeningId(Long openingId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("MassMail.findAllByOpeningId");
        query.setParameter("openingId", openingId);
        List<MassMail> massMails = query.list();
        return massMails;
    }
    
       

    @Override
    public List<MassMail> findAllByOpeningAndSenderId(Long openingId, Long senderId) {
        Session session = sessionFactory.openSession();
        StringBuilder queryString = new StringBuilder();
        queryString.append("SELECT m FROM MassMail m WHERE m.opening.id = ");
        queryString.append(openingId);
        if(senderId != null && senderId > 0){
            queryString.append(" AND m.sentBy.id = ");
            queryString.append(senderId);
        }
        /*
        Query query = session.getNamedQuery("MassMail.findAllByOpeningAndSender");
        query.setParameter("openingId", openingId);
        query.setParameter("senderId", senderId);
         * 
         */
        Query query = session.createQuery(queryString.toString());
        List<MassMail> massMails = query.list();
        return massMails;
    }

    

    
    
}
