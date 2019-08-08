/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.web.controller;

import com.opgea.cms.domain.modal.FileUploadBean;
import com.opgea.cms.domain.modal.JsonModelMap;
import com.opgea.cms.domain.modal.SessionData;
import com.opgea.cms.domain.qualifiers.ConditionType;
import com.opgea.cms.domain.qualifiers.DecisionType;
import com.opgea.cms.service.PipelineService;
import com.opgea.cms.service.ResumeService;
import com.opgea.cms.web.dto.AdvanceSearchResumeDTO;
import com.opgea.cms.web.dto.ResumeDTO;
import com.opgea.constraints.ApplicationConstraints;
import com.opgea.constraints.SessionConstraints;
import com.opgea.util.ExcelUtil;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 *
 * @author Ramesh
 */
@Controller
@RequestMapping(value="resume")     
public class ResumeController {
    
    
    private static final String FILE_UPLOAD_SUCCESS_RESPONSE = 
		"{\"success\":%s,\"message\":\"%s\",\"fileData\":{\"size\":%s," +
		" \"name\":\"%s\", \"type\":\"%s\"}}";

    protected String buildSuccessFileUploadResonse(String message, long fileSize, String fileName, String fileType){
            return String.format(FILE_UPLOAD_SUCCESS_RESPONSE, Boolean.TRUE, message,fileSize,fileName,fileType);
    }
    
    
    
    @Autowired
    private ResumeService resumeService;
    
    @Autowired
    private PipelineService pipelineService;
    
    /*
     * 
     * 
     * Comment
     */
    @RequestMapping(method= RequestMethod.POST, value="create")
    public @ResponseBody Map<String, Object> create(ResumeDTO resumeDTO, HttpServletRequest request){

        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        resumeDTO.setBranchId(sessionData.getBranchId());
        resumeDTO.setUpdatedById(sessionData.getEmpId());
        resumeService.create(resumeDTO);
        System.out.println("ResumeController >> "+resumeDTO.getId());
        return JsonModelMap.success().data(resumeDTO);
    }
    
    /*
     * 
     * 
     * Comment
     */
    @RequestMapping(method= RequestMethod.POST, value="createBatch")
    public @ResponseBody String uploadExcel(FileUploadBean fileUploadBean, 
                                            HttpServletRequest request,
                                            HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        
        List<ResumeDTO> resumeDTOList = ExcelUtil.getResumeDetailsFromExcel(fileUploadBean);
        int totalCreated = resumeService.createBatch(resumeDTOList, sessionData.getBranchId(), sessionData.getEmpId());

        StringBuilder emailIds = new StringBuilder("'0'");
        for(ResumeDTO resumeDTO : resumeDTOList){
            emailIds.append(",");
            emailIds.append('\'');
            emailIds.append(resumeDTO.getEmail());
            emailIds.append('\'');
        }
        
        response.setContentType("text/html");
        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);
        Writer out = responseWrapper.getWriter();
        String json = buildSuccessFileUploadResonse(emailIds.toString(),fileUploadBean.getFile().getSize(),
                                                                        fileUploadBean.getFile().getOriginalFilename(),
                                                                        fileUploadBean.getFile().getContentType());
        out.write(json);
        out.close();
        return null;
    }
    
    /*
     * 
     * 
     * Comment
     */
    @RequestMapping(method = RequestMethod.POST, value = "uploadResume")
    public String uploadResume(@RequestParam(value="resumeId") Long resumeId,
                                    FileUploadBean fileUploadBean,
                                    HttpServletRequest request,
                                    HttpServletResponse response)throws IOException{
         
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());

        fileUploadBean.setResumeId(resumeId);
        resumeService.uploadResume(fileUploadBean, resumeId.toString(), request);
        
        response.setContentType("text/html");
        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);
        Writer out = responseWrapper.getWriter();
        String json = buildSuccessFileUploadResonse("File uploaded Successfully", 
                                                                        fileUploadBean.getFile().getSize(),
                                                                        fileUploadBean.getFile().getOriginalFilename(),
                                                                        fileUploadBean.getFile().getContentType());
        out.write(json);
        out.close();

        return null;
    }

    /*
     * 
     * 
     * Comment
     */
    @RequestMapping(method= RequestMethod.GET, value="searchedResumeList")
    public @ResponseBody Map<String, Object> searchResume(
                                                @RequestParam(value="searchKey", required=false) String searchKey, 
                                                @RequestParam(value="branchId", required=false) Long branchId, 
                                                HttpServletRequest request){
        List<ResumeDTO> resumeList = resumeService.findBySearchKey(searchKey, branchId);
        return JsonModelMap.success().data(resumeList);
    }   
    
    /*
     * 
     * 
     * Comment
     */
    @RequestMapping(method= RequestMethod.GET, value="advanceSearch")
    public @ResponseBody Map<String, Object> advanceSearchResume(
                                                @RequestParam(value="branchId") Long branchId,
                                                @RequestParam(value="keySkills") String keySkills,
                                                @RequestParam(value="keySkillsLogic") Integer keySkillsLogic,
                                                @RequestParam(value="designation")String designation,
                                                @RequestParam(value="designationLogic")Integer designationLogic,
                                                @RequestParam(value="qualification") String qualification,
                                                @RequestParam(value="qualificationLogic") Integer qualificationLogic,
                                                @RequestParam(value="location") String location,
                                                @RequestParam(value="locationLogic") Integer locationLogic,
                                                @RequestParam(value="minSalary") Long minSalary,
                                                @RequestParam(value="maxSalary") Long maxSalary,
                                                @RequestParam(value="minExp") Long minExp,
                                                @RequestParam(value="maxExp") Long maxExp){
        
        AdvanceSearchResumeDTO searchDTO = new AdvanceSearchResumeDTO();
        
        searchDTO.setBranchId(branchId);
        searchDTO.setKeySkills(keySkills);
        if(designationLogic != null){
            searchDTO.setDesignationLogic(ConditionType.values()[designationLogic]);
        }
        if(designationLogic == null){
            searchDTO.setDesignationLogic(ConditionType.OR);
        }
        if(keySkillsLogic != null){
            searchDTO.setKeySkillsLogic(ConditionType.values()[keySkillsLogic]);
        }
        if(keySkillsLogic == null){
            searchDTO.setKeySkillsLogic(ConditionType.OR);
        }
        if(locationLogic != null){
            searchDTO.setLocationLogic(ConditionType.values()[locationLogic]);
        }
        if(locationLogic == null){
            searchDTO.setLocationLogic(ConditionType.OR);
        }
        if(qualificationLogic != null){
            searchDTO.setQualificationLogic(ConditionType.values()[qualificationLogic]);
        }
        if(qualificationLogic == null){
            searchDTO.setQualificationLogic(ConditionType.OR);
        }
        searchDTO.setCurrentDesignation(designation);
        searchDTO.setLocation(location);
        searchDTO.setQualification(qualification);
        searchDTO.setMinSalary(minSalary);
        searchDTO.setMaxSalary(maxSalary);
        searchDTO.setMinExp(minExp);
        searchDTO.setMaxExp(maxExp);
        System.out.println("Advance SearchDTO: "+searchDTO);
        List<ResumeDTO> resumeList = resumeService.advanceSearch(searchDTO);

        return JsonModelMap.success().data(resumeList);
    }
    
    /*
     * 
     * 
     * Comment
     */
    @RequestMapping(method= RequestMethod.GET, value="resumeListByOpeningId")
    public @ResponseBody Map<String, Object> resumeListByOpeningId(@RequestParam(value="openingId")
                                                Long openingId, HttpServletRequest request){

        List<ResumeDTO> resumeList = resumeService.findAllByOpeningId(openingId);
        return JsonModelMap.success().data(resumeList);
    }
    
    /*
     * 
     * 
     * Comment
     */
    @RequestMapping(method= RequestMethod.GET, value="resumeListByEmailIds")
    public @ResponseBody Map<String, Object> resumeListInIds(@RequestParam(value="emailIds")
                                                String resumeIds, HttpServletRequest request){

        List<ResumeDTO> resumeList = resumeService.findAllByEmailIds(resumeIds);
        return JsonModelMap.success().data(resumeList);
    }
    
    /*
     * 
     * 
     * This method is not being currently used.
     */
    @RequestMapping(value="viewResume/{resumeId}", method=RequestMethod.GET)
    public void viewResume(@PathVariable Long resumeId,   HttpServletResponse response){
        
        ResumeDTO resumeDTO = resumeService.find(resumeId);
        byte[] content = resumeDTO.getResumeContent();
        
        String name = resumeDTO.getId()+"_"+resumeDTO.getName();
        response.setContentType("image/jpg");
        response.setContentLength(content.length);
 
        response.setHeader("Content-Disposition", "inline; filename=\"" + name
                + "\"");
 
        BufferedInputStream input = null;
        BufferedOutputStream output = null;
 
        try {
            input = new BufferedInputStream(new ByteArrayInputStream(content));
            //input = new BufferedInputStream(new CharSequenceInputStream(content.toString(), Charset.defaultCharset()));
            output = new BufferedOutputStream(response.getOutputStream());
            byte[] buffer = new byte[8192];
            int length;

            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
        } catch (IOException e) {
            System.out.println("There are errors in reading/writing image stream "
                    + e.getMessage());
        } finally {
            if (output != null)
                try {
                    output.close();
                } catch (IOException ignore) {
                }
            if (input != null)
                try {
                    input.close();
                } catch (IOException ignore) {
                }
        }
    }
    
    /*
     * 
     * 
     * Comment
     */
    @RequestMapping(value="showResume", method=RequestMethod.GET)
    public @ResponseBody 
            Map<String, Object> showResume(@RequestParam(value="resumeId") Long resumeId, 
                                            HttpServletRequest request) throws IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException{
        
        String path = ApplicationConstraints.getResumeFolderLocation(request)+"/"+resumeId+".doc";
        String result = resumeService.convertWordToHTML(path);
        
        return JsonModelMap.success().add("data", result);
    }
    
    
    
    
    /*
     * 
     * 
     * Comment
     */
    @RequestMapping(value="showResumeByEmailId", method=RequestMethod.GET)
    public @ResponseBody 
            Map<String, Object> showResume(@RequestParam(value="emailId") String emailId, 
                                            HttpServletRequest request) throws IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException{
        
        ResumeDTO resumeDTO = resumeService.find(emailId);
        String path = ApplicationConstraints.getResumeFolderLocation(request)+"/"+resumeDTO.getId()+".doc";
        String result = resumeService.convertWordToHTML(path);
        
        //return JsonModelMap.success().add("data", result);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("success","true");
        dataMap.put("data", result);
        dataMap.put("resumeId", resumeDTO.getId());
        dataMap.put("branchId", resumeDTO.getBranchId());
        dataMap.put("companyId", resumeDTO.getCompanyId());
        dataMap.put("name", resumeDTO.getName());
        dataMap.put("remarks",resumeDTO.getRemarks());
        
        return dataMap;
    }
    
    
    /*
     * 
     * 
     * Comment
     */
    @RequestMapping(value="isExistingResume", method= RequestMethod.GET)
    public @ResponseBody 
    Map<String, Object> isExistingResume(@RequestParam(value="emailId", required=false)String emailId,
                                         @RequestParam(value="contactNo", required=false)String contactNo,
                                         HttpServletRequest request){
        
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        
        if(resumeService.isExistingResume(emailId, contactNo, sessionData.getBranchId()) == true){
            return JsonModelMap.success().data(DecisionType.YES.name());
        }else{
            return JsonModelMap.success().data(DecisionType.NO.name());
        }
    }
    
    /*
     * 
     * Comment
     */
    @RequestMapping(value="isResumeAlreadyPipelined", method= RequestMethod.GET)
    public @ResponseBody 
    Map<String, Object> isExistingResume(@RequestParam(value="resumeId", required=false)Long resumeId,
                                         @RequestParam(value="openingId", required=false)Long openingId,
                                         HttpServletRequest request){
        
        if(pipelineService.isResumeAlreadyPipelined(resumeId, openingId).size() > 0){
            return JsonModelMap.success().data("YES");
        }else{
            return JsonModelMap.success().data("NO");
        }
    }
}
