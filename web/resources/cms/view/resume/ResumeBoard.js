Ext.define('Cms.view.resume.ResumeBoard', {
    extend: 'Ext.panel.Panel',

    autoHeight: true,
    width: '100%', 
    layout: {
        type: 'border'
    },
    title: 'Resume Board',
    
    createWindow: function(extComponent, title, height, width){
        extComponent.region = 'center';
        var win;
        if(!win){
            win = Ext.create('widget.window',{
                title: title,
                layout: 'border',
                closeAction: 'destroy',
                items:[extComponent],
                autoHeight: true,
                width: width,
                modal: true,
                closable:true,
                listeners: {
                    maximize: function(){
                        var viewportHeight = Ext.getCmp('userMainViewport').height;
                        //Ext.Msg.alert('Message',viewportHeight);
                        win.setHeight(viewportHeight);
                        win.doLayout();
                    }
                }
            })
            win.show(this, function(){

            });
        }
    },
    
    initComponent: function() {
        
        var resumeSearch  = Ext.create('Cms.view.resume.ResumeSearch',{
            region: 'center'
        });
       
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'tabpanel',
                    id: 'resumeBoardPanel',
                    activeTab: 0,
                    region: 'center',
                    items: [
                        {
                            xtype: 'panel',
                            layout: {
                                type: 'border'
                            },
                            autoHeight: true,
                            title: 'Resume Entry',
                            items: [
                                {
                                    xtype: 'panel',
                                    width: 320,
                                    collapsible: true,
                                    title: 'Candidate Info',
                                    titleCollapse: true,
                                    region: 'west',
                                    split: true,
                                    items: [
                                        {
                                            xtype: 'form',
                                            id: 'candidateInfoForm',
                                            bodyPadding: 10,
                                            border: false,
                                            //title: 'Hello',
                                            items: [
                                                {
                                                    xtype: 'fieldset',
                                                    title: 'Personal Info',
                                                    border: false,
                                                    items: [
                                                            {
                                                                xtype: 'hidden',
                                                                name: 'id',
                                                                id: 'id',
                                                                value: '0'
                                                            },
                                                            {
                                                                xtype: 'textfield',
                                                                name: 'name',
                                                                fieldLabel: 'Name',
                                                                labelAlign: 'right',
                                                                anchor: '100%',
                                                                allowBlank: false,
                                                                msgTarget: 'under'
                                                            },
                                                            {
                                                                xtype: 'datefield',
                                                                name: 'dateOfBirth',
                                                                fieldLabel: 'Date of Birth',
                                                                labelAlign: 'right',
                                                                format: 'Y-m-d',
                                                                anchor: '100%'
                                                            },
                                                            {
                                                                xtype: 'textfield',
                                                                name: 'email',
                                                                fieldLabel: 'Email Id',
                                                                labelAlign: 'right',
                                                                anchor: '100%',
                                                                allowBlank: false,
                                                                msgTarget: 'under',
                                                                vtype: 'email',
                                                                listeners: {
                                                                    blur : function(){
                                                                        //Ext.Msg.alert('Message','Blur');
                                                                        if(this.value.length > 0){
                                                                            isExistingResume(this.value, '<NULL>');
                                                                        }
                                                                    }
                                                                }
                                                            },
                                                            {
                                                                xtype: 'textfield',
                                                                name: 'contactNo',
                                                                fieldLabel: 'Contact No.',
                                                                labelAlign: 'right',
                                                                anchor: '100%',
                                                                allowBlank: false,
                                                                msgTarget: 'under',
                                                                listeners: {
                                                                    blur : function(){
                                                                        //Ext.Msg.alert('Message','Blur');
                                                                        if(this.value.length > 0){
                                                                            isExistingResume('<NULL>', this.value);
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                    ]
                                                },
                                                {
                                                    xtype: 'fieldset',
                                                    title: 'Profile Info',
                                                    items: [
                                                            {
                                                                xtype: 'textfield',
                                                                name: 'experience',
                                                                fieldLabel: 'Total Exp',
                                                                labelAlign: 'right',
                                                                anchor: '100%'
                                                            },
                                                            {
                                                                xtype: 'textfield',
                                                                name: 'currentCompany',
                                                                fieldLabel: 'Current Company',
                                                                labelAlign: 'right',
                                                                anchor: '100%'
                                                            },
                                                            {
                                                                xtype: 'textfield',
                                                                name: 'currentLocation',
                                                                fieldLabel: 'Current Location',
                                                                labelAlign: 'right',
                                                                anchor: '100%'
                                                            },
                                                            {
                                                                xtype: 'fieldcontainer',
                                                                layout: 'table',
                                                                items: [
                                                                        {
                                                                            xtype: 'displayfield',
                                                                            value: 'Current Salary In Lacs. Like (12.5): '
                                                                        },
                                                                        {
                                                                            xtype: 'textfield',
                                                                            name: 'currentSalary',
                                                                            width: 60
                                                                        },
                                                                ]
                                                            },
                                                            {
                                                                xtype: 'textfield',
                                                                name: 'currentDesignation',
                                                                fieldLabel: 'Designation',
                                                                labelAlign: 'right',
                                                                anchor: '100%'
                                                            }
                                                    ]
                                                }
                                                /*
                                                ,
                                                {
                                                    xtype: 'textareafield',
                                                    height: 60,
                                                    name: 'keySkills',
                                                    fieldLabel: 'Key Skills',
                                                    labelAlign: 'top',
                                                    anchor: '100%'
                                                    //allowBlank: false,
                                                    //msgTarget: 'under'
                                                }*/
                                                ,
                                                {
                                                    xtype: 'textareafield',
                                                    height: 60,
                                                    name: 'remarks',
                                                    fieldLabel: 'Remarks',
                                                    labelAlign: 'top',
                                                    anchor: '100%'
                                                    //allowBlank: false,
                                                    //msgTarget: 'under'
                                                }

                                            ],
                                            dockedItems: [
                                                {
                                                    xtype: 'toolbar',
                                                    dock: 'bottom',
                                                    layout: {
                                                        pack: 'start',
                                                        type: 'hbox'
                                                    },
                                                    items: [
                                                        {
                                                            xtype: 'button',
                                                            id: 'uploadButton',
                                                            text: 'Upload',
                                                            disabled: true,
                                                            iconCls: 'uploadIcon',
                                                            handler: function(){
                                                                var resumeId = Ext.getCmp('id').getValue();
                                                                /*
                                                                var dialog = Ext.create('Ext.ux.upload.Dialog', {
                                                                    //dialogTitle: 'My Upload Widget',
                                                                    uploadUrl: 'resume/uploadResume?resumeId='+resumeId
                                                                });
                                                                dialog.getTopToolbarConfig();
                                                                */
                                                                var component = Ext.create('Cms.view.resume.UploadResume');
                                                                component.setResumeId(resumeId);
                                                                me.createWindow(component, 'Upload Resume', 500, 400);
                                                            }
                                                        },
                                                        '-',
                                                        {
                                                            xtype: 'tbfill'
                                                        },
                                                        {
                                                            xtype: 'button',
                                                            text: 'Save',
                                                            iconCls: 'saveIcon',
                                                            formBind: true,
                                                            handler: function(){
                                                                    var form = Ext.getCmp('candidateInfoForm').getForm();
                                                                    if(form.isValid()){
                                                                        form.submit({
                                                                           url: 'resume/create',
                                                                           method:'POST',
                                                                           waitMsg: 'Processing...',
                                                                           success: function(form, action){
                                                                               var responseData = action.result.data;
                                                                                Ext.Msg.alert('Success',"Data for <b> "+responseData.name+"</b> is saved! "+responseData.id);;
                                                                                Ext.getCmp('id').setValue(responseData.id);
                                                                                //form.reset();
                                                                                //clientStore.load();
                                                                                Ext.getCmp('uploadButton').setDisabled(false);
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
                                                                    }
                                                                }
                                                        },
                                                        '-',
                                                        {
                                                            xtype: 'button',
                                                            text: 'Reset',
                                                            handler: function(){
                                                                Ext.getCmp('candidateInfoForm').getForm().reset();
                                                                Ext.getCmp('uploadButton').setDisabled(true);
                                                            }
                                                        }
                                                    ]
                                                }
                                            ]
                                        }
                                    ]
                                },
                                resumeSearch
                            ]
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }
});