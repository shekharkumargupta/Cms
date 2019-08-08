/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao.impl;

import com.opgea.cms.dao.PositionDAO;
import com.opgea.cms.domain.entities.Position;
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
public class PositionDAOImpl implements PositionDAO{
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Position create(Position position) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(position);
        tx.commit();
        session.close();
        return position;
    }

    @Override
    public Position update(Position position) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.update(position);
        tx.commit();
        session.close();
        return position;
    }

    @Override
    public Position delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Position find(Long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        Query query = session.getNamedQuery("Position.findById");
        query.setParameter("id", id);
        tx.commit();
        Position position = (Position) query.uniqueResult();
        session.close();
        return position;
    }

    @Override
    public List<Position> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Position.findAll");
        return query.list();
    }

    @Override
    public List<Position> findAllByCategoryId(Long categoryId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        Query query = session.getNamedQuery("Position.findAllByCategoryId").
        setParameter("categoryId", categoryId);
        tx.commit();
        List<Position> positionList =  query.list();
        session.close();
        return positionList;
    }
    
}
