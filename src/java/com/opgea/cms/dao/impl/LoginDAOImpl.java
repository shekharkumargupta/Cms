/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao.impl;

import com.opgea.cms.dao.LoginDAO;
import com.opgea.cms.domain.entities.Login;
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
public class LoginDAOImpl implements LoginDAO{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Login create(Login login) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(login);
        tx.commit();
        session.close();
        return login;
    }

    @Override
    public Login update(Login login) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.update(login);
        tx.commit();
        session.close();
        return login;
    }

    @Override
    public Login remove(String loginId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Login find(String loginId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Login.findByLoginId");
        query.setParameter("loginId", loginId);
        Login login = (Login) query.uniqueResult();
        session.close();
        return login;
    }

    @Override
    public List<Login> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Login findByEmployeeId(Long employeeId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Login.findByEmployeeId");
        query.setParameter("employeeId", employeeId);
        Login login = (Login) query.uniqueResult();
        session.close();
        return login;
    }
   
}
