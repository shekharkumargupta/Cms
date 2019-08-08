/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.web.controller;

import com.opgea.constraints.SessionConstraints;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Ramesh
 */
@Controller
@RequestMapping(value="home")
public class HomeController {
    
    @RequestMapping(method= RequestMethod.GET)
    public String show(HttpServletRequest request){
        
        HttpSession session = request.getSession();
        String targetPage = "redirect: login";
        if(session.getAttribute(SessionConstraints.SESSION_DATA.name()) != null){
            targetPage = "home";
        }
        return targetPage;
    }
}
