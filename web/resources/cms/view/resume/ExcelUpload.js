Ext.define('Cms.view.resume.ExcelUpload', {
    extend: 'Ext.panel.Panel',

    height: 550,
    width: '100%',
    layout: {
        type: 'border'
    },
    
       
    initComponent: function() {
       
        var resumeStore = Ext.create('Cms.data.store.ResumeStore',{
            proxy: {
                type: 'ajax',
                url: 'resume/resumeListByEmailIds',
                reader: {
                    type: 'json',
                    root: 'data'
                }
            }
        });
        
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'panel',
                    id: 'basicSearchPanel',
                    height: 100,
                    //width: '100%',
                    region: 'north',
                    title: 'Search Resume',
                    split: true,
                    collapsible:true,
                    animCollapse: true,
                    items: [
                        {
                            xtype: 'form',
                            id: 'excelUploadForm',
                            bodyPadding: 10,
                            width: '100%',
                            items: [
                                {
                                    xtype: 'filefield',
                                    labelWidth: 105,
                                    fieldLabel: 'Select File',
                                    name: 'file',
                                    labelAlign: 'right',
                                    anchor: '100%'
                                }
                            ]
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
                                    xtype: 'tbfill'
                                },
                                {
                                    xtype: 'button',
                                    text: 'Upload',
                                    iconCls: 'uploadIcon',
                                    handler: function(){
                                        var form = Ext.getCmp('excelUploadForm').getForm();
                                            if(form.isValid()){
                                                form.submit({
                                                   enctype: 'multipart/form-data',
                                                   url: 'resume/createBatch',
                                                   mevthod:'POST',
                                                   waitMsg: 'Processing...',
                                                   success: function(form, action){
                                                       var responseData = action.result.message;
                                                       //Ext.Msg.alert('Response', responseData);
                                                       resumeStore.load({
                                                           params:{
                                                               emailIds: responseData
                                                           }
                                                       })
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
                                        //Ext.Msg.alert('Hello','Hello');
                                        resumeStore.load({
                                           params:{
                                               emailIds: '\'shekharkumargupta@gmail.com\', \'shekharkumargupta@outlook.com\''
                                           } 
                                        });
                                    }
                                }
                            ]
                        }
                    ]
                },
                {
                    xtype: 'gridpanel',
                    //title: 'Client List',
                    store: resumeStore,
                    region: 'center',
                    //height: 300,
                    columnLines: true,
                    selModel: Ext.create('Ext.selection.CheckboxModel',{
                        listeners:{ 
                            selectionchange: function(selectionModel, selected, options){ 
                                // Bunch of code to update store 
                            } 
                        } 
                    }),
                    columns: [
                        {
                            xtype: 'templatecolumn',
                            dataIndex: 'id',
                            text: 'Resume Id',
                            tpl: '{id}'
                        },
                        {
                            xtype: 'templatecolumn',
                            dataIndex: 'name',
                            text: 'Name',
                            width: 190,
                            tpl: '<b>{name}</b></br>'+
                                 'Dob: {dateOfBirth}</br>'+
                                 'Contact: {contactNo}'
                        },
                        {
                            xtype: 'templatecolumn',
                            dataIndex: 'email',
                            text: 'Email',
                            tpl: '{email}',
                            width: 200
                        },
                        {
                            xtype: 'templatecolumn',
                            text: 'Experience',
                            dataIndex: 'experience',
                            tpl: '{experience} Yrs.'
                        },
                        {
                            xtype: 'templatecolumn',
                            text: 'Current Salary',
                            dataIndex: 'currentSalary',
                            tpl: '{currentSalary} LPA.'
                        },
                        {
                            xtype: 'templatecolumn',
                            text: 'Current Company',
                            dataIndex: 'currentCompany',
                            tpl: '{currentCompany}'
                        },
                        {
                            xtype: 'templatecolumn',
                            dataIndex: 'currentDesignation',
                            text: 'Designation',
                            tpl: '{currentDesignation}',
                            width: 130
                        },
                        {
                            xtype: 'templatecolumn',
                            dataIndex: 'createdByName',
                            text: 'Uploaded By',
                            width: 130,
                            tpl: '{createdByName}'+
                                 '</br>'+
                                 '({createdAt})'
                        }
                    ],
                    viewConfig: {

                    }
                }
            ]
        });

        me.callParent(arguments);
    }
});