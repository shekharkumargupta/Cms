/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.web.controller;

import com.opgea.cms.web.dto.CompanyDTO;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.bind.ServletRequestDataBinder;

/**
 *
 * @author Ramesh
 */
public class CompanyControllerTest extends TestCase{
    
    private CompanyDTO companyDTO;
    private ServletRequestDataBinder dataBinder;
    private MockHttpServletRequest request;
    private CompanyController companyController;
    
    
    
    @Override
    protected void setUp() throws Exception{
        companyDTO = new CompanyDTO();
        dataBinder = new ServletRequestDataBinder(companyDTO, "company");
        request = new MockHttpServletRequest();
        companyController = new CompanyController();
    }
    
    @Test
    public void testBinding(){
        request.addParameter("name", "OPGEA");
        request.addParameter("website", "www.opgea.com");
        dataBinder.bind(request);
        assertEquals("OPGEA", companyDTO.getName());
        assertEquals("www.opgea.com", companyDTO.getWebsite());
    }
    
    @Test
    public void testShow(){
        request.setMethod("GET");
        assertEquals("company", companyController.show());
    }
    
}
