/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service.impl;

import com.opgea.cms.dao.BranchDAO;
import com.opgea.cms.dao.CategoryDAO;
import com.opgea.cms.dao.EmployeeDAO;
import com.opgea.cms.dao.ResumeDAO;
import com.opgea.cms.dao.ResumeDocumentDAO;
import com.opgea.cms.domain.entities.Branchh;
import com.opgea.cms.domain.entities.Company;
import com.opgea.cms.domain.entities.Employee;
import com.opgea.cms.domain.entities.Resume;
import com.opgea.cms.domain.entities.ResumeDocument;
import com.opgea.cms.domain.modal.FileUploadBean;
import com.opgea.cms.domain.qualifiers.ConditionType;
import com.opgea.cms.domain.qualifiers.DecisionType;
import com.opgea.cms.service.ResumeService;
import com.opgea.cms.service.mail.MailService;
import com.opgea.cms.web.dto.AdvanceSearchResumeDTO;
import com.opgea.cms.web.dto.ResumeDTO;
import com.opgea.constraints.ApplicationConstraints;
import com.opgea.util.DateUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.poi.hwpf.HWPFDocumentCore;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.converter.WordToHtmlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;

/**
 *
 * @author Ramesh
 */
@Service
public class ResumeServiceImpl implements ResumeService{

    @Autowired
    private ResumeDAO resumeDAO;
    
    @Autowired
    private ResumeDocumentDAO documentDAO;
    
    @Autowired
    private CategoryDAO categoryDAO;
    
    @Autowired
    private BranchDAO branchDAO;
    
    @Autowired
    private MailService mailService;
    
    @Autowired
    private EmployeeDAO employeeDAO;
    
    @Override
    public ResumeDTO create(ResumeDTO resumeDTO) {
        Branchh branch = branchDAO.find(resumeDTO.getBranchId());
        Employee createdBy = employeeDAO.find(resumeDTO.getUpdatedById());
        Resume resume = null;
        if(resumeDTO.getId() > 0){
            resume = resumeDAO.find(resumeDTO.getId());
        }else{
            resume = new Resume();
        }
        
        resume.setName(resumeDTO.getName());
        resume.setEmail(resumeDTO.getEmail());
        resume.setContactNo(resumeDTO.getContactNo());
        resume.setExperience(resumeDTO.getExperience());
        resume.setKeySkills(resumeDTO.getKeySkills());
        resume.setMetaData(resumeDTO.getMetaData());
        resume.setBranch(branch);
        resume.setDateOfBirth(DateUtil.getDateFromYYYYMMDD(resumeDTO.getDateOfBirth(), "-"));
        resume.setCurrentCompany(resumeDTO.getCurrentCompany());
        resume.setCurrentLocation(resumeDTO.getCurrentLocation());
        resume.setCurrentSalary(resumeDTO.getCurrentSalary());
        resume.setCurrentDesignation(resumeDTO.getCurrentDesignation());
        resume.setRemarks(resumeDTO.getRemarks());
        
        if(resumeDTO.getId() > 0){
            resumeDAO.update(resume);
        }else{
            ResumeDocument document = new ResumeDocument();
            document.setContent("CONTENT_NOT_AVAILABLE");
            documentDAO.create(document);
            resume.setCreatedBy(createdBy);
            resume.setCreatedAt(Calendar.getInstance().getTime());
            resume.setResumeDocument(document);
            resumeDAO.create(resume);
        }
        resumeDTO.setId(resume.getId());
        return resumeDTO;
    }
    
    @Override
    public int createBatch(List<ResumeDTO> resumeDTOList, Long branchId, Long updatedById) {
        //System.out.println("Record List: "+resumeDTOList.size());
        Branchh branch = branchDAO.find(branchId);
        Employee createdBy = employeeDAO.find(updatedById);
        
        List<Resume> resumeList = new ArrayList<Resume>();
        for(ResumeDTO resumeDTO: resumeDTOList){
            Resume resume = new Resume();
            resume.setName(resumeDTO.getName());
            resume.setEmail(resumeDTO.getEmail());
            resume.setContactNo(resumeDTO.getContactNo());
            resume.setExperience(resumeDTO.getExperience());
            resume.setKeySkills(resumeDTO.getKeySkills());
            resume.setMetaData(resumeDTO.getMetaData());
            resume.setDateOfBirth(DateUtil.getDateFromYYYYMMDD(resumeDTO.getDateOfBirth(), "-"));
            resume.setCurrentCompany(resumeDTO.getCurrentCompany());
            resume.setCurrentLocation(resumeDTO.getCurrentLocation());
            resume.setCurrentSalary(resumeDTO.getCurrentSalary());
            resume.setCurrentDesignation(resumeDTO.getCurrentDesignation());
            resume.setRemarks(resumeDTO.getRemarks());
            resume.setBranch(branch);
            resume.setCreatedBy(createdBy);
            resume.setCreatedAt(Calendar.getInstance().getTime());
            if(this.isExistingResume(resumeDTO.getEmail(), resumeDTO.getContactNo(), branchId)){
                //System.out.println("Excel Existing Record.");
            }else{
                resumeList.add(resume);
                //System.out.println("Record Added: ");
            }
        }
        int uploadedRecord = resumeDAO.createBatch(resumeList);
        //System.out.println("Uploaded Record: "+uploadedRecord);
        return uploadedRecord;
    }

    @Override
    public ResumeDTO update(ResumeDTO resumeDTO) {
       throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ResumeDTO remove(Long resumeId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ResumeDTO find(Long resumeId) {
        Resume resume = resumeDAO.find(resumeId);
        Branchh branch = resume.getBranch();
        Company company = branch.getCompany();
        Employee createdBy = resume.getCreatedBy();
        ResumeDTO resumeDTO = new ResumeDTO();
        resumeDTO.setId(resume.getId());
        resumeDTO.setName(resume.getName());
        resumeDTO.setContactNo(resume.getContactNo());
        resumeDTO.setEmail(resume.getEmail());
        resumeDTO.setExperience(resume.getExperience());
        resumeDTO.setKeySkills(resume.getKeySkills());
        //resumeDTO.setResumeContent(resume.getResumeDocument().getContent());
        String content = new String(resume.getResumeDocument().getContent().getBytes());
        if(!content.equalsIgnoreCase("CONTENT_NOT_AVAILABLE")){
            resumeDTO.setResumeContent(DecisionType.YES.name().getBytes());
        }
        resumeDTO.setCurrentCompany(resume.getCurrentCompany());
        resumeDTO.setCurrentLocation(resume.getCurrentLocation());
        resumeDTO.setCurrentSalary(resume.getCurrentSalary());
        resumeDTO.setCurrentDesignation(resume.getCurrentDesignation());
        resumeDTO.setRemarks(resume.getRemarks());
        if(resume.getDateOfBirth() != null){
            resumeDTO.setDateOfBirth(DateUtil.getYYYYMMDDFromDate(resume.getDateOfBirth(), "-"));
        }
        if(branch != null){
            resumeDTO.setBranchId(branch.getId());
            resumeDTO.setBranchName(branch.getBranchDetails().getBranchName());
        }
        if(company != null){
            resumeDTO.setCompanyId(company.getId());
            resumeDTO.setCompanyName(company.getCompanyName());
        }
        if(createdBy != null){
            resumeDTO.setCreatedById(createdBy.getId());
            resumeDTO.setCreatedByName(createdBy.getFirstName()+" "+createdBy.getMiddleInitial()+" "+createdBy.getLastName());
        }
        if(resume.getCreatedAt() != null){
            resumeDTO.setCreatedAt(DateUtil.getDateTimeString(resume.getCreatedAt().getTime()));
        }
        
        return resumeDTO;
    }
    
    @Override
    public ResumeDTO find(String emailId) {
        Resume resume = resumeDAO.find(emailId);
        Branchh branch = resume.getBranch();
        Company company = branch.getCompany();
        Employee createdBy = resume.getCreatedBy();
        ResumeDTO resumeDTO = new ResumeDTO();
        resumeDTO.setId(resume.getId());
        resumeDTO.setName(resume.getName());
        resumeDTO.setContactNo(resume.getContactNo());
        resumeDTO.setEmail(resume.getEmail());
        resumeDTO.setExperience(resume.getExperience());
        resumeDTO.setKeySkills(resume.getKeySkills());
        //resumeDTO.setResumeContent(resume.getResumeDocument().getContent());
        if(resume.getResumeDocument().getContent() != null){
            resumeDTO.setResumeContent(DecisionType.YES.name().getBytes());
        }
        resumeDTO.setCurrentCompany(resume.getCurrentCompany());
        resumeDTO.setCurrentLocation(resume.getCurrentLocation());
        resumeDTO.setCurrentSalary(resume.getCurrentSalary());
        resumeDTO.setCurrentDesignation(resume.getCurrentDesignation());
        resumeDTO.setRemarks(resume.getRemarks());
        if(resume.getDateOfBirth() != null){
            resumeDTO.setDateOfBirth(DateUtil.getYYYYMMDDFromDate(resume.getDateOfBirth(), "-"));
        }
        if(branch != null){
            resumeDTO.setBranchId(branch.getId());
            resumeDTO.setBranchName(branch.getBranchDetails().getBranchName());
        }
        if(company != null){
            resumeDTO.setCompanyId(company.getId());
            resumeDTO.setCompanyName(company.getCompanyName());
        }
        if(createdBy != null){
            resumeDTO.setCreatedById(createdBy.getId());
            resumeDTO.setCreatedByName(createdBy.getFirstName()+" "+createdBy.getMiddleInitial()+" "+createdBy.getLastName());
        }
        if(resume.getCreatedAt() != null){
            resumeDTO.setCreatedAt(DateUtil.getDateTimeString(resume.getCreatedAt().getTime()));
        }
        
        return resumeDTO;
    }

    @Override
    public List<ResumeDTO> findAll() {
        List<Resume> resumeList = resumeDAO.findAll();
        List<ResumeDTO> resumeDTOList = new ArrayList<ResumeDTO>();
        for(Resume resume: resumeList){
            Branchh branch = resume.getBranch();
            Company company = branch.getCompany();
            Employee createdBy = resume.getCreatedBy();
            ResumeDTO resumeDTO = new ResumeDTO();
            resumeDTO.setId(resume.getId());
            resumeDTO.setName(resume.getName());
            resumeDTO.setContactNo(resume.getContactNo());
            resumeDTO.setEmail(resume.getEmail());
            resumeDTO.setExperience(resume.getExperience());
            resumeDTO.setKeySkills(resume.getKeySkills());
            resumeDTO.setCurrentCompany(resume.getCurrentCompany());
            resumeDTO.setCurrentLocation(resume.getCurrentLocation());
            resumeDTO.setCurrentSalary(resume.getCurrentSalary());
            resumeDTO.setCurrentDesignation(resume.getCurrentDesignation());
            resumeDTO.setRemarks(resume.getRemarks());
            if(resume.getResumeDocument().getContent() != null){
                resumeDTO.setResumeContent(DecisionType.YES.name().getBytes());
            }
            if(resume.getDateOfBirth() != null){
                resumeDTO.setDateOfBirth(DateUtil.getYYYYMMDDFromDate(resume.getDateOfBirth(), "-"));
            }
            if(branch != null){
                resumeDTO.setBranchId(branch.getId());
                resumeDTO.setBranchName(branch.getBranchDetails().getBranchName());
            }
            if(company != null){
                resumeDTO.setCompanyId(company.getId());
                resumeDTO.setCompanyName(company.getCompanyName());
            }
            if(createdBy != null){
                resumeDTO.setCreatedById(createdBy.getId());
                resumeDTO.setCreatedByName(createdBy.getFirstName()+" "+createdBy.getMiddleInitial()+" "+createdBy.getLastName());
            }
            if(resume.getCreatedAt() != null){
                resumeDTO.setCreatedAt(DateUtil.getDateTimeString(resume.getCreatedAt().getTime()));
            }
            resumeDTOList.add(resumeDTO);
        }
        return resumeDTOList;
    }
    
    @Override
    public List<ResumeDTO> findAllByEmailIds(String commaSeperatedEmailIds) {
        List<Resume> resumeList = resumeDAO.findAllByEmailIds(commaSeperatedEmailIds);
        List<ResumeDTO> resumeDTOList = new ArrayList<ResumeDTO>();
        for(Resume resume: resumeList){
            Branchh branch = resume.getBranch();
            Company company = branch.getCompany();
            Employee createdBy = resume.getCreatedBy();
            ResumeDTO resumeDTO = new ResumeDTO();
            resumeDTO.setId(resume.getId());
            resumeDTO.setName(resume.getName());
            resumeDTO.setContactNo(resume.getContactNo());
            resumeDTO.setEmail(resume.getEmail());
            resumeDTO.setExperience(resume.getExperience());
            resumeDTO.setKeySkills(resume.getKeySkills());
            resumeDTO.setCurrentCompany(resume.getCurrentCompany());
            resumeDTO.setCurrentLocation(resume.getCurrentLocation());
            resumeDTO.setCurrentSalary(resume.getCurrentSalary());
            resumeDTO.setCurrentDesignation(resume.getCurrentDesignation());
            resumeDTO.setRemarks(resume.getRemarks());
            if(resume.getResumeDocument().getContent() != null){
                resumeDTO.setResumeContent(DecisionType.YES.name().getBytes());
            }
            if(resume.getDateOfBirth() != null){
                resumeDTO.setDateOfBirth(DateUtil.getYYYYMMDDFromDate(resume.getDateOfBirth(), "-"));
            }
            if(branch != null){
                resumeDTO.setBranchId(branch.getId());
                resumeDTO.setBranchName(branch.getBranchDetails().getBranchName());
            }
            if(company != null){
                resumeDTO.setCompanyId(company.getId());
                resumeDTO.setCompanyName(company.getCompanyName());
            }
            if(createdBy != null){
                resumeDTO.setCreatedById(createdBy.getId());
                resumeDTO.setCreatedByName(createdBy.getFirstName()+" "+createdBy.getMiddleInitial()+" "+createdBy.getLastName());
            }
            if(resume.getCreatedAt() != null){
                resumeDTO.setCreatedAt(DateUtil.getDateTimeString(resume.getCreatedAt().getTime()));
            }
            resumeDTOList.add(resumeDTO);
        }
        return resumeDTOList;
    }
    
    @Override
    public List<ResumeDTO> findAllByOpeningId(Long openingId) {
        List<Resume> resumeList = resumeDAO.findAllByOpeningId(openingId);
        List<ResumeDTO> resumeDTOList = new ArrayList<ResumeDTO>();
        for(Resume resume: resumeList){
            Branchh branch = resume.getBranch();
            Company company = branch.getCompany();
            Employee createdBy = resume.getCreatedBy();
            ResumeDTO resumeDTO = new ResumeDTO();
            resumeDTO.setId(resume.getId());
            resumeDTO.setName(resume.getName());
            resumeDTO.setContactNo(resume.getContactNo());
            resumeDTO.setEmail(resume.getEmail());
            resumeDTO.setExperience(resume.getExperience());
            resumeDTO.setKeySkills(resume.getKeySkills());
            resumeDTO.setCurrentCompany(resume.getCurrentCompany());
            resumeDTO.setCurrentLocation(resume.getCurrentLocation());
            resumeDTO.setCurrentSalary(resume.getCurrentSalary());
            resumeDTO.setCurrentDesignation(resume.getCurrentDesignation());
            resumeDTO.setRemarks(resume.getRemarks());
            if(resume.getResumeDocument().getContent() != null){
                resumeDTO.setResumeContent(DecisionType.YES.name().getBytes());
            }
            if(resume.getDateOfBirth() != null){
                resumeDTO.setDateOfBirth(DateUtil.getYYYYMMDDFromDate(resume.getDateOfBirth(), "-"));
            }
            if(branch != null){
                resumeDTO.setBranchId(branch.getId());
                resumeDTO.setBranchName(branch.getBranchDetails().getBranchName());
            }
            if(company != null){
                resumeDTO.setCompanyId(company.getId());
                resumeDTO.setCompanyName(company.getCompanyName());
            }
            if(createdBy != null){
                resumeDTO.setCreatedById(createdBy.getId());
                resumeDTO.setCreatedByName(createdBy.getFirstName()+" "+createdBy.getMiddleInitial()+" "+createdBy.getLastName());
            }
            if(resume.getCreatedAt() != null){
                resumeDTO.setCreatedAt(DateUtil.getDateTimeString(resume.getCreatedAt().getTime()));
            }
            resumeDTOList.add(resumeDTO);
        }
        return resumeDTOList;
    }
    
    @Override
    public List<ResumeDTO> findBySearchKey(String searchKey, Long branchId) {
        List<Resume> resumeList = resumeDAO.findBySearchKey(searchKey, branchId);
        List<ResumeDTO> resumeDTOList = new ArrayList<ResumeDTO>();
        for(Resume resume: resumeList){
            Branchh branch = resume.getBranch();
            Company company = branch.getCompany();
            Employee createdBy = resume.getCreatedBy();
            ResumeDTO resumeDTO = new ResumeDTO();
            resumeDTO.setId(resume.getId());
            resumeDTO.setName(resume.getName());
            resumeDTO.setContactNo(resume.getContactNo());
            resumeDTO.setEmail(resume.getEmail());
            resumeDTO.setExperience(resume.getExperience());
            resumeDTO.setKeySkills(resume.getKeySkills());
            resumeDTO.setCurrentCompany(resume.getCurrentCompany());
            resumeDTO.setCurrentLocation(resume.getCurrentLocation());
            resumeDTO.setCurrentSalary(resume.getCurrentSalary());
            resumeDTO.setCurrentDesignation(resume.getCurrentDesignation());
            resumeDTO.setRemarks(resume.getRemarks());
            String content = new String(resume.getResumeDocument().getContent().getBytes());
            if(!content.equalsIgnoreCase("CONTENT_NOT_AVAILABLE")){
                resumeDTO.setResumeContent(DecisionType.YES.name().getBytes());
            }
            if(resume.getDateOfBirth() != null){
                resumeDTO.setDateOfBirth(DateUtil.getYYYYMMDDFromDate(resume.getDateOfBirth(), "-"));
            }
            if(branch != null){
                resumeDTO.setBranchId(branch.getId());
                resumeDTO.setBranchName(branch.getBranchDetails().getBranchName());
            }
            if(company != null){
                resumeDTO.setCompanyId(company.getId());
                resumeDTO.setCompanyName(company.getCompanyName());
            }
            if(createdBy != null){
                resumeDTO.setCreatedById(createdBy.getId());
                resumeDTO.setCreatedByName(createdBy.getFirstName()+" "+createdBy.getMiddleInitial()+" "+createdBy.getLastName());
            }
            if(resume.getCreatedAt() != null){
                resumeDTO.setCreatedAt(DateUtil.getDateTimeString(resume.getCreatedAt().getTime()));
            }
            resumeDTOList.add(resumeDTO);
        }
        return resumeDTOList;
    }
    
     @Override
    public List<ResumeDTO> advanceSearch(AdvanceSearchResumeDTO searchDTO) {
        List<Resume> resumeList = resumeDAO.advanceSearch(searchDTO);
        List<ResumeDTO> resumeDTOList = new ArrayList<ResumeDTO>();
        for(Resume resume: resumeList){
            Branchh branch = resume.getBranch();
            Company company = branch.getCompany();
            Employee createdBy = resume.getCreatedBy();
            ResumeDTO resumeDTO = new ResumeDTO();
            resumeDTO.setId(resume.getId());
            resumeDTO.setName(resume.getName());
            resumeDTO.setContactNo(resume.getContactNo());
            resumeDTO.setEmail(resume.getEmail());
            resumeDTO.setExperience(resume.getExperience());
            resumeDTO.setKeySkills(resume.getKeySkills());
            resumeDTO.setCurrentCompany(resume.getCurrentCompany());
            resumeDTO.setCurrentLocation(resume.getCurrentLocation());
            resumeDTO.setCurrentSalary(resume.getCurrentSalary());
            resumeDTO.setCurrentDesignation(resume.getCurrentDesignation());
            resumeDTO.setRemarks(resume.getRemarks());
            String content = new String(resume.getResumeDocument().getContent().getBytes());
            if(!content.equalsIgnoreCase("CONTENT_NOT_AVAILABLE")){
                resumeDTO.setResumeContent(DecisionType.YES.name().getBytes());
            }
            if(resume.getDateOfBirth() != null){
                resumeDTO.setDateOfBirth(DateUtil.getYYYYMMDDFromDate(resume.getDateOfBirth(), "-"));
            }
            if(branch != null){
                resumeDTO.setBranchId(branch.getId());
                resumeDTO.setBranchName(branch.getBranchDetails().getBranchName());
            }
            if(company != null){
                resumeDTO.setCompanyId(company.getId());
                resumeDTO.setCompanyName(company.getCompanyName());
            }
            if(createdBy != null){
                resumeDTO.setCreatedById(createdBy.getId());
                resumeDTO.setCreatedByName(createdBy.getFirstName()+" "+createdBy.getMiddleInitial()+" "+createdBy.getLastName());
            }
            if(resume.getCreatedAt() != null){
                resumeDTO.setCreatedAt(DateUtil.getDateTimeString(resume.getCreatedAt().getTime()));
            }
            resumeDTOList.add(resumeDTO);
        }
        return resumeDTOList;
    }

    @Override
    public boolean uploadResume(FileUploadBean fileUploadBean, String fileName,
                                        HttpServletRequest request) {
        
        String path = ApplicationConstraints.getResumeFolderLocation(request);
        File folder = new File(path);
        if(folder.exists() == false){
            folder.mkdirs();
        }
        
        try {
            MultipartFile file = fileUploadBean.getFile();
            path = path+"/"+fileName+".doc";
            InputStream inputStream = null;
            OutputStream outputStream = null;
            if (file.getSize() > 0) {
                    inputStream = file.getInputStream();
                    if (file.getSize() > 10000) {
                            //return "/uploadfile";
                    }
                    outputStream = new FileOutputStream(path);
                    int readBytes = 0;
                    byte[] buffer = new byte[10000];
                    while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
                            outputStream.write(buffer, 0, readBytes);
                    }
                    outputStream.close();
                    inputStream.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        String content = null;
        try {
            content = this.convertWordToHTML(path);
        } catch (IOException ex) {
            Logger.getLogger(ResumeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ResumeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(ResumeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(ResumeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       return resumeDAO.uploadResume(fileUploadBean.getResumeId(), content);
    }

    

    @Override
    public String convertWordToHTML(String wordFilePath) throws IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException{
        HWPFDocumentCore wordDocument = WordToHtmlUtils.loadDoc(new FileInputStream(wordFilePath));
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder()
                        .newDocument());
        wordToHtmlConverter.processDocument(wordDocument);
        Document htmlDocument = wordToHtmlConverter.getDocument();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(out);

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
        out.close();
        String result = new String(out.toByteArray());
        return result;
    }

    @Override
    public boolean isExistingResume(String emailId, String contactNo, Long branchId) {
        
        if(resumeDAO.findBySearchKey(contactNo, branchId).size() > 0){
            return true;
        }
        if(resumeDAO.findBySearchKey(emailId, branchId).size() > 0){
            return true;
        }
        else{
            return false;
        }
    }

}
