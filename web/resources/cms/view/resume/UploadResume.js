Ext.define('Cms.view.resume.UploadResume', {
    extend: 'Ext.panel.Panel',
    iconCls: 'bookIcon',
    resumeId: '0',
    
    setResumeId: function(resumeId){
        this.resumeId = resumeId;
    },
    
    getResumeId: function(){
        return this.resumeId;
    },

    initComponent: function() {
        var me = this;
        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'form',
                    bodyPadding: 10,
                    //collapsible: true,
                    //title: 'Upload Employee Image',
                    //titleCollapse: true,
                    region: 'west',
                    split: true,
                    items: [
                        {
                            xtype: 'filefield',
                            fieldLabel: 'Browse File',
                            labelAlign: 'top',
                            name: 'file',
                            anchor: '100%'
                        }
                    ],
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
                                    text: 'Upload',
                                    iconCls: 'addIcon',
                                    handler: function(){
                                        var form = this.up('form').getForm();
                                            if(form.isValid()){
                                                form.submit({
                                                   enctype: 'multipart/form-data',
                                                   url: 'resume/uploadResume?resumeId='+me.getResumeId(),
                                                   mevthod:'POST',
                                                   waitMsg: 'Processing...',
                                                   success: function(form, action){
                                                       Ext.Msg.alert('Success','Success');
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
                                    xtype: 'button',
                                    text: 'Reset',
                                    handler: function(){
                                        //this.up('form').getForm().reset();
                                        Ext.Msg.alert('Message', me.getResumeId());
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