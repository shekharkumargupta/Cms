/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao.impl;

import com.opgea.cms.dao.EmailMessageDAO;
import com.opgea.cms.domain.entities.EmailMessage;
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
public class EmailMessageDAOImpl implements EmailMessageDAO{
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public EmailMessage create(EmailMessage emailMessage) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(emailMessage);
        tx.commit();
        session.close();
        return emailMessage;
    }

    @Override
    public EmailMessage update(EmailMessage emailMessage) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.merge(emailMessage);
        tx.commit();
        session.close();
        return emailMessage;
    }

    @Override
    public EmailMessage findById(Long id) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("EmailMessage.findById");
        query.setParameter("id", id);
        EmailMessage emailMessage = (EmailMessage) query.uniqueResult();
        //session.close();
        return emailMessage;
    }

    @Override
    public EmailMessage findByBranchId(Long branchId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("EmailMessage.findAllByBranchId");
        query.setParameter("branchId", branchId);
        EmailMessage emailMessage = (EmailMessage) query.uniqueResult();
        //session.close();
        return emailMessage;
    }

    @Override
    public List<EmailMessage> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("EmailMessage.findAll");
        List<EmailMessage> emailMessages =  query.list();
        //session.close();
        return emailMessages;
    }
    
}
