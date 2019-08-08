Ext.define('Cms.view.massmail.MassMail', {
    extend: 'Ext.form.Panel',

    height: 600,
    width: 500,
    bodyPadding: 15,
    //title: 'Pipeline Resume',
    openingId: '0',
    emailIds: '',
    
    
    setOpeningId: function(openingId){
        this.openingId = openingId;
    },
    getOpeningId: function(){
        return this.openingId;
    },
    
    setEmailIds: function(emailIds){
        this.emailIds = emailIds;
    },
    getEmailIds: function(){
        return this.emailIds;
    },
    
    setCandidateName: function(candidateName){
        this.candidateName = candidateName;
    },
    getCandidateName: function(){
        return this.candidateName;
    },

    initComponent: function() {
        var me = this;
        
        var openingStore = Ext.create('Cms.data.store.OpeningByEmployeeStore',{
        });

        Ext.applyIf(me, {
            items: [
                
                {
                    xtype: 'combobox',
                    fieldLabel: 'Opening',
                    id: 'openingCombo',
                    name: 'openingId',
                    labelAlign: 'right',
                    anchor: '100%',
                    store: openingStore,
                    displayField: 'positionName',
                    valueField: 'openingId',
                    triggerAction: 'all',
                    editable: false,
                    //fieldStyle:'text-transform:uppercase',
                    listConfig: {
                        loadingText: 'Loading...',
                        // Custom rendering template for each item
                        getInnerTpl: function() {
                            return '<table width="100%">\n\
                                        <tr>\n\
                                            <td align="left">\n\
                                                <b>{positionName}, {clientName} ({location})</b>\n\
                                                <br>\n\
                                                Exp: {minimumExp} - {maximumExp} Yrs\n\\n\
                                                <br>\n\\n\
                                                {clientName}\n\
                                            </td>\n\
                                            <td align="right" valign="top">\n\
                                                Opening Id: {openingId}\n\
                                            </td>\n\
                                        </tr>\n\
                                    </table>';
                        },
                        emptyText:'No Values Found'
                    },
                    listeners: {
                        select: function(combo, rec, index){
                            var form = this.up('form').getForm();
                                if(form.isValid()){
                                    form.load({
                                       url: 'opening/getDetails?openingId='+combo.getValue(),
                                       method:'GET',
                                       waitMsg: 'Processing...',
                                       success: function(form, action){
                                           var responseData = action.result.data;
                                            //Ext.Msg.alert('Success', responseData);
                                            form.findField('message').setValue(responseData);
                                            
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
                    }
                },
                {
                   xtype: 'textfield',
                   fieldLabel: 'Subject',
                   labelAlign: 'right',
                   name:'subject',
                   anchor:'100%'
                },
                {
                    xtype: 'textareafield',
                    fieldLabel: 'Email Ids',
                    name: 'to',
                    labelAlign: 'right',
                    anchor: '100%',
                    editable: true,
                    value: me.getEmailIds()
                },
                {
                    xtype: 'htmleditor',
                    name: 'message',
                    height: 430,
                    style: 'background-color: white;',
                    fieldLabel: 'Opening Details',
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
                            xtype: 'tbfill'
                        },
                        {
                            xtype: 'button',
                            text: 'Send',
                            iconCls: 'pipelineProcessingIcon',
                            handler: function(){
                                var form = this.up('form').getForm();
                                var openingId = form.findField('openingCombo').getValue();
                                if(form.isValid()){
                                    form.submit({
                                       url: 'massMail/create?openingId='+openingId,
                                       method:'POST',
                                       waitMsg: 'Processing...',
                                       success: function(form, action){
                                           var responseData = action.result.data;
                                            Ext.Msg.alert('Success',"Resume Sent to: <b> "+responseData.to+"</b>");
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
                            text: 'Close'
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }
});