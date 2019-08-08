/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao.impl;

import com.opgea.cms.dao.PipelineStageDAO;
import com.opgea.cms.domain.entities.PipelineStage;
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
public class PipelineStatgeDAOImpl implements PipelineStageDAO{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public PipelineStage create(PipelineStage pipelineStatus) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();        
        session.save(pipelineStatus);
        tx.commit();
        session.close();
        return pipelineStatus;
    }

    @Override
    public PipelineStage update(PipelineStage pipelineStatus) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();        
        session.merge(pipelineStatus);
        tx.commit();
        session.close();
        return pipelineStatus;
    }

    @Override
    public PipelineStage remove(Long pipelineStatusId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PipelineStage find(Long pipelineStatusId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("PipelineStage.findById");
        query.setParameter("id", pipelineStatusId);
        PipelineStage pipelineStatus = (PipelineStage) query.uniqueResult();
        //session.close();
        return pipelineStatus;
    }

    @Override
    public List<PipelineStage> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("PipelineStage.findAll");
        List<PipelineStage> pipelineStatuses = query.list();
        return pipelineStatuses;
    }

    @Override
    public List<PipelineStage> findAllByCompanyId(Long companyId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("PipelineStage.findAllByCompanyId");
        query.setParameter("companyId", companyId);
        List<PipelineStage> pipelineStatuses = query.list();
        return pipelineStatuses;
    }
    
}
