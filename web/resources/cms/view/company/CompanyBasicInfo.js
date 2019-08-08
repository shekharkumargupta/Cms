Ext.define('Cms.view.company.CompanyBasicInfo', {
    extend: 'Ext.form.Panel',

    width: 400,
    bodyPadding: 20,
    //title: 'Company Information',
    frame: false,

    initComponent: function() {
        var me = this;
        
        var countryStore = Ext.create('Cms.data.store.CountryStore');
        var cityStore = Ext.create('Cms.data.store.CityStore');
        var categoryStore = Ext.create('Cms.data.store.CategoryStore');
        

        Ext.applyIf(me, {
            items: [
                /*{
                    xtype: 'combo',
                    fieldLabel: 'Type of Business',
                    labelAlign: 'right',
                    //hiddenName: 'destinationId',
                    name: 'categoryId',
                    store: categoryStore,
                    displayField: 'value',
                    valueField: 'id',
                    triggerAction: 'all',
                    editable: false,
                    anchor:'100%'
                },*/
                {
                    xtype: 'fieldset',
                    title: 'Company Information',
                    items: [
                        {
                            xtype: 'textfield',
                            //id: 'companyName',
                            name: 'name',
                            fieldLabel: 'Company Name',
                            labelAlign: 'right',
                            msgTarget: 'side',
                            allowBlank: false,
                            emptyText: 'Opgea Systems',
                            anchor: '100%'
                        },
                        {
                            xtype: 'textfield',
                            //id: 'website',
                            name: 'website',
                            fieldLabel: 'Website',
                            labelAlign: 'right',
                            //msgTarget: 'side',
                            //allowBlank: false,
                            emptyText: 'http://www.opgea.com',
                            anchor: '100%'
                        }
                        /*{
                            xtype: 'combo',
                            fieldLabel: 'Country',
                            labelAlign: 'right',
                            //hiddenName: 'destinationId',
                            name: 'countryId',
                            store: countryStore,
                            displayField: 'value',
                            valueField: 'id',
                            triggerAction: 'all',
                            editable: false,
                            anchor:'100%'
                        },
                        {
                            xtype: 'combo',
                            fieldLabel: 'City',
                            labelAlign: 'right',
                            //hiddenName: 'destinationId',
                            name: 'cityId',
                            store: cityStore,
                            displayField: 'value',
                            valueField: 'id',
                            triggerAction: 'all',
                            editable: false,
                            anchor:'100%'
                        }*/
                    ]
                },
                {
                    xtype: 'fieldset',
                    title: 'Contact Information',
                    items: [
                        {
                            xtype: 'textfield',
                            name: 'firstName',
                            fieldLabel: 'First Name',
                            labelAlign: 'right',
                            emptyText: 'First Name',
                            allowBlank: false,
                            msgTarget: 'side',
                            anchor: '100%'
                        },
                        {
                            xtype: 'textfield',
                            name:'middleInitial',
                            fieldLabel: 'Middle Initial',
                            labelAlign: 'right',
                            emptyText: 'M.I.',
                            anchor: '40%'
                        },
                        {
                            xtype: 'textfield',
                            name: 'lastName',
                            fieldLabel: 'Last Name',
                            labelAlign: 'right',
                            emptyText: 'Last Name',
                            anchor: '100%'
                        },
                        {
                            xtype: 'textfield',
                            //id: 'emailId',
                            name: 'email',
                            fieldLabel: 'Email Id',
                            labelAlign: 'right',
                            msgTarget: 'side',
                            allowBlank: false,
                            emptyText: 'shekharkumargupta@example.com',
                            anchor: '100%',
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
                            //id: 'contactNo',
                            name: 'contactNo',
                            fieldLabel: 'Contact No.',
                            labelAlign: 'right',
                            msgTarget: 'side',
                            allowBlank: false,
                            emptyText: '9868351070',
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
                        pack: 'end',
                        type: 'hbox'
                    },
                    items: [
                        {
                            //xtype: 'button',
                            text: 'Register',
                            formBind: true,
                            handler: function(){
                                var form = this.up('form').getForm();
                                if(form.isValid()){
                                    form.submit({
                                       url: 'company/create',
                                       method:'POST',
                                       waitMsg: 'Submiting form...',
                                       success: function(form, action){
                                           Ext.Msg.alert('Success',"LoginId: <b>"+
                                               action.result.data+"</b> created successfully!"
                                                + "<br>Please check your email account for password.");
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
            ]
        });
        
        me.callParent(arguments);
    }
});