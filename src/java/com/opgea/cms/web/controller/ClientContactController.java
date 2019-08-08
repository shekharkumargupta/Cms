/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.web.controller;

import com.opgea.cms.domain.modal.JsonModelMap;
import com.opgea.cms.service.ClientContactService;
import com.opgea.cms.web.dto.ClientContactDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping(value="clientContact")
public class ClientContactController {
    
    @Autowired
    private ClientContactService clientContactService;
    
    @RequestMapping(method= RequestMethod.POST, value="addContact")
    public @ResponseBody Map<String, Object> addContact(@RequestParam(value="clientId")Long clientId, ClientContactDTO clientContactDTO){
        clientContactDTO.setClientId(clientId);
        System.out.println("ClientController >> addConact >> Client Id: "+clientId);
        clientContactService.create(clientContactDTO);
        return JsonModelMap.success().data(clientContactDTO);
    }
    
    @RequestMapping(method= RequestMethod.GET, value="contactList")
    public @ResponseBody Map<String, Object> contactList(@RequestParam(value="clientId", required=false)Long clientId, HttpServletRequest request){
        List<ClientContactDTO> clientContactDTOList = clientContactService.findAllByClientId(clientId);
        return JsonModelMap.success().data(clientContactDTOList);
    }
    
    @RequestMapping(method= RequestMethod.GET, value="contactListByOpeningId")
    public @ResponseBody Map<String, Object> contactListByOpeningId(@RequestParam(value="openingId", required=false)Long openingId, HttpServletRequest request){
        if(openingId != 0){
            List<ClientContactDTO> clientContactDTOList = clientContactDTOList = clientContactService.findAllByOpeningId(openingId);
            return JsonModelMap.success().data(clientContactDTOList);
        }else{
            List<ClientContactDTO> clientContactDTOList = new ArrayList<ClientContactDTO>();
            return JsonModelMap.success().data(clientContactDTOList);
        }
    }
    
}
