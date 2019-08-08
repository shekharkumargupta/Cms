Ext.define('Cms.view.opening.OpeningDetails', {
    extend: 'Ext.panel.Panel',

    height: 470,
    layout: {
        type: 'border'
    },
    //title: 'Opening Details',
    
    openingId: '0',
    positionName: '',
    clientName: '',
    
    getOpeningId: function(){
      return this.openingId;  
    },
    setOpeningId: function(openingId){
        this.openingId = openingId;
    },
    
    getPositionName: function(){
      return this.positionName;  
    },
    setPositionName: function(positionName){
        this.positionName = positionName;
    },
    getClientName: function(){
      return this.clientName;  
    },
    setClientName: function(clientName){
        this.clientName = clientName;
    },

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'form',
                    maxHeight: 470,
                    bodyPadding: 10,
                    //title: 'Opening Details',
                    region: 'center',
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
                                    handler: function(){
                                            var form = this.up('form').getForm();
                                            if(form.isValid()){
                                                form.submit({
                                                   url: 'opening/updateDetails?openingId='+me.getOpeningId(),
                                                   method:'POST',
                                                   waitMsg: 'Processing...',
                                                   success: function(form, action){
                                                       var responseData = action.result.data;
                                                        Ext.Msg.alert('Success',"Details Updated for Opening Id: <b>"+me.getOpeningId()+"</b> is created!");
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
                                '-',
                                {
                                    xtype: 'button',
                                    text: 'Close',
                                    handler: function(){
                                        this.up('window').hide();
                                    }
                                }
                            ]
                        }
                    ],
                    items: [
                        {
                           xtype: 'hidden',
                           name: 'id',
                           value: '0'
                        },
                        {
                            xtype: 'htmleditor',
                            name: 'details',
                            height: 400,
                            style: 'background-color: white;',
                            fieldLabel: 'Opening Details',
                            labelAlign: 'top',
                            anchor: '100%'
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }
});