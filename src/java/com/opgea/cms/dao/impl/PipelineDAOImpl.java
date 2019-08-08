/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao.impl;

import com.opgea.cms.dao.PipelineDAO;
import com.opgea.cms.domain.entities.Company;
import com.opgea.cms.domain.entities.Employee;
import com.opgea.cms.domain.entities.Opening;
import com.opgea.cms.domain.entities.Pipeline;
import com.opgea.cms.domain.entities.PipelineStage;
import com.opgea.cms.domain.entities.Resume;
import com.opgea.cms.domain.entities.Team;
import com.opgea.cms.domain.modal.ChartModel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
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
public class PipelineDAOImpl implements PipelineDAO{
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Pipeline create(Long resumeId, Long openingId, Long pipelinedById, Long companyId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Resume.findById");
        query.setParameter("id", resumeId);
        Resume resume = (Resume) query.uniqueResult();
        
        Query query2 = session.getNamedQuery("Opening.findById");
        query2.setParameter("id", openingId);
        Opening opening = (Opening) query2.uniqueResult();
        opening.addResume(resume);
        //resume.addOpening(opening); not required to do twoway entry.
        
        Query query3 = session.getNamedQuery("Employee.findById");
        query3.setParameter("id", pipelinedById);
        Employee pipelinedBy = (Employee) query3.uniqueResult();
        Team pipelinedByTeam = pipelinedBy.getTeam();
        
        Query query4 = session.getNamedQuery("Company.findById");
        query4.setParameter("id", companyId);
        Company company = (Company) query4.uniqueResult();
        
        
        Pipeline pipeline = new Pipeline();
        pipeline.setOpening(opening);
        pipeline.setResume(resume);
        pipeline.setPipelinedBy(pipelinedBy);
        pipeline.setPipelinedByTeam(pipelinedByTeam);
        pipeline.setCompany(company);
        pipeline.setPipelineAt(Calendar.getInstance().getTime());
        //pipeline.setPipelineStatus();
        
        Transaction tx = session.getTransaction();
        tx.begin();        
        session.save(pipeline);
        tx.commit();
        session.close();
        return pipeline;
    }

    @Override
    public Pipeline update(Pipeline pipeline) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Pipeline remove(Long pipelineId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Pipeline find(Long pipelineId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Pipeline.findById");
        query.setParameter("id", pipelineId);
        Pipeline pipeline = (Pipeline) query.uniqueResult();
        //session.close();
        return pipeline;
    }

    @Override
    public List<Pipeline> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Pipeline.findAll");
        List<Pipeline> pipelines = query.list();
        return pipelines;
    }

    @Override
    public List<Pipeline> findAllByEmployeeId(Long employeeId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Pipeline> findAllByTeamId(Long teamId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Pipeline> findAllByBranchId(Long branchId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Pipeline> findAllByCompanyId(Long companyId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Pipeline> findAllByOpeningId(Long openingId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Pipeline.findAllByOpeningId");
        query.setParameter("openingId", openingId);
        List<Pipeline> pipelines = query.list();
        return pipelines;
    }

    @Override
    public List<Pipeline> findAllByClientId(Long clientId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Pipeline> find(Long resumeId, Long openingId) {
        Session session = sessionFactory.openSession();
        String queryString = "SELECT p FROM Pipeline p WHERE p.resume.id = "+resumeId+" AND p.opening.id = "+openingId+" ";
        Query query = session.createQuery(queryString);
        List<Pipeline> pipelineList = query.list();
        return pipelineList;
    }

    @Override
    public List<Pipeline> findAllByDate(String fromDate, String toDate, Long employeeId, Long teamId, Integer stageId){
        Session session = sessionFactory.openSession();
        StringBuilder queryString = new StringBuilder("SELECT p FROM Pipeline p WHERE ");
        if(fromDate != null && toDate != null){
            queryString.append(" p.pipelineAt BETWEEN ");
            queryString.append("'");
            queryString.append(fromDate);
            queryString.append("'");
            queryString.append(" AND ");
            queryString.append("'");
            queryString.append(toDate);
            queryString.append("'");
        }
        if(employeeId != null && employeeId > 0){
            queryString.append(" AND p.pipelinedBy = ");
            queryString.append(employeeId);
        }
        if(teamId != null && teamId > 0){
            queryString.append(" AND p.pipelinedByTeam = ");
            queryString.append(teamId);
        }
        if(stageId != null && stageId > 0){
            queryString.append(" AND p.pipelineStatus = ");
            queryString.append(stageId);
        }
        Query query = session.createQuery(queryString.toString());
        System.out.println("Query: "+query.getQueryString());
        List<Pipeline> pipelines = query.list();
        return pipelines;
    }

    @Override
    public List<ChartModel> getCountByDate(String fromDate, String toDate, Long employeeId, Long teamId, Long companyId) {
        Session session = sessionFactory.openSession();
        
        StringBuilder queryString = new StringBuilder("SELECT p.pipelineStatus, "
                + " count(p) FROM Pipeline p WHERE ");
        if(fromDate != null && toDate != null){
            queryString.append(" p.pipelineAt BETWEEN ");
            queryString.append("'");
            queryString.append(fromDate);
            queryString.append("'");
            queryString.append(" AND ");
            queryString.append("'");
            queryString.append(toDate);
            queryString.append("'");
        }
        if(employeeId != null && employeeId > 0){
            queryString.append(" AND p.pipelinedBy = ");
            queryString.append(employeeId);
        }
        if(teamId != null && teamId > 0){
            queryString.append(" AND p.pipelinedByTeam = ");
            queryString.append(teamId);
        }
        queryString.append(" AND p.company.id = ");
        queryString.append(companyId);
        queryString.append(" group by p.pipelineStatus");
        Query query = session.createQuery(queryString.toString());

        
        Query query2 = session.getNamedQuery("PipelineStage.findAllByCompanyId");
        query2.setParameter("companyId", companyId);
        List<PipelineStage> stages = query2.list();
        
        List<ChartModel> chartModels = new ArrayList<ChartModel>();
        for(PipelineStage stage: stages){
            chartModels.add(new ChartModel(stage.getName(), "0", stage.getStepNo()));
        }
        
        for (Iterator it = query.iterate(); it.hasNext();) {
                Object[] row = (Object[]) it.next();
                
                PipelineStage status = (PipelineStage) row[0];
                ChartModel model = new ChartModel(status.getName(), row[1].toString(), status.getStepNo());
                if(chartModels.contains(model)){
                    chartModels.remove(model);
                }
                chartModels.add(model);
        }
        
        Collections.sort(chartModels);
        return chartModels;
    }
    
}
