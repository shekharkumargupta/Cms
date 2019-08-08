Ext.define('Cms.view.pipeline.PipelineResume', {
    extend: 'Ext.form.Panel',

    height: 550,
    width: 500,
    bodyPadding: 15,
    //title: 'Pipeline Resume',
    openingId: '0',
    resumeId: '0',
    candidateName:'---',
    
    setOpeningId: function(openingId){
        this.openingId = openingId;
    },
    getOpeningId: function(){
        return this.openingId;
    },
    
    setResumeId: function(resumeId){
        this.resumeId = resumeId;
    },
    getResumeId: function(){
        return this.resumeId;
    },
    
    setCandidateName: function(candidateName){
        this.candidateName = candidateName;
    },
    getCandidateName: function(){
        return this.candidateName;
    },

    initComponent: function() {
        var me = this;
        var clientStore = Ext.create('Cms.data.store.ClientStore',{
        });
        
        var openingStore = Ext.create('Cms.data.store.OpeningByEmployeeStore',{
        });
        
        var contactStore = Ext.create('Cms.data.store.ContactByOpeningStore',{
        });
        
        var documentStore = Ext.create('Cms.data.store.DocumentStore',{
            proxy:{
                type: 'ajax',
                url: 'document/documentByResumeId',
                reader: {
                    type: 'json',
                    root: 'data'
                }
            }
        });
        
       

        Ext.applyIf(me, {
            items: [
                /*
                {
                    xtype: 'combobox',
                    fieldLabel: 'Company',
                    labelAlign: 'right',
                    anchor: '100%',
                    store: clientStore,
                    displayField: 'clientName',
                    valueField: 'clientId',
                    triggerAction: 'all',
                    editable: false,
                    listeners: {
                        select: function(combo, rec, index){
                           var contactPersonCombo = Ext.getCmp('contactPersonCombo') ;
                           contactPersonCombo.setDisabled(false);
                           contactPersonCombo.clearValue();
                           //contactPersonCombo.removeAll();
                           contactStore.proxy.extraParams = {
                               clientId: combo.getValue()
                           }
                           contactStore.load();
                           
                           var openingCombo = Ext.getCmp('openingCombo') ;
                           openingCombo.setDisabled(false);
                           openingCombo.clearValue();
                           //contactPersonCombo.removeAll();
                           openingStore.proxy.extraParams = {
                               clientId: combo.getValue()
                           }
                           openingStore.load();
                        }
                    }
                },
                */
                {
                    xtype: 'combobox',
                    fieldLabel: 'Opening',
                    id: 'openingCombo',
                    name: 'openingId',
                    labelAlign: 'right',
                    anchor: '100%',
                    store: openingStore,
                    displayField: 'positionName',
                    valueField: 'openingId',
                    triggerAction: 'all',
                    editable: false,
                    //fieldStyle:'text-transform:uppercase',
                    listConfig: {
                        loadingText: 'Loading...',
                        // Custom rendering template for each item
                        getInnerTpl: function() {
                            return '<table width="100%">\n\
                                        <tr>\n\
                                            <td align="left">\n\
                                                <b>{positionName} ({location})</b>\n\
                                                <br>\n\
                                                Exp: {minimumExp} - {maximumExp} Yrs\n\\n\
                                                <br>\n\\n\
                                                {clientName}\n\
                                            </td>\n\
                                            <td align="right" valign="top">\n\
                                                Opening Id: {openingId}\n\
                                            </td>\n\
                                        </tr>\n\
                                    </table>';
                        },
                        emptyText:'No Values Found'
                    },
                    listeners: {
                        select: function(combo, rec, index){
                                var contactPersonCombo = Ext.getCmp('contactPersonCombo') ;
                                contactPersonCombo.setDisabled(false);
                                contactPersonCombo.clearValue();
                                //contactPersonCombo.removeAll();
                                contactStore.proxy.extraParams = {
                                   openingId: combo.getValue()
                                }
                                /*
                                contactStore.load({
                                   openingId: combo.getValue()
                                })
                                */
                                isResumeAlreadyPipelined(me.getResumeId(), combo.getValue());
                        }
                    }
                },
                {
                    xtype: 'combobox',
                    fieldLabel: 'Contact Person',
                    id: 'contactPersonCombo',
                    name: 'to',
                    labelAlign: 'right',
                    anchor: '100%',
                    store: contactStore,
                    displayField: 'email',
                    valueField: 'email',
                    triggerAction: 'all',
                    editable: false,
                    //fieldStyle:'text-transform:uppercase',
                    listConfig: {
                        loadingText: 'Loading...',
                        // Custom rendering template for each item
                        getInnerTpl: function() {
                            return '<table width="100%">\n\
                                        <tr>\n\
                                            <td align="left">\n\
                                                <b>{firstName} {middleInitial} {lastName}</b>\n\
                                                <br>\n\
                                                {contactTypeName}\n\
                                            </td>\n\
                                            <td align="right" valign="top">\n\
                                                <b>{email}</b>\n\
                                            </td>\n\
                                        </tr>\n\
                                    </table>';
                        },
                        emptyText:'No Values Found'
                    }
                },
                {
                    xtype: 'htmleditor',
                    name: 'message',
                    height: 210,
                    style: 'background-color: white;',
                    fieldLabel: 'Message',
                    labelAlign: 'top',
                    anchor: '100%'
                },
                {
                    xtype: 'gridpanel',
                    id: 'documentGridPanel',
                    title: 'Document List',
                    collapsible: true,
                    animCollapse: true,
                    //collapsed: true,
                    store: documentStore,
                    //region: 'center',
                    height: 200,
                    autoScroll: true,
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
                            xtype: 'gridcolumn',
                            dataIndex: 'id',
                            text: 'Id',
                            hidden: true
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'documentTypeName',
                            text: 'Document',
                            width: 100
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'fileName',
                            text: 'File Name',
                            width: 120
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'fileType',
                            text: 'Type',
                            width: 120
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'fileSize',
                            text: 'Size (KB)',
                            width: 60
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'updatedAt',
                            text: 'Updated At',
                            width: 90
                        },
                        {
                            xtype: 'templatecolumn',
                            width: 40,
                            tpl: '<a href="/Cms/resumes/{companyId}/{branchId}/{fileName}"><img src="../images/download.jpg"></a>'
                        }
                    ],
                    viewConfig: {

                    },
                    listeners: {
                        selectionchange: function(model, records){
                            if(records[0]){
                                //Ext.getCmp('documentUploadForm').getForm().loadRecord(records[0]);
                                //var record = records[0];
                            }
                        }
                    },
                    dockedItems: [
                        {
                            xtype: 'toolbar',
                            dock: 'top',
                            layout: {
                                align: 'middle',
                                pack: 'end',
                                type: 'hbox'
                            },
                            items: [
                                {
                                    xtype: 'button',
                                    text: 'Load',
                                    iconCls: 'tableRefreshIcon',
                                    handler: function(){
                                        //Ext.Msg.alert('ResumeId: '+me.getResumeId());
                                        documentStore.load({
                                            params:{
                                                resumeId: me.getResumeId()
                                            }
                                        });
                                    }
                                }
                            ]
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
                            xtype:'button',
                            text: 'Load Template Message',
                            iconCls: 'tableRefreshIcon',
                            handler: function(){
                                var form = this.up('form').getForm();
                                if(form.isValid()){
                                    form.load({
                                       url: 'emailMessage/load',
                                       method:'GET',
                                       waitMsg: 'Processing...',
                                       success: function(form, action){
                                           var responseData = action.result.data;
                                            //Ext.Msg.alert('Success',"Message successfully saved.");
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
                            xtype: 'tbfill'
                        },
                        {
                            xtype: 'button',
                            text: 'Send',
                            iconCls: 'pipelineProcessingIcon',
                            handler: function(){
                                var form = this.up('form').getForm();

                                var grid = Ext.getCmp('documentGridPanel');
                                var records = grid.selModel.getSelection();
                                var fileNames = ''
                                Ext.each(records, function (record) {
                                    fileNames = fileNames+","+record.get('fileName');
                                }); 
                                fileNames = fileNames.substring(1, fileNames.length);
                                //Ext.Msg.alert('Message', email);
                                //Ext.Msg.alert('Message', fileNames);
                               
                                if(form.isValid()){
                                    form.submit({
                                       url: 'pipeline/sendResume?resumeId='+me.getResumeId()+'&documentNames='+fileNames,
                                       method:'POST',
                                       waitMsg: 'Processing...',
                                       success: function(form, action){
                                           var responseData = action.result.data;
                                            Ext.Msg.alert('Success',"Resume Sent to: <b> "+responseData.to+"</b>");
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
                            text: 'Cancel'
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }
});