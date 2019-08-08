/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao.impl;

import com.opgea.cms.dao.CategoryDAO;
import com.opgea.cms.domain.entities.Category;
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
public class CategoryDAOImpl implements CategoryDAO{

    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Category create(Category category) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Category update(Category category) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Category delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Category find(Long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        Query query = session.getNamedQuery("Category.findById");
        query.setParameter("id", id);
        tx.commit();
        Category category = (Category) query.uniqueResult();
        session.close();
        return category;
        //return (Category) session.get(Category.class, id);
    }

    @Override
    public List<Category> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Category.findAll");
        return query.list();
    }
    
}
