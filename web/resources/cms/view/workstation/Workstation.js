Ext.define('Cms.view.workstation.Workstation', {
    extend: 'Ext.panel.Panel',
    
    height: 400,
    layout: {
        type: 'border'
    },
    //title: 'Create Designation
    iconCls: 'monitorIcon',

    initComponent: function() {
        var me = this;
        var workstationStore = Ext.create('Cms.data.store.WorkstationStore',{
            url: 'workstation/workstationList'
        });
        
        
        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'form',
                    id: 'workstationForm',
                    bodyPadding: 10,
                    collapsible: true,
                    title: 'Create Workstation',
                    titleCollapse: true,
                    region: 'west',
                    split: true,
                    items: [
                        {
                            xtype: 'hidden',
                            name:'id'
                        },
                        {
                            xtype: 'hidden',
                            name: 'companyId'
                        },
                        {
                            xtype: 'textfield',
                            name: 'name',
                            fieldLabel: 'Workstation Name',
                            labelAlign: 'top',
                            msgTarget: 'side',
                            allowBlank: false,
                            anchor: '100%'
                        },
                        {
                            xtype: 'textfield',
                            name: 'ipAddress',
                            fieldLabel: 'IP. Address',
                            labelAlign: 'top',
                            msgTarget: 'side',
                            allowBlank: false,
                            anchor: '100%'
                        },
                        {
                            xtype: 'button',
                            text: 'Save',
                            handler: function(){
                                var form = this.up('form').getForm();
                                if(form.isValid()){
                                    form.submit({
                                        url: 'workstation/create',
                                        method:'POST',
                                        waitMsg: 'Processing...',
                                        success:function(form, action){
                                           Ext.Msg.alert('Success',action.result.data);
                                           
                                           var store = Ext.getStore('designationStore');

                                           store.load(function(records, operation, success) {
                                            console.log('Employee created and loaded in list.');
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
                        }
                    ]
                },
                {
                    xtype: 'gridpanel',
                    title: 'Workstation List',
                    store: workstationStore,
                    region: 'center',
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
                            dataIndex: 'ipAddress',
                            text: 'IP Address',
                            flex:1
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'loginId',
                            text: 'User',
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
                                        var store = Ext.getStore('workstationStore');
                                        store.load(function(records, operation, success) {
                                            console.log('Employee created and loaded in list.');
                                        }); 
                                    }
                                }
                            ]
                        }
                    ],
                    listeners:{
                        selectionchange:function(model, records){
                            if(records[0]){
                                Ext.getCmp('workstationForm').getForm().loadRecord(records[0]);
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