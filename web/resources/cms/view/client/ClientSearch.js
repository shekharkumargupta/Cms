Ext.define('Cms.view.client.ClientSearch', {
    extend: 'Ext.panel.Panel',
    title: 'ClientSearch',
    //iconCls: 'bookIcon',
    selectedClientId: '0',
    columnLines: true,
    collapsible: true,
    animCollapse: true,
    collapseDirection: 'top',
    
    getSelectedClientId: function(){
        return this.selectedClientId;
    },
    
    setSelectedClientId: function(clientId){
       this.selectedClientId = clientId;  
    },
    

    initComponent: function() {
        var me = this;
        var clientStore = Ext.create('Cms.data.store.ClientStore',{
            //url: 'designation/designationList'
        });
        
        
        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'gridpanel',
                    //title: 'Client List',
                    store: clientStore,
                    height: 220,
                    scroll: 'both',
                    columns: [
                        {
                            xtype: 'templatecolumn',
                            dataIndex: 'clientName',
                            text: 'Name',
                            width: 300,
                            tpl: '<b>{clientName}</b><br>'+
                                 '{street1}<br>'+
                                 '{countryName}, {cityName} <br>'+
                                 '{zipCode}<br>'

                        }
                        /*,
                        {
                            xtype: 'templatecolumn',
                            dataIndex: 'cityId',
                            text: 'Address',
                            width: 200,
                            tpl: '{street1}<br>'+
                                 '{countryName}, {cityName} <br>'+
                                 '{zipCode}<br>'
                        } 
                        */
                    ],
                    viewConfig: {

                    },
                    listeners: {
                        selectionchange: function(model, records){
                            var grid = me.down('gridpanel');
                            var arraySelected =grid.getSelectionModel().getSelection();
                            var record = arraySelected[0];
                            var clientId = record.get('clientId');
                            me.setSelectedClientId(clientId);
                            
                            //var openingStore = Ext.getStore('openingStore');
                            Ext.getStore('openingStore').load({
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
                            layout: {
                                pack: 'start',
                                type: 'hbox'
                            },
                            items: [
                                {
                                    xtype: 'textfield',
                                    labelWidth: 70,
                                    fieldLabel: 'Search Key'
                                },
                                {
                                    //xtype: 'button',
                                    text: 'Search',
                                    iconCls: 'searchIcon',
                                    anchor: '100%',
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
                                                    Ext.Msg.alert('Success',"Client: "+responseData.clientName+" is created!");
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