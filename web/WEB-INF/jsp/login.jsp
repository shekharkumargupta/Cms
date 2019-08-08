<%-- 
    Document   : company
    Created on : Aug 9, 2011, 12:57:57 AM
    Author     : Ramesh
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OPGEA</title>
        <link rel="stylesheet" type="text/css" href="<spring:url value="/resources/ext-4.0/resources/css/ext-all.css" />" type="text/css" media="all"/>
        <script type="text/javascript" src="<spring:url value="/resources/ext-4.0/ext-all-debug.js"/>" ></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/login/LoginForm.js"/>"></script>
        
        
        
        <script type="text/javascript">
            Ext.onReady(function(){
                Ext.Loader.setConfig({enabled:true});
                Ext.create("Cms.view.login.LoginForm",{
                            title: 'Login',
                            renderTo: 'loginForm',
                            url: 'login/verify',
                            frame: false
                    });
            });
        </script>

        <style type="text/css">
            .clock { 
                background-color: royalblue;
                color: white;
                
                font-family: 'verdana';
                font-size: 20px;
                font-weight: bolder;
                
                text-align: center;
                padding: 7px;
            }
            .topDiv { 
                height: 25px;
                background-color: royalblue;
                color: white;
                font-family: 'verdana';
                font-size: 12px;
                font-weight: bolder;
                text-align: right;
                padding-top: 4px;
                padding-right: 7px;
            }
            .loginForm{
                margin-left: 20px;
                margin-top: 20px;
                padding-right: 10%;
                width: 40%;
            }
        </style>        
    </head>
    <body>
        <div id="topDiv" class="topDiv">
            <a href="company" style="color: white;">Create Account</a>
            &nbsp; | &nbsp;
            <a href="<spring:url value="/images/Recruitment_Management.pdf"/>" style="color: white;">
                 Guideline
            </a>
        </div>                        
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <table width="100%">
            <tr>
                <td align="center">
                    <a href="http://www.opgea.com">
                        <img src="<spring:url value="/images/opgea_logo.jpg"/>" height="150" width="250" alt="opgea"/>
                    </a>
                </td>
                <td id="loginForm" class="loginForm"></td>
            </tr>
            <tr>
                <td>
                    <br>
                </td>
            </tr>
        </table>
        <br/>
        <br/>
        <br/>
        <br/>       
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <hr>
        <table width="100%">
            <tr>
                <td>
                    </br>
                </td>
                <td align="center">
                    <font face="calibri" size="3">
                    Powered By: OPGEA
                    </br>
                    Contact: 0120-4137653, +91-9868351070
                    </font>
                </td>
            </tr>
        </table>
        
        
            
            
    </body>
</html>
