Ext.define('Cms.view.login.LoginForm', {
    extend: 'Ext.form.Panel',
    width: 356,
    bodyPadding: 15,
    //title: 'Login',
    
    createWindow: function(extComponent, title, height, width){
        var win;
        if(!win){
            win = Ext.create('widget.window',{
                title: title,
                closeAction: 'hide',
                items:[extComponent],
                height: height,
                width: width,
                modal:true
            })
            win.show(this, function(){

            });
        }
    },

    initComponent: function() {
        Ext.QuickTips.init();
        var me = this;
        new Ext.ToolTip({
            target: 'loginId',
            html: 'A very simple tooltip'
        });
        
        me.items = [
            {
                xtype: 'fieldset',
                title: 'Passkey',
                items: [
                        {
                            xtype: 'textfield',
                            name: 'loginId',
                            fieldLabel: 'Login Id',
                            labelAlign: 'right',
                            msgTarget: 'side',
                            allowBlank: false,
                            emptyText: '',
                            anchor: '100%',
                            emptyText: 'shekhar@gmail.com'
                        },
                        {
                            xtype: 'textfield',
                            name: 'password',
                            inputType: 'password',
                            fieldLabel: 'Password',
                            labelAlign: 'right',
                            msgTarget: 'side',
                            allowBlank: false,
                            emptyText: '',
                            anchor: '100%'
                        }
                ]
            }
        ];
        me.dockedItems = [
            {
                xtype: 'toolbar',
                height: 28,
                dock: 'bottom',
                layout: {
                    pack: 'end',
                    type: 'hbox'
                },
                items: [
                    {
                        xtype: 'button',
                        id: 'loginButton',
                        text: 'Login',
                        handler: function(){
                            //window.location = 'desktop';
                            var form = this.up('form').getForm();
                            if(form.isValid()){
                                form.submit({
                                    method: 'post',
                                    waitMsg: 'Verifing...',
                                    url: 'login/verify',
                                    success: function(form, action){
                                        Ext.Msg.alert('Message',action.result.data);
                                        var data = action.result.data;
                                        if(data == 'Success'){
                                            window.location = 'home';
                                        }
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
                                })
                            }
                        }
                    },
                    '-',
                    {
                        xtype: 'button',
                        id: 'resetButton',
                        text: 'Reset',
                        handler: function(){
                            Ext.up('form').getForm().reset();
                        }
                    }
                ]
            }
        ];
        me.callParent(arguments);
    },
    
    showTitle: function(){
      alert(this.title);
    }

});
