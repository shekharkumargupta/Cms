Ext.define('Cms.view.resume.UploadDocument', {
    extend: 'Ext.panel.Panel',
    iconCls: 'bookIcon',
    resumeId: '0',
    height: 320,
    layout: 'border',
    
    setResumeId: function(resumeId){
        this.resumeId = resumeId;
    },
    
    getResumeId: function(){
        return this.resumeId;
    },
    
    
    
    initComponent: function() {
        var me = this;
        
        var documentTypeStore = Ext.create('Cms.data.store.DocumentTypeStore',{
        });
        
        var documentStore = Ext.create('Cms.data.store.DocumentStore',{
            proxy:{
                type: 'ajax',
                url: 'document/documentByResumeId?resumeId='+me.getResumeId(),
                reader: {
                    type: 'json',
                    root: 'data'
                }
            }
        });
        
        
        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'form',
                    id: 'documentUploadForm',
                    bodyPadding: 10,
                    //collapsible: true,
                    //title: 'Upload Employee Image',
                    //titleCollapse: true,
                    region: 'north',
                    height: 100,
                    split: true,
                    items: [
                        {
                            xtype:'hidden',
                            name: 'id',
                            value: '0'
                        },
                        {
                            xtype: 'combobox',
                            name: 'documentTypeId',
                            fieldLabel: 'Select Branch',
                            labelAlign: 'right',
                            width: 300,
                            anchor: '100%',
                            store: documentTypeStore,
                            triggerAction: 'all',
                            displayField: 'value',
                            valueField: 'id',
                            editable: false
                        },
                        {
                            xtype: 'filefield',
                            fieldLabel: 'Browse File',
                            labelAlign: 'right',
                            name: 'file',
                            anchor: '100%'
                        }
                    ],
                    dockedItems: [
                        {
                            xtype: 'toolbar',
                            dock: 'bottom',
                            layout: {
                                align: 'middle',
                                pack: 'end',
                                type: 'hbox'
                            },
                            items: [
                                {
                                    xtype: 'button',
                                    text: 'Upload',
                                    iconCls: 'addIcon',
                                    handler: function(){
                                        var form = this.up('form').getForm();
                                            if(form.isValid()){
                                                form.submit({
                                                   enctype: 'multipart/form-data',
                                                   url: 'document/upload?resumeId='+me.getResumeId(),
                                                   mevthod:'POST',
                                                   waitMsg: 'Processing...',
                                                   success: function(form, action){
                                                       Ext.Msg.alert('Success','Success');
                                                       documentStore.load({
                                                          params:{
                                                              resumeId: me.getResumeId()
                                                          } 
                                                       });
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
                                }
                            ]
                        }
                    ]
                },
                {
                    xtype: 'gridpanel',
                    //title: 'Client List',
                    store: documentStore,
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
                        /*
                        {
                            xtype: 'actioncolumn',
                            width: 30,
                            text: '',
                            align: 'center',
                            items:[
                                    {
                                    icon   : '../images/download.jpg',
                                    tooltip: 'Upload Document',
                                    handler: function(grid, rowIndex, colIndex){
                                                var record = grid.getStore().getAt(rowIndex);
                                                var resumeId = record.get('id');
                                            }
                                    }
                            ]
                        },
                        */
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
                            text: 'Size',
                            width: 50
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
                                Ext.getCmp('documentUploadForm').getForm().loadRecord(records[0]);
                                var record = records[0];
                            }
                        }
                    },
                    dockedItems: [
                        {
                            xtype: 'toolbar',
                            dock: 'bottom',
                            layout: {
                                align: 'middle',
                                pack: 'end',
                                type: 'hbox'
                            },
                            items: [
                                {
                                    xtype: 'button',
                                    text: 'Remove',
                                    iconCls: 'removeIcon',
                                    handler: function(){
                                        
                                    }
                                },
                                '-',
                                {   
                                    xtype: 'button',
                                    text: 'Close'
                                }
                            ]
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }
});