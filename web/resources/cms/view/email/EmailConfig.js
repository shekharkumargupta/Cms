Ext.define('Cms.view.email.EmailConfig', {
    extend: 'Ext.form.Panel',
    title: 'Email Configuration',
    id: 'emailConfigForm',
    //height: 450,
    //width: 500,
    bodyPadding: 100,
   
    loadConfigDetails: function(){
        var form = Ext.getCmp('emailConfigForm').getForm();
        
            form.load({
               url: 'emailConfig/load',
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
        
    },
    
    initComponent: function() {
        
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'hidden',
                    name: 'id',
                    value: '0'
                },
                {
                    xtype: 'textfield',
                    name: 'emailId',
                    fieldLabel: 'Email Id',
                    labelAlign: 'right',
                    allowBlank: false,
                    msgTarget: 'under',
                    anchor: '100%'
                },
                {
                    xtype: 'textfield',
                    inputType: 'password',
                    name: 'password',
                    fieldLabel: 'Password',
                    labelAlign: 'right',
                    allowBlank: false,
                    msgTarget: 'under',
                    anchor: '100%'
                },
                {
                    xtype: 'textfield',
                    name: 'smtpHost',
                    fieldLabel: 'SMTP Host Name',
                    labelAlign: 'right',
                    allowBlank: false,
                    msgTarget: 'under',
                    anchor: '100%'
                },
                {
                    xtype: 'textfield',
                    name: 'portNo',
                    fieldLabel: 'Port No.',
                    labelAlign: 'right',
                    allowBlank: false,
                    msgTarget: 'under',
                    anchor: '100%'
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
                            text: 'Load',
                            iconCls: 'tableRefreshIcon',
                            handler: function(){
                                me.loadConfigDetails();
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
                            handler: function(){
                                var form = this.up('form').getForm();
                                if(form.isValid()){
                                    form.submit({
                                       url: 'emailConfig/create',
                                       method:'POST',
                                       waitMsg: 'Processing...',
                                       success: function(form, action){
                                           var responseData = action.result.data;
                                            Ext.Msg.alert('Success',"Configuration successfully saved.");
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
                            iconCls: 'removeIcon',
                            handler: function(){
                                 var form = this.up('form').getForm();
                                 form.reset();
                            }
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }
});