Ext.define('Cms.view.client.Client', {
    extend: 'Ext.panel.Panel',

    height: 600,
    layout: {
        type: 'border'
    },
    title: 'Client',

    initComponent: function() {
        var designationStore = Ext.create('Cms.data.store.DesignationStore',{
            url: 'designation/designationList'
        });
        
        var categoryStore = Ext.create('Cms.data.store.CategoryStore',{
        });
        
        var contactTypeStore = Ext.create('Cms.data.store.ContactTypeStore',{
        });
        
        var countryStore = Ext.create('Cms.data.store.CountryStore',{
        });
        
        var cityStore = Ext.create('Cms.data.store.CityStore',{
        });
        
        var clientStore = Ext.create('Cms.data.store.ClientStore',{
        });
        
        var contactStore = Ext.create('Cms.data.store.ContactStore',{
            //url: 'client/contactList?clientId=0'
        });
        
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'form',
                    id: 'clientForm',
                    width: 300,
                    bodyPadding: 10,
                    title: 'Client Info',
                    region: 'west',
                    split: true,
                    collapsible: true,
                    animCollapse: true,
                    items: [
                        {
                            xtype: 'hidden',
                            id: 'clientId',
                            name: 'clientId',
                            fieldLabel: 'Client Id',
                            labelAlign: 'right',
                            value: '0'
                        },
                        /*
                        {
                            xtype: 'combo',
                            fieldLabel: 'Category',
                            name: 'categoryId',
                            labelAlign: 'right',
                            labelWidth: 80,
                            anchor: '100%',
                            allowBlank: false,
                            msgTarget: 'under',
                            blankText: 'Must select a category',
                            store: categoryStore,
                            valueField: 'id',
                            displayField: 'value',
                            triggerAction: 'all',
                            typeAhead: true,
                            minChars: 2,
                            forceSelection: true,
                            hideTrigger: true,
                            
                            fieldStyle:'text-transform:uppercase',
                            listConfig: {
                                loadingText: 'Loading...',
                                // Custom rendering template for each item
                                getInnerTpl: function() {
                                    return '<table width="200px"><tr><td height="5"></td></tr><tr valign="top"><td>Code:{id}</td></tr><tr><td height="2"></td></tr><tr valign="top"><td>Description:{value}</td></tr><tr><td height="5"></td></tr></table>';
                                },
                                emptyText:'No Values Found'
                            }
                            
                        },
                        */
                        {
                            xtype: 'textfield',
                            fieldLabel: 'Name',
                            name: 'clientName',
                            labelAlign: 'right',
                            labelWidth: 80,
                            anchor: '100%',
                            allowBlank: false,
                            emptyText: 'Company name',
                            msgTarget: 'under',
                            blankText: 'Must enter the company name',
                            minLength: 3,
                            minLengthText: 'Company Name can\'t be less than 3 character.',
                            maxLength: 30,
                            maxLengthText: 'Comapny Name can\'t be greater than 30 character.'
                        },
                        {
                            xtype: 'textfield',
                            name: 'website',
                            fieldLabel: 'Website',
                            labelAlign: 'right',
                            labelWidth: 80,
                            emptyText: 'http://www.opgea.com',
                            anchor: '100%'
                        },
                        {
                            xtype: 'textfield',
                            fieldLabel: 'Street1',
                            name: 'street1',
                            labelAlign: 'right',
                            labelWidth: 80,
                            anchor: '100%',
                            allowBlank: false,
                            msgTarget: 'under',
                            blankText: 'Must enter the street1'
                        },
                        {
                            xtype: 'textfield',
                            fieldLabel: 'Street2',
                            name: 'street2',
                            labelAlign: 'right',
                            labelWidth: 80,
                            anchor: '100%'
                        },
                        {
                            xtype: 'combo',
                            fieldLabel: 'Country',
                            name: 'countryId',
                            labelAlign: 'right',
                            labelWidth: 80,
                            anchor: '100%',
                            allowBlank: false,
                            msgTarget: 'under',
                            blankText: 'Must select a country',
                            store: countryStore,
                            valueField: 'id',
                            displayField: 'value',
                            triggerAction: 'all',
                            editable: false,
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
                            name: 'cityId',
                            id: 'cityCombo',
                            fieldLabel: 'City',
                            labelAlign: 'right',
                            allowBlank: false,
                            editable:false,
                            labelWidth: 80,
                            store: cityStore,
                            displayField: 'value',
                            valueField: 'id',
                            triggerAction: 'all',
                            emptyText: 'Select City',
                            anchor: '100%'
                        },
                        {
                            xtype: 'textfield',
                            fieldLabel: 'Zip Code',
                            name: 'zipCode',
                            labelAlign: 'right',
                            labelWidth: 80,
                            anchor: '100%'
                        }
                    ],
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
                                    //xtype: 'button',
                                    text: 'Save',
                                    iconCls: 'saveIcon',
                                    formBind: true,
                                    handler: function(){
                                        var form = this.up('form').getForm();
                                        if(form.isValid()){
                                            form.submit({
                                               url: 'client/create',
                                               method:'POST',
                                               waitMsg: 'Processing...',
                                               success: function(form, action){
                                                   var responseData = action.result.data;
                                                    Ext.Msg.alert('Success',"Client:<b> "+responseData.clientName+"</b> is created!");
                                                    //form.reset();
                                                    clientStore.load();
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
                    ]
                },
                {
                    xtype: 'panel',
                    //title: 'My Panel',
                    region: 'center',
                    split: true,
                    items: [
                        {
                            xtype: 'gridpanel',
                            
                            title: 'Client List',
                            store: clientStore,
                            //height: 300,
                            columnLines: true,
                            columns: [
                                {
                                    xtype: 'templatecolumn',
                                    dataIndex: 'clientName',
                                    text: 'Name',
                                    width: 150,
                                    tpl: '<b>{clientName}</b><br>'+
                                         '{website}'
                                    
                                },
                                {
                                    xtype: 'templatecolumn',
                                    dataIndex: 'cityId',
                                    text: 'Address',
                                    width: 200,
                                    tpl: '{street1}<br>'+
                                         '{countryName}, {cityName} <br>'+
                                         '{zipCode}<br>'
                                }   
                            ],
                            viewConfig: {

                            },
                            listeners: {
                                selectionchange: function(model, records){
                                    
                                    if(records[0]){
                                        Ext.getCmp('clientForm').getForm().loadRecord(records[0]);
                                    }
                                    var grid = me.down('gridpanel');
                                    var arraySelected =grid.getSelectionModel().getSelection();
                                    var record = arraySelected[0];
                                    var clientId = record.get('clientId');
                                    
                                    contactStore.load({
                                        url: 'clientContact/contactList',
                                        params: {
                                            clientId: clientId
                                        }
                                    });
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
                                                    var store = Ext.getStore('clientStore');
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
                },
                {
                    xtype: 'panel',
                    title: 'Contact List',
                    region: 'east',
                    activeTab: 0,
                    width: 300,
                    collapsible: true,
                    animCollapse: true,
                    collapsed: true,
                    collapseDirection: 'right',
                    split: true,
                    items: [
                        {
                            xtype: 'gridpanel',
                            //title: 'Contact List',
                            store: contactStore,
                            height: 220,
                            columnLines: true,
                            columns: [
                                {
                                    xtype: 'templatecolumn',
                                    dataIndex: 'firstName',
                                    text: 'Name',
                                    tpl: '<b>{firstName} {middleInitial} {lastName}</b><br>'+
                                         '{email}',
                                    width: 160
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'phone1',
                                    text: 'Phone'
                                }
                            ],
                            viewConfig: {

                            },
                            listeners: {
                                selectionchange: function(model, records){
                                    if(records[0]){
                                        Ext.getCmp('contactDetailsForm').getForm().loadRecord(records[0]);
                                    }
                                }
                            }
                        },
                        {
                            xtype: 'form',
                            width: 300,
                            border: false,
                            id: 'contactDetailsForm',
                            //bodyPadding: 10,
                            //title: 'Contact Details',
                            region: 'west',
                            split: true,
                            items: [
                                {
                                    xtype: 'tabpanel',
                                    anchor: '100%',
                                    items: [
                                        {
                                            xtype: 'panel',
                                            title: 'Contact Info',
                                            anchor: '100%',
                                            bodyPadding: 10,
                                            items:[
                                                {
                                                    xtype: 'hidden',
                                                    name: 'contactId',
                                                    value: '0'
                                                },
                                                {
                                                    xtype: 'combo',
                                                    fieldLabel: 'Contact Type',
                                                    name: 'contactType',
                                                    labelAlign: 'right',
                                                    labelWidth: 110,
                                                    anchor: '100%',
                                                    allowBlank: false,
                                                    msgTarget: 'under',
                                                    blankText: 'Must select a contact type',
                                                    store: contactTypeStore,
                                                    valueField: 'id',
                                                    displayField: 'value',
                                                    triggerAction: 'all',
                                                    editable: false
                                                },
                                                {
                                                    xtype: 'textfield',
                                                    fieldLabel: 'First Name',
                                                    name: 'firstName',
                                                    labelAlign: 'right',
                                                    labelWidth: 110,
                                                    anchor: '100%',
                                                    allowBlank: false,
                                                    emptyText: 'Contact person name',
                                                    msgTarget: 'under',
                                                    blankText: 'Must enter the contact person name',
                                                    minLength: 3,
                                                    minLengthText: 'Contact Name can\'t be less than 3 character.',
                                                    maxLength: 30,
                                                    maxLengthText: 'Contact Name can\'t be greater than 30 character.'
                                                },
                                                {
                                                    xtype: 'textfield',
                                                    fieldLabel: 'Middle Initial',
                                                    name: 'middleInitial',
                                                    labelAlign: 'right',
                                                    labelWidth: 110,
                                                    anchor: '100%'
                                                },
                                                {
                                                    xtype: 'textfield',
                                                    fieldLabel: 'Last Name',
                                                    name: 'lastName',
                                                    labelAlign: 'right',
                                                    labelWidth: 110,
                                                    anchor: '100%'
                                                },
                                                {
                                                    xtype: 'textfield',
                                                    fieldLabel: 'Designation',
                                                    name: 'designation',
                                                    labelAlign: 'right',
                                                    labelWidth: 110,
                                                    anchor: '100%'
                                                },
                                                {
                                                    xtype: 'textfield',
                                                    fieldLabel: 'Email Id',
                                                    name: 'email',
                                                    labelAlign: 'right',
                                                    labelWidth: 110,
                                                    anchor: '100%'
                                                },
                                                {
                                                    xtype: 'textfield',
                                                    fieldLabel: 'Phone1',
                                                    name: 'phone1',
                                                    labelAlign: 'right',
                                                    labelWidth: 110,
                                                    anchor: '100%'
                                                },
                                                {
                                                    xtype: 'textfield',
                                                    fieldLabel: 'Phone2',
                                                    name: 'phone2',
                                                    labelAlign: 'right',
                                                    labelWidth: 110,
                                                    anchor: '100%'
                                                }
                                            ]
                                        },
                                        {
                                            xtype: 'panel',
                                            title: 'Important Days',
                                            padding: 10,
                                            items: [
                                                {
                                                    xtype: 'datefield',
                                                    name: 'dateOfBirth',
                                                    fieldLabel: 'Date of Birth',
                                                    labelAlign: 'right',
                                                    labelWidth: 120,
                                                    format: 'Y-m-d'
                                                },
                                                {
                                                    xtype: 'datefield',
                                                    name: 'dateOfAnniversary',
                                                    fieldLabel: 'Date of Anniversary',
                                                    labelAlign: 'right',
                                                    labelWidth: 120,
                                                    format: 'Y-m-d'
                                                }
                                            ]
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
                                pack: 'end',
                                type: 'hbox'
                            },
                            items: [
                                {
                                    //xtype: 'button',
                                    text: 'Save',
                                    formBind: true,
                                    handler: function(){
                                        var clientId = Ext.getCmp('clientId').value;
                                        var form = Ext.getCmp('contactDetailsForm').getForm();
                                        if(form.isValid()){
                                            form.submit({
                                               url: 'clientContact/addContact?clientId='+clientId,
                                               method:'POST',
                                               waitMsg: 'Adding contact...',
                                               success: function(form, action){
                                                   var responsedData = action.result.data;
                                                   Ext.Msg.alert('Message', "Contact: "+responsedData.firstName+" saved successfully!");
                                                   //form.reset();
                                                   contactStore.load({
                                                       params:{
                                                           clientId: clientId
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
                                },
                                {
                                    xtype: 'tbseparator'
                                },
                                {
                                    xtype: 'button',
                                    text: 'Reset',
                                    handler: function(){
                                                Ext.getCmp('contactDetailsForm').getForm().reset();
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