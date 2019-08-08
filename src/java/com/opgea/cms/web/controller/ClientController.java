/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.web.controller;

import com.opgea.cms.domain.modal.JsonModelMap;
import com.opgea.cms.domain.modal.SessionData;
import com.opgea.cms.service.ClientService;
import com.opgea.cms.web.dto.ClientDTO;
import com.opgea.constraints.SessionConstraints;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Ramesh
 */
@Controller
@RequestMapping(value="client")
public class ClientController {
    
    @Autowired
    private ClientService clientService;
    
    @RequestMapping(method= RequestMethod.POST, value="create")
    public @ResponseBody Map<String, Object> create(ClientDTO clientDTO, HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        clientDTO.setCompanyId(sessionData.getCompanyId());
        clientService.create(clientDTO);
        
        return JsonModelMap.success().data(clientDTO);
    }
    
    
    
    @RequestMapping(method= RequestMethod.GET, value="clientList")
    public @ResponseBody Map<String, Object> clientList(HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        List<ClientDTO> clientDTOList = clientService.findAllByCompanyId(sessionData.getCompanyId());
        
        return JsonModelMap.success().data(clientDTOList);
    }
    
    
    /*
    @RequestMapping(method= RequestMethod.POST, value="addContact")
    public @ResponseBody Map<String, Object> addContact(@RequestParam(value="clientId")Long clientId, ClientContactDTO clientContactDTO){
        clientService.addContact(clientId, clientContactDTO);
        return JsonModelMap.success().data(clientContactDTO);
    }
    
    
    @RequestMapping(method= RequestMethod.GET, value="contactList")
    public @ResponseBody Map<String, Object> contactList(@RequestParam(value="clientId")Long clientId, HttpServletRequest request){
        HttpSession session = request.getSession();
        List<ClientContactDTO> clientContactDTOList = clientService.getContactList(clientId);
        return JsonModelMap.success().data(clientContactDTOList);
    }
     * 
     */
    
}
