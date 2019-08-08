/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao.impl;

import com.opgea.cms.dao.CompanyDAO;
import com.opgea.cms.domain.entities.Company;
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
public class CompanyDAOImpl implements CompanyDAO{
    
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Company create(Company company) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(company);
        tx.commit();
        session.close();
        return company;
    }

    @Override
    public Company update(Company company) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.update(company);
        tx.commit();
        session.close();
        return company;
    }

    @Override
    public Company remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Company find(Long id) {
        Session session = sessionFactory.openSession();
        //Query query = session.getNamedQuery("Company.findById");
        Query query = session.createQuery("from Company c WHERE c.id = "+id);
        //query.setParameter("id", id);
        Company company = (Company) query.uniqueResult();
        session.close();
        return company;
    }

    @Override
    public List<Company> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Company.findAll");
        List<Company> companies = query.list();
        return companies;
    }
}
