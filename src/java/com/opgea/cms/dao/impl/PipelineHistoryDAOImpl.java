/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao.impl;

import com.opgea.cms.dao.PipelineHistoryDAO;
import com.opgea.cms.domain.entities.Pipeline;
import com.opgea.cms.domain.entities.PipelineHistory;
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
public class PipelineHistoryDAOImpl implements PipelineHistoryDAO{
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public PipelineHistory create(PipelineHistory pipelineHistory) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();        
        session.save(pipelineHistory);
        
        Pipeline pipeline = pipelineHistory.getPipeline();
        PipelineStage status = pipelineHistory.getPipelineStatus();
        
        pipeline.setPipelineStatus(status);
        session.update(pipeline);
        tx.commit();
        session.close();
        return pipelineHistory;
    }
    
    @Override
    public PipelineHistory update(PipelineHistory pipelineHistory) {
        Session session = sessionFactory.openSession();
        PipelineHistory history = new PipelineHistory();
        
        Transaction tx = session.getTransaction();
        tx.begin();        
        session.update(history);
        tx.commit();
        session.close();
        return history;
    }

    @Override
    public PipelineHistory remove(Long pipelineHistoryId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PipelineHistory find(Long pipelineHistoryId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("PipelineHistory.findById");
        query.setParameter("id", pipelineHistoryId);
        PipelineHistory pipelineHistory = (PipelineHistory) query.uniqueResult();
        //session.close();
        return pipelineHistory;
    }

    @Override
    public List<PipelineHistory> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("PipelineHistory.findAll");
        List<PipelineHistory> pipelineHistoryList = query.list();
        return pipelineHistoryList;
    }

    @Override
    public List<PipelineHistory> findAllByPipelineId(Long pipelineId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("PipelineHistory.findAllByPipelineId");
        query.setParameter("pipelineId", pipelineId);
        List<PipelineHistory> pipelineHistoryList = query.list();
        return pipelineHistoryList;
    }
    
}
