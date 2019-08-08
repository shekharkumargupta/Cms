<%-- 
    Document   : home
    Created on : Dec 23, 2011, 10:03:17 PM
    Author     : Ramesh
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <title>OPGEA</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" type="text/css" href="<spring:url value="/resources/cms/css/clock.css" />" type="text/css" media="all"/>
        <link rel="stylesheet" type="text/css" href="<spring:url value="/resources/ext-4.0/resources/css/ext-all.css" />" type="text/css" media="all"/>
        <script type="text/javascript" src="<spring:url value="/resources/ext-4.0/ext-all-debug.js"/>" ></script>
        <!--
        <script type="text/javascript" src="<spring:url value="/resources/extjs-upload-widget/lib/upload/Item.js"/>" ></script>
        -->
        
        <!--UI Imports-->
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/viewport/MainViewport.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/company/CompanyBasicInfo.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/branch/Branch.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/designation/Designation.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/workstation/Workstation.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/employee/EmployeeInfo.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/employee/EmployeeAndOpening.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/team/Team.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/team/TeamAndOpening.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/category/CategorySetup.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/client/Client.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/client/ClientSearch.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/opening/Opening.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/opening/OpeningByEmployee.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/opening/OpeningDetails.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/opening/OpeningAllotment.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/resume/ResumeBoard.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/resume/ResumeSearch.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/resume/AdvanceResumeSearch.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/resume/ResumeView.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/resume/UploadResume.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/resume/UploadDocument.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/pipeline/PipelineResume.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/pipeline/PipelineStatus.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/pipeline/PipelineStage.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/pipeline/PipelineHistory.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/email/TemplateMessage.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/email/EmailConfig.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/login/PasswordChange.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/massmail/MassMail.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/massmail/MassMailStatus.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/resume/ExcelUpload.js"/>"></script>
        
        <!-- Employee Report -->
        <script type="text/javascript" src="<spring:url value="/resources/cms/view/report/EmployeeReport.js"/>"></script>
        

        <!--Util Imports-->
        <script type="text/javascript" src="<spring:url value="/resources/cms/utiljs/BusinessLogicValidation.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/utiljs/ExtJSUtil.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/utiljs/Util.js"/>"></script>
        
        
        <!--Store Imports-->
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/DynamicMenuStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/CountryStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/CityStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/CategoryStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/BranchTypeStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/BranchStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/DesignationStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/WorkstationStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/EmployeeStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/EmployeeTypeStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/EmployeeOpeningStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/TeamStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/TeamMemberStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/TeamOpeningStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/ClientStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/ContactTypeStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/ConditionTypeStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/ContactStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/ContactByOpeningStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/PositionStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/QualificationStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/OpeningStatusStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/OpeningStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/OpeningByTeamStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/OpeningByEmployeeStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/ResumeStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/AdvanceResumeStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/PipelineStageStore.js"/>"></script>        
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/PipelineHistoryStore.js"/>"></script>        
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/SalaryInLacsStore.js"/>"></script>        
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/SalaryInThousandsStore.js"/>"></script>        
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/ExpInYearStore.js"/>"></script>        
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/ExpInMonthStore.js"/>"></script>        
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/DocumentTypeStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/DocumentStore.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/store/MassMailStore.js"/>"></script>
        
        
        <!--Modal Imports-->
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/model/BranchModel.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/data/model/SimpleModel.js"/>"></script>
        
        <!-- Chart -->
        <script type="text/javascript" src="<spring:url value="/resources/cms/chart/pipeline/EmployeeWisePipelinedChart.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/cms/chart/pipeline/CountPipelineStore.js"/>"></script>
        
        <style type="text/css">
            .startMenuIcon{
                background-image: url(../images/om_start.jpg);
            }
            .userIcon{
                background-image: url(../images/user.gif);
            }
            .activeUserIcon{
                background-image: url(../images/user_green.gif);
            }
            .teamIcon{
                background-image: url(../images/team.png);
            }
            .settingIcon{
                background-image: url(../images/gears.gif);
            }
            .toolsIcon{
                background-image: url(../images/cog.png);
            }
            .greenBallIcon{
                background-image: url(../images/green_ball.jpg);
            }
            .redBallIcon{
                background-image: url(../images/red_ball.jpg);
            }
            .logoutIcon{
                background-image: url(../images/icon_padlock.png);
            }
            .addIcon{
                background-image: url(../images/add.gif);
            }
            .saveIcon{
                background-image: url(../images/disk.png);
            }
            .removeIcon{
                background-image: url(../images/delete.gif);
            }
            .folderIcon{
                background-image: url(../images/folder_go.gif);
            }
            .monitorIcon{
                background-image: url(../images/monitor.gif);
            }
            .notepadIcon{
                background-image: url(../images/notepad.gif);
            }
            .bookIcon{
                background-image: url(../images/book.png);
            }
            .reasonIcon{
                background-image: url(../images/reason.gif);
            }
            .timeInIcon{
                background-image: url(../images/time_in.gif);
            }
            .timeOutIcon{
                background-image: url(../images/time_out.gif);
            }
            .tableRefreshIcon{
                background-image: url(../images/table_refresh.png);
            }
            .branchIcon{
                background-image: url(../images/branch.jpg);
            }
            .clientIcon{
                background-image: url(../images/factory.jpg);
            }
            .clientContactIcon{
                background-image: url(../images/user_female.gif);
            }
            .pipelineProcessingIcon{
                background-image: url(../images/email_go.png);
            }
            .openingIcon{
                background-image: url(../images/classical_speaker.jpg);
            }
            .openingListIcon{
                background-image: url(../images/grid.png);
            }
            .assignToTeamEmployee{
                background-image: url(../images/forward1.gif);
            }
            .assignToTeamIcon{
                background-image: url(../images/forward2.gif);
            }
            .managerIcon{
                background-image: url(../images/manager.png);
            }
            .wordIcon{
                background-image: url(../images/word.jpg);
            }
            .excelIcon{
                background-image: url(../images/excel.gif);
            }
            .calendarIcon{
                background-image: url(../images/calendar.gif);
            }
            .reportsIcon{
                background-image: url(../images/reports.gif);
            }
            .searchIcon{
                background-image: url(../images/search.jpg);
            }
            .uploadIcon{
                background-image: url(../images/upload.jpg);
            }
            .downloadIcon{
                background-image: url(../images/download.jpg);
            }
            .performanceReportsIcon{
                background-image: url(../images/bar_chart.jpg);
            }
            
            .x-action-col-cell img
            {
              width: 20px;
              height: 20px;
              margin-top: 3px;
              margin-right: 3px;
              cursor: pointer;
              background-repeat: no-repeat;
              background-position: center;
            }

            
            .x-action-col-cell img.WORD_ICON
            {
              background-image: url(../images/WORD_ICON.jpg);
            }

            .x-action-col-cell img.BLANK_ICON
            {
              background-image: url(../images/BLANK_ICON.jpg);
            }
        </style>

        <script type="text/javascript">
            Ext.onReady(function(){
                Ext.Loader.setConfig({enabled:true});
                Ext.Loader.setPath({
                    'Ext.ux.upload' : '/Cms/resources/extjs-upload-widget/lib/upload'
                });
                
                var p = Ext.create("Cms.view.viewport.MainViewport",{
                });
                p.getLoginInfo();
                Ext.QuickTips.init();
            });
        </script>
        
    </head>
    <body>
    </body>
</html>
