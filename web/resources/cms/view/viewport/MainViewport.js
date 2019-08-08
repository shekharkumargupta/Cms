Ext.define('Cms.view.viewport.MainViewport', {
    extend: 'Ext.container.Viewport',
    id: 'mainViewport',
    padding: 0,
    layout: {
        type: 'border'
    },
    
    showComponent: function(component){
        component.closable = true;
        
        var tabContainer = Ext.getCmp('centerTabPanel');
        tabContainer.add(component);
        tabContainer.setActiveTab(component);
    },
    
    getLoginInfo: function(){
      Ext.Ajax.request({
        url: 'login/loginInfo',
        method: 'GET',
        success: function(result, request){
            var responseData = result.responseText;
            var profileButton = Ext.getCmp('mainProfileButton');
            var jsonData = Ext.JSON.decode(responseData); 
            //profileButton.setText(jsonData.data.loginId);
            profileButton.setText(jsonData.data.loginId+" ("+jsonData.data.employeeTypeName+") ");
            setAutenticationData(jsonData); //this methos is in Util.js
        },
        failure: function(form, action){
               if(action.failureType == Ext.form.Action.CLIENT_INVALID){
                   Ext.Msg.alert("Cannot Submit", "Some fields are still invalid! ");
               }
               if(action.failureType == Ext.form.Action.CONNECT_FAILURE){
                   Ext.Msg.alert("Failure","Server communication failure: "+
                   action.response.status+' '+action.response.statusText);
               }
               if(action.failuretype == Ext.form.Action.SERVER_INVALID){
                   Ext.Mst.alert("Warning", "action.result.errormsg");
               }
         }
      }); 
    },
    
    createMenuTree : function(){
        var dynamicMenuStore = Ext.create('Cms.data.store.DynamicMenuStore',{
        });
        var tree = new Ext.create('Ext.tree.Panel', {
            id:'menuTree',
            title: 'Actions',
            rootVisible:false,
            region: 'west',
            lines:false,
            width: 200,
            split:true,
            autoScroll:true,
            useArrows:true,
            collapsible: true,
            animCollapse: true,
            collapsibleDirection: true,
            store: dynamicMenuStore,
            listeners:{
                itemclick: function(view,rec,item,index,eventObj){
                    var component = null;
                    var viewport = Ext.getCmp('userMainViewport');
                    
                    if(rec.get('id') == 1){
                         component = Ext.create('Cms.view.branch.Branch',{
                            title: 'Branch',
                            iconCls: 'userIcon',
                            closable: true
                        });
                    }
                    if(rec.get('id') == 2){
                        component = Ext.create('Cms.view.designation.Designation',{
                            title: 'Designation',
                            iconCls: 'bookIcon',
                            closable: true
                        });
                    }
                    if(rec.get('id') == 3){
                        component = Ext.create('Cms.view.pipeline.PipelineStage',{
                            title: 'Pipeline Stage',
                            iconCls: 'monitorIcon',
                            closable: true
                        });
                    }
                    if(rec.get('id') == 4){
                        component = Ext.create('Cms.view.employee.EmployeeInfo',{
                            title: 'Employee Information',
                            iconCls: 'userIcon',
                            closable: true
                        });
                        //component.setFormVisible(true);
                        //component.setRequestActionVisible(false);
                    }
                    if(rec.get('id') == 5){
                       if(Ext.getCmp('teamForm') == null){ 
                           component = Ext.create('Cms.view.team.Team',{
                                title: 'Team',
                                iconCls: 'teamIcon',
                                closable: true
                            });
                        }
                    }
                    if(rec.get('id') == 6){
                           component = Ext.create('Cms.view.client.Client',{
                                title: 'Client',
                                iconCls: 'clientContactIcon',
                                closable: true
                            });
                    }
                    if(rec.get('id') == 7){
                        component = Ext.create('Cms.view.opening.Opening',{
                            title: 'Opening',
                            height: '100%',
                            iconCls: 'openingListIcon',
                            closable: true
                        });
                    }
                    if(rec.get('id') == 8){
                        component = Ext.create('Cms.view.opening.OpeningAllotment',{
                            title: 'Job Allotment',
                            height: '100%',
                            iconCls: 'assignToTeamIcon',
                            closable: true
                        });
                    }
                    if(rec.get('id') == 9){
                        component = Ext.create('Cms.view.resume.ResumeBoard',{
                            title: 'Resume Board',
                            height: '100%',
                            iconCls: 'notepadIcon',
                            closable: true
                        });
                    }
                    if(rec.get('id') == 10){
                        component = Ext.create('Cms.view.resume.AdvanceResumeSearch',{
                            title: 'Advance Search',
                            height: '100%',
                            iconCls: 'wordIcon',
                            closable: true
                        });
                    }
                    if(rec.get('id') == 11){
                        component = Ext.create('Cms.view.pipeline.PipelineStatus',{
                            title: 'Pipeline Status',
                            height: '100%',
                            iconCls: 'greenBallIcon',
                            closable: true
                        });
                    }
                    
                    if(rec.get('id') == 12){
                           component = Ext.create('Cms.view.email.TemplateMessage',{
                                title: 'Email Message',
                                iconCls: 'pipelineProcessingIcon',
                                closable: true
                            });
                    }
                    if(rec.get('id') == 13){
                        component = Ext.create('Cms.view.opening.OpeningByEmployee',{
                            title: 'Opening List',
                            height: '100%',
                            iconCls: 'openingListIcon',
                            closable: true
                        });
                    }
                    if(rec.get('id') == 14){
                        component = Ext.create('Cms.view.report.EmployeeReport',{
                            title: 'Employee Wise Report',
                            height: '100%',
                            iconCls: 'greenBallIcon',
                            closable: true
                        });
                    }
                    if(rec.get('id') == 15){
                        component = Ext.create('Cms.view.email.EmailConfig',{
                            title: 'Email Configuration',
                            height: '100%',
                            iconCls: 'toolsIcon',
                            closable: true
                        });
                        component.loadConfigDetails();
                    }
                    if(rec.get('id') == 16){
                        component = Ext.create('Cms.view.massmail.MassMailStatus',{
                            title: 'Mass Mail Status',
                            height: '100%',
                            iconCls: 'settingIcon',
                            closable: true
                        });
                    }
                    if(rec.get('id') == 17){
                        component = Ext.create('Cms.view.resume.ExcelUpload',{
                            title: 'Excel Upload',
                            height: '100%',
                            iconCls: 'excelIcon',
                            closable: true
                        });
                    }
                    var tabContainer = Ext.getCmp('centerTabPanel');
                    tabContainer.add(component);
                    tabContainer.setActiveTab(component);
                }
            }
        });

        return tree;
    },

    initComponent: function() {
        
        var me = this;
        Ext.applyIf(me, {
            items: [
                me.createMenuTree(),
                {
                    xtype: 'tabpanel',
                    id: 'centerTabPanel',
                    activeTab: 0,
                    region: 'center',
                    items: [
                            {
                                xtype : 'panel',
                                title : 'Dashoard',
                                layout: 'column',
                                padding: 10,
                                autoScroll: true,
                                items: [
                                        {
                                            xtype:'panel',
                                            title: 'StatusReport',
                                            closable: true,
                                            collapsible: true,
                                            animCollapse: true,
                                            margin: '5,5,5,5',
                                            items: [
                                                Ext.create('Cms.chart.pipeline.EmployeeWisePipelinedChart',{
                                                    title: 'Status Chart',
                                                    closable: true
                                                })
                                            ]
                                        }
                                        
                                ]
                            }
                    ]
                },
                {
                    xtype: 'panel',
                    height: 30,
                    title: '',
                    region: 'north',
                    dockedItems: [
                        {
                            xtype: 'toolbar',
                            dock: 'top',
                            items: [
                                {
                                    xtype: 'button',
                                    id: 'mainProfileButton',
                                    text: 'shekharkumargupta@gmail.com',
                                    iconCls: 'activeUserIcon',
                                    handler: function(){
                                        var component = Ext.create('Cms.view.login.PasswordChange',{
                                            //iconCls: 'userIcon',
                                            //closable: true,
                                            height: 125
                                        });
                                        createWindow(component, 'Change Password', 160, 400);
                                    }
                                },
                                {
                                    xtype: 'tbfill'
                                },
                                {
                                    xtype: 'button',
                                    id: 'logoutButton',
                                    text: 'Logout',
                                    iconCls: 'logoutIcon',
                                    handler: function(){
                                        Ext.Ajax.request({
                                            url	: 'login/logout',
                                            method	: 'GET',
                                            success 	: function(){
                                                         Ext.Msg.alert('Confirm', 'Successfully Logout');
                                                         window.location = 'login';
                                                        },
                                            failure	: function(){
                                                         Ext.Msg.alert('Error', 'Failure');
                                                        }			
                                        });

                                    }
                                }
                            ]
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    height: 80,
                    collapsible: true,
                    title: 'Messages',
                    titleCollapse: true,
                    region: 'south',
                    split: true
                }
            ]
        });

        me.callParent(arguments);
    }
});