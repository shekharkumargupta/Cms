Ext.define('Cms.view.email.TemplateMessage', {
    extend: 'Ext.form.Panel',

    //height: 450,
    //width: 500,
    bodyPadding: 15,
   
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
                    xtype: 'htmleditor',
                    name: 'message',
                    height: 310,
                    style: 'background-color: white;',
                    fieldLabel: 'Message',
                    labelAlign: 'top',
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
                            text: 'Save',
                            iconCls: 'saveIcon',
                            handler: function(){
                                var form = this.up('form').getForm();
                                if(form.isValid()){
                                    form.submit({
                                       url: 'emailMessage/create',
                                       method:'POST',
                                       waitMsg: 'Processing...',
                                       success: function(form, action){
                                           var responseData = action.result.data;
                                            Ext.Msg.alert('Success',"Message successfully saved.");
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
                            text: 'Clear',
                            iconCls: 'removeIcon'
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }
});