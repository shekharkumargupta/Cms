Ext.define('Cms.view.employee.EmployeeInfo', {
    extend: 'Ext.form.Panel',
    iconCls: 'userIcon',
    height: 500,
    padding: 0,
    layout: {
        type: 'border'
    },
    bodyPadding: '',
    title: '',

    initComponent: function() {
        var me = this;
        var employeeTypeStore = Ext.create("Cms.data.store.EmployeeTypeStore");
        var employeeStore = Ext.create('Cms.data.store.EmployeeStore');
        var designationStore = Ext.create("Cms.data.store.DesignationStore");
        //var workstationStore = Ext.create("Cms.data.store.WorkstationStore");
        

        
        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'panel',
                    margin: '',
                    padding: '',
                    bodyPadding: 10,
                    width: 320,
                    collapsible: true,
                    title: 'Employee/Associates Information',
                    titleCollapse: true,
                    region: 'west',
                    split: true,
                    items: [
                        {
                            xtype: 'fieldset',
                            padding: 5,
                            title: 'Basic Information',
                            items: [
                                {
                                    xtype: 'hidden',
                                    name: 'id',
                                    value: '0'
                                },
                                {
                                    xtype: 'combo',
                                    fieldLabel: 'Employee Type',
                                    labelAlign: 'right',
                                    name: 'employeeTypeId',
                                    store: employeeTypeStore,
                                    displayField: 'value',
                                    valueField: 'id',
                                    triggerAction: 'all',
                                    editable: false
                                },
                                {
                                    xtype: 'textfield',
                                    name: 'firstName',
                                    fieldLabel: 'First Name',
                                    labelAlign: 'right',
                                    anchor: '100%'
                                },
                                {
                                    xtype: 'textfield',
                                    name: 'middleInitial',
                                    fieldLabel: 'Middle Initial',
                                    labelAlign: 'right',
                                    anchor: '100%'
                                },
                                {
                                    xtype: 'textfield',
                                    name: 'lastName',
                                    fieldLabel: 'Last Name',
                                    labelAlign: 'right',
                                    anchor: '100%'
                                }
                            ]
                        },
                        {
                            xtype: 'fieldset',
                            padding: 5,
                            title: 'Company Profile',
                            items: [
                                /*
                                {
                                    xtype: 'textfield',
                                    name: 'empCode',
                                    fieldLabel: 'Employee Code',
                                    labelAlign: 'right',
                                    msgTarget: 'side',
                                    anchor: '100%'
                                },
                                */
                                {
                                    xtype: 'combo',
                                    fieldLabel: 'Designation',
                                    labelAlign: 'right',
                                    name: 'designationId',
                                    store: designationStore,
                                    displayField: 'name',
                                    valueField: 'id',
                                    triggerAction: 'all',
                                    editable: false
                                }
                            ]
                        },
                        {
                            xtype: 'fieldset',
                            padding: 5,
                            title: 'Contact Information',
                            items: [
                                {
                                    xtype: 'textfield',
                                    name: 'email',
                                    fieldLabel: 'Email Id',
                                    labelAlign: 'right',
                                    msgTarget: 'side',
                                    anchor: '100%',
                                    vtype: 'email',
                                    allowBlank: false,
                                    invalidText: 'Email cannot be empty.',
                                    listeners: {
                                        blur : function(){
                                            //Ext.Msg.alert('Message','Blur');
                                            isExistingEmail(this.value);
                                        }
                                    }
                                },
                                {
                                    xtype: 'textfield',
                                    name: 'phone1',
                                    fieldLabel: 'Phone1',
                                    labelAlign: 'right',
                                    msgTarget: 'side',
                                    anchor: '100%'
                                }
                            ]
                        },
                        {
                            xtype: 'fieldset',
                            title: 'Special Days Info',
                            items: [
                                {
                                    xtype: 'datefield',
                                    name: 'dateOfBirth',
                                    fieldLabel: 'Date of Birth',
                                    labelAlign: 'right',
                                    format: 'Y-m-d',
                                    labelWidth: 120
                                },
                                {
                                    xtype: 'datefield',
                                    name: 'dateOfAnniversary',
                                    fieldLabel: 'Date of Anniversary',
                                    labelAlign: 'right',
                                    format: 'Y-m-d',
                                    labelWidth: 120
                                }
                            ]
                        }
                        
                    ],
                    dockedItems: [
                        {
                            xtype: 'toolbar',
                            id: 'personInfoTB',
                            dock: 'bottom',
                            layout: {
                                align: 'middle',
                                pack: 'end',
                                type: 'hbox'
                            },
                            items: [
                                {
                                    xtype: 'button',
                                    id: 'saveButton',
                                    text: 'Save',
                                    iconCls: 'saveIcon',
                                    formBind: true,
                                    handler: function(){
                                            var form = this.up('form').getForm();
                                            if(form.isValid()){
                                                form.submit({
                                                   url: 'user/create',
                                                   method:'POST',
                                                   waitMsg: 'Processing...',
                                                   success: function(form, action){
                                                       employeeStore.load(function(records, operation, success) {
                                                        console.log('Employee created and loaded in list.');
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
                                },
                                {
                                    xtype: 'tbseparator'
                                },
                                {
                                    xtype: 'button',
                                    id: 'resetButton',
                                    text: 'Reset',
                                    handler: function(){
                                        this.up('form').getForm().reset();
                                    }
                                }
                            ]
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    title: 'Employee List',
                    region: 'center',
                    items: [
                        {
                            xtype: 'gridpanel',
                            title: '',
                            store: employeeStore,
                            columnLines: true,
                            autoHeight: true,
                            columns: [
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'id',
                                    text: 'Id',
                                    hidden: true
                                },
                                {
                                    xtype: 'templatecolumn',
                                    dataIndex: 'firstName',
                                    text: 'Name',
                                    tpl: '<b>{firstName} {middleInitial} {lastName}</b>'+
                                         '<br>'+
                                         '{designationName}',
                                    width: 180
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'email',
                                    text: 'Email',
                                    width: 180
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'phone1',
                                    text: 'Phone1'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'password',
                                    text: 'Password'
                                }
                            ],
                            viewConfig: {

                            },
                            listeners: {
                                selectionchange: function(model, records){
                                    if(records[0]){
                                        //Ext.getCmp('personInfo').getForm().loadRecord(records[0]);
                                        this.up('form').getForm().loadRecord(records[0]);
                                        var record = records[0];
                                    }
                                }
                            }
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
                                            employeeStore.load();
                                    }
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