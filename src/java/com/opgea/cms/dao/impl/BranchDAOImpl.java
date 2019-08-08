/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao.impl;

import com.opgea.cms.dao.BranchDAO;
import com.opgea.cms.domain.entities.Branchh;
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
public class BranchDAOImpl implements BranchDAO{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Branchh create(Branchh branch) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(branch);
        tx.commit();
        session.close();
        return branch;
    }

    @Override
    public Branchh update(Branchh branch) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.update(branch);
        tx.commit();
        session.close();
        return branch;
    }

    @Override
    public Branchh remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Branchh find(Long id) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Branch.findById");
        query.setParameter("id", id);
        return (Branchh) query.uniqueResult();
    }

    @Override
    public List<Branchh> findByCompanyId(Long companyId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Branch.findAllByCompanyId");
        query.setParameter("companyId", companyId);
        List<Branchh> branches = query.list();
        return branches;
    }

    @Override
    public List<Branchh> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Branch.findAll");
        List<Branchh> branches = query.list();
        return branches;
    }
    
}
