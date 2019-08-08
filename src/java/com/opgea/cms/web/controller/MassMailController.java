/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.web.controller;

import com.opgea.cms.domain.modal.JsonModelMap;
import com.opgea.cms.domain.modal.MailModel;
import com.opgea.cms.domain.modal.SessionData;
import com.opgea.cms.service.MassMailService;
import com.opgea.cms.service.mail.MailService;
import com.opgea.cms.web.dto.MassMailDTO;
import com.opgea.constraints.SessionConstraints;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Ramesh
 */
@Controller
@RequestMapping(value="/massMail")
public class MassMailController {
    
    @Autowired
    private MailService mailService;
    @Autowired
    private MassMailService massMailService;
    
    /*
     * 
     * 
     * Comment
     */
    @RequestMapping(method= RequestMethod.POST, value="create")
    public @ResponseBody Map<String, Object> create(
                                    @RequestParam(value="openingId") Long openingId,
                                    HttpServletRequest request,
                                    MailModel mailModel){

        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        String ids[] = mailModel.getTo().split(",");
        List<MassMailDTO> massMailDTOList = new ArrayList<MassMailDTO>();
        for(String emailId: ids){
            MassMailDTO dto = new MassMailDTO();
            dto.setEmail(emailId);
            //dto.setStatus("NONE");
            massMailDTOList.add(dto);
        }
        massMailService.create(massMailDTOList, openingId, sessionData.getEmpId());
        /*
        try {
            mailService.sendMimeMail(mailModel, sessionData.getBranchId());
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MassMailController.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
         
       return JsonModelMap.success();
    }
    
    
    /*
     * 
     * 
     * Comment
     */
    @RequestMapping(method= RequestMethod.GET, value="listByOpeningId")
    public @ResponseBody Map<String, Object> getListByOpeningId(
                                    @RequestParam(value="openingId") Long openingId,
                                    HttpServletRequest request){

                
        List<MassMailDTO> massMailDTOList = massMailService.findAllByOpeningId(openingId);
        System.out.println("MassMailDTOLIST: "+massMailDTOList);
        return JsonModelMap.success().data(massMailDTOList);
    }
    
    /*
     * 
     * 
     * Comment
     */
    @RequestMapping(method= RequestMethod.GET, value="listByOpeningAndSenderId")
    public @ResponseBody Map<String, Object> getListByOpeningAndSenderId(
                                    @RequestParam(value="openingId") Long openingId,
                                    @RequestParam(value="senderId") Long senderId,
                                    HttpServletRequest request){

                
        List<MassMailDTO> massMailDTOList = massMailService.findAllByOpeningAndSenderId(openingId, senderId);
        System.out.println("MassMailDTOLIST: "+massMailDTOList);
        return JsonModelMap.success().data(massMailDTOList);
    }
}
