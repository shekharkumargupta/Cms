/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.web.controller;

import com.opgea.cms.domain.modal.FileUploadBean;
import com.opgea.cms.domain.modal.JsonModelMap;
import com.opgea.cms.domain.modal.SessionData;
import com.opgea.cms.service.DocumentService;
import com.opgea.cms.web.dto.DocumentDTO;
import com.opgea.constraints.SessionConstraints;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Shekhar
 */
@Controller
@RequestMapping(value="document")
public class DocumentController {
    
    
    private static final String FILE_UPLOAD_SUCCESS_RESPONSE = 
		"{\"success\":%s,\"message\":\"%s\",\"fileData\":{\"size\":%s," +
		" \"name\":\"%s\", \"type\":\"%s\"}}";

    protected String buildSuccessFileUploadResonse(String message, long fileSize, String fileName, String fileType){
		return String.format(FILE_UPLOAD_SUCCESS_RESPONSE, Boolean.TRUE, message,fileSize,fileName,fileType);
	}
    
    
    @Autowired
    private DocumentService documentService;
    
    
    @RequestMapping(method=RequestMethod.POST, value="upload")
    public String upload(@RequestParam(value="resumeId") Long resumeId,
                                                FileUploadBean uploadBean, 
                                                HttpServletRequest request,
                                                HttpServletResponse response) throws IOException{

        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        uploadBean.setResumeId(resumeId);
        uploadBean.setUploadedById(sessionData.getEmpId());
        if(documentService.uploadDocument(uploadBean, request)){
            documentService.create(uploadBean);
        }
        response.setContentType("text/html");
        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);
        Writer out = responseWrapper.getWriter();
        String json = buildSuccessFileUploadResonse("File uploaded Successfully", 
                                                                        uploadBean.getFile().getSize(),
                                                                        uploadBean.getFile().getOriginalFilename(),
                                                                        uploadBean.getFile().getContentType());
        out.write(json);
        out.close();

        return null;
    }
    
    
    @RequestMapping(method=RequestMethod.GET, value="documentByResumeId")
    public @ResponseBody Map<String, Object> getDocumentListByResumeId(
                                    @RequestParam(value="resumeId", required=false) Long resumeId){
        
        List<DocumentDTO> documentDTOList = documentService.findByResumeId(resumeId);
        return JsonModelMap.success().data(documentDTOList);
    }
}
