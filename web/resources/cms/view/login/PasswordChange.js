Ext.define('Cms.view.login.PasswordChange', {
    extend: 'Ext.form.Panel',

    bodyPadding: 10,
    //title: 'Login Information',
    height: 180,
    
    getLoginInfo: function(){
      var form = this.getForm();
      form.load({
          method: 'GET',
          url: 'login/loginInfo',
          //waitMsg: 'Loading...',
          success: function(){
              //Ext.Msg.alert('Success','Success');
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
                    name: 'loginId',
                    fieldLabel: 'LoginId',
                    labelAlign: 'right',
                    msgTarget: 'side',
                    allowBlank: false,
                    anchor: '100%',
                    editable: false
                },
                {
                    xtype: 'textfield',
                    inputType: 'password',
                    name: 'currentPassword',
                    fieldLabel: 'Current Password',
                    labelAlign: 'right',
                    msgTarget: 'side',
                    anchor: '100%'
                },
                {
                    xtype: 'textfield',
                    inputType: 'password',
                    id: 'newPassword',
                    name: 'newPassword',
                    fieldLabel: 'New Password',
                    labelAlign: 'right',
                    msgTarget: 'side',
                    allowBlank: false,
                    anchor: '100%'
                },
                {
                    xtype: 'textfield',
                    inputType: 'password',
                    id: 'confirmPassword',
                    name: 'confirmPassword',
                    fieldLabel: 'Confirm Password',
                    labelAlign: 'right',
                    msgTarget: 'side',
                    allowBlank: false,
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
                        '-',
                        {
                            xtype: 'button',
                            text: 'Change',
                            iconCls: 'logoutIcon',
                            handler: function(){
                                var newPassword = Ext.getCmp('newPassword').getValue();
                                var confirmPassword = Ext.getCmp('confirmPassword').getValue();
                                
                                if(newPassword == confirmPassword){
                                    var form = this.up('form').getForm();
                                    if(form.isValid()){
                                        form.submit({
                                           url: 'login/updatePassword',
                                           mevthod:'POST',
                                           waitMsg: 'Processing...',
                                           success: function(form, action){
                                               Ext.Msg.alert('Success',action.result.data);
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
                                }else{
                                    Ext.Msg.alert('Alert', 'Confirm Password does not match with New Password.');
                                }
                            }
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }
});