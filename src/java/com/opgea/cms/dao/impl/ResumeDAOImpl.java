/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao.impl;

import com.opgea.cms.dao.OpeningDAO;
import com.opgea.cms.dao.ResumeDAO;
import com.opgea.cms.dao.ResumeDocumentDAO;
import com.opgea.cms.domain.entities.Opening;
import com.opgea.cms.domain.entities.Resume;
import com.opgea.cms.domain.entities.ResumeDocument;
import com.opgea.cms.web.dto.AdvanceSearchResumeDTO;
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
public class ResumeDAOImpl implements ResumeDAO{
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private ResumeDocumentDAO resumeDocumentDAO;
    
    @Autowired
    private OpeningDAO openingDAO;

    @Override
    public Resume create(Resume resume) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(resume);
        tx.commit();
        session.close();
        return resume;
    }
    
    @Override
    public int createBatch(List<Resume> resumeList) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        int counter = 0;
        for(Resume resume: resumeList){
            ResumeDocument resumeDoc = new ResumeDocument();
            session.save(resumeDoc);
            resume.setResumeDocument(resumeDoc);
            session.save(resume);
            counter = counter + 1;
        }
        tx.commit();
        session.close();
        return counter;
    }

    @Override
    public Resume update(Resume resume) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.merge(resume);
        tx.commit();
        session.close();
        return resume;
    }

    @Override
    public Resume remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Resume find(Long id) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Resume.findById");
        query.setParameter("id", id);
        Resume resume = (Resume) query.uniqueResult();
        session.close();
        return resume;
    }
    
    @Override
    public Resume find(String emailId){
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Resume.findByEmailId");
        query.setParameter("emailId", emailId);
        //Query query = session.createQuery("SELECT r FROM Resume r WHERE r.email = '"+emailId+"' ");
        Resume resume = (Resume) query.uniqueResult();
        session.close();
        return resume;
    }

    @Override
    public List<Resume> findAllByEmailIds(String commaSeperatedEmailIds) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT r FROM Resume r WHERE r.email IN (" +commaSeperatedEmailIds+ ") ");
        List<Resume> resumes = query.list();
        //System.out.println("Query: "+query.getQueryString());
        //System.out.println("ResumeDAOImpl: >> resumeListInIds >> "+resumes.size());
        return resumes;
    }
    
    @Override
    public List<Resume> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Resume.findAll");
        List<Resume> resumes = query.list();
        return resumes;
    }
    
    @Override
    public List<Resume> findAllByOpeningId(Long openingId) {
        Session session = sessionFactory.openSession();
        /*
        Query query = session.createQuery("FROM Opening as o LEFT JOIN FETCH  o.resumes WHERE o.id="+openingId);
        Opening opening = (Opening) query.uniqueResult();
         * 
         */
        Opening opening = openingDAO.find(openingId);
        List<Resume> resumes = opening.getResumes();
        return resumes;
    }

    @Override
    public List<Resume> findBySearchKey(String searchKey, Long branchId) {
        
        String keys[] = null;
        if(searchKey != null){
           keys =  searchKey.split(",");
        }
        Session session = sessionFactory.openSession();
        StringBuilder queryString = new StringBuilder();
        queryString.append("SELECT r FROM Resume r WHERE r.metaData LIKE ");
        
        if(keys.length > 1){
            for(int a=0; a<keys.length; a++){
                queryString.append("'%");
                queryString.append(keys[a].trim());
                queryString.append("%'");
                if(a < keys.length-1){
                    queryString.append(" OR r.metaData LIKE ");
                }
            }
            for(int a=0; a<keys.length; a++){
                queryString.append(" OR r.resumeDocument.content LIKE ");
                queryString.append("'%");
                queryString.append(keys[a].trim());
                queryString.append("%'");
            }
        }
        if(keys.length == 1){
            queryString.append("'%");
            queryString.append(searchKey);
            queryString.append("%'");
            queryString.append(" OR r.resumeDocument.content LIKE ");
            queryString.append("'%");
            queryString.append(searchKey);
            queryString.append("%'");
        }
        
        if(branchId != null || branchId > 0){
            queryString.append(" and r.branch.id = ");
            queryString.append(branchId);
        }
        
        
        Query query = session.createQuery(queryString.toString());
        //System.out.println("Query: "+query.getQueryString());
        List<Resume> resumes = query.list();
        //System.out.println("Resumes: "+resumes.size());
        return resumes;
    }
    
    @Override
    public List<Resume> advanceSearch(AdvanceSearchResumeDTO searchDTO) {
        Session session = sessionFactory.openSession();
        StringBuilder queryString = new StringBuilder();
        queryString.append("SELECT r FROM Resume r WHERE ");
        if(searchDTO.getCurrentDesignation() != null){
           String designations[] = searchDTO.getCurrentDesignation().split(",");
            if(designations.length > 1){
                //there will be the whole Conditional Logic will apply
                queryString.append(" r.currentDesignation LIKE ");
                for(int a=0; a<designations.length; a++){
                    queryString.append("'%");
                    queryString.append(designations[a].trim());
                    queryString.append("%' ");
                    if(a < designations.length-1){
                        queryString.append(searchDTO.getDesignationLogic());
                        queryString.append(" r.currentDesignation LIKE ");
                    }
                    
                }
            }
            if(designations.length == 1){
                //there will be the whole Conditional Logic will apply
                queryString.append(" r.currentDesignation LIKE ");
                queryString.append("'%");
                queryString.append(searchDTO.getCurrentDesignation());
                queryString.append("%'");
            } 
        }
        if(searchDTO.getKeySkills() != null){
            String keys[] = searchDTO.getKeySkills().split(",");
            if(keys.length > 1){
                queryString.append(" AND r.resumeDocument.content LIKE ");
                for(int a=0; a<keys.length; a++){
                    queryString.append("'%");
                    queryString.append(keys[a].trim());
                    queryString.append("%' ");
                    if(a < keys.length-1){
                        queryString.append(searchDTO.getKeySkillsLogic());
                        queryString.append(" r.resumeDocument.content LIKE ");
                    }
                    
                }
            }
            if(keys.length == 1){
                queryString.append(" AND r.resumeDocument.content LIKE ");
                queryString.append("'%");
                queryString.append(searchDTO.getKeySkills());
                queryString.append("%'");
            }
        }
        if(searchDTO.getLocation() != null){
           String locations[] = searchDTO.getLocation().split(",");
            if(locations.length > 1){
                //there will be the whole Conditional Logic will apply
                queryString.append(" AND r.currentLocation LIKE ");
                for(int a=0; a<locations.length; a++){
                    queryString.append("'%");
                    queryString.append(locations[a].trim());
                    queryString.append("%' ");
                    if(a < locations.length-1){
                        queryString.append(searchDTO.getLocationLogic());
                        queryString.append(" r.currentLocation LIKE ");
                    }
                    
                }
            }
            if(locations.length == 1){
                //there will be the whole Conditional Logic will apply
                queryString.append(" AND r.currentLocation LIKE ");
                queryString.append("'%");
                queryString.append(searchDTO.getLocation());
                queryString.append("%'");
            } 
        }
        if(searchDTO.getQualification() != null){
           String qualifications[] = searchDTO.getQualification().split(",");
            if(qualifications.length > 1){
                //there will be the whole Conditional Logic will apply
                queryString.append(" AND r.resumeDocument.content LIKE ");
                for(int a=0; a<qualifications.length; a++){
                    queryString.append("'%");
                    queryString.append(qualifications[a].trim());
                    queryString.append("%' ");
                    if(a < qualifications.length-1){
                        queryString.append(searchDTO.getQualificationLogic());
                        queryString.append(" r.resumeDocument.content LIKE ");
                    }
                    
                }
            }
            if(qualifications.length == 1){
                //there will be the whole Conditional Logic will apply
                queryString.append(" AND r.resumeDocument.content LIKE ");
                queryString.append("'%");
                queryString.append(searchDTO.getQualification());
                queryString.append("%'");
            } 
        }
        if(searchDTO.getMinSalary() != null && searchDTO.getMaxSalary() != null){
            queryString.append(" AND r.currentSalary between ");
            queryString.append(searchDTO.getMinSalary());
            queryString.append(" AND ");
            queryString.append(searchDTO.getMaxSalary());
        }
        if(searchDTO.getMinExp() != null && searchDTO.getMaxExp() != null){
            queryString.append(" AND r.experience between ");
            queryString.append(searchDTO.getMinExp());
            queryString.append(" AND ");
            queryString.append(searchDTO.getMaxExp());
        }
        
        if(searchDTO.getBranchId() != null || searchDTO.getBranchId() > 0){
            queryString.append(" and r.branch.id = ");
            queryString.append(searchDTO.getBranchId());
        }
        
        
        Query query = session.createQuery(queryString.toString());
        System.out.println("Query: "+query.getQueryString());
        List<Resume> resumes = query.list();
        System.out.println("Resumes: "+resumes.size());
        return resumes;
    }

    
    @Override
    public boolean uploadResume(Long resumeId, String content) {
        
        boolean status = false;
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Resume.findById");
        query.setParameter("id", resumeId);
        Resume resume = (Resume) query.uniqueResult();


        ResumeDocument doc = resume.getResumeDocument();
        if(doc != null){
            doc.setContent(content);
            resumeDocumentDAO.update(doc);
            resume.setResumeDocument(doc);
        }
        this.update(resume);
        status = true;
        
        return status;
    }

}
