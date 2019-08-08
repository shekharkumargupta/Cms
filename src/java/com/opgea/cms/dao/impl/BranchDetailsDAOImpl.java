/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao.impl;

import com.opgea.cms.dao.BranchDetailsDAO;
import com.opgea.cms.domain.entities.BranchDetails;
import java.util.List;

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
public class BranchDetailsDAOImpl implements BranchDetailsDAO{
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public BranchDetails create(BranchDetails branchDetails) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(branchDetails);
        tx.commit();
        session.close();
        return branchDetails;
    }

    @Override
    public BranchDetails update(BranchDetails branchDetails) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.update(branchDetails);
        tx.commit();
        session.close();
        return branchDetails;
    }

    @Override
    public BranchDetails remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BranchDetails find(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<BranchDetails> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
