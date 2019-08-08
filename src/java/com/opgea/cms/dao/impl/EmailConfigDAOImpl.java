/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao.impl;

import com.opgea.cms.dao.EmailConfigDAO;
import com.opgea.cms.domain.entities.EmailConfig;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Shekhar
 */
@Repository
public class EmailConfigDAOImpl implements EmailConfigDAO{
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public EmailConfig create(EmailConfig emailConfig) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(emailConfig);
        tx.commit();
        session.close();
        return emailConfig;
    }

    @Override
    public EmailConfig update(EmailConfig emailConfig) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.update(emailConfig);
        tx.commit();
        session.close();
        return emailConfig;
    }

    @Override
    public EmailConfig remove(Long emailConfigId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public EmailConfig findById(Long emailConfigId) {
         Session session = sessionFactory.openSession();
        //Query query = session.createQuery("from Employee e WHERE e.id="+id);
        Query query = session.getNamedQuery("EmailConfig.findById")
                .setParameter("id", emailConfigId);
        EmailConfig emailConfig = (EmailConfig)query.uniqueResult();
        session.close();
        return emailConfig;
    }

    @Override
    public EmailConfig findByEmployeeId(Long employeeId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("EmailConfig.findAllByEmployeeId");
        query.setParameter("employeeId", employeeId);
        //Query query = session.createQuery("from Employee e WHERE e.branch.id="+branchId);
        EmailConfig emailConfig = (EmailConfig) query.uniqueResult();
        session.close();
        return emailConfig;
    }

    @Override
    public List<EmailConfig> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("EmailConfig.findAll");
        List<EmailConfig> employees = query.list();
        session.close();
        return employees;
    }

    @Override
    public List<EmailConfig> findAllByCompanyId(Long companyId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("EmailConfig.findAllByCompanyId");
        query.setParameter("companyId", companyId);
        //Query query = session.createQuery("from Employee e WHERE e.branch.id="+branchId);
        List<EmailConfig> emailConfigs = query.list();
        session.close();
        return emailConfigs;
    }
    
}
