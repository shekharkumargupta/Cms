Ext.define('Cms.view.branch.Branch', {
    extend: 'Ext.form.Panel',

    layout: {
        type: 'border'
    },
    title: 'Branches',
    iconCls: 'branchIcon',

    initComponent: function() {
        var me = this;
        
        

        var countryStore = Ext.create('Cms.data.store.CountryStore');
        var cityStore = Ext.create('Cms.data.store.CityStore',{
        });
        var branchModel = Ext.create('Cms.data.model.BranchModel');
        var branchStore = Ext.create('Cms.data.store.BranchStore',{
            //model: branchModel
        });
        var branchTypeStore = Ext.create('Cms.data.store.BranchTypeStore');

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'form',
                    width: 320,
                    bodyPadding: 15,
                    collapsed: false,
                    collapsible: true,
                    title: 'Branch Information',
                    titleCollapse: true,
                    region: 'west',
                    split: true,
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
                                    text: 'Save',
                                    iconCls: 'saveIcon',
                                    formBind: true,
                                    handler: function(){
                                            var form = this.up('form').getForm();
                                            if(form.isValid()){
                                                form.submit({
                                                   url: 'branch/create',
                                                   method:'POST',
                                                   waitMsg: 'Processing...',
                                                   success: function(form, action){
                                                       //Ext.Msg.alert('Success',"<b>"+action.result.data+"</b> created successfully!");
                                                       //var store = Ext.getStore('employeeStore');
                                                       
                                                        branchStore.load(function(records, operation, success) {
                                                            console.log('Branch created and loaded in list.');
                                                       });
                                                       form.reset();
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
                            xtype: 'fieldset',
                            title: 'Branch Info',
                            padding: 10,
                            items: [
                                {
                                    xtype: 'hidden',
                                    name:'branchId',
                                    value: '0'
                                },
                                {
                                    xtype: 'combobox',
                                    name: 'branchType',
                                    store: branchTypeStore,
                                    fieldLabel: 'Branch Type',
                                    labelAlign: 'right',
                                    msgTarget: 'side',
                                    allowBlank: false,
                                    editable:false,
                                    displayField: 'value',
                                    valueField: 'id',
                                    triggerAction: 'all',
                                    anchor: '100%',
                                    queryMode: 'local'
                                },
                                {
                                    xtype: 'textfield',
                                    name: 'branchName',
                                    fieldLabel: 'Branch Name',
                                    labelAlign: 'right',
                                    msgTarget: 'side',
                                    allowBlank: false,
                                    anchor: '100%'
                                },
                                {
                                    xtype: 'textfield',
                                    name: 'street1',
                                    fieldLabel: 'Street1',
                                    labelAlign: 'right',
                                    msgTarget: 'side',
                                    allowBlank: false,
                                    anchor: '100%'
                                },
                                {
                                    xtype: 'textfield',
                                    name: 'street2',
                                    fieldLabel: 'Street2',
                                    labelAlign: 'right',
                                    msgTarget: 'side',
                                    allowBlank: true,
                                    anchor: '100%'
                                },
                                {
                                    xtype: 'combobox',
                                    name: 'country',
                                    store: countryStore,
                                    fieldLabel: 'Country',
                                    labelAlign: 'right',
                                    allowBlank: false,
                                    editable:false,
                                    displayField: 'value',
                                    valueField: 'id',
                                    triggerAction: 'all',
                                    anchor: '100%',
                                    emptyText: 'Select Country',
                                    listeners: {
                                        select: function(combo, rec, index){
                                           var cityCombo = Ext.getCmp('cityCombo') ;
                                           cityCombo.setDisabled(false);
                                           cityCombo.clearValue();
                                           cityStore.removeAll();
                                           cityStore.proxy.extraParams = {
                                               countryId: combo.getValue()
                                           }
                                           cityStore.load();
                                        }
                                    }
                                },
                                {
                                    xtype: 'combobox',
                                    name: 'city',
                                    id: 'cityCombo',
                                    fieldLabel: 'City',
                                    labelAlign: 'right',
                                    allowBlank: false,
                                    editable:false,
                                    //disabled:true,
                                    store: cityStore,
                                    displayField: 'value',
                                    valueField: 'id',
                                    triggerAction: 'all',
                                    emptyText: 'Select City',
                                    anchor: '100%'
                                },
                                {
                                    xtype: 'textfield',
                                    name: 'pinCode',
                                    fieldLabel: 'Zip Code',
                                    labelAlign: 'right',
                                    allowBlank: false,
                                    anchor: '100%'
                                }
                            ]
                        },
                        {
                            xtype: 'fieldset',
                            title: 'Branch Head Info',
                            padding: 10,
                            items: [
                                {
                                    xtype: 'textfield',
                                    name: 'firstName',
                                    fieldLabel: 'First Name',
                                    labelAlign: 'right',
                                    anchor: '100%',
                                    allowBlank: false,
                                    msgTarget: 'under'
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
                                    anchor: '100%',
                                    allowBlank: false,
                                    msgTarget: 'under'
                                },
                                {
                                    xtype: 'textfield',
                                    name: 'email',
                                    fieldLabel: 'Email',
                                    labelAlign: 'right',
                                    anchor: '100%',
                                    allowBlank: false,
                                    msgTarget: 'under',
                                    listeners: {
                                        blur : function(){
                                            //Ext.Msg.alert('Message','Blur');
                                            isExistingEmail(this.value);
                                        }
                                    }
                                },
                                {
                                    xtype: 'textfield',
                                    name: 'contactNo',
                                    fieldLabel: 'Contact No',
                                    labelAlign: 'right',
                                    anchor: '100%',
                                    allowBlank: false,
                                    msgTarget: 'under'
                                }
                            ]
                        }
                    ]
                },
                {
                    xtype: 'gridpanel',
                    title: 'Branch List',
                    region: 'center',
                    split: true,
                    store: branchStore,
                    columnLines: true,
                    columns: [
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'branchId',
                            text: 'Id',
                            width: 40
                        },
                        {
                            xtype: 'templatecolumn',
                            text: 'Branch Name',
                            //dataIndex: 'branchName'
                            tpl: '<b>{branchName}</b> <br>{cityString}',
                            width: 150
                        },
                        {
                            xtype: 'templatecolumn',
                            text: 'Address',
                            tpl: '{street1}'+
                                 '<br>{street2}'+
                                 '<br>City: {cityString}'+
                                 '<br>Country: {countryString}'+
                                 '<br>Pincode: {pinCode}',
                            width: 140
                        },
                        {
                            xtype: 'templatecolumn',
                            text: 'Branch Head',
                            tpl: '<b>{firstName} {middleInitial} {lastName}</b>'+
                                 '<br>{email}'+
                                 '<br>{contactNo}',
                            width: 150
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'branchType',
                            text: 'branchType',
                            hidden: true
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'country',
                            text: 'country',
                            hidden: true
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'city',
                            text: 'city',
                            hidden: true
                        }
                    ],
                    viewConfig: {

                    },
                    listeners: {
                        selectionchange: function(model, records){
                            /*
                            var cityCombo = Ext.getCmp('cityCombo') ;
                               cityCombo.setDisabled(false);
                               cityCombo.clearValue();
                               cityStore.removeAll();
                               cityStore.proxy.extraParams = {
                                   countryId: records[0].get('country')
                               }
                               cityStore.load();
                               */
                            if(records[0]){
                                //Ext.getCmp('personInfo').getForm().loadRecord(records[0]);
                                this.up('form').getForm().loadRecord(records[0]);
                            }
                        }
                    },
                    dockedItems: [
                        {
                            xtype: 'toolbar',
                            dock: 'top',
                            items: [
                                {
                                    xtype: 'tbfill'
                                },
                                {
                                    xtype: 'button',
                                    text: 'Refresh',
                                    iconCls: 'tableRefreshIcon',
                                    handler: function(){
                                        var store = Ext.getStore('branchStore');
                                        store.load(function(records, operation, success) {
                                            console.log('Employee created and loaded in list.');
                                        }); 
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