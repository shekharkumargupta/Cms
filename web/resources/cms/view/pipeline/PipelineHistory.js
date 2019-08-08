Ext.define('Cms.view.pipeline.PipelineHistory', {
    extend: 'Ext.panel.Panel',

    height: 270,
    layout: {
        type: 'border'
    },
    //title: 'Opening Details',
    
    resumeId: '0',
    pipelineId: '0',
    
    setResumeId: function(resumeId){
        this.resumeId = resumeId;
    },
    getResumeId: function(){
      return this.resumeId;  
    },
    
    setPipelineId: function(pipelineId){
        this.pipelineId = pipelineId;
    },
    getPipelineId: function(){
      return this.pipelineId;  
    },

    initComponent: function() {
        var me = this;

        var pipelineStageStore = Ext.create('Cms.data.store.PipelineStageStore',{
        });

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'form',
                    maxHeight: 270,
                    bodyPadding: 10,
                    //title: 'Opening Details',
                    region: 'center',
                    items: [
                            {
                              xtype: 'hidden',
                              name: 'id',
                              value: '0'
                            },
                            {
                            xtype: 'combobox',
                            name: 'pipelineStatusId',
                            fieldLabel: 'Pipeline Status',
                            labelAlign: 'right',
                            anchor: '100%',
                            store: pipelineStageStore,
                            displayField: 'name',
                            valueField: 'id',
                            triggerAction: 'all',
                            editable: false
                        },
                        {
                            xtype: 'htmleditor',
                            name: 'remarks',
                            height: 180,
                            style: 'background-color: white;',
                            fieldLabel: 'Comment',
                            labelAlign: 'top',
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
                                    xtype: 'button',
                                    text: 'Save',
                                    formBind: true,
                                    handler: function(){
                                        var form = this.up('form').getForm();
                                        if(form.isValid()){
                                            form.submit({
                                               url: 'history/saveComment?pipelineId='+me.getPipelineId(),
                                               method:'POST',
                                               waitMsg: 'Processing...',
                                               success: function(form, action){
                                                   Ext.Msg.alert('Success', 'Status and Comment Updated');
                                                   //Ext.Msg.alert('Success',action.result.data.id);
                                                   //employeeStore.load();                                                       
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
                                    text: 'Close',
                                    handler: function(){
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