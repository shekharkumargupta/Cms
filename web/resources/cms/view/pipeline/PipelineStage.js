Ext.define('Cms.view.pipeline.PipelineStage', {
    extend: 'Ext.panel.Panel',
    
    height: 400,
    layout: {
        type: 'border'
    },
    //title: 'Create Designation
    iconCls: 'bookIcon',

    initComponent: function() {
        var me = this;
        var pipelineStageStore = Ext.create('Cms.data.store.PipelineStageStore',{
            //url: 'designation/designationList'
        });
        
        
        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'form',
                    id: 'pipelineStageForm',
                    bodyPadding: 10,
                    collapsible: true,
                    title: 'Create Pipeline Stages',
                    titleCollapse: true,
                    region: 'west',
                    split: true,
                    dockedItems: [
                        {
                            xtype: 'toolbar',
                            dock: 'bottom',
                            layout: {
                                        pack: 'end',
                                        type: 'hbox'
                                    },
                            items: [
                                {
                                    xtype: 'button',
                                    text: 'Save',
                                    iconCls: 'saveIcon',
                                    handler: function(){
                                        var form = this.up('form').getForm();
                                        if(form.isValid()){
                                            form.submit({
                                                url: 'pipelineStage/create',
                                                method:'POST',
                                                waitMsg: 'Processing...',
                                                success:function(form, action){
                                                   Ext.Msg.alert('Success',action.result.data);

                                                   pipelineStageStore.load(function(records, operation, success) {
                                                    console.log('Stage created and loaded in list.');
                                                   });   

                                                },
                                                failure:function(form, action){
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
                                            })
                                        }
                                    }
                                },
                                '-',
                                {
                                    xtype: 'button',
                                    text: 'Reset',
                                    handler: function(){
                                        this.up('form').getForm().reset();
                                    }
                                }    
                            ]
                        }
                    ],
                    items: [
                        {
                            xtype: 'hidden',
                            name:'id',
                            value: '0'
                        },
                        {
                            xtype: 'hidden',
                            name: 'companyId'
                        },
                        {
                            xtype: 'textfield',
                            name: 'stepNo',
                            fieldLabel: 'Step No.',
                            labelAlign: 'top',
                            msgTarget: 'side',
                            allowBlank: false,
                            anchor: '100%'
                        },
                        {
                            xtype: 'textfield',
                            name: 'name',
                            fieldLabel: 'Pipeline Stage Name',
                            labelAlign: 'top',
                            msgTarget: 'side',
                            allowBlank: false,
                            anchor: '100%'
                        }
                    ]
                },
                {
                    xtype: 'gridpanel',
                    title: 'Pipeline Stages List',
                    store: pipelineStageStore,
                    region: 'center',
                    columnLines: true,
                    viewConfig: {

                    },
                    columns: [
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'companyId',
                            text: 'Company Id',
                            hidden: true
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'id',
                            text: 'Id',
                            hidden: true
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'name',
                            text: 'Name',
                            flex:1
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'stepNo',
                            text: 'Step No.',
                            flex:1
                        }
                    ],
                    dockedItems: [
                        {
                            xtype: 'toolbar',
                            dock: 'top',
                            items: [
                                {
                                    xtype: 'button',
                                    text: 'Add New',
                                    iconCls: 'addIcon'
                                },
                                {
                                    xtype: 'tbseparator'
                                },
                                {
                                    xtype: 'button',
                                    text: 'Remove',
                                    iconCls: 'removeIcon'
                                },
                                {
                                    xtype: 'tbfill'
                                },
                                {
                                    xtype: 'button',
                                    text: 'Refresh',
                                    iconCls: 'tableRefreshIcon',
                                    handler: function(){
                                        pipelineStageStore.load(function(records, operation, success) {
                                            console.log('Stage created and loaded in list.');
                                        }); 
                                    }
                                }
                            ]
                        }
                    ],
                    listeners:{
                        selectionchange:function(model, records){
                            if(records[0]){
                                Ext.getCmp('pipelineStageForm').getForm().loadRecord(records[0]);
                                //this.up('form').getForm().loadRecord(records[0]);
                            }
                            
                        }
                    }
                }
                
            ]
        });

        me.callParent(arguments);
    }
});