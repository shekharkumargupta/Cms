/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.constraints;

import com.opgea.cms.domain.modal.SessionData;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ramesh
 */
public class ApplicationConstraints {
    
    public static String getResumeFolderLocation(HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());

        String path=request.getRealPath("")+"/resumes/"+sessionData.getCompanyId()+"/"+sessionData.getBranchId();
        return path;
    }
}
